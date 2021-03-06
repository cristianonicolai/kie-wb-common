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

package org.kie.workbench.common.screens.datamodeller.client.widgets.advanceddomain;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.kie.workbench.common.screens.datamodeller.client.DataModelerContext;
import org.kie.workbench.common.screens.datamodeller.client.widgets.common.domain.FieldEditor;
import org.kie.workbench.common.services.datamodeller.core.Annotation;
import org.kie.workbench.common.services.datamodeller.core.DataObject;
import org.kie.workbench.common.services.datamodeller.core.ElementType;
import org.kie.workbench.common.services.datamodeller.core.ObjectProperty;

@Dependent
public class AdvancedDataObjectFieldEditor
        extends FieldEditor
        implements AdvancedDataObjectFieldEditorView.Presenter {

    private AdvancedDataObjectFieldEditorView view;

    public AdvancedDataObjectFieldEditor() {
    }

    @Inject
    public AdvancedDataObjectFieldEditor( AdvancedDataObjectFieldEditorView view ) {
        this.view = view;
        view.setPresenter( this );
        initWidget( view.asWidget() );
    }

    @Override
    public String getName() {
        return "ADVANCED_FIELD_EDITOR";
    }

    @Override
    public String getDomainName() {
        return AdvancedDomainEditor.ADVANCED_DOMAIN;
    }

    @Override
    protected void loadDataObjectField( DataObject dataObject, ObjectProperty objectField ) {
        clean();
        setReadonly( context != null ? context.isReadonly() : true );
        view.setReadonly( isReadonly() );
        this.dataObject = dataObject;
        this.objectField = objectField;
        if ( dataObject != null && objectField != null ) {
            view.loadAnnotations( objectField.getAnnotations() );
        }
    }

    @Override
    public void onDeleteAnnotation( Annotation annotation ) {
        commandBuilder.buildFieldAnnotationRemoveCommand( getContext(),
                getName(),
                getDataObject(),
                getObjectField(),
                annotation.getClassName() ).execute();
        view.removeAnnotation( annotation );
    }

    @Override
    public void onValuePairChanged( String annotationClassName, String valuePair, Object newValue ) {
        commandBuilder.buildFieldAnnotationValueChangeCommand( getContext(),
                getName(),
                getDataObject(),
                getObjectField(),
                annotationClassName,
                valuePair,
                newValue,
                false ).execute();
        //TODO provide a way for refreshing only the changed annotation
        refresh();
    }

    @Override
    public void onClearValuePair( Annotation annotation, String valuePair ) {
        commandBuilder.buildFieldAnnotationValueChangeCommand( getContext(),
                getName(),
                getDataObject(),
                getObjectField(),
                annotation.getClassName(),
                valuePair,
                null,
                false ).execute();
        //TODO provide a way for refreshing only the changed annotation
        refresh();
    }

    @Override
    public void onAddAnnotation( Annotation annotation ) {
        commandBuilder.buildFieldAnnotationAddCommand( getContext(),
                getName(),
                getDataObject(),
                getObjectField(),
                annotation ).execute();
        //TODO provide a way for refreshing only the changed annotation
        refresh();
    }

    public void clean() {
        view.clear();
    }

    @Override
    public void onContextChange( DataModelerContext context ) {
        view.init( context != null ? context.getCurrentProject() : null, ElementType.FIELD );
        view.setReadonly( context != null && context.isReadonly() );
        super.onContextChange( context );
    }

    private void refresh() {
        loadDataObjectField( dataObject, objectField );
    }
}