/*
 * Copyright 1999-2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.jxpath;

import java.util.Iterator;
import java.util.Map;

/**
 * Implements the DynamicPropertyHandler interface for java.util.Map.
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.6 $ $Date: 2004/02/29 14:17:42 $
 */
public class MapDynamicPropertyHandler implements DynamicPropertyHandler {

    private static final String[] STRING_ARRAY = new String[0];

    /**
     * Returns string representations of all keys in the map.
     */
    public String[] getPropertyNames(Object object) {
        Map map = (Map) object;
        String names[] = new String[map.size()];
        Iterator it = map.keySet().iterator();
        for (int i = 0; i < names.length; i++) {
            names[i] = String.valueOf(it.next());
        }
        return names;
    }

    /**
     * Returns the value for the specified key.
     */
    public Object getProperty(Object object, String propertyName) {
        return ((Map) object).get(propertyName);
    }

    /**
     * Sets the specified key value.
     */
    public void setProperty(Object object, String propertyName, Object value) {
        ((Map) object).put(propertyName, value);
    }
}