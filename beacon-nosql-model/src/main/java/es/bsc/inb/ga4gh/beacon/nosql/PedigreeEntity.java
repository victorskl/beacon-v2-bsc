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

import es.bsc.inb.ga4gh.beacon.model.v200.common.Pedigree;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import java.util.List;

/**
 * @author Dmitry Repchevsky
 */

@Entity("Pedigree")
public class PedigreeEntity 
        implements Pedigree<DiseaseEntity, PedigreeMemberEntity> {
    
    @Column("id")
    private String id;
    
    @Column("disease")
    private DiseaseEntity disease;
    
    @Column("numSubjects")
    private Integer numSubjects;
    
    @Column("members")
    private List<PedigreeMemberEntity> members;
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public DiseaseEntity getDisease() {
        return disease;
    }

    @Override
    public void setDisiase(DiseaseEntity disease) {
        this.disease = disease;
    }
    
    @Override
    public Integer getNumSubjects() {
        return numSubjects;
    }

    @Override
    public void setNumSubjects(Integer numSubjects) {
        this.numSubjects = numSubjects;
    }

    @Override
    public List<PedigreeMemberEntity> getMembers() {
        return members;
    }
    
    @Override
    public void setMembers(List<PedigreeMemberEntity> members) {
        this.members = members;
    }
}
