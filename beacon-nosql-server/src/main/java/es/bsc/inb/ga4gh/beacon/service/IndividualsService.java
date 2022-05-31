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
 *****************************************************************************
 */

package es.bsc.inb.ga4gh.beacon.service;

import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestBody;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestQuery;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResultsetsResponse;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.IndividualsRequestParameters;
import es.bsc.inb.ga4gh.beacon.nosql.CaseLevelVariantEntity;
import es.bsc.inb.ga4gh.beacon.nosql.IndividualEntity;
import es.bsc.inb.ga4gh.beacon.nosql.VariantEntity;
import es.bsc.inb.ga4gh.beacon.query.BiosamplesRepository;
import es.bsc.inb.ga4gh.beacon.query.IndividualsRepository;
import es.bsc.inb.ga4gh.beacon.query.VariantsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;
import jakarta.nosql.mapping.Pagination;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Dmitry Repchevsky
 */

@ApplicationScoped
public class IndividualsService 
        extends AbstractBeaconService<IndividualsRepository, IndividualsRequestParameters> {
    
    @Inject
    @Database(DatabaseType.DOCUMENT)
    private IndividualsRepository individuals_repository;

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private VariantsRepository variants_repository;

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private BiosamplesRepository biosamples_repository;

    @Override
    public IndividualsRepository getRepository() {
        return individuals_repository;
    }
    
    @Override
    protected List findEntities(IndividualsRequestParameters params, 
            Pagination pagination) {

        return pagination == null ? individuals_repository.findAll() : individuals_repository.findAll(pagination);
    }

    public BeaconResultsetsResponse getIndividuals(final BeaconRequestBody request) {
        final BeaconRequestQuery request_query = request.getQuery();
        
        request_query.getFilters();
        return null;
    }
    
    public BeaconResultsetsResponse getGenomicVariationIndividuals(String id, BeaconRequestBody request) {

        final Optional<VariantEntity> variant = variants_repository.findById(id);
        if (variant == null || variant.isEmpty()) {
            return makeResponse(Collections.EMPTY_LIST);
        }
        
        final List<CaseLevelVariantEntity> data = variant.get().getCaseLevelData();
        if (data == null || data.isEmpty()) {
            return makeResponse(Collections.EMPTY_LIST);
        }

        final List<IndividualEntity> individuals = new ArrayList();
        
        final Pagination pagination = getPagination(request);
        final int start = pagination == null ? 0 : (int)pagination.getSkip();
        final int end = pagination == null ? data.size() : (int)pagination.getLimit();

        for (int i = start; i < end; i++) {
            final CaseLevelVariantEntity entity = data.get(i);
            final String individualId = entity.getIndividualId();
            if (individualId != null) {
                final Optional<IndividualEntity> individual = 
                        individuals_repository.findById(id);
                if (individual != null && individual.isPresent()) {
                    individuals.add(individual.get());
                }
            }
        }
        return makeResponse(individuals);
    }

    public BeaconResultsetsResponse getCohortIndividuals(String id, BeaconRequestBody request) {
        return null;
    }
}
