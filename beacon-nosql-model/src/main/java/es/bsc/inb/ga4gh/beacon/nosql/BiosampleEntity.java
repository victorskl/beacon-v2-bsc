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

import es.bsc.inb.ga4gh.beacon.model.v200.biosample.Biosample;
import jakarta.json.JsonObject;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Dmitry Repchevsky
 */

@Entity("Biosamples")
public class BiosampleEntity 
        implements Biosample<OntologyTermEntity, ProcedureEntity, PhenotypicFeatureEntity, MeasurementEntity> {

    @Id
    private String _id;
    
    @Column("id")
    private String id;
    
    @Column("individualId")
    private String individualId;

    @Column("notes")
    private String notes;

    @Column("biosampleStatus")
    private OntologyTermEntity biosampleStatus;
    
    @Column("collectionDate")
    private LocalDate collectionDate;
    
    @Column("collectionMoment")
    private String collectionMoment;
    
    @Column("sampleOriginType")
    private OntologyTermEntity sampleOriginType;
    
    @Column("sampleOriginDetail")
    private OntologyTermEntity sampleOriginDetail;
    
    @Column("obtentionProcedure")
    private ProcedureEntity obtentionProcedure;
    
    @Column("tumorProgression")
    private OntologyTermEntity tumorProgression;

    @Column("tumorGrade")
    private OntologyTermEntity tumorGrade;
    
    @Column("pathologicalStage")
    private OntologyTermEntity pathologicalStage;

    @Column("pathologicalTnmFinding")
    private List<OntologyTermEntity> pathologicalTnmFinding;
    
    @Column("histologicalDiagnosis")
    private OntologyTermEntity histologicalDiagnosis;
    
    @Column("diagnosticMarkers")
    private List<OntologyTermEntity> diagnosticMarkers;
    
    @Column("phenotypicFeatures")
    private List<PhenotypicFeatureEntity> phenotypicFeatures;
    
    @Column("measurements")
    private List<MeasurementEntity> measurements;
    
    @Column("sampleProcessing")
    private OntologyTermEntity sampleProcessing;

    @Column("sampleStorage")
    private OntologyTermEntity sampleStorage;
    
    @Column("info")
    private JsonObject info;

    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getIndividualId() {
        return individualId;
    }

    @Override
    public void setIndividualId(String individualId) {
        this.individualId = individualId;
    }
    
    @Override
    public String getNotes() {
        return notes;
    }
    
    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public OntologyTermEntity getBiosampleStatus() {
        return biosampleStatus;
    }

    @Override
    public void setBiosampleStatus(OntologyTermEntity biosampleStatus) {
        this.biosampleStatus = biosampleStatus;
    }

    @Override
    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    @Override
    public void getCollectionDate(LocalDate collectionDate) {
        this.collectionDate = collectionDate;
    }

    @Override
    public String getCollectionMoment() {
        return collectionMoment;
    }
    
    @Override
    public void setCollectionMoment(String collectionMoment) {
        this.collectionMoment = collectionMoment;
    }
    
    @Override
    public OntologyTermEntity getSampleOriginType() {
        return sampleOriginType;
    }
    
    @Override
    public void setSampleOriginType(OntologyTermEntity sampleOriginType) {
        this.sampleOriginType = sampleOriginType;
    }

    @Override
    public OntologyTermEntity getSampleOriginDetail() {
        return sampleOriginDetail;
    }

    @Override
    public void setSampleOriginDetail(OntologyTermEntity sampleOriginDetail) {
        this.sampleOriginDetail = sampleOriginDetail;
    }
    
    @Override
    public ProcedureEntity getObtentionProcedure() {
        return obtentionProcedure;
    }

    @Override
    public void setObtentionProcedure(ProcedureEntity obtentionProcedure) {
        this.obtentionProcedure = obtentionProcedure;
    }

    @Override
    public OntologyTermEntity getTumorProgression() {
        return tumorProgression;
    }

    @Override
    public void setTumorProgression(OntologyTermEntity tumorProgression) {
        this.tumorProgression = tumorProgression;
    }

    @Override
    public OntologyTermEntity getTumorGrade() {
        return tumorGrade;
    }

    @Override
    public void setTumorGrade(OntologyTermEntity tumorGrade) {
        this.tumorGrade = tumorGrade;
    }
    
    @Override
    public OntologyTermEntity getPathologicalStage() {
        return pathologicalStage;
    }
    
    @Override
    public void setPathologicalStage(OntologyTermEntity pathologicalStage) {
        this.pathologicalStage = pathologicalStage;
    }
    
    @Override
    public List<OntologyTermEntity> getPathologicalTnmFinding() {
        return pathologicalTnmFinding;
    }
    
    @Override
    public void setPathologicalTnmFinding(List<OntologyTermEntity> pathologicalTnmFinding) {
        this.pathologicalTnmFinding = pathologicalTnmFinding;
    }
    
    @Override
    public OntologyTermEntity getHistologicalDiagnosis() {
        return histologicalDiagnosis;
    }
    
    @Override
    public void setHistologicalDiagnosis(OntologyTermEntity histologicalDiagnosis) {
        this.histologicalDiagnosis = histologicalDiagnosis;
    }

    @Override
    public List<OntologyTermEntity> getDiagnosticMarkers() {
        return diagnosticMarkers;
    }

    @Override
    public void setDiagnosticMarkers(List<OntologyTermEntity> diagnosticMarkers) {
        this.diagnosticMarkers = diagnosticMarkers;
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
    public List<MeasurementEntity> getMeasurements() {
        return measurements;
    }
    
    @Override
    public void setMeasurements(List<MeasurementEntity> measurements) {
        this.measurements = measurements;
    }

    @Override
    public OntologyTermEntity getSampleProcessing() {
        return sampleProcessing;
    }
    
    @Override
    public void setSampleProcessing(OntologyTermEntity sampleProcessing) {
        this.sampleProcessing = sampleProcessing;
    }
    
    @Override
    public OntologyTermEntity getSampleStorage() {
        return sampleStorage;
    }

    @Override
    public void setSampleStorage(OntologyTermEntity sampleStorage) {
        this.sampleStorage = sampleStorage;
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
