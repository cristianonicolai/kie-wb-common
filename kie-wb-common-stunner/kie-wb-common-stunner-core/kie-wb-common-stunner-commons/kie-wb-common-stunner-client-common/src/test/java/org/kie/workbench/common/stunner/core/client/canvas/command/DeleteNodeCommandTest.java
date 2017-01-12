/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.stunner.core.client.canvas.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.stunner.core.command.CompositeCommand;
import org.kie.workbench.common.stunner.core.graph.Node;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith( MockitoJUnitRunner.class )
public class DeleteNodeCommandTest extends AbstractCanvasCommandTest {

    private static final String ID = "e1";

    @Mock
    private Node candidate;

    private DeleteNodeCommand tested;

    @Before
    public void setup() throws Exception {
        super.setup();
        when( candidate.getUUID() ).thenReturn( ID );
        this.tested = new DeleteNodeCommand( candidate );
    }

    @Test
    public void testGetGraphCommand() {
        final org.kie.workbench.common.stunner.core.graph.command.impl.SafeDeleteNodeCommand graphCommand =
                ( org.kie.workbench.common.stunner.core.graph.command.impl.SafeDeleteNodeCommand ) tested.newGraphCommand( canvasHandler );
        assertNotNull( graphCommand );
        assertEquals( candidate, graphCommand.getNode() );
    }

    @Test
    public void testGetCanvasCommand() {
        final CompositeCommand canvasCommand =
                ( CompositeCommand ) tested.newCanvasCommand( canvasHandler );
        assertNotNull( canvasCommand );
        assertTrue( canvasCommand instanceof CompositeCommand );
    }
}
