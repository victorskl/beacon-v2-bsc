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

import es.bsc.inb.ga4gh.beacon.framework.model.v200.DatasetsRequestParameters;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestBody;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestQuery;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconCollectionsResponse;
import es.bsc.inb.ga4gh.beacon.query.DatasetsRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;
import jakarta.nosql.mapping.Pagination;
import java.util.List;

/**
 * @author Dmitry Repchevsky
 */

@Named("dataset")
@ApplicationScoped
public class DatasetsService 
        extends AbstractBeaconService<DatasetsRepository, DatasetsRequestParameters> {

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private DatasetsRepository datsets_repository;

    @Override
    public DatasetsRepository getRepository() {
        return datsets_repository;
    }

    @Override
    protected List findEntities(DatasetsRequestParameters params, Pagination pagination) {
        return pagination == null ? datsets_repository.findAll() : datsets_repository.findAll(pagination);
    }
    
    public BeaconCollectionsResponse getDatasets(BeaconRequestBody request) {
        try {
            final Pagination pagination = getPagination(request);
            
            final BeaconRequestQuery<DatasetsRequestParameters> request_query = request.getQuery();
            final DatasetsRequestParameters params = request_query == null ? null : 
                    request_query.getRequestParameters();
          
            final List beacons = findEntities(params, pagination);

            return makeCollectionsResponse(beacons, request);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }
}
