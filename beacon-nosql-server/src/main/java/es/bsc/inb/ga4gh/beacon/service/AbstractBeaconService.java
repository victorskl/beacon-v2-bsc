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
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestParameters;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestQuery;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResponseSummary;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResultset;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResultsets;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResultsetsResponse;
import jakarta.nosql.mapping.Pagination;
import jakarta.nosql.mapping.Repository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Dmitry Repchevsky
 * 
 * @param <K> Repository interface
 * @param <L> BeaconRequestParameters implementation class
 */

public abstract class AbstractBeaconService<K extends Repository, 
        L extends BeaconRequestParameters> {
    
    public abstract K getRepository();
    
    protected abstract List findEntities(L params, Pagination pagination);
    
    public BeaconResultsetsResponse getBeacon(final String id) {
        try {
            Optional entity = getRepository().findById(id);

            BeaconResultsetsResponse response = new BeaconResultsetsResponse();

            if (entity.isEmpty()) {
                response.setResponseSummary(new BeaconResponseSummary(false));
            } else {
                response.setResponseSummary(new BeaconResponseSummary(1));

                BeaconResultset resultset = new BeaconResultset();
                resultset.setExists(true);
                resultset.setResultsCount(1);
                resultset.setResults(Arrays.asList(entity.get()));

                BeaconResultsets resultsets = new BeaconResultsets();
                resultsets.setResultSets(Arrays.asList(resultset));

                response.setResponse(resultsets);
            }
            return response;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    public BeaconResultsetsResponse getBeacon(final BeaconRequestBody request) {
        try {
            final Pagination pagination = getPagination(request);
            
            final BeaconRequestQuery<L> request_query = request.getQuery();
            final L params = request_query == null ? null : 
                    request_query.getRequestParameters();
          
            final List beacons = findEntities(params, pagination);

            return makeResponse(beacons);
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
    protected Pagination getPagination(final BeaconRequestBody request) {
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
    
    protected BeaconResultsetsResponse makeResponse(List beacons) {
        BeaconResultsetsResponse response = new BeaconResultsetsResponse();

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

            response.setResponse(resultsets);
        }
        return response;
    }
    
}
