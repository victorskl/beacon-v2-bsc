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

import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestBody;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconCollectionsResponse;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconFilteringTermsResponse;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResultsetsResponse;
import es.bsc.inb.ga4gh.beacon.framework.rest.DatasetsEndpointInterface;
import es.bsc.inb.ga4gh.beacon.service.DatasetsService;
import jakarta.inject.Inject;

/**
 * @author Dmitry Repchevsky
 */

public class DatasetsEndpoint extends AbstractAsyncEndpoint
        implements DatasetsEndpointInterface {

    @Inject 
    private DatasetsService datasets_service;
    
    @Override
    public BeaconCollectionsResponse getDatasets(String requested_schema, Integer skip, Integer limit) {
        BeaconCollectionsResponse response = new BeaconCollectionsResponse();
        return response;
    }

    @Override
    public BeaconCollectionsResponse postDatasetsRequest(BeaconRequestBody request) {
        BeaconCollectionsResponse response = new BeaconCollectionsResponse();
        return response;
    }

    @Override
    public BeaconResultsetsResponse getOneDataset(String id) {
        return datasets_service.getBeacon(id);
    }

    @Override
    public BeaconResultsetsResponse postOneDatasetRequest(String id, BeaconRequestBody request) {
        return datasets_service.getBeacon(id);
    }

    @Override
    public BeaconResultsetsResponse getOneDatasetGenomicVariants(String id, String requested_schema, Integer skip, Integer limit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BeaconResultsetsResponse postOneDatasetGenomicVariantsRequest(String id, BeaconRequestBody request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BeaconResultsetsResponse getOneDatasetBiosamples(String id, String requested_schema, Integer skip, Integer limit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BeaconResultsetsResponse postOneDatasetBiosamplesRequest(String id, BeaconRequestBody request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BeaconResultsetsResponse getOneDatasetIndividuals(String id, String requested_schema, Integer skip, Integer limit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BeaconResultsetsResponse postOneDatasetIndividualsRequest(String id, BeaconRequestBody request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BeaconFilteringTermsResponse getOneDatasetFilteringTerms(Integer skip, Integer limit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BeaconFilteringTermsResponse postOneDatasetFilteringTermsRequest(BeaconRequestBody request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
