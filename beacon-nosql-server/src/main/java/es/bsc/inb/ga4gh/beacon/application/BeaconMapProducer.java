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

import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconMapResponse;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Dmitry Repchevsky
 */

public class BeaconMapProducer {

    private final static String BEACON_MAP_FILE = "BEACON-INF/beacon_map.json";
    
    @Inject 
    private ServletContext ctx;

    private BeaconMapResponse beacon_map;
    
    @PostConstruct
    public void init() {
        try (InputStream in = ctx.getResourceAsStream(BEACON_MAP_FILE)) {
            if (in == null) {
                Logger.getLogger(BeaconMapProducer.class.getName()).log(
                        Level.SEVERE, "no beacons map file found: " + BEACON_MAP_FILE);
            } else {
                beacon_map = JsonbBuilder.create().fromJson(in, BeaconMapResponse.class);
            }
        } catch (IOException ex) {
            Logger.getLogger(BeaconMapProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Produces
    public BeaconMapResponse beaconMap() {
        return beacon_map;
    }

}
