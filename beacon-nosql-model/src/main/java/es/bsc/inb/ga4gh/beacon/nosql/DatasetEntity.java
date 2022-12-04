package es.bsc.inb.ga4gh.beacon.nosql;

import es.bsc.inb.ga4gh.beacon.model.v200.dataset.Dataset;
import javax.json.JsonObject;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import java.time.ZonedDateTime;

/**
 * @author Dmitry Repchevsky
 */

@Entity("Datasets")
public class DatasetEntity implements Dataset<DataUseConditionsEntity> {

    @Id("_id")
    private String _id;

    @Column("id")
    private String id;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("createDateTime")
    private ZonedDateTime createDateTime;
    
    @Column("updateDateTime")
    private ZonedDateTime updateDateTime;

    @Column("version")
    private String version;

    @Column("externalUrl")
    private String externalUrl;
    
    @Column("dataUseConditions")
    private DataUseConditionsEntity dataUseConditions;
    
    @Column("info")
    private JsonObject info;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public ZonedDateTime getCreateDateTime() {
        return createDateTime;
    }

    @Override
    public void setCreateDateTime(ZonedDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    @Override
    public ZonedDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    @Override
    public void setUpdateDateTime(ZonedDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getExternalUrl() {
        return externalUrl;
    }

    @Override
    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    @Override
    public DataUseConditionsEntity getDataUseConditions() {
        return dataUseConditions;
    }

    @Override
    public void setDataUseConditions(DataUseConditionsEntity dataUseConditions) {
        this.dataUseConditions = dataUseConditions;
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
