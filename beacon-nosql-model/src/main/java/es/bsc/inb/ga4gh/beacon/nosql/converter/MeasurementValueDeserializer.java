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

package es.bsc.inb.ga4gh.beacon.nosql.converter;

import es.bsc.inb.ga4gh.beacon.nosql.ComplexValueEntity;
import es.bsc.inb.ga4gh.beacon.nosql.MeasurementValueEntity;
import es.bsc.inb.ga4gh.beacon.nosql.OntologyTermValueEntity;
import es.bsc.inb.ga4gh.beacon.nosql.QuantityEntity;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import java.lang.reflect.Type;

/**
 * @author Dmitry Repchevsky
 */

public class MeasurementValueDeserializer implements JsonbDeserializer<MeasurementValueEntity> {

    private static final Jsonb jsonb = JsonbBuilder.create();
    
    @Override
    public MeasurementValueEntity deserialize(JsonParser parser, DeserializationContext ctx, Type type) {
        
        final JsonValue value = parser.getValue();
        if (JsonValue.ValueType.OBJECT == value.getValueType()) {
            final JsonObject obj = value.asJsonObject();
            if (obj.containsKey(("typedQuantities"))) {
                return jsonb.fromJson(value.toString(), ComplexValueEntity.class);
            }
            if (obj.containsKey(("id"))) {
                return jsonb.fromJson(value.toString(), OntologyTermValueEntity.class);
            }
            if (obj.containsKey(("unit"))) {
                return jsonb.fromJson(value.toString(), QuantityEntity.class);
            }
        }
        return null;
    }
}
