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

package es.bsc.inb.ga4gh.beacon.nosql;

import es.bsc.inb.ga4gh.beacon.model.v200.individuals.Individual;
import javax.json.JsonObject;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import java.util.List;

/**
 * @author Dmitry Repchevsky
 */

@Entity("Individuals")
public class IndividualEntity 
        implements Individual<OntologyTermEntity, DiseaseEntity, PedigreeEntity, 
        PhenotypicFeatureEntity, TreatmentEntity, ProcedureEntity, MeasurementEntity, 
        ExposureEntity> {

    @Id
    private String _id;

    @Id("id")
    public String id;

    @Column("sex")
    public OntologyTermEntity sex;

    @Column("ethnicity")
    public OntologyTermEntity ethnicity;

    @Column("geographicOrigin")
    public OntologyTermEntity geographicOrigin;  
    
    @Column("diseases")
    public List<DiseaseEntity> diseases;
    
    @Column("pedigrees")
    public List<PedigreeEntity> pedigrees;
    
    @Column("phenotypicFeatures")
    public List<PhenotypicFeatureEntity> phenotypicFeatures;
    
    @Column("treatments")
    public List<TreatmentEntity> treatments;
    
    @Column("interventionsOrProcedures")
    public List<ProcedureEntity> interventionsOrProcedures;
    
    @Column("measures")
    public List<MeasurementEntity> measures;
    
    @Column("exposures")
    public List<ExposureEntity> exposures;
    
    @Column("info")
    public JsonObject info;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public OntologyTermEntity getSex() {
        return sex;
    }

    @Override
    public void setSex(OntologyTermEntity sex) {
        this.sex = sex;
    }

    @Override
    public OntologyTermEntity getEthnicity() {
        return ethnicity;
    }

    @Override
    public void setEthnicity(OntologyTermEntity ethnicity) {
        this.ethnicity = ethnicity;
    }
    
    @Override
    public OntologyTermEntity getGeographicOrigin() {
        return geographicOrigin;
    }

    @Override
    public void setGeographicOrigin(OntologyTermEntity geographicOrigin) {
        this.geographicOrigin = geographicOrigin;
    }

    @Override
    public List<DiseaseEntity> getDiseases() {
        return diseases;
    }

    @Override
    public void setDiseases(List<DiseaseEntity> diseases) {
        this.diseases = diseases;
    }

    @Override
    public List<PedigreeEntity> getPedigrees() {
        return pedigrees;
    }

    @Override
    public void setPedigrees(List<PedigreeEntity> pedigrees) {
        this.pedigrees = pedigrees;
    }

    @Override
    public List<PhenotypicFeatureEntity> getPhenotypicFeatures() {
        return phenotypicFeatures;
    }

    @Override
    public void setPhenotypicFeatures(List<PhenotypicFeatureEntity> phenotypicFeatures) {
        this.phenotypicFeatures = phenotypicFeatures;
    }
    
    @Override
    public List<TreatmentEntity> getTreatments() {
        return treatments;
    }

    @Override
    public void setTreatments(List<TreatmentEntity> treatments) {
        this.treatments = treatments;
    }

    @Override
    public List<ProcedureEntity> getInterventionsOrProcedures() {
        return interventionsOrProcedures;
    }

    @Override
    public void setInterventionsOrProcedures(List<ProcedureEntity> interventionsOrProcedures) {
        this.interventionsOrProcedures = interventionsOrProcedures;
    }

    @Override
    public List<MeasurementEntity> getMeasures() {
        return measures;
    }

    @Override
    public void setMeasures(List<MeasurementEntity> measures) {
        this.measures = measures;
    }

    @Override
    public List<ExposureEntity> getExposures() {
        return exposures;
    }

    @Override
    public void setExposures(List<ExposureEntity> exposures) {
        this.exposures = exposures;
    }

    @Override
    public JsonObject getInfo() {
        return info;
    }

    @Override
    public void setInfo(JsonObject info) {
        this.info = info;
    }

}
