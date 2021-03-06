/**
 * Copyright 2015 JBoss Inc
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.screens.datamodeller.client.context;

public class DataModelerWorkbenchFocusEvent {


    private boolean focused;

    public DataModelerWorkbenchFocusEvent() {
        this.focused = true;
    }

    public DataModelerWorkbenchFocusEvent lostFocus() {
        this.focused = false;
        return this;
    }

    public boolean isFocused() {
        return focused;
    }
}