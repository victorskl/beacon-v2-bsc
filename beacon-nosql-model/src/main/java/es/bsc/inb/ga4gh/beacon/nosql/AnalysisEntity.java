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

import es.bsc.inb.ga4gh.beacon.model.v200.analysis.Analysis;
import javax.json.JsonObject;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import java.time.LocalDate;

/**
 * @author Dmitry Repchevsky
 */

@Entity("Analyses")
public class AnalysisEntity implements Analysis {

    @Id
    private String _id;

    @Column("id")
    private String id;
    
    @Column("runId")
    private String runId;
    
    @Column("biosampleId")
    private String biosampleId;

    @Column("individualId")
    private String individualId;    

    @Column("analysisDate")
    private LocalDate analysisDate;
    
    @Column("pipelineName")
    private String pipelineName;
    
    @Column("pipelineRef")
    private String pipelineRef;
    
    @Column("aligner")
    private String aligner;
    
    @Column("variantCaller")
    private String variantCaller;
    
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
    public String getRunId() {
        return runId;
    }

    @Override
    public void setRunId(String runId) {
        this.runId = runId;
    }
    
    @Override
    public String getBiosampleId() {
        return biosampleId;
    }
    
    @Override
    public void setBiosampleId(String biosampleId) {
        this.biosampleId = biosampleId;
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
    public LocalDate getAnalysisDate() {
        return analysisDate;
    }

    @Override
    public void setAnalysisDate(LocalDate analysisDate) {
        this.analysisDate = analysisDate;
    }

    @Override
    public String getPipelineName() {
        return pipelineName;
    }

    @Override
    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
    }

    @Override
    public String getPipelineRef() {
        return pipelineRef;
    }

    @Override
    public void setPipelineRef(String pipelineRef) {
        this.pipelineRef = pipelineRef;
    }

    @Override
    public String getAligner() {
        return aligner;
    }

    @Override
    public void setAligner(String aligner) {
        this.aligner = aligner;
    }

    @Override
    public String getVariantCaller() {
        return variantCaller;
    }

    @Override
    public void setVariantCaller(String variantCaller) {
        this.variantCaller = variantCaller;
    }

    @Override
    public JsonObject getInfo() {
        return info;
    }

    @Override
    public void setInfo(final JsonObject info) {
        this.info = info;
    }
}
