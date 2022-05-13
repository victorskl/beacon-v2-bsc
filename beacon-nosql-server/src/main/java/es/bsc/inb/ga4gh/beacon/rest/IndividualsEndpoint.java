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

import es.bsc.inb.ga4gh.beacon.framework.model.v200.AnalysesRequestParameters;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.IndividualsRequestParameters;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.common.Pagination;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestBody;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestQuery;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconFilteringTermsResponse;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResultsetsResponse;
import es.bsc.inb.ga4gh.beacon.framework.rest.IndividualsEndpointInterface;
import es.bsc.inb.ga4gh.beacon.service.IndividualsService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

/**
 * @author Dmitry Repchevsky
 */

@Path("/")
@ApplicationScoped
public class IndividualsEndpoint extends AbstractAsyncEndpoint
        implements IndividualsEndpointInterface {
    
    @Inject 
    private IndividualsService service;
    
    @Override
    public BeaconResultsetsResponse getIndividuals(
            String requested_schema, Integer skip, Integer limit, String include_responses) {
        BeaconRequestQuery query = new BeaconRequestQuery<AnalysesRequestParameters>();
        if (skip != null || limit != null) {
            query.setPagination(new Pagination(skip, limit));
        }
        
        BeaconRequestBody request = new BeaconRequestBody();
        request.setQuery(query);
        request.setSchema(requested_schema);
        
        return service.getBeacon(request);
    }

    @Override
    public BeaconResultsetsResponse postIndividualsRequest(BeaconRequestBody request) {
        return service.getBeacon(request);
    }

    @Override
    public BeaconResultsetsResponse getOneIndividual(String id) {
        return service.getBeacon(id);
    }

    @Override
    public BeaconResultsetsResponse postOneIndividualRequest(String id, BeaconRequestBody request) {
        return service.getBeacon(id);
    }

    @Override
    public BeaconResultsetsResponse getOneIndividualGenomicVariants(String id, 
            String requested_schema, Integer skip, Integer limit) {

        BeaconRequestQuery query = new BeaconRequestQuery<IndividualsRequestParameters>();
        if (skip != null || limit != null) {
            query.setPagination(new Pagination(skip, limit));
        }
        
        BeaconRequestBody request = new BeaconRequestBody();
        request.setQuery(query);
        request.setSchema(requested_schema);
        
        return service.getOneIndividualGenomicVariants(id, request);
    }

    @Override
    public BeaconResultsetsResponse postOneIndividualGenomicVariantsRequest(String id, 
            BeaconRequestBody request) {

        return service.getOneIndividualGenomicVariants(id, request);
    }

    @Override
    public BeaconResultsetsResponse getOneIndividualBiosamples(String id, 
            String requested_schema, Integer skip, Integer limit) {

        BeaconRequestQuery query = new BeaconRequestQuery<IndividualsRequestParameters>();
        if (skip != null || limit != null) {
            query.setPagination(new Pagination(skip, limit));
        }
        
        BeaconRequestBody request = new BeaconRequestBody();
        request.setQuery(query);
        request.setSchema(requested_schema);
        
        return service.getOneIndividualBiosamples(id, request);
    }

    @Override
    public BeaconResultsetsResponse postOneIndividualBiosamplesRequest(String id,
            BeaconRequestBody request) {

        return service.getOneIndividualBiosamples(id, request);
    }

    @Override
    public BeaconFilteringTermsResponse getIndividualFilteringTerms(Integer skip, 
            Integer limit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BeaconFilteringTermsResponse postIndividualFilteringTermsRequest(BeaconRequestBody request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
