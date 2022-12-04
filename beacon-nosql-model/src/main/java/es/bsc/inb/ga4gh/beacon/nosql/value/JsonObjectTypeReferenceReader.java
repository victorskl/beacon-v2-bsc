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

package es.bsc.inb.ga4gh.beacon.nosql.value;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import jakarta.nosql.TypeReferenceReader;
import jakarta.nosql.TypeSupplier;
import jakarta.nosql.document.Document;
import java.util.Iterator;
import java.util.List;

/**
 * @author Dmitry Repchevsky
 */

public class JsonObjectTypeReferenceReader implements TypeReferenceReader {

    @Override
    public boolean test(TypeSupplier<?> typeReference) {
        return JsonObject.class.equals(typeReference.get());
    }

    @Override
    public <T> T convert(TypeSupplier<T> typeReference, Object obj) {
        if (Iterable.class.isInstance(obj)) {
            return (T)convert(Iterable.class.cast(obj));
        }
        return null;
    }
    
    public static JsonObject convert(Iterable iterable) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        Iterator<Document> iter = iterable.iterator();
        while (iter.hasNext()) {
            final Document doc = iter.next();
            final String name = doc.getName();
            final Object value = doc.get();
            if (List.class.isInstance(value)) {
                final List l = List.class.cast(value);
                if (l.isEmpty()) {
                    builder.add(name, Json.createObjectBuilder().build());
                } else if (l.stream().anyMatch(p -> p instanceof Document)){
                    builder.add(name, convert(l));
                } else {
                    final JsonArrayBuilder ab = Json.createArrayBuilder();
                    for (Object elem : l) {
                        JsonValue v = List.class.isInstance(elem) ?
                                convert(List.class.cast(elem)) : getJsonValue(elem);
                        if (v != null) {
                            ab.add(v);
                        }
                    }
                    builder.add(name, ab);                  
                }
            } else {
               builder.add(name, getJsonValue(value)); 
            }
        }
        return builder.build();
    }

    private static JsonValue getJsonValue(Object value) {
        if (value == null) {
            return JsonValue.NULL;
        } else if (value instanceof String) {
            return Json.createValue(value.toString());
        } else if (value instanceof Double) {
            return Json.createValue(Double.class.cast(value).doubleValue());
        } else if (value instanceof Long) {
            return Json.createValue(Long.class.cast(value).longValue());
        } else if (value instanceof Integer) {
            return Json.createValue(Integer.class.cast(value).intValue());
        } else if (value instanceof Boolean) {
            return Boolean.class.cast(value) ? JsonValue.TRUE : JsonValue.FALSE;
        }
        return null;
    }
}
