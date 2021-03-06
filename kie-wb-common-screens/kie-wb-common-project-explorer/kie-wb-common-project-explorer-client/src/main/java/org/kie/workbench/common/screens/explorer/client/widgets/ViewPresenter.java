/*
 * Copyright 2013 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.kie.workbench.common.screens.explorer.client.widgets;

import java.util.Set;

import com.google.gwt.user.client.ui.HasVisibility;
import org.guvnor.common.services.project.context.ProjectContext;
import org.guvnor.common.services.project.model.Project;
import org.guvnor.structure.organizationalunit.OrganizationalUnit;
import org.guvnor.structure.repositories.Repository;
import org.kie.workbench.common.screens.explorer.service.ActiveOptions;
import org.kie.workbench.common.screens.explorer.model.FolderItem;
import org.kie.workbench.common.screens.explorer.model.FolderListing;
import org.kie.workbench.common.screens.explorer.service.Option;

/**
 * Base for the different views
 */
public interface ViewPresenter extends HasVisibility {

    void update( final ActiveOptions options );

    void organizationalUnitSelected( final OrganizationalUnit organizationalUnit );

    void branchChanged( final String branch );

    void repositorySelected( final Repository repository );

    void projectSelected( final Project project );

    void activeFolderItemSelected( final FolderItem folderItem );

    void itemSelected( final FolderItem folderItem );

    void refresh();

    void loadContent( final FolderItem item,
                      final ActiveOptions options );

    ActiveOptions getActiveOptions();

    FolderListing getActiveContent();

    void deleteItem( final FolderItem folderItem );

    void renameItem( final FolderItem folderItem );

    void copyItem( final FolderItem folderItem );

    void uploadArchivedFolder( final FolderItem folderItem );

    String getCurrentTag();

    Set<String> getActiveContentTags();

    void initialiseViewForActiveContext( final ProjectContext context );

    void initialiseViewForActiveContext( final String initPath );

}
