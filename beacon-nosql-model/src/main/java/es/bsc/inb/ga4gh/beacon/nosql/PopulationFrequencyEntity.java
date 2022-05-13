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

package es.bsc.inb.ga4gh.beacon.nosql;

import es.bsc.inb.ga4gh.beacon.model.v200.variations.PopulationFrequency;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;

/**
 * @author Dmitry Repchevsky
 */

@Entity("PopulationFrequency")
public class PopulationFrequencyEntity implements PopulationFrequency {

    @Column("population")
    private String population;
    
    @Column("alleleFrequency")
    private Double alleleFrequency;

    @Override
    public String getPopulation() {
        return population;
    }

    @Override
    public void setPopulation(String population) {
        this.population = population;
    }
    
    @Override
    public Double getAlleleFrequency() {
        return alleleFrequency;
    }

    @Override
    public void setAlleleFrequency(Double alleleFrequency) {
        this.alleleFrequency = alleleFrequency;
    }
}
