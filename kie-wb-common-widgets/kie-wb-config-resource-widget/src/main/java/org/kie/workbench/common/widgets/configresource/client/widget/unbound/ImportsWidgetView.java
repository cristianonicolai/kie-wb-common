/*
 * Copyright 2015 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.kie.workbench.common.widgets.configresource.client.widget.unbound;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import org.drools.workbench.models.datamodel.imports.Import;
import org.guvnor.common.services.project.model.ProjectImports;
import org.uberfire.client.mvp.UberView;
import org.uberfire.ext.widgets.common.client.common.HasBusyIndicator;

public interface ImportsWidgetView
        extends HasBusyIndicator,
                UberView<ImportsWidgetView.Presenter> {

    interface Presenter {

        void setContent( final ProjectImports importTypes,
                         final boolean isReadOnly );

        Widget asWidget();

    }

    void setContent( final List<Import> importTypes,
                     final boolean isReadOnly );

}
