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

import es.bsc.inb.ga4gh.beacon.model.v200.common.PhenotypicFeature;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import java.util.List;

/**
 * @author Dmitry Repchevsky
 */

@Entity("PhenotypicFeature")
public class PhenotypicFeatureEntity 
        implements PhenotypicFeature<OntologyTermEntity, 
        TimeElementEntity, EvidenceEntity> {
    
    @Column("featureType")
    private OntologyTermEntity featureType;
    
    @Column("excluded")
    private Boolean excluded;
    
    @Column("modifiers")
    private List<OntologyTermEntity> modifiers;
    
    @Column("onset")
    private TimeElementEntity onset;
    
    @Column("resolution")
    private TimeElementEntity resolution;
    
    @Column("evidence")
    private EvidenceEntity evidence;
    
    @Column("severityLevel")
    private OntologyTermEntity severityLevel;
    
    @Column("notes")
    private String notes;

    @Override
    public OntologyTermEntity getFeatureType() {
        return featureType;
    }

    @Override
    public void setFeatureType(OntologyTermEntity featureType) {
        this.featureType = featureType;
    }
    
    @Override
    public Boolean getExcluded() {
        return excluded;
    }
    
    @Override
    public void setExcluded(Boolean excluded) {
        this.excluded = excluded;
    }
    
    @Override
    public List<OntologyTermEntity> getModifiers() {
        return modifiers;
    }

    @Override
    public void setModifiers(List<OntologyTermEntity> modifiers) {
        this.modifiers = modifiers;
    }
    
    @Override
    public TimeElementEntity getOnset() {
        return onset;
    }
    
    @Override
    public void setOnset(TimeElementEntity onset) {
        this.onset = onset;
    }
    
    @Override
    public TimeElementEntity getResolution() {
        return resolution;
    }
    
    @Override
    public void setResolution(TimeElementEntity resolution) {
        this.resolution = resolution;
    }

    @Override
    public EvidenceEntity getEvidence() {
        return evidence;
    }
    
    @Override
    public void setEvidence(EvidenceEntity evidence) {
        this.evidence = evidence;
    }

    @Override
    public OntologyTermEntity getSeverityLevel() {
        return severityLevel;
    }

    @Override
    public void setSeverityLevel(OntologyTermEntity severityLevel) {
        this.severityLevel = severityLevel;
    }
    
    @Override
    public String getNotes() {
        return notes;
    }
    
    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
