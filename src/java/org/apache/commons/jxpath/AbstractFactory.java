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

/**
 * The  {@link JXPathContext#createPath JXPathContext.createPath()} method of
 * JXPathContext can create missing objects as it traverses an XPath; it
 * utilizes an AbstractFactory for that purpose. Install a factory on
 * JXPathContext by calling {@link JXPathContext#setFactory JXPathContext.
 * setFactory()}.
 * <p>
 * All  methods of this class return false.  Override any of them to return true
 * to indicate that the factory has successfully created the described object.
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.8 $ $Date: 2004/02/29 14:17:42 $
 */
public abstract class AbstractFactory {

    /**
     * The  parameters may describe a collection element or an individual
     * object. It is up to the factory to infer which one it is. If it is a
     * collection, the factory should check if the collection exists.  If not,
     * it should create the collection. Then it should create the index'th
     * element of the collection and return true.
     * <p>
     * 
     * @param context can be used to evaluate other XPaths, get to variables
     * etc.
     * @param pointer describes the location of the node to be created
     * @param parent is the object that will server as a parent of the new
     * object
     * @param name is the name of the child of the parent that needs to be
     * created. In the case of DOM may be qualified.
     * @param index is used if the pointer represents a collection element. You
     * may need to expand or even create the collection to accomodate the new
     * element.
     * 
     * @return true if the object was successfully created
     */
    public boolean createObject(JXPathContext context, Pointer pointer, 
                                Object parent, String name, int index) 
    {
        return false;
    }

    /**
     * Declare the specified variable
     * 
     * @param context hosts variable pools. See 
     * {@link JXPathContext#getVariables() JXPathContext.getVariables()}
     * @param name is the name of the variable without the "$" sign
     * 
     * @return true if the variable was successfully defined
     */
    public boolean declareVariable(JXPathContext context, String name) {
        return false;
    }
}