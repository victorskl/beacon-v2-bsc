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

package es.bsc.inb.ga4gh.beacon.application;

import es.bsc.inb.ga4gh.beacon.framework.model.v200.configuration.ServiceConfiguration;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconFilteringTermsResponse;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 * @author Dmitry Repchevsky
 */

public class BeaconFilteringTermsProducer {
    
    @Inject
    private ServiceConfiguration configuration;

    private BeaconFilteringTermsResponse filtering_terms;

    @PostConstruct
    public void init() {
        filtering_terms = new BeaconFilteringTermsResponse();
        filtering_terms.setMeta(configuration.getMeta());
    }
    
    @Produces
    public BeaconFilteringTermsResponse filteringTerms() {
        return filtering_terms;
    }
}
