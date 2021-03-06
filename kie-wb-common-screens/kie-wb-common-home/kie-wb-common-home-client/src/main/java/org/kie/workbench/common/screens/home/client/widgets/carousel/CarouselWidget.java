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

package org.kie.workbench.common.screens.home.client.widgets.carousel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import org.gwtbootstrap3.client.ui.Carousel;
import org.gwtbootstrap3.client.ui.CarouselInner;

/**
 * A Carousel
 */
public class CarouselWidget extends Composite {

    interface CarouselBinder
            extends
            UiBinder<Carousel, CarouselWidget> {

    }

    private static CarouselBinder uiBinder = GWT.create( CarouselBinder.class );

    @UiField
    CarouselInner carousel;

    public CarouselWidget() {
        initWidget( uiBinder.createAndBindUi( this ) );
    }

    public void addCarouselEntry( final CarouselEntryWidget entry ) {
        carousel.add( entry );
    }

}
