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

package es.bsc.inb.ga4gh.beacon.application;

import es.bsc.inb.ga4gh.beacon.framework.model.v200.configuration.BeaconConfiguration;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.configuration.ServiceConfiguration;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconEntryTypesResponse;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.EntryTypes;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import java.io.Serializable;

/**
 * @author Dmitry Repchevsky
 */

@ApplicationScoped
public class BeaconEntryTypesProducer implements Serializable {
    
    @Inject
    private ServiceConfiguration configuration;

    private BeaconEntryTypesResponse entry_types_response;
    
    @PostConstruct
    public void init() {
        entry_types_response = new BeaconEntryTypesResponse();
        
        final BeaconConfiguration conf = configuration.getResponse();
        final EntryTypes entry_types = new EntryTypes();
        entry_types.setEntryTypes(conf.getEntryTypes());
        
        entry_types_response.setResponse(entry_types);
        entry_types_response.setMeta(configuration.getMeta());
    }
    
    @Produces
    public BeaconEntryTypesResponse entryTypes() {
        return entry_types_response;
    }
}