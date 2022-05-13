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

import es.bsc.inb.ga4gh.beacon.model.v200.biosample.Measurement;
import es.bsc.inb.ga4gh.beacon.nosql.converter.MeasurementValueDeserializer;
import jakarta.json.bind.annotation.JsonbTypeDeserializer;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import java.time.LocalDate;

/**
 * @author Dmitry Repchevsky
 */

@Entity("Measurement")
public class MeasurementEntity 
        implements Measurement<OntologyTermEntity, MeasurementValueEntity, 
        TimeElementEntity, ProcedureEntity> {

    @Column("assayCode")
    private OntologyTermEntity assayCode;
    
    @Column("measurementValue")
    @JsonbTypeDeserializer(MeasurementValueDeserializer.class)
    private MeasurementValueEntity measurementValue;
    
    @Column("observationMoment")
    private TimeElementEntity observationMoment;
    
    @Column("date")
    private LocalDate date;
    
    @Column("procedure")
    private ProcedureEntity procedure;

    @Column("notes")
    private String notes;
    
    @Override
    public OntologyTermEntity getAssayCode() {
        return assayCode;
    }

    @Override
    public void setAssayCode(OntologyTermEntity assayCode) {
        this.assayCode = assayCode;
    }

    @Override
    public MeasurementValueEntity getMeasurementValue() {
        return measurementValue;
    }

    @Override
    public void setMeasurementValue(MeasurementValueEntity measurementValue) {
        this.measurementValue = measurementValue;
    }

    @Override
    public TimeElementEntity getObservationMoment() {
        return observationMoment;
    }

    @Override
    public void setObservationMoment(TimeElementEntity observationMoment) {
        this.observationMoment = observationMoment;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public ProcedureEntity getProcedure() {
        return procedure;
    }

    @Override
    public void setProcedure(ProcedureEntity procedure) {
        this.procedure = procedure;
    }
    
    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }

}
