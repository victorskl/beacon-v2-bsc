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

package es.bsc.inb.ga4gh.beacon.service;

import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestBody;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResultsetsResponse;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.GenomicVariationsRequestParameters;
import es.bsc.inb.ga4gh.beacon.nosql.BiosampleEntity;
import es.bsc.inb.ga4gh.beacon.nosql.CaseLevelVariantEntity;
import es.bsc.inb.ga4gh.beacon.nosql.IndividualEntity;
import es.bsc.inb.ga4gh.beacon.nosql.VariantEntity;
import es.bsc.inb.ga4gh.beacon.query.BiosamplesRepository;
import es.bsc.inb.ga4gh.beacon.query.IndividualsRepository;
import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;
import java.util.List;
import es.bsc.inb.ga4gh.beacon.query.VariantsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.document.DocumentQuery.DocumentFrom;
import jakarta.nosql.document.DocumentQuery.DocumentWhere;
import jakarta.nosql.mapping.Pagination;
import jakarta.nosql.mapping.document.DocumentTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Dmitry Repchevsky
 */

@ApplicationScoped
public class GenomicVariationsService 
        extends AbstractBeaconService<VariantsRepository, GenomicVariationsRequestParameters>{

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private VariantsRepository variants_repository;

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private BiosamplesRepository biosamples_repository;

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private IndividualsRepository individuals_repository;

    @Inject
    private DocumentTemplate template;
    
    @Override
    public VariantsRepository getRepository() {
        return variants_repository;
    }
    
    @Override
    protected List findEntities(GenomicVariationsRequestParameters params, 
            Pagination pagination) {

        if (params == null && pagination == null) {
            return variants_repository.findAll();
        } else if (params == null) {
            return variants_repository.findAll(pagination);
        }

        DocumentQuery.DocumentFrom from = DocumentQuery.select().from("Variants");
        
        DocumentWhere query = null;
        
        query = addEqCondition(from, query, "variantType", params.getVariantType());
        
        query = addEqCondition(from, query, "referenceBases", params.getReferenceBases());
        query = addEqCondition(from, query, "alternateBases", params.getAlternateBases());

        query = addEqCondition(from, query, "position.assemblyId", params.getAssemblyId());
        query = addEqCondition(from, query, "position.refseqId", params.getReferenceName());
        
        final long[] start = params.getStart();
        if (start != null && start.length > 0) {
            query = addGteCondition(from, query, "position.start", start[0]);
            if (start.length > 1) {
                query = addLteCondition(from, query, "position.start", start[1]);
            }
        }

        final long[] end = params.getEnd();
        if (end != null && end.length > 0) {
            if (end.length > 1) {
                query = addGteCondition(from, query, "position.end", end[0]);
                query = addLteCondition(from, query, "position.end", end[1]);
            } else {
                query = addLteCondition(from, query, "position.end", end[0]);
            }
        }

        query = addEqCondition(from, query, "molecularAttributes.geneIds", params.getGeneId());
        query = addEqCondition(from, query, "molecularAttributes.aminoacidChanges", params.getAminoacidChange());
        
        if (query == null) {
            return pagination == null ? variants_repository.findAll() :
                                        variants_repository.findAll(pagination);
        }
        
        if (pagination != null) {
            final long skip = pagination.getSkip();
            if (skip > 0) {
                query.skip(skip);
            }
            final long limit = pagination.getLimit();
            if (limit > 0) {
                query.limit(limit);
            }
        }

        return template.select(query.build()).collect(Collectors.toList());
    }

    public BeaconResultsetsResponse getOneGenomicVariationBiosamples(String id, BeaconRequestBody request) {

        final Optional<VariantEntity> variant = variants_repository.findById(id);
        if (variant == null || variant.isEmpty()) {
            return makeResponse(Collections.EMPTY_LIST);
        }
        
        final List<CaseLevelVariantEntity> data = variant.get().getCaseLevelData();
        if (data == null || data.isEmpty()) {
            return makeResponse(Collections.EMPTY_LIST);
        }

        final List<BiosampleEntity> biosamples = new ArrayList();
        
        final Pagination pagination = getPagination(request);
        final int start = pagination == null ? 0 : (int)pagination.getSkip();
        final int end = pagination == null ? data.size() : (int)pagination.getLimit();

        for (int i = start; i < end; i++) {
            final CaseLevelVariantEntity entity = data.get(i);
            final String biosampleId = entity.getBiosampleId();
            if (biosampleId != null) {
                final Optional<BiosampleEntity> biosample = 
                        biosamples_repository.findById(id);
                if (biosample != null && biosample.isPresent()) {
                    biosamples.add(biosample.get());
                }
            }
        }
        return makeResponse(biosamples);
    }

    public BeaconResultsetsResponse getOneGenomicVariationIndividuals(String id, BeaconRequestBody request) {

        final Optional<VariantEntity> variant = variants_repository.findById(id);
        if (variant == null || variant.isEmpty()) {
            return makeResponse(Collections.EMPTY_LIST);
        }
        
        final List<CaseLevelVariantEntity> data = variant.get().getCaseLevelData();
        if (data == null || data.isEmpty()) {
            return makeResponse(Collections.EMPTY_LIST);
        }

        final List<IndividualEntity> individuals = new ArrayList();
        
        final Pagination pagination = getPagination(request);
        final int start = pagination == null ? 0 : (int)pagination.getSkip();
        final int end = pagination == null ? data.size() : (int)pagination.getLimit();

        for (int i = start; i < end; i++) {
            final CaseLevelVariantEntity entity = data.get(i);
            final String individualId = entity.getIndividualId();
            if (individualId != null) {
                final Optional<IndividualEntity> individual = 
                        individuals_repository.findById(id);
                if (individual != null && individual.isPresent()) {
                    individuals.add(individual.get());
                }
            }
        }
        return makeResponse(individuals);
    }
    
    private DocumentWhere addEqCondition(DocumentFrom from, DocumentWhere where, String field, Object obj) {
        if (obj != null) {
            return where == null ? from.where(field).eq(obj) : where.and(field).eq(obj);
        }
        return where;
    }

    private DocumentWhere addGteCondition(DocumentFrom from, DocumentWhere where, String field, Object obj) {
        return where == null ? from.where(field).gte(obj) : where.and(field).gte(obj);
    }

    private DocumentWhere addLteCondition(DocumentFrom from, DocumentWhere where, String field, Object obj) {
        return where == null ? from.where(field).lte(obj) : where.and(field).lte(obj);
    }
}
