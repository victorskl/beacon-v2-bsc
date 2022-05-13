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
 *****************************************************************************
 */

package es.bsc.inb.ga4gh.beacon.rest;

import es.bsc.inb.ga4gh.beacon.framework.model.v200.GenomicVariationsRequestParameters;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestBody;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.requests.BeaconRequestQuery;
import es.bsc.inb.ga4gh.beacon.framework.model.v200.responses.BeaconResultsetsResponse;
import es.bsc.inb.ga4gh.beacon.framework.rest.GenomicVariationsEndpointInterface;
import es.bsc.inb.ga4gh.beacon.service.GenomicVariationsService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

/**
 * @author Dmitry Repchevsky
 */

@Path("/")
@RequestScoped
public class GenomicVariationsEndpoint extends AbstractAsyncEndpoint
        implements GenomicVariationsEndpointInterface {

    @Inject 
    private GenomicVariationsService service;

    @Override
    public BeaconResultsetsResponse getGenomicVariations(
            String requested_schema,
            Integer skip,
            Integer limit,
            String include_responses,
            long[] start,
            long[] end,
            String assembly_id,
            String reference_name,
            String reference_bases,
            String alternate_bases,
            Long variant_min_length,
            Long variant_max_length,
            String genomic_allele_short_form,
            String gene_id,
            String aminoacid_change) {

        GenomicVariationsRequestParameters params = new GenomicVariationsRequestParameters();
        params.setStart(start);
        params.setEnd(end);
        params.setAssemblyId(assembly_id);
        params.setReferenceName(reference_name);
        params.setReferenceBases(reference_bases);
        params.setAlternateBases(alternate_bases);
        params.setVariantMinLength(variant_min_length);
        params.setVariantMaxLength(variant_max_length);
        params.setGenomicAlleleShortForm(genomic_allele_short_form);
        params.setGeneId(gene_id);
        params.setAminoacidChange(aminoacid_change);
        
        BeaconRequestQuery query = new BeaconRequestQuery();
        query.setRequestParameters(params);
        
        BeaconRequestBody request = new BeaconRequestBody();
        request.setQuery(query);
        
        return service.getBeacon(request);
    }

    @Override
    public BeaconResultsetsResponse postGenomicVariationsRequest(BeaconRequestBody request) {
        return service.getBeacon(request);
    }

    @Override
    public BeaconResultsetsResponse getOneGenomicVariation(String id) {
        return service.getBeacon(id);
    }

    @Override
    public BeaconResultsetsResponse postOneGenomicVariationRequest(String id, BeaconRequestBody request) {
        return service.getBeacon(id);
    }

    @Override
    public BeaconResultsetsResponse getBiosamples(String id) {
        return service.getOneGenomicVariationBiosamples(id, null);
    }

    @Override
    public BeaconResultsetsResponse postBiosamplesRequest(String id, BeaconRequestBody request) {
        return service.getOneGenomicVariationBiosamples(id, request);
    }

    @Override
    public BeaconResultsetsResponse getIndividuals(String id) {
        return service.getOneGenomicVariationIndividuals(id, null);
    }

    @Override
    public BeaconResultsetsResponse postIndividualsRequest(String id, BeaconRequestBody request) {
        return service.getOneGenomicVariationIndividuals(id, request);
    }

}
