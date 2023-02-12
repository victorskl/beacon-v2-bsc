package es.bsc.inb.ga4gh.beacon.nosql;

import es.bsc.inb.ga4gh.beacon.model.v200.variations.ValueType;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;

@Entity("ValueType")
public class ValueTypeEntity implements ValueType {

    @Column("value")
    private Number value;

    @Column("type")
    private String type;

    @Override
    public Number getValue() {
        return value;
    }

    @Override
    public void setValue(Number value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }
}
