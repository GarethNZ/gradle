/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.buildinit.plugins.internal;

public interface ScriptBlockBuilder {
    /**
     * Adds a property assignment statement to this block
     */
    void propertyAssignment(String comment, String propertyName, Object propertyValue);

    /**
     * Adds a method invocation statement to this block
     */
    void methodInvocation(String comment, String methodName, Object... methodArgs);

    /**
     * Returns a property expression that can be used as a method argument or property assignment value
     */
    BuildScriptBuilder.Expression propertyExpression(String value);
}