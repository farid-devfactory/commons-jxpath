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
package org.apache.commons.jxpath.ri.model.container;

import java.util.Locale;

import org.apache.commons.jxpath.Container;
import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.model.NodePointer;
import org.apache.commons.jxpath.ri.model.NodePointerFactory;

/**
 * Implements NodePointerFactory for Container objects.
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.6 $ $Date: 2004/02/29 14:17:45 $
 */
public class ContainerPointerFactory implements NodePointerFactory {

    public static final int CONTAINER_POINTER_FACTORY_ORDER = 200;

    public int getOrder() {
        return CONTAINER_POINTER_FACTORY_ORDER;
    }

    public NodePointer createNodePointer(
        QName name,
        Object bean,
        Locale locale) 
    {
        if (bean instanceof Container) {
            return new ContainerPointer((Container) bean, locale);
        }
        return null;
    }

    public NodePointer createNodePointer(
        NodePointer parent,
        QName name,
        Object bean) 
    {
        if (bean instanceof Container) {
            return new ContainerPointer(parent, (Container) bean);
        }
        return null;
    }
}