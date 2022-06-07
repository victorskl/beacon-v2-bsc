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

import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconEntryTypesResponse;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Dmiry Repchevsky
 */

@ApplicationScoped
public class BeaconEntryTypesProducer implements Serializable {

    private final static String ENTRY_TYPES = "BEACON-INF/entry_types.json";
    
    @Inject 
    private ServletContext ctx;

    private BeaconEntryTypesResponse entry_types;
    
    @PostConstruct
    public void init() {
        try (InputStream in = ctx.getResourceAsStream(ENTRY_TYPES)) {
            if (in == null) {
                Logger.getLogger(BeaconInfoProducer.class.getName()).log(
                        Level.SEVERE, "no entry types file found: " + ENTRY_TYPES);
            } else {
                entry_types = JsonbBuilder.create().fromJson(in, BeaconEntryTypesResponse.class);
            }
        } catch (IOException ex) {
            Logger.getLogger(BeaconInfoProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Produces
    public BeaconEntryTypesResponse entryTypes() {
        return entry_types;
    }
}