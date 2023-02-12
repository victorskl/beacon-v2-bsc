package es.bsc.inb.ga4gh.beacon.nosql;

import es.bsc.inb.ga4gh.beacon.model.v200.variations.Location;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;

@Entity("Location")
public class LocationEntity implements Location<IntervalEntity> {

    @Column("type")
    private String type;

    @Column("sequence_id")
    private String sequenceId;

    @Column("interval")
    private IntervalEntity interval;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getSequenceId() {
        return sequenceId;
    }

    @Override
    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    @Override
    public IntervalEntity getInterval() {
        return interval;
    }

    @Override
    public void setInterval(IntervalEntity interval) {
        this.interval = interval;
    }
}
