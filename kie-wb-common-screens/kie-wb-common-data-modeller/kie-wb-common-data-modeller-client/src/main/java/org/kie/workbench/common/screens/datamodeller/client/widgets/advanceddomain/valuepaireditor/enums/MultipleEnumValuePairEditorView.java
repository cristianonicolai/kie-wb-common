/*
 * Copyright 2015 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.screens.datamodeller.client.widgets.advanceddomain.valuepaireditor.enums;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import org.kie.workbench.common.screens.datamodeller.client.widgets.advanceddomain.valuepaireditor.HasErrorMessage;
import org.uberfire.commons.data.Pair;

public interface MultipleEnumValuePairEditorView
        extends IsWidget,
        HasErrorMessage {

    String EMPTY_ARRAY = "_EMPTY_ARRAY_";

    interface Presenter {

        void onValueChanged( String valueName, boolean isChecked );

    }

    void setPresenter( Presenter presenter );

    void initItems( List<Pair<String, String>> options );

    void setSelectedValues( List<String> value );

    void setValuePairLabel( String label );

}
