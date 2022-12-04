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

package es.bsc.inb.ga4gh.beacon.query;

import es.bsc.inb.ga4gh.beacon.nosql.VariantEntity;
import javax.enterprise.context.ApplicationScoped;
import jakarta.nosql.mapping.Pagination;
import jakarta.nosql.mapping.Param;
import jakarta.nosql.mapping.Query;
import jakarta.nosql.mapping.Repository;
import java.util.List;
import java.util.Optional;

/**
 * @author Dmitry Repchevsky
 */

@ApplicationScoped
public interface VariantsRepository extends Repository<VariantEntity, String> {
    
    /**
     *
     * @param variantInternalId
     * @return
     */
    @Override
    @Query("select * from Variants where variantInternalId = @variantInternalId")
    public Optional<VariantEntity> findById(@Param("variantInternalId") String variantInternalId);
    
    List<VariantEntity> findAll();
    
    List<VariantEntity> findAll(Pagination pagination);
    
    @Query("select * from Variants where molecularAttributes.geneIds = @geneId")
    List<VariantEntity> find(@Param("geneId") String geneId);
 
    @Query("select * from Variants where caseLevelData.analysisId = @analysisId")
    List<VariantEntity> findByAnalisisId(@Param("analysisId") String analysisId);

    @Query("select * from Variants where caseLevelData.analysisId = @analysisId")
    List<VariantEntity> findByAnalisisId(@Param("analysisId") String analysisId, Pagination pagination);
    
    @Query("select * from Variants where caseLevelData.biosampleId = @biosampleId")
    List<VariantEntity> findByBiosampleId(@Param("biosampleId") String biosampleId);

    @Query("select * from Variants where caseLevelData.biosampleId = @biosampleId")
    List<VariantEntity> findByBiosampleId(@Param("biosampleId") String biosampleId, Pagination pagination);

    @Query("select * from Variants where caseLevelData.individualId = @individualId")
    List<VariantEntity> findByIndividualId(@Param("individualId") String individualId);

    @Query("select * from Variants where caseLevelData.individualId = @individualId")
    List<VariantEntity> findByIndividualId(@Param("individualId") String individualId, Pagination pagination);

    @Query("select * from Variants where caseLevelData.runId = @runId")
    List<VariantEntity> findByRunId(@Param("runId") String runId);

    @Query("select * from Variants where caseLevelData.runId = @runId")
    List<VariantEntity> findByRunId(@Param("runId") String runId, Pagination pagination);
    
}
