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

import jakarta.nosql.ValueReader;
import java.util.List;

/**
 * @author Dmitry Repchevsky
 */

public class IntegerArrayReader implements ValueReader {

    @Override
    public boolean test(Class<?> type) {
        return type.isArray() && int.class.isAssignableFrom(type.getComponentType());
    }

    @Override
    public <T> T read(Class<T> clazz, Object value) {

        if (test(value.getClass())) {
            return (T) value;
        }

        if (List.class.isInstance(value)) {
            final List<Number> list = (List)value;
            final int[] arr = new int[list.size()];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = list.get(i).intValue();
            }
            return (T)arr;
        }
        
        return null;
    }
}
