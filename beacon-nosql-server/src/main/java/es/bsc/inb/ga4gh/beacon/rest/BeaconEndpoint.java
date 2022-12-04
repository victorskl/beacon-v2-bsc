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

import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconInfoResponse;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconMapResponse;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconEntryTypesResponse;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.configuration.ServiceConfiguration;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconFilteringTermsResponse;
import es.bsc.inb.ga4gh.beacon.framework.rest.BeaconEndpointInterface;
import es.bsc.inb.ga4gh.service_info.model.v100.Service;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Dmitry Repchevsky
 */

@ApplicationScoped
public class BeaconEndpoint implements BeaconEndpointInterface {

    @Inject
    private BeaconInfoResponse beacon_info;

    @Inject
    private Service service_info;
    
    @Inject
    private ServiceConfiguration service_configuration;

    @Inject
    private BeaconEntryTypesResponse entry_types;
    
    @Inject
    private BeaconMapResponse beacon_map;
    
    @Inject
    private BeaconFilteringTermsResponse filtering_terms;
    
    @Override
    public BeaconInfoResponse getBeaconInfoRoot(String requestedSchema) {
        return beacon_info;
    }

    @Override
    public BeaconInfoResponse getBeaconInfo(String requestedSchema) {
        return beacon_info;
    }

    @Override
    public Service getBeaconServiceInfo() {
        return service_info;
    }

    @Override
    public ServiceConfiguration getBeaconConfiguration() {
        return service_configuration;
    }

    @Override
    public BeaconMapResponse getBeaconMap() {
        return beacon_map;
    }

    @Override
    public BeaconEntryTypesResponse getEntryTypes() {
        return entry_types;
    }

    @Override
    public BeaconFilteringTermsResponse getFilteringTerms() {
        return filtering_terms;
    }
}
