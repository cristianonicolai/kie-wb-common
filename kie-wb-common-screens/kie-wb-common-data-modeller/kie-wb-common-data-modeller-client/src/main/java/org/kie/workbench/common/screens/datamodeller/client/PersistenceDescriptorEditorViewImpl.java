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

package org.kie.workbench.common.screens.datamodeller.client;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.HelpBlock;
import org.gwtbootstrap3.client.ui.PanelBody;
import org.gwtbootstrap3.client.ui.PanelCollapse;
import org.gwtbootstrap3.client.ui.PanelGroup;
import org.gwtbootstrap3.client.ui.PanelHeader;
import org.gwtbootstrap3.client.ui.RadioButton;
import org.gwtbootstrap3.client.ui.TextBox;
import org.kie.workbench.common.screens.datamodeller.client.pdescriptor.PersistenceUnitPropertyGrid;
import org.kie.workbench.common.screens.datamodeller.client.pdescriptor.ProjectClassList;
import org.kie.workbench.common.screens.datamodeller.client.pdescriptor.XMLViewer;
import org.kie.workbench.common.screens.datamodeller.model.persistence.PersistenceDescriptorEditorContent;
import org.kie.workbench.common.screens.datamodeller.model.persistence.PersistenceDescriptorModel;
import org.kie.workbench.common.widgets.metadata.client.KieEditorViewImpl;

public class PersistenceDescriptorEditorViewImpl
        extends KieEditorViewImpl
        implements PersistenceDescriptorEditorView {

    interface PersistenceDescriptorEditorViewBinder
            extends
            UiBinder<Widget, PersistenceDescriptorEditorViewImpl> {

    }

    private static PersistenceDescriptorEditorViewBinder uiBinder = GWT.create( PersistenceDescriptorEditorViewBinder.class );

    @UiField
    TextBox persistenceUnitTextBox;

    @UiField
    HelpBlock persistenceUnitHelpInline;

    @UiField
    TextBox persistenceProviderTextBox;

    @UiField
    HelpBlock persistenceProviderHelpInline;

    @UiField
    TextBox datasourceTextBox;

    @UiField
    HelpBlock datasourceHelpInline;

    @UiField
    RadioButton transactionTypeJTARadioButton;

    @UiField
    RadioButton transactionTypeResourceLocalRadioButton;

    @UiField
    HelpBlock transactionTypeHelpInline;

    @UiField
    PanelGroup accordion1;

    @UiField
    PanelHeader header1;

    @UiField
    PanelCollapse collapse1;

    @UiField
    PanelBody propertiesGridPanel;

    @Inject
    PersistenceUnitPropertyGrid persistenceUnitProperties;

    @UiField
    PanelGroup accordion2;

    @UiField
    PanelHeader header2;

    @UiField
    PanelCollapse collapse2;

    @UiField
    PanelBody persistenceUnitClassesPanel;

    @Inject
    ProjectClassList persistenceUnitClasses;

    @Inject
    private XMLViewer xmlViewer;

    PersistenceDescriptorEditorContent content;

    PersistenceDescriptorModel model;

    Presenter presenter;

    public PersistenceDescriptorEditorViewImpl() {
        initWidget( uiBinder.createAndBindUi( this ) );
    }

    @PostConstruct
    void init() {
        accordion1.setId( DOM.createUniqueId() );
        header1.setDataParent( accordion1.getId() );
        header1.setDataTargetWidget( collapse1 );

        accordion2.setId( DOM.createUniqueId() );
        header2.setDataParent( accordion2.getId() );
        header2.setDataTargetWidget( collapse2 );

        propertiesGridPanel.add( persistenceUnitProperties );
        persistenceUnitClassesPanel.add( persistenceUnitClasses );
    }

    @Override
    public void setPersistenceUnitName( String persistenceUnitName ) {
        persistenceUnitTextBox.setText( persistenceUnitName );
    }

    @Override
    public void setPersistenceProvider( String persistenceProvider ) {
        persistenceProviderTextBox.setText( persistenceProvider );
    }

    @Override
    public void setJTADataSource( String jtaDataSource ) {
        datasourceTextBox.setText( jtaDataSource );
    }

    @Override
    public boolean getJTATransactions() {
        return transactionTypeJTARadioButton.getValue();
    }

    @Override
    public void setJTATransactions( boolean jtaTransactions ) {
        transactionTypeJTARadioButton.setValue( jtaTransactions );
    }

    @Override
    public boolean getResourceLocalTransactions() {
        return transactionTypeResourceLocalRadioButton.getValue();
    }

    @Override
    public void setResourceLocalTransactions( boolean resourceLocalTransactions ) {
        transactionTypeResourceLocalRadioButton.setValue( resourceLocalTransactions );
    }

    @Override
    public void setResourceLocalTransactionsVisible( boolean visible ) {
        transactionTypeResourceLocalRadioButton.setVisible( visible );
    }

    @Override
    public void setTransactionTypeHelpMessage( String message ) {
        transactionTypeHelpInline.setText( message );
    }

    @Override
    public void setSource( String source ) {
        xmlViewer.setContent( source );
    }

    @Override
    public void setPresenter( Presenter presenter ) {
        this.presenter = presenter;
        persistenceUnitClasses.addLoadClassesHandler( presenter );
    }

    @Override
    public Widget getSourceEditor() {
        return xmlViewer;
    }

    @Override
    public PersistenceUnitPropertyGrid getPersistenceUnitProperties() {
        return persistenceUnitProperties;
    }

    @Override
    public ProjectClassList getPersistenceUnitClasses() {
        return persistenceUnitClasses;
    }

    @Override
    public void redraw() {
        persistenceUnitProperties.redraw();
        persistenceUnitClasses.redraw();
    }

    @Override
    public void clear() {
        setPersistenceUnitName( null );
        setPersistenceProvider( null );
        setJTADataSource( null );
        setJTATransactions( false );
        setResourceLocalTransactions( false );
        setResourceLocalTransactionsVisible( false );
        setTransactionTypeHelpMessage( null );
    }

    @Override
    public void setReadOnly( boolean readOnly ) {
        persistenceUnitTextBox.setReadOnly( readOnly );
        persistenceProviderTextBox.setReadOnly( readOnly );
        datasourceTextBox.setReadOnly( readOnly );
        transactionTypeJTARadioButton.setEnabled( !readOnly );
        transactionTypeResourceLocalRadioButton.setEnabled( !readOnly );
        persistenceUnitProperties.setReadOnly( readOnly );
        persistenceUnitClasses.setReadOnly( readOnly );
    }

    @UiHandler("persistenceUnitTextBox")
    void onPersistenceUnitChanged( ValueChangeEvent<String> event ) {
        presenter.onPersistenceUnitNameChanged( event.getValue() );
    }

    @UiHandler("persistenceProviderTextBox")
    void onPersistenceProviderChanged( ChangeEvent event ) {
        presenter.onPersistenceProviderChanged( persistenceProviderTextBox.getText() );
    }

    @UiHandler("datasourceTextBox")
    void onJTADataSourceChanged( ChangeEvent event ) {
        presenter.onJTADataSourceChanged( datasourceTextBox.getValue() );
    }

    @UiHandler("transactionTypeResourceLocalRadioButton")
    void onTransactionTypeResourceLocalRadioButtonChanged( ClickEvent event ) {
        presenter.onJTATransactionsChanged();
    }

    @UiHandler("transactionTypeJTARadioButton")
    void onTransactionTypeJTARadioButtonChanged( ClickEvent event ) {
        presenter.onResourceLocalTransactionsChanged();
    }
}