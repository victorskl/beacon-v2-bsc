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

import es.bsc.inb.ga4gh.beacon.framework.model.v200.BiosamplesRequestParameters;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.common.Pagination;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestBody;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestQuery;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResultsetsResponse;
import es.bsc.inb.ga4gh.beacon.framework.rest.BiosamplesEndpointInterface;
import es.bsc.inb.ga4gh.beacon.service.AnalysesService;
import es.bsc.inb.ga4gh.beacon.service.BiosamplesService;
import es.bsc.inb.ga4gh.beacon.service.GenomicVariationsService;
import es.bsc.inb.ga4gh.beacon.service.RunsService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Dmitry Repchevsky
 */

@ApplicationScoped
public class BiosamplesEndpoint extends AbstractAsyncEndpoint
        implements BiosamplesEndpointInterface {

    @Inject 
    private BiosamplesService biosamples_service;
    
    @Inject 
    private RunsService runs_service;
    
    @Inject 
    private AnalysesService analyses_service;

    @Inject 
    private GenomicVariationsService variants_service;
    
    @Override
    public BeaconResultsetsResponse getBiosamples(String requested_schema, 
            Integer skip, Integer limit, String include_responses, List<String> filters) {
        
        if (limit == null) {
            limit = 3; // limit 
        }

        BeaconRequestQuery query = new BeaconRequestQuery<BiosamplesRequestParameters>();
        query.setPagination(new Pagination(skip, limit));
        query.setIncludeResultsetResponses(include_responses);
        
        BeaconRequestBody request = new BeaconRequestBody();
        request.setQuery(query);
        request.setSchema(requested_schema);

        return biosamples_service.getBeacon(request);
    }

    @Override
    public BeaconResultsetsResponse postBiosamplesRequest(
            BeaconRequestBody request) {

        return biosamples_service.getBeacon(request);
    }

    @Override
    public BeaconResultsetsResponse getOneBiosample(String id) {
        return biosamples_service.getBeacon(id, null);
    }

    @Override
    public BeaconResultsetsResponse postOneBiosampleRequest(
            String id, BeaconRequestBody request) {
        return biosamples_service.getBeacon(id, request);
    }

    @Override
    public BeaconResultsetsResponse getOneBiosampleGenomicVariants(
            String id, String requested_schema, Integer skip, Integer limit) {

        BeaconRequestQuery query = new BeaconRequestQuery<BiosamplesRequestParameters>();
        if (skip != null || limit != null) {
            query.setPagination(new Pagination(skip, limit));
        }
        
        BeaconRequestBody request = new BeaconRequestBody();
        request.setQuery(query);
        request.setSchema(requested_schema);
        
        return variants_service.getBiosampleGenomicVariants(id, request);
    }

    @Override
    public BeaconResultsetsResponse postOneBiosampleGenomicVariantsRequest(
            String id, BeaconRequestBody request) {
        return variants_service.getBiosampleGenomicVariants(id, request);
    }

    @Override
    public BeaconResultsetsResponse getOneBiosampleAnalysis(
            String id, String requested_schema, Integer skip, Integer limit) {

        BeaconRequestQuery query = new BeaconRequestQuery<BiosamplesRequestParameters>();
        if (skip != null || limit != null) {
            query.setPagination(new Pagination(skip, limit));
        }
        
        BeaconRequestBody request = new BeaconRequestBody();
        request.setQuery(query);
        request.setSchema(requested_schema);
        
        return analyses_service.getBiosampleAnalysis(id, request);
    }

    @Override
    public BeaconResultsetsResponse postOneBiosampleAnalysisRequest(
            String id, BeaconRequestBody request) {
        return analyses_service.getBiosampleAnalysis(id, request);
    }

    @Override
    public BeaconResultsetsResponse getOneBiosampleRuns(
            String id, String requested_schema, Integer skip, Integer limit) {

        BeaconRequestQuery query = new BeaconRequestQuery<BiosamplesRequestParameters>();
        if (skip != null || limit != null) {
            query.setPagination(new Pagination(skip, limit));
        }
        
        BeaconRequestBody request = new BeaconRequestBody();
        request.setQuery(query);
        request.setSchema(requested_schema);
        
        return runs_service.getBiosampleRuns(id, request);
    }

    @Override
    public BeaconResultsetsResponse getOneBiosampleRuns(String id, BeaconRequestBody request) {
        return runs_service.getBiosampleRuns(id, request);
    }

}
