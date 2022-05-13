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
import es.bsc.inb.ga4gh.beacon.framework.model.v200.RunsRequestParameters;
import es.bsc.inb.ga4gh.beacon.nosql.AnalysisEntity;
import es.bsc.inb.ga4gh.beacon.nosql.VariantEntity;
import es.bsc.inb.ga4gh.beacon.query.AnalysesRepository;
import es.bsc.inb.ga4gh.beacon.query.RunsRepository;
import es.bsc.inb.ga4gh.beacon.query.VariantsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;
import jakarta.nosql.mapping.Pagination;
import java.util.List;

/**
 * @author Dmitry Repchevsky
 */

@ApplicationScoped
public class RunsService 
        extends AbstractBeaconService<RunsRepository, RunsRequestParameters> {

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private RunsRepository runs_repository;

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private VariantsRepository variants_repository;

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private AnalysesRepository analyses_repository;

    @Override
    public RunsRepository getRepository() {
        return runs_repository;
    }

    @Override
    protected List findEntities(RunsRequestParameters params, 
            Pagination pagination) {

        return pagination == null ? runs_repository.findAll() : runs_repository.findAll(pagination);
    }
    
    public BeaconResultsetsResponse getOneRunsGenomicVariants(String id, BeaconRequestBody request) {
        final Pagination pagination = getPagination(request);

        final List<VariantEntity> variants = pagination == null ? 
                variants_repository.findByRunId(id) : 
                variants_repository.findByRunId(id, pagination);

        return makeResponse(variants);
    }
    
    public BeaconResultsetsResponse getOneRunsAnalyses(String id, BeaconRequestBody request) {
        final Pagination pagination = getPagination(request);

        final List<AnalysisEntity> variants = pagination == null ? 
                analyses_repository.findByRunId(id) : 
                analyses_repository.findByRunId(id, pagination);

        return makeResponse(variants);
    }
}