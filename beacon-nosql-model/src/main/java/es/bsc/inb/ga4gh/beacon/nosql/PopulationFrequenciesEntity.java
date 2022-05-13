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

import es.bsc.inb.ga4gh.beacon.model.v200.variations.PopulationFrequencies;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import java.util.List;

/**
 * @author Dmitry Repchevsky
 */

@Entity("PopulationFrequencies")
public class PopulationFrequenciesEntity 
        implements PopulationFrequencies<PopulationFrequencyEntity> {
    
    @Column("source")
    private String source;
    
    @Column("sourceReference")
    private String sourceReference;
    
    @Column("version")
    private String version;
    
    @Column("frequencies")
    private List<PopulationFrequencyEntity> frequencies;

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String getSourceReference() {
        return sourceReference;
    }

    @Override
    public void setSourceReference(String sourceReference) {
        this.sourceReference = sourceReference;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public List<PopulationFrequencyEntity> getFrequencies() {
        return frequencies;
    }

    @Override
    public void setFrequencies(List<PopulationFrequencyEntity> frequencies) {
        this.frequencies = frequencies;
    }

}
