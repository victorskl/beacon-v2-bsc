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

import es.bsc.inb.ga4gh.beacon.model.v200.common.Treatment;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;

/**
 * @author Dmitry Repchevsky
 */

@Entity("Treatment")
public class TreatmentEntity implements Treatment<OntologyTermEntity, AgeEntity> {

    @Column("treatmentCode")
    private OntologyTermEntity treatmentCode;
    
    @Column("route")
    private OntologyTermEntity route;
    
    @Column("ageOnOnset")
    public AgeEntity ageOnOnset;

    @Column("dose")
    private Double dose;

    @Column("units")
    private OntologyTermEntity units;
    
    @Column("frequency")
    private String frequency;
    
    @Column("duration")
    public String duration;

    @Override
    public OntologyTermEntity getTreatmentCode() {
        return treatmentCode;
    }

    @Override
    public void setTreatmentCode(OntologyTermEntity treatmentCode) {
        this.treatmentCode = treatmentCode;
    }
    
    @Override
    public OntologyTermEntity getRoute() {
        return route;
    }

    @Override
    public void setRoute(OntologyTermEntity route) {
        this.route = route;
    }

    @Override
    public AgeEntity getAgeOnOnset() {
        return ageOnOnset;
    }
    
    @Override
    public void setAgeOnOnset(AgeEntity ageOnOnset) {
        this.ageOnOnset = ageOnOnset;
    }
    
    @Override
    public Double getDose() {
        return dose;
    }

    @Override
    public void setDose(Double dose) {
        this.dose = dose;
    }

    @Override
    public OntologyTermEntity getUnits() {
        return units;
    }
    
    @Override
    public void setUnits(OntologyTermEntity units) {
        this.units = units;
    }

    @Override
    public String getFrequency() {
        return frequency;
    }

    @Override
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public String getDuration() {
        return duration;
    }

    @Override
    public void setDuration(String duration) {
        this.duration = duration;
    }

}
