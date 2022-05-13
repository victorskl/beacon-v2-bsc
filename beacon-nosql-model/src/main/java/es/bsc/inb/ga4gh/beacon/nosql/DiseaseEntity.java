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

import es.bsc.inb.ga4gh.beacon.model.v200.common.Disease;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;

/**
 * @author Dmitry Repchevsky
 */

@Entity("Disease")
public class DiseaseEntity implements Disease<OntologyTermEntity, AgeEntity> {

    @Column("diseaseCode")    
    private OntologyTermEntity diseaseCode;
    
    @Column("ageOfOnset")
    private AgeEntity ageOfOnset;
    
    @Column("stage")
    private OntologyTermEntity stage;
    
    @Column("severityLevel")
    private OntologyTermEntity severityLevel;
    
    @Column("familyHistory")
    private Boolean familyHistory;
    
    @Column("notes")
    private String notes;
    
    @Override
    public OntologyTermEntity getDiseaseCode() {
        return diseaseCode;
    }

    @Override
    public void setDiseaseCode(OntologyTermEntity diseaseCode) {
        this.diseaseCode = diseaseCode;
    }

    @Override
    public AgeEntity getAgeOfOnset() {
        return ageOfOnset;
    }

    @Override
    public void setAgeOfOnset(AgeEntity ageOfOnset) {
        this.ageOfOnset = ageOfOnset;
    }

    @Override
    public OntologyTermEntity getStage() {
        return stage;
    }

    @Override
    public void setStage(OntologyTermEntity stage) {
        this.stage = stage;
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
    public Boolean getFamilyHistory() {
        return familyHistory;
    }

    @Override
    public void setFamilyHistory(Boolean familyHistory) {
        this.familyHistory = familyHistory;
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
