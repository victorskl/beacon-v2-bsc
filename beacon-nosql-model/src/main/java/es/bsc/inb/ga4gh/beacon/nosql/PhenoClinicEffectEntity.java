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

import es.bsc.inb.ga4gh.beacon.model.v200.variations.PhenoClinicEffect;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;

/**
 * @author Dmitry Repchevsky
 */

@Entity("PhenoClinicEffect")
public class PhenoClinicEffectEntity 
        implements PhenoClinicEffect<OntologyTermEntity, SoftwareToolEntity> {

    @Column("conditionId")
    private String conditionId;
    
    @Column("category")
    private OntologyTermEntity category;
    
    @Column("effect")
    private OntologyTermEntity effect;
    
    @Column("clinicalRelevance")
    private String clinicalRelevance;
    
    @Column("evidenceType")
    private OntologyTermEntity evidenceType;
    
    @Column("annotatedWith")
    private SoftwareToolEntity annotatedWith;
    
    @Override
    public String getConditionId() {
        return conditionId;
    }

    @Override
    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }
    
    @Override
    public OntologyTermEntity getCategory() {
        return category;
    }
    
    @Override
    public void setCategory(OntologyTermEntity category) {
        this.category = category;
    }
    
    @Override
    public OntologyTermEntity getEffect() {
        return effect;
    }

    @Override
    public void setEffect(OntologyTermEntity effect) {
        this.effect = effect;
    }
    
    @Override
    public String getClinicalRelevance() {
        return clinicalRelevance;
    }

    @Override
    public void setClinicalRelevance(String clinicalRelevance) {
        this.clinicalRelevance = clinicalRelevance;
    }
    
    @Override
    public OntologyTermEntity getEvidenceType() {
        return evidenceType;
    }

    @Override
    public void setEvidenceType(OntologyTermEntity evidenceType) {
        this.evidenceType = evidenceType;
    }
    
    @Override
    public SoftwareToolEntity getAnnotatedWith() {
        return annotatedWith;
    }

    @Override
    public void setAnnotatedWith(SoftwareToolEntity annotatedWith) {
        this.annotatedWith = annotatedWith;
    }
}
