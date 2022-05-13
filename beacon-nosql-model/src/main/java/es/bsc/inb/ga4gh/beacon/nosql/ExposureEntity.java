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

import es.bsc.inb.ga4gh.beacon.model.v200.common.Exposure;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import java.time.ZonedDateTime;

/**
 * @author Dmitry Repchevsky
 */

@Entity("Exposure")
public class ExposureEntity implements Exposure<OntologyTermEntity, AgeEntity> {
    
    @Column("exposureCode")
    private OntologyTermEntity exposureCode;
    
    @Column("value")
    private Double value;
    
    @Column("units")
    private OntologyTermEntity units;

    @Column("duration")
    private String duration;
    
    @Column("date")
    private ZonedDateTime date;
    
    @Column("ageAtExposure")
    private AgeEntity ageAtExposure;

    @Override
    public OntologyTermEntity getExposureCode() {
        return exposureCode;
    }

    @Override
    public void setExposureCode(OntologyTermEntity exposureCode) {
        this.exposureCode = exposureCode;
    }
    
    @Override
    public Double getValue() {
        return value;
    }
    
    @Override
    public void setValue(Double value) {
        this.value = value;
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
    public String getDuration() {
        return duration;
    }

    @Override
    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public ZonedDateTime getDate() {
        return date;
    }

    @Override
    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
    
    @Override
    public AgeEntity getAgeAtExposure() {
        return ageAtExposure;
    }

    @Override
    public void setAgeAtExposure(AgeEntity ageAtExposure) {
        this.ageAtExposure = ageAtExposure;
    }
}
