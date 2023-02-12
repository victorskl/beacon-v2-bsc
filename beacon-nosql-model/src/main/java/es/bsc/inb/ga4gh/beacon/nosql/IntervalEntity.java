package es.bsc.inb.ga4gh.beacon.nosql;

import es.bsc.inb.ga4gh.beacon.model.v200.variations.Interval;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;

@Entity("Interval")
public class IntervalEntity implements Interval<ValueTypeEntity> {

    @Column("start")
    private ValueTypeEntity start;

    @Column("end")
    private ValueTypeEntity end;

    @Column("type")
    private String type;

    @Override
    public ValueTypeEntity getStart() {
        return start;
    }

    @Override
    public void setStart(ValueTypeEntity start) {
        this.start = start;
    }

    @Override
    public ValueTypeEntity getEnd() {
        return end;
    }

    @Override
    public void setEnd(ValueTypeEntity end) {
        this.end = end;
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
