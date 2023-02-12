package es.bsc.inb.ga4gh.beacon.nosql;

import es.bsc.inb.ga4gh.beacon.model.v200.variations.Variation;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;

@Entity("Variation")
public class VariationEntity implements Variation<LocationEntity> {

    @Column("variantType")
    private String variantType;

    @Column("referenceBases")
    private String referenceBases;

    @Column("alternateBases")
    private String alternateBases;

    @Column("location")
    private LocationEntity location;

    @Override
    public String getVariantType() {
        return variantType;
    }

    @Override
    public void setVariantType(String variantType) {
        this.variantType = variantType;
    }

    @Override
    public String getReferenceBases() {
        return referenceBases;
    }

    @Override
    public void setReferenceBases(String referenceBases) {
        this.referenceBases = referenceBases;
    }

    @Override
    public String getAlternateBases() {
        return alternateBases;
    }
    @Override
    public void setAlternateBases(String alternateBases) {
        this.alternateBases = alternateBases;
    }

    @Override
    public LocationEntity getLocation() {
        return location;
    }

    @Override
    public void setLocation(LocationEntity location) {
        this.location = location;
    }
}
