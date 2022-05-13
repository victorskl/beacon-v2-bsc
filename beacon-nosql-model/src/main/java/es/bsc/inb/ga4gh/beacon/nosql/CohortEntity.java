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

import es.bsc.inb.ga4gh.beacon.model.v200.cohort.Cohort;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import java.util.List;

/**
 * @author Dmitry Repchevsky
 */

@Entity("Cohorts")
public class CohortEntity implements Cohort<OntologyTermEntity, 
        CohortCriteriaEntity, CollectionEventEntity> {

    @Id("_id")
    private String _id;
    
    @Column("id")
    private String id;
    
    @Column("name")
    private String name;
    
    @Column("cohortType")
    private String cohortType;
    
    @Column("cohortDesign")
    private OntologyTermEntity cohortDesign;
    
    @Column("inclusionCriteria")
    private CohortCriteriaEntity inclusionCriteria;
    
    @Column("exclusionCriteria")
    private CohortCriteriaEntity exclusionCriteria;
    
    @Column("cohortSize")
    private Integer cohortSize;
    
    @Column("cohortDataTypes")
    private List<OntologyTermEntity> cohortDataTypes;
    
    @Column("collectionEvents")
    private List<CollectionEventEntity> collectionEvents;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCohortType() {
        return cohortType;
    }

    @Override
    public void setCohortType(String cohortType) {
        this.cohortType = cohortType;
    }

    @Override
    public OntologyTermEntity getCohortDesign() {
        return cohortDesign;
    }

    @Override
    public void setCohortDesign(OntologyTermEntity cohortDesign) {
        this.cohortDesign = cohortDesign;
    }

    @Override
    public CohortCriteriaEntity getInclusionCriteria() {
        return inclusionCriteria;
    }

    @Override
    public void setInclusionCriteria(CohortCriteriaEntity inclusionCriteria) {
        this.inclusionCriteria = inclusionCriteria;
    }
    
    @Override
    public CohortCriteriaEntity getExclusionCriteria() {
        return exclusionCriteria;
    }

    @Override
    public void setExclusionCriteria(CohortCriteriaEntity exclusionCriteria) {
        this.exclusionCriteria = exclusionCriteria;
    }
    
    @Override
    public Integer getCohortSize() {
        return cohortSize;
    }

    @Override
    public void setCohortSize(Integer cohortSize) {
        this.cohortSize = cohortSize;
    }

    @Override
    public List<OntologyTermEntity> getCohortDataTypes() {
        return cohortDataTypes;
    }

    @Override
    public void setCohortDataTypes(List<OntologyTermEntity> cohortDataTypes) {
        this.cohortDataTypes = cohortDataTypes;
    }
    
    @Override
    public List<CollectionEventEntity> getCollectionEvents() {
        return collectionEvents;
    }

    @Override
    public void setCollectionEvents(List<CollectionEventEntity> collectionEvents) {
        this.collectionEvents = collectionEvents;
    }
}
