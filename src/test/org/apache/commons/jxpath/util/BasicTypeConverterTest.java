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
package org.apache.commons.jxpath.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.jxpath.NodeSet;
import org.apache.commons.jxpath.Pointer;

/**
 * Tests BasicTypeConverter
 * 
 * @author Dmitri Plotnikov
 * @version $Revision: 1.6 $ $Date: 2004/02/29 14:17:46 $
 */

public class BasicTypeConverterTest extends TestCase {
    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public BasicTypeConverterTest(String name) {
        super(name);
    }

    public void testPrimitiveToString() {
        assertConversion(new Integer(1), String.class, "1");
    }

    public void testArrayToList() {
        assertConversion(
            new int[] { 1, 2 },
            List.class,
            Arrays.asList(new Object[] { new Integer(1), new Integer(2)}));
    }

    public void testArrayToArray() {
        assertConversion(
            new int[] { 1, 2 },
            String[].class,
            Arrays.asList(new String[] { "1", "2" }));
    }

    public void testListToArray() {
        assertConversion(
            Arrays.asList(new Integer[] { new Integer(1), new Integer(2)}),
            String[].class,
            Arrays.asList(new String[] { "1", "2" }));

        assertConversion(
            Arrays.asList(new String[] { "1", "2" }),
            int[].class,
            Arrays.asList(new Integer[] { new Integer(1), new Integer(2)}));
    }

    public void testInvalidConversion() {
        boolean exception = false;
        try {
            TypeUtils.convert("'foo'", Date.class);
        }
        catch (Throwable ex) {
            exception = true;
        }
        assertTrue("Type conversion exception", exception);
    }

    public void assertConversion(Object from, Class toType, Object expected) {
        boolean can = TypeUtils.canConvert(from, toType);
        assertTrue("Can convert: " + from.getClass() + " to " + toType, can);
        Object result = TypeUtils.convert(from, toType);
        if (result.getClass().isArray()) {
            ArrayList list = new ArrayList();
            for (int j = 0; j < Array.getLength(result); j++) {
                list.add(Array.get(result, j));
            }
            result = list;
        }
        assertEquals(
            "Convert: " + from.getClass() + " to " + toType,
            expected,
            result);
    }
    
    public void testSingletonCollectionToString() {
        assertConversion(Collections.singleton("Earth"), String.class, "Earth");
    }

    public void testSingletonArrayToString() {
        assertConversion(new String[] { "Earth" }, String.class, "Earth");
    }

    public void testPointerToString() {
        assertConversion(new Pointer() {
            public Object getValue() {
                return "value";
            }
            public Object getNode() {
                return null;
            }
            public void setValue(Object value) {
            }
            public Object getRootNode() {
                return null;
            }
            public String asPath() {
                return null;
            }
            public Object clone() {
                return null;
            }
            public int compareTo(Object o) {
                return 0;
            }
        }, String.class, "value");
    }

    public void testNodeSetToString() {
        assertConversion(new NodeSet() {
            public List getNodes() {
                return null;
            }
            public List getPointers() {
                return null;
            }
            public List getValues() {
                List list = new ArrayList();
                list.add("hello");
                list.add("goodbye");
                return Collections.singletonList(list);
            }
        }, String.class, "hello");
    }

    // succeeds in current version
    public void testNodeSetToInteger() {
        assertConversion(new NodeSet() {
            public List getNodes() {
                return null;
            }
            public List getPointers() {
                return null;
            }
            public List getValues() {
                return Collections.singletonList("9");
            }
        }, Integer.class, new Integer(9));
    }    
}