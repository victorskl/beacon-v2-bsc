/**
 * *****************************************************************************
 * Copyright (C) 2022 ELIXIR ES, Spanish National Bioinformatics Institute (INB)
 * and Barcelona Supercomputing Center (BSC)
 *
 * Modifications to the initial code base are copyright of their respective
 * authors, or their employers as appropriate.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 * *****************************************************************************
 */

package es.bsc.inb.ga4gh.beacon.service;

import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestBody;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResultsetsResponse;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.BiosamplesRequestParameters;
import es.bsc.inb.ga4gh.beacon.nosql.BiosampleEntity;
import es.bsc.inb.ga4gh.beacon.nosql.CaseLevelVariantEntity;
import es.bsc.inb.ga4gh.beacon.nosql.VariantEntity;
import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;
import java.util.List;
import es.bsc.inb.ga4gh.beacon.query.BiosamplesRepository;
import es.bsc.inb.ga4gh.beacon.query.VariantsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.nosql.mapping.Pagination;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

/**
 * @author Dmitry Repchevsky
 */

@Named("biosample")
@ApplicationScoped
public class BiosamplesService 
        extends AbstractBeaconService<BiosamplesRepository, BiosamplesRequestParameters> {

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private BiosamplesRepository biosamples_repository;

    @Inject
    @Database(DatabaseType.DOCUMENT)    
    private VariantsRepository variants_repository;

    @Override
    public BiosamplesRepository getRepository() {
        return biosamples_repository;
    }

    @Override
    protected List findEntities(BiosamplesRequestParameters params, 
            Pagination pagination) {
        return pagination == null ? biosamples_repository.findAll() : 
                biosamples_repository.findAll(pagination);
    }
    
    public BeaconResultsetsResponse getGenomicVariationBiosamples(String id, BeaconRequestBody request) {

        final Optional<VariantEntity> variant = variants_repository.findById(id);
        if (variant == null || variant.isEmpty()) {
            return makeResultsetsResponse(Collections.EMPTY_LIST, request);
        }
        
        final List<CaseLevelVariantEntity> data = variant.get().getCaseLevelData();
        if (data == null || data.isEmpty()) {
            return makeResultsetsResponse(Collections.EMPTY_LIST, request);
        }

        final List<BiosampleEntity> biosamples = new ArrayList();
        
        final Pagination pagination = getPagination(request);
        final int start = Math.min(data.size(), pagination == null ? 0 : (int)pagination.getSkip());
        final int end = Math.min(data.size(), pagination == null ? data.size() : (int)pagination.getLimit());

        for (int i = start; i < end; i++) {
            final CaseLevelVariantEntity entity = data.get(i);
            final String biosampleId = entity.getBiosampleId();
            if (biosampleId != null) {
                final Optional<BiosampleEntity> biosample = 
                        biosamples_repository.findById(biosampleId);
                if (biosample != null && biosample.isPresent()) {
                    biosamples.add(biosample.get());
                }
            }
        }
        return makeResultsetsResponse(biosamples, request);
    }

    public BeaconResultsetsResponse getIndividualBiosamples(String id, BeaconRequestBody request) {
        final Pagination pagination = getPagination(request);

        final List<BiosampleEntity> variants = pagination == null ? 
                biosamples_repository.findByIndividualId(id) : 
                biosamples_repository.findByIndividualId(id, pagination);

        return makeResultsetsResponse(variants, request);
    }
    
    public BeaconResultsetsResponse getDatasetBiosamples(String id, BeaconRequestBody request) {
        // not implemented
        return makeResultsetsResponse(Collections.EMPTY_LIST, request);
    }
}
