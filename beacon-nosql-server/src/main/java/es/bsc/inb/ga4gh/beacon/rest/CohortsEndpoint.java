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

import es.bsc.inb.ga4gh.beacon.framework.model.v200.CohortsRequestParameters;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.IndividualsRequestParameters;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.common.Pagination;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestBody;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestQuery;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconCollectionsResponse;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconFilteringTermsResponse;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResultsetsResponse;
import es.bsc.inb.ga4gh.beacon.framework.rest.CohortsEndpointInterface;
import es.bsc.inb.ga4gh.beacon.service.CohortsService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * @author Dmitry Repchevsky
 */

@ApplicationScoped
public class CohortsEndpoint extends AbstractAsyncEndpoint
        implements CohortsEndpointInterface {

    @Inject 
    private CohortsService service;

    @Override
    public BeaconCollectionsResponse getCohorts(String requested_schema, Integer skip, Integer limit) {
        BeaconCollectionsResponse response = new BeaconCollectionsResponse();
        return response;
    }

    @Override
    public BeaconCollectionsResponse postCohortsRequest(BeaconRequestBody request) {
        BeaconCollectionsResponse response = new BeaconCollectionsResponse();
        return response;
    }

    @Override
    public BeaconResultsetsResponse getOneCohort(String id) {
        return service.getBeacon(id);
    }

    @Override
    public BeaconResultsetsResponse postOneCohortRequest(String id, BeaconRequestBody request) {
        return service.getBeacon(id);
    }

    @Override
    public BeaconResultsetsResponse getOneCohortIndividuals(String id, String requested_schema, Integer skip, Integer limit) {
        BeaconRequestQuery query = new BeaconRequestQuery<IndividualsRequestParameters>();
        if (skip != null || limit != null) {
            query.setPagination(new Pagination(skip, limit));
        }
        
        BeaconRequestBody request = new BeaconRequestBody();
        request.setQuery(query);
        request.setSchema(requested_schema);
        
        return service.getOneCohortIndividuals(id, request);
    }

    @Override
    public BeaconResultsetsResponse postOneCohortIndividualsRequest(String id, BeaconRequestBody request) {
        return service.getOneCohortIndividuals(id, request);
    }

    @Override
    public BeaconFilteringTermsResponse getOneCohortFilteringTerms(String id, Integer skip, Integer limit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BeaconFilteringTermsResponse postOneCohortFilteringTermsRequest(String id, BeaconRequestBody request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
