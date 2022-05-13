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

import es.bsc.inb.ga4gh.beacon.model.v200.cohort.CohortCriteria;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import java.util.List;

/**
 * @author Dmitry Repchevsky
 */

@Entity("CohortCriteria")
public class CohortCriteriaEntity 
        implements CohortCriteria<OntologyTermEntity, DiseaseEntity, 
        PhenotypicFeatureEntity, AgeRangeEntity> {

    @Column("locations")
    private List<OntologyTermEntity> locations;
    
    @Column("genders")
    private List<OntologyTermEntity> genders;
    
    @Column("ethnicities")
    private List<OntologyTermEntity> ethnicities;
    
    @Column("diseaseConditions")
    private List<DiseaseEntity> diseaseConditions;
    
    @Column("phenotypicConditions")
    private List<PhenotypicFeatureEntity> phenotypicConditions;

    @Column("ageRange")
    private AgeRangeEntity ageRange;
    
    @Override
    public List<OntologyTermEntity> getLocations() {
        return locations;
    }

    @Override
    public void setLocations(List<OntologyTermEntity> locations) {
        this.locations = locations;
    }

    @Override
    public List<OntologyTermEntity> getGenders() {
        return genders;
    }

    @Override
    public void setGenders(List<OntologyTermEntity> genders) {
        this.genders = genders;
    }

    @Override
    public List<OntologyTermEntity> getEthnicities() {
        return ethnicities;
    }

    @Override
    public void setEthnicities(List<OntologyTermEntity> ethnicities) {
        this.ethnicities = ethnicities;
    }

    @Override
    public List<DiseaseEntity> getDiseaseConditions() {
        return diseaseConditions;
    }

    @Override
    public void setDiseaseConditions(List<DiseaseEntity> diseaseConditions) {
        this.diseaseConditions = diseaseConditions;
    }

    @Override
    public List<PhenotypicFeatureEntity> getPhenotypicConditions() {
        return phenotypicConditions;
    }

    @Override
    public void setPhenotypicConditions(List<PhenotypicFeatureEntity> phenotypicConditions) {
        this.phenotypicConditions = phenotypicConditions;
    }

    @Override
    public AgeRangeEntity getAgeRange() {
        return ageRange;
    }

    @Override
    public void setAgeRange(AgeRangeEntity ageRange) {
        this.ageRange = ageRange;
    }
}
