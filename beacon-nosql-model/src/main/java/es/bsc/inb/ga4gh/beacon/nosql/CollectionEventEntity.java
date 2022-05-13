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

import es.bsc.inb.ga4gh.beacon.model.v200.cohort.CollectionEvent;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import java.time.ZonedDateTime;

/**
 * @author Dmitry Repchevsky
 */

@Entity("CollectionEvent")
public class CollectionEventEntity implements CollectionEvent<EventTimelineEntity> {
    
    @Column("eventNum")
    private Integer eventNum;
    
    @Column("eventDate")
    private ZonedDateTime eventDate;
    
    @Column("eventTimeline")
    private EventTimelineEntity eventTimeline;
    
    @Column("eventSize")
    private Integer eventSize;

    @Column("eventCases")
    private Integer eventCases;

    @Column("eventControls")
    private Integer eventControls;
    
    @Override
    public Integer getEventNum() {
        return eventNum;
    }

    @Override
    public void setEventNum(Integer eventNum) {
        this.eventNum = eventNum;
    }

    @Override
    public ZonedDateTime getEventDate() {
        return eventDate;
    }

    @Override
    public void setEventDate(ZonedDateTime eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public EventTimelineEntity getEventTimeline() {
        return eventTimeline;
    }

    @Override
    public void setEventTimeline(EventTimelineEntity eventTimeline) {
        this.eventTimeline = eventTimeline;
    }

    @Override
    public Integer getEventSize() {
        return eventSize;
    }

    @Override
    public void setEventSize(Integer eventSize) {
        this.eventSize = eventSize;
    }

    @Override
    public Integer getEventCases() {
        return eventCases;
    }

    @Override
    public void setEventCases(Integer eventCases) {
        this.eventCases = eventCases;
    }

    @Override
    public Integer getEventControls() {
        return eventControls;
    }

    @Override
    public void setEventControls(Integer eventControls) {
        this.eventControls = eventControls;
    }
}
