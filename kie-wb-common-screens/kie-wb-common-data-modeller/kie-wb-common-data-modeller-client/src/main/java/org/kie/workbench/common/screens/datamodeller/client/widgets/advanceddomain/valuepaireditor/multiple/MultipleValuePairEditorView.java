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

package org.kie.workbench.common.screens.datamodeller.client.widgets.advanceddomain.valuepaireditor.multiple;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import org.kie.workbench.common.screens.datamodeller.client.widgets.advanceddomain.valuepaireditor.HasErrorMessage;
import org.kie.workbench.common.screens.datamodeller.client.widgets.advanceddomain.valuepaireditor.ValuePairEditor;
import org.kie.workbench.common.services.datamodeller.core.AnnotationValuePairDefinition;

public interface MultipleValuePairEditorView
        extends IsWidget,
                HasErrorMessage {

    interface Presenter {

        void init( AnnotationValuePairDefinition valuePairDefinition );

        void onValueChanged( Integer itemId );

        void onRemoveItem( Integer itemId );

        void onAddItem();

        ValuePairEditor<?> createValuePairEditor( AnnotationValuePairDefinition valuePairDefinition );

    }

    void setPresenter( Presenter presenter );

    void init( AnnotationValuePairDefinition valuePairDefinition );

    void setValuePairLabel( String valuePairLabel );

    void removeItem( Integer itemId );

    ValuePairEditor<?> getItemEditor( Integer itemId );

    List<ValuePairEditor<?>> getItemEditors();

    ValuePairEditor<?> getAddItemEditor();

    Integer addItemEditor( ValuePairEditor<?> valuePairEditor );

    void clear();

}
