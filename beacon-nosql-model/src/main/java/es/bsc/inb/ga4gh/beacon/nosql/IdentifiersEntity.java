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

import es.bsc.inb.ga4gh.beacon.model.v200.variations.Identifiers;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import java.util.List;

/**
 * @author Dmitry Repchevsky
 */

@Entity("Identifiers")
public class IdentifiersEntity implements Identifiers {
    
    @Column("variantAlternativeIds")
    private List<String> variantAlternativeIds;
    
    @Column("genomicHGVSId")
    private String genomicHGVSId;
    
    @Column("transcriptHGVSIds")
    private List<String> transcriptHGVSIds;
    
    @Column("proteinHGVSIds")
    private List<String> proteinHGVSIds;
    
    @Column("clinVarIds")
    private String clinVarIds;
    
    @Override
    public List<String> getVariantAlternativeIds() {
        return variantAlternativeIds;
    }
    
    @Override
    public void setVariantAlternativeIds(List<String> variantAlternativeIds) {
        this.variantAlternativeIds = variantAlternativeIds;
    }
    
    @Override
    public String getGenomicHGVSId() {
        return genomicHGVSId;
    }

    @Override
    public void setGenomicHGVSId(String genomicHGVSId) {
        this.genomicHGVSId = genomicHGVSId;
    }
    
    @Override
    public List<String> getTranscriptHGVSIds() {
        return transcriptHGVSIds;
    }

    @Override
    public void setTranscriptHGVSIds(List<String> transcriptHGVSIds) {
        this.transcriptHGVSIds = transcriptHGVSIds;
    }
    
    @Override
    public List<String> getProteinHGVSIds() {
        return proteinHGVSIds;
    }
    
    @Override
    public void setProteinHGVSIds(List<String> proteinHGVSIds) {
        this.proteinHGVSIds = proteinHGVSIds;
    }
    
    @Override
    public String getClinVarIds() {
        return clinVarIds;
    }

    @Override
    public void setClinVarIds(String clinVarIds) {
        this.clinVarIds = clinVarIds;
    }

}
