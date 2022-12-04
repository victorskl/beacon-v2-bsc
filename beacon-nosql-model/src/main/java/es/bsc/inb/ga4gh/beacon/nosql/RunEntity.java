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

import es.bsc.inb.ga4gh.beacon.model.v200.runs.Run;
import javax.json.JsonObject;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import java.time.LocalDate;

/**
 * @author Dmitry Repchevsky
 */

@Entity("Runs")
public class RunEntity implements Run<OntologyTermEntity> {

    @Id
    private String _id;

    @Column("id")
    private String id;
    
    @Column("biosampleId")
    private String biosampleId;
    
    @Column("individualId")
    private String individualId;
    
    @Column("runDate")
    private LocalDate runDate;
    
    @Column("librarySource")
    private OntologyTermEntity librarySource;
    
    @Column("librarySelection")
    private String librarySelection;
    
    @Column("libraryStrategy")
    private String libraryStrategy;
    
    @Column("libraryLayout")
    private String libraryLayout;
    
    @Column("platform")
    private String platform;
    
    @Column("platformModel")
    private OntologyTermEntity platformModel;
    
    @Column("info")
    private JsonObject info;
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public String getBiosampleId() {
        return biosampleId;
    }

    @Override
    public void setBiosampleId(String biosampleId) {
        this.biosampleId = biosampleId;
    }

    @Override
    public String getIndividualId() {
        return individualId;
    }

    @Override
    public void setIndividualId(String individualId) {
        this.individualId = individualId;
    }

    @Override
    public LocalDate getRunDate() {
        return runDate;
    }

    @Override
    public void setRunDate(LocalDate runDate) {
        this.runDate = runDate;
    }

    @Override
    public OntologyTermEntity getLibrarySource() {
        return librarySource;
    }

    @Override
    public void setLibrarySource(OntologyTermEntity librarySource) {
        this.librarySource = librarySource;
    }

    @Override
    public String getLibrarySelection() {
        return librarySelection;
    }

    @Override
    public void setLibrarySelection(String librarySelection) {
        this.librarySelection = librarySelection;
    }

    @Override
    public String getLibraryStrategy() {
        return libraryStrategy;
    }

    @Override
    public void setLibraryStrategy(String libraryStrategy) {
        this.libraryStrategy = libraryStrategy;
    }

    @Override
    public String getLibraryLayout() {
        return libraryLayout;
    }

    @Override
    public void setLibraryLayout(String libraryLayout) {
        this.libraryLayout = libraryLayout;
    }

    @Override
    public String getPlatform() {
        return platform;
    }

    @Override
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public OntologyTermEntity getPlatformModel() {
        return platformModel;
    }

    @Override
    public void setPlatformModel(OntologyTermEntity platformModel) {
        this.platformModel = platformModel;
    }

    @Override
    public JsonObject getInfo() {
        return info;
    }

    @Override
    public void setInfo(JsonObject info) {
        this.info = info;
    }
}
