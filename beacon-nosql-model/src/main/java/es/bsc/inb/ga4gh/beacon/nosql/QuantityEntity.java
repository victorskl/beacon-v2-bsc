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

import es.bsc.inb.ga4gh.beacon.model.v200.common.Quantity;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;

/**
 * @author Dmitry Repchevsky
 */

@Entity("Quantity")
public class QuantityEntity extends ValueEntity
        implements Quantity<OntologyTermEntity, ReferenceRangeEntity> {

    @Column("unit")
    private OntologyTermEntity unit;
    
    @Column("value")
    private double value;
    
    @Column("referenceRange")
    private ReferenceRangeEntity referenceRange;
    
    @Override
    public OntologyTermEntity getUnit() {
        return unit;
    }

    @Override
    public void setUnit(OntologyTermEntity unit) {
        this.unit = unit;
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
    public ReferenceRangeEntity getReferenceRange() {
        return referenceRange;
    }

    @Override
    public void setReferenceRange(ReferenceRangeEntity referenceRange) {
        this.referenceRange = referenceRange;
    }
    
}
