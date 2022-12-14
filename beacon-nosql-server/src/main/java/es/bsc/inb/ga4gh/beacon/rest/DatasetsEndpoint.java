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

import es.bsc.inb.ga4gh.beacon.framework.model.v200.DatasetsRequestParameters;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.common.Pagination;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestBody;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestQuery;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconCollectionsResponse;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconFilteringTermsResponse;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResultsetsResponse;
import es.bsc.inb.ga4gh.beacon.framework.rest.DatasetsEndpointInterface;
import es.bsc.inb.ga4gh.beacon.service.DatasetsService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;

/**
 * @author Dmitry Repchevsky
 */

@ApplicationScoped
public class DatasetsEndpoint extends AbstractAsyncEndpoint
        implements DatasetsEndpointInterface {

    @Inject 
    private DatasetsService datasets_service;
    
    @Override
    public BeaconCollectionsResponse getDatasets(String requested_schema, 
            Integer skip, Integer limit) {
        
        if (limit == null) {
            limit = 3; // limit 
        }
        
        BeaconRequestQuery query = new BeaconRequestQuery<DatasetsRequestParameters>();
        query.setPagination(new Pagination(skip, limit));
        
        BeaconRequestBody request = new BeaconRequestBody();
        request.setQuery(query);
        request.setSchema(requested_schema);

        return datasets_service.getDatasets(request);
    }

    @Override
    public BeaconCollectionsResponse postDatasetsRequest(BeaconRequestBody request) {
        return datasets_service.getDatasets(request);
    }

    @Override
    public BeaconResultsetsResponse getOneDataset(String id) {
        return datasets_service.getBeacon(id, null);
    }

    @Override
    public BeaconResultsetsResponse postOneDatasetRequest(
            String id, BeaconRequestBody request) {
        return datasets_service.getBeacon(id, request);
    }

    @Override
    public BeaconResultsetsResponse getOneDatasetGenomicVariants(
            String id, String requested_schema, Integer skip, Integer limit) {
        throw new WebApplicationException("not implemented", 501);
    }

    @Override
    public BeaconResultsetsResponse postOneDatasetGenomicVariantsRequest(
            String id, BeaconRequestBody request) {
        throw new WebApplicationException("not implemented", 501);
    }

    @Override
    public BeaconResultsetsResponse getOneDatasetBiosamples(
            String id, String requested_schema, Integer skip, Integer limit) {
        throw new WebApplicationException("not implemented", 501);

    /*
        BeaconRequestQuery query = new BeaconRequestQuery<DatasetsRequestParameters>();
        query.setPagination(new Pagination(skip, limit));
        
        BeaconRequestBody request = new BeaconRequestBody();
        request.setQuery(query);
        request.setSchema(requested_schema);

        return biosamples_service.getDatasetBiosamples(id, request);
    */
    }

    @Override
    public BeaconResultsetsResponse postOneDatasetBiosamplesRequest(
            String id, BeaconRequestBody request) {
        throw new WebApplicationException("not implemented", 501);
    }

    @Override
    public BeaconResultsetsResponse getOneDatasetIndividuals(
            String id, String requested_schema, Integer skip, Integer limit) {
        throw new WebApplicationException("not implemented", 501);
    }

    @Override
    public BeaconResultsetsResponse postOneDatasetIndividualsRequest(
            String id, BeaconRequestBody request) {
        throw new WebApplicationException("not implemented", 501);
    }

    @Override
    public BeaconFilteringTermsResponse getOneDatasetFilteringTerms(Integer skip, Integer limit) {
        return new BeaconFilteringTermsResponse();
    }

    @Override
    public BeaconFilteringTermsResponse postOneDatasetFilteringTermsRequest(BeaconRequestBody request) {
        return new BeaconFilteringTermsResponse();
    }
    
}
