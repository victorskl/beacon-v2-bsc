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

import es.bsc.inb.ga4gh.beacon.framework.model.v200.common.Granularity;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.common.SchemaPerEntity;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.common.SchemaReference;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.configuration.BeaconConfiguration;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.configuration.BeaconSecurityAttributes;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.configuration.ServiceConfiguration;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconQueryFilter;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestBody;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestMeta;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestParameters;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestQuery;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconCollectionsResponse;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconInformationalResponseMeta;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconReceivedRequestSummary;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResponseMeta;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResponseSummary;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResultset;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResultsets;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResultsetsResponse;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.EntryTypeDefinition;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;
import jakarta.nosql.mapping.Pagination;
import jakarta.nosql.mapping.Repository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Dmitry Repchevsky
 * 
 * @param <K> Repository interface
 * @param <L> BeaconRequestParameters implementation class
 */

public abstract class AbstractBeaconService<K extends Repository, 
        L extends BeaconRequestParameters> {
    
    @Inject BeanManager manager;
    
    @Inject
    private ServiceConfiguration configuration;

    private String beaconId;
    private String apiVersion;
    private String defaultGranularity;
    private String entry_type;
    private SchemaPerEntity defaultSchema;
    
    @PostConstruct
    public void init() {
        BeaconInformationalResponseMeta meta = configuration.getMeta();
        if (meta != null) {
            beaconId = meta.getBeaconId();
            apiVersion = meta.getApiVersion();
        }
        
        final BeaconConfiguration config = configuration.getResponse();
        if (config != null) {
            final BeaconSecurityAttributes security_attributes = config.getSecurityAttributes();
            if (security_attributes != null) {
                defaultGranularity = security_attributes.getDefaultGranularity();
            }
            
            final Map<String, EntryTypeDefinition> entry_types = config.getEntryTypes();
            if (entry_types != null) {
                final String name = manager.resolve(manager.getBeans(this.getClass())).getName();
                final EntryTypeDefinition entry_type_definition = entry_types.get(name);
                if (entry_type_definition != null) {
                    entry_type = entry_type_definition.getId();
                    final SchemaReference schema_ref = entry_type_definition.getDefaultSchema();
                    if (schema_ref != null) {
                        defaultSchema = new SchemaPerEntity();
                        defaultSchema.setSchema(schema_ref.getReferenceToSchemaDefinition());
                        defaultSchema.setEntityType(entry_type_definition.getId());
                    }
                }
            }
        }
    }

    public abstract K getRepository();

    protected long countEntities(L params, Pagination pagination) {
        return getRepository().count();
    }

    protected abstract List findEntities(L params, Pagination pagination);
    
    public BeaconResultsetsResponse getBeacon(
            String id, BeaconRequestBody request) {

        try {
            Optional entity = getRepository().findById(id);
            if (entity.isEmpty()) {
                return makeResultsetsResponse(Collections.EMPTY_LIST, request);
            }
            
            return makeResultsetsResponse(Arrays.asList(entity.get()), request);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    public BeaconResultsetsResponse getBeacon(BeaconRequestBody request) {
        try {
            final Pagination pagination = getPagination(request);
            
            final BeaconRequestQuery<L> request_query = request.getQuery();

            final L params = request_query == null ? null : 
                    request_query.getRequestParameters();
          
            final Granularity granularity = getGranularity(request);
            if (granularity == null || granularity == Granularity.RECORD) {
                final List beacons = findEntities(params, pagination);
                return makeResultsetsResponse(beacons, request);
            }
            
            if (granularity == Granularity.COUNT) {
                final long count = countEntities(params, pagination);
                return makeResultsetsResponse(count, request);
            }
            
            if (granularity == Granularity.BOOLEAN) {
                final long count = countEntities(params, pagination);
                return makeResultsetsResponse(count > 0, request);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    /**
     * Auxiliary method to create NoSQL pagination object from the Beacon request.
     * 
     * @param request Beacon request object
     * @return Jakarta NoSQL pagination object or null
     */
    protected Pagination getPagination(BeaconRequestBody request) {
        if (request != null) {
            final BeaconRequestQuery<L> request_query = request.getQuery();
            if (request_query != null) {
                es.bsc.inb.ga4gh.beacon.framework.model.v200.common.Pagination pagination = 
                        request_query.getPagination();
                if (pagination != null) {
                    final Integer skip = pagination.getSkip();
                    final Integer limit = pagination.getLimit();

                    return new es.bsc.inb.ga4gh.beacon.nosql.Pagination(
                            skip == null ? 0 : skip, limit == null ? 0 : limit);
                }
            }
        }
        return null;
    }

    /**
     * Auxiliary method to get granularity the Beacon request.
     * If no granularity found in request, defaultGranularity from configuration 
     * is returned.
     * 
     * @param request Beacon request object
     * @return granularity enum
     */    
    protected Granularity getGranularity(BeaconRequestBody request) {
        if (request != null) {
            final BeaconRequestQuery<L> request_query = request.getQuery();
            if (request_query != null) {
                final String granularity = request_query.getGranularity();
                if (granularity != null) {
                    try {
                        return Granularity.getGranularity(granularity);
                    } catch (IllegalArgumentException ex) {}
                }
            }
        }
        if (defaultGranularity != null) {
            try {
                return Granularity.getGranularity(defaultGranularity);
            } catch (IllegalArgumentException ex) {}
        }
        
        return Granularity.COUNT;
    }

    protected BeaconResultsetsResponse makeResultsetsResponse(
            boolean exists, BeaconRequestBody request) {
        BeaconResultsetsResponse response = new BeaconResultsetsResponse();
        response.setResponseSummary(new BeaconResponseSummary(exists));
        response.setMeta(getMeta(request));
        return response;
    }

    protected BeaconResultsetsResponse makeResultsetsResponse(
            long count, BeaconRequestBody request) {
        BeaconResultsetsResponse response = new BeaconResultsetsResponse();

        if (count == 0) {
            response.setResponseSummary(new BeaconResponseSummary(false));
        } else {
            response.setResponseSummary(new BeaconResponseSummary((int)count));

            BeaconResultset resultset = new BeaconResultset();
            resultset.setExists(true);
            resultset.setResultsCount((int)count);

            BeaconResultsets resultsets = new BeaconResultsets();
            resultsets.setResultSets(Arrays.asList(resultset));

            response.setResponse(resultsets);
        }
        response.setMeta(getMeta(request));
        return response;
    }

    protected BeaconResultsetsResponse makeResultsetsResponse(
            List beacons, BeaconRequestBody request) {
        BeaconResultsetsResponse response = new BeaconResultsetsResponse();

        if (beacons.isEmpty()) {
            response.setResponseSummary(new BeaconResponseSummary(false));
        } else {
            response.setResponseSummary(new BeaconResponseSummary(beacons.size()));

            BeaconResultset resultset = new BeaconResultset();
            resultset.setId(entry_type);
            resultset.setSetType(entry_type);
            resultset.setExists(true);
            resultset.setResultsCount(beacons.size());
            resultset.setResults(beacons);

            BeaconResultsets resultsets = new BeaconResultsets();
            resultsets.setResultSets(Arrays.asList(resultset));

            response.setResponse(resultsets);
        }
        response.setMeta(getMeta(request));
        return response;
    }

    protected BeaconCollectionsResponse makeCollectionsResponse(
            List beacons, BeaconRequestBody request) {
        BeaconCollectionsResponse response = new BeaconCollectionsResponse();

        if (beacons.isEmpty()) {
            response.setResponseSummary(new BeaconResponseSummary(false));
        } else {
            response.setResponseSummary(new BeaconResponseSummary(beacons.size()));

            BeaconResultset resultset = new BeaconResultset();
            resultset.setExists(true);
            resultset.setResultsCount(beacons.size());
            resultset.setResults(beacons);

            BeaconResultsets resultsets = new BeaconResultsets();
            resultsets.setResultSets(Arrays.asList(resultset));

            //response.setResponse(resultsets);
        }
        response.setMeta(getMeta(request));
        return response;
    }

    protected BeaconResponseMeta getMeta(BeaconRequestBody request) {
        final BeaconResponseMeta response_meta = new BeaconResponseMeta();

        final BeaconReceivedRequestSummary request_summary = 
                new BeaconReceivedRequestSummary();

        if (request != null) {
            final BeaconRequestMeta request_meta = request.getMeta();
            if (request_meta != null) {
                request_summary.setApiVersion(request_meta.getApiVersion());
                request_summary.setRequestedSchemas(request_meta.getRequestedSchemas());
            }
            
            final BeaconRequestQuery request_query = request.getQuery();  
            if (request_query != null) {
                request_summary.setPagination(request_query.getPagination());
                request_summary.setBeaconRequestParameters(request_query.getRequestParameters());
                
                final List<BeaconQueryFilter> filters = request_query.getFilters();
                if (filters != null) {
                    request_summary.setFilters(filters.stream()
                            .map(BeaconQueryFilter::toString)
                            .collect(Collectors.toList()));
                }

                request_summary.setRequestedGranularity(request_query.getGranularity());
                request_summary.setTestMode(request_query.getTestMode());
            }
        }
        
        if (request_summary.getApiVersion() == null) {
            request_summary.setApiVersion(apiVersion);
        }

        if (request_summary.getRequestedGranularity() == null) {
            request_summary.setRequestedGranularity(defaultGranularity);
        }

        if (request_summary.getRequestedSchemas() == null) {
            request_summary.setRequestedSchemas(Collections.EMPTY_LIST);
        }
        
        response_meta.setReceivedRequestSummary(request_summary);
      
        response_meta.setBeaconId(beaconId);
        response_meta.setApiVersion(apiVersion);

        response_meta.setReturnedGranularity(request_summary.getRequestedGranularity());

        if (defaultSchema != null) {
            response_meta.setReturnedSchemas(Arrays.asList(defaultSchema));
        }

        return response_meta;
    }
}
