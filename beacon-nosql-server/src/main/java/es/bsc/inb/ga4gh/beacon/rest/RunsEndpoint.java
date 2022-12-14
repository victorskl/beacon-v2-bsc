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

package es.bsc.inb.ga4gh.beacon.rest;

import es.bsc.inb.ga4gh.beacon.framework.model.v200.RunsRequestParameters;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.common.Pagination;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestBody;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestQuery;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResultsetsResponse;
import es.bsc.inb.ga4gh.beacon.framework.rest.RunsEndpointInterface;
import es.bsc.inb.ga4gh.beacon.service.AnalysesService;
import es.bsc.inb.ga4gh.beacon.service.GenomicVariationsService;
import es.bsc.inb.ga4gh.beacon.service.RunsService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * @author Dmitry Repchevsky
 */

@ApplicationScoped
public class RunsEndpoint extends AbstractAsyncEndpoint 
        implements RunsEndpointInterface {

    @Inject 
    private RunsService runs_service;
    
    @Inject 
    private AnalysesService analyses_service;
    
    @Inject 
    private GenomicVariationsService variants_service;

    @Override
    public BeaconResultsetsResponse getRuns(String requested_schema, 
            Integer skip, Integer limit, String include_responses) {

        if (limit == null) {
            limit = 3; // limit 
        }

        BeaconRequestQuery query = new BeaconRequestQuery<RunsRequestParameters>();
        query.setPagination(new Pagination(skip, limit));
        
        BeaconRequestBody request = new BeaconRequestBody();
        request.setQuery(query);
        request.setSchema(requested_schema);
        
        return runs_service.getBeacon(request);
    }

    @Override
    public BeaconResultsetsResponse postRunsRequest(BeaconRequestBody request) {
        return runs_service.getBeacon(request);
    }

    @Override
    public BeaconResultsetsResponse getOneRun(String id) {
        return runs_service.getBeacon(id, null);
    }

    @Override
    public BeaconResultsetsResponse postOneRun(String id, 
            BeaconRequestBody request) {

        return runs_service.getBeacon(id, request);
    }

    @Override
    public BeaconResultsetsResponse getOneRunGenomicVariants(String id, 
            String requested_schema, Integer skip, Integer limit) {

        BeaconRequestQuery query = new BeaconRequestQuery<RunsRequestParameters>();
        if (skip != null || limit != null) {
            query.setPagination(new Pagination(skip, limit));
        }
        
        BeaconRequestBody request = new BeaconRequestBody();
        request.setQuery(query);
        request.setSchema(requested_schema);
        
        return variants_service.getRunsGenomicVariants(id, request);
    }

    @Override
    public BeaconResultsetsResponse postOneRunGenomicVariantsRequest(String id, 
            BeaconRequestBody request) {

        return variants_service.getRunsGenomicVariants(id, request);
    }

    @Override
    public BeaconResultsetsResponse getOneRunAnalyses(String id, 
            String requested_schema, Integer skip, Integer limit) {

        BeaconRequestQuery query = new BeaconRequestQuery<RunsRequestParameters>();
        if (skip != null || limit != null) {
            query.setPagination(new Pagination(skip, limit));
        }
        
        BeaconRequestBody request = new BeaconRequestBody();
        request.setQuery(query);
        request.setSchema(requested_schema);
        
        return analyses_service.getRunsAnalyses(id, request);    
    }

    @Override
    public BeaconResultsetsResponse postOneRunAnalysesRequest(String id, 
            BeaconRequestBody request) {

        return analyses_service.getRunsAnalyses(id, request);  
    }
}
