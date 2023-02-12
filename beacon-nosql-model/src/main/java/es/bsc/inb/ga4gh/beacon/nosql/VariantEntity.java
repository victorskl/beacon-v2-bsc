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

import es.bsc.inb.ga4gh.beacon.model.v200.variations.Variant;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import java.util.List;

/**
 * @author Dmitry Repchevsky
 */

@Entity("Variants")
public class VariantEntity 
        implements Variant<VariationEntity, PositionEntity, IdentifiersEntity, MolecularAttributesEntity,
        CaseLevelVariantEntity, VariantLevelDataEntity, PopulationFrequenciesEntity> {

    @Id
    private String _id;

    @Column("variantInternalId")
    private String variantInternalId;

    @Column("variation")
    private VariationEntity variation;

    @Column("_position")
    private PositionEntity position;

    @Column("identifiers")
    private IdentifiersEntity identifiers;

    @Column("molecularAttributes")
    private MolecularAttributesEntity molecularAttributes;

    @Column("caseLevelData")
    private List<CaseLevelVariantEntity> caseLevelData;
    
    @Column("variantLevelData")
    private VariantLevelDataEntity variantLevelData;
    
    @Column("frequencyInPopulations")
    private List<PopulationFrequenciesEntity> frequencyInPopulations;
    
    @Override
    public String getVariantInternalId() {
        return variantInternalId;
    }

    @Override
    public void setVariantInternalId(String variantInternalId) {
        this.variantInternalId = variantInternalId;
    }

    public VariationEntity getVariation() {
        return variation;
    }

    @Override
    public void setVariation(VariationEntity variation) {
        this.variation = variation;
    }

    @Override
    public PositionEntity getPosition() {
        return position;
    }

    @Override
    public void setPosition(PositionEntity position) {
        this.position = position;
    }

    @Override
    public IdentifiersEntity getIdentifiers() {
        return identifiers;
    }

    @Override
    public void setIdentifiers(IdentifiersEntity identifiers) {
        this.identifiers = identifiers;
    }
    
    @Override
    public MolecularAttributesEntity getMolecularAttributes() {
        return molecularAttributes;
    }

    @Override
    public void setMolecularAttributes(MolecularAttributesEntity molecularAttributes) {
        this.molecularAttributes = molecularAttributes;
    }
    
    @Override
    public List<CaseLevelVariantEntity> getCaseLevelData() {
        return caseLevelData;
    }
    
    @Override
    public void setCaseLevelData(List<CaseLevelVariantEntity> caseLevelData) {
        this.caseLevelData = caseLevelData;
    }
    
    @Override
    public VariantLevelDataEntity getVariantLevelData() {
        return variantLevelData;
    }
    
    @Override
    public void setVariantLevelData(VariantLevelDataEntity variantLevelData) {
        this.variantLevelData = variantLevelData;
    }
    
    @Override
    public List<PopulationFrequenciesEntity> getFrequencyInPopulations() {
        return frequencyInPopulations;
    }

    @Override
    public void setFrequencyInPopulations(List<PopulationFrequenciesEntity> frequencyInPopulations) {
        this.frequencyInPopulations = frequencyInPopulations;
    }

}
