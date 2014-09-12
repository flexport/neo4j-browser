/**
 * Copyright (c) 2002-2014 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.com;

import java.nio.ByteBuffer;

import org.junit.Test;
import org.neo4j.kernel.impl.transaction.xaframework.InMemoryLogBuffer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class TxExtractorTest
{

    @Test
    public void shouldExtractToLogBuffer() throws Exception
    {
        // Given
        InMemoryLogBuffer input = new InMemoryLogBuffer(),
                         output = new InMemoryLogBuffer();

        input.putLong( 1337l );

        TxExtractor extractor = TxExtractor.create( input );

        // When
        extractor.extract( output );

        // Then
        ByteBuffer buf = ByteBuffer.allocate( 128 );
        output.read( buf );
        buf.flip();

        assertThat(buf.getLong(), equalTo(1337l));
    }

}