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

import es.bsc.inb.ga4gh.beacon.model.v200.variations.CaseLevelVariant;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import java.util.List;

/**
 * @author Dmitry Repchevsky
 */

@Entity("CaseLevelVariant")
public class CaseLevelVariantEntity 
        implements CaseLevelVariant<OntologyTermEntity, PhenoClinicEffectEntity> {

    @Column("individualId")
    private String individualId;
    
    @Column("biosampleId")
    private String biosampleId;
    
    @Column("analysisId")
    private String analysisId;
    
    @Column("zigosity")
    private OntologyTermEntity zigosity;
    
    @Column("alleleOrigin")
    private OntologyTermEntity alleleOrigin;
    
    @Column("clinicalInterpretations")
    private List<PhenoClinicEffectEntity> clinicalInterpretations;
    
    @Column("phenotypicEffects")
    private List<PhenoClinicEffectEntity> phenotypicEffects;

    @Override
    public String getIndividualId() {
        return individualId;
    }
    
    @Override
    public void setIndividualId(String individualId) {
        this.individualId = individualId;
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
    public String getAnalysisId() {
        return analysisId;
    }

    @Override
    public void setAnalysisId(String analysisId) {
        this.analysisId = analysisId;
    }

    @Override
    public OntologyTermEntity getZigosity() {
        return zigosity;
    }

    @Override
    public void setZigosity(OntologyTermEntity zigosity) {
        this.zigosity = zigosity;
    }
    
    @Override
    public OntologyTermEntity getAlleleOrigin() {
        return alleleOrigin;
    }

    @Override
    public void setAlleleOrigin(OntologyTermEntity alleleOrigin) {
        this.alleleOrigin = alleleOrigin;
    }
    
    @Override
    public List<PhenoClinicEffectEntity> getClinicalInterpretations() {
        return clinicalInterpretations;
    }
    
    @Override
    public void setClinicalInterpretations(List<PhenoClinicEffectEntity> clinicalInterpretations) {
        this.clinicalInterpretations = clinicalInterpretations;
    }

    @Override
    public List<PhenoClinicEffectEntity> getPhenotypicEffects() {
        return phenotypicEffects;
    }

    @Override
    public void setPhenotypicEffects(List<PhenoClinicEffectEntity> phenotypicEffects) {
        this.phenotypicEffects = phenotypicEffects;
    }
}
