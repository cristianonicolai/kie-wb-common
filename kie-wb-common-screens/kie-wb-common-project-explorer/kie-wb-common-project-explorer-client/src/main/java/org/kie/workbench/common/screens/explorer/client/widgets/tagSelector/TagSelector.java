/*
 * Copyright 2014 JBoss Inc
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

package org.kie.workbench.common.screens.explorer.client.widgets.tagSelector;

import java.util.Set;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.kie.workbench.common.screens.explorer.client.resources.i18n.ProjectExplorerConstants;

public class TagSelector
        implements IsWidget {

    private TagSelectorView view;

    @Inject
    protected Event<TagChangedEvent> tagChangedEvent;

    @Inject
    public TagSelector( TagSelectorView view ) {
        this.view = view;
        view.setPresenter(this);
    }

    public void loadContent(Set<String> tags, String currentTag) {
        view.clear();
        if (currentTag == null) currentTag = ProjectExplorerConstants.INSTANCE.none();
        view.addTag( ProjectExplorerConstants.INSTANCE.none() );
        view.setCurrentTag(currentTag);
        for (String tag : tags) {
            view.addTag( tag );
        }
    }

    public void selectTag(String tag) {
        view.setCurrentTag(tag);
        if (tag.equals( ProjectExplorerConstants.INSTANCE.none())) tag = null;
        tagChangedEvent.fire( new TagChangedEvent( tag ) );
    }

    @Override
    public Widget asWidget() {
        return view.asWidget();
    }

    public void show() {
        view.show();
    }

    public void hide() {
        view.hide();
    }
}
