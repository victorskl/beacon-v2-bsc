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

import es.bsc.inb.ga4gh.beacon.model.v200.variations.MolecularAttributes;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import java.util.List;

/**
 * @author Dmitry Repchevsky
 */

@Entity("MolecularAttributes")
public class MolecularAttributesEntity implements MolecularAttributes<GenomicFeatureEntity, OntologyTermEntity> {

    @Column("geneIds")
    private List<String> geneIds;
    
    @Column("genomicFeatures")
    private List<GenomicFeatureEntity> genomicFeatures;
    
    @Column("molecularEffects")
    private List<OntologyTermEntity> molecularEffects;
    
    @Column("aminoacidChanges")
    private List<String> aminoacidChanges;

    @Override
    public List<String> getGeneIds() {
        return geneIds;
    }

    @Override
    public void setGeneIds(final List<String> geneIds) {
        this.geneIds = geneIds;
    }
    
    @Override
    public List<GenomicFeatureEntity> getGenomicFeatures() {
        return genomicFeatures;
    }

    @Override
    public void setGenomicFeatures(final List<GenomicFeatureEntity> genomicFeatures) {
        this.genomicFeatures = genomicFeatures;
    }
    
    @Override
    public List<OntologyTermEntity> getMolecularEffects() {
        return molecularEffects;
    }

    @Override
    public void setMolecularEffects(final List<OntologyTermEntity> molecularEffects) {
        this.molecularEffects = molecularEffects;
    }
    
    @Override
    public List<String> getAminoacidChanges() {
        return aminoacidChanges;
    }

    @Override
    public void setAminoacidChanges(final List<String> aminoacidChanges) {
        this.aminoacidChanges = aminoacidChanges;
    }
}
