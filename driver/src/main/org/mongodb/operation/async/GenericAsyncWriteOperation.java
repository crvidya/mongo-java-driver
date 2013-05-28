/*
 * Copyright (c) 2008 - 2013 10gen, Inc. <http://10gen.com>
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

package org.mongodb.operation.async;

import org.mongodb.MongoNamespace;
import org.mongodb.WriteConcern;
import org.mongodb.connection.BufferPool;
import org.mongodb.operation.BaseWrite;
import org.mongodb.operation.protocol.MessageSettings;
import org.mongodb.operation.protocol.RequestMessage;

import java.nio.ByteBuffer;

class GenericAsyncWriteOperation extends AsyncWriteOperation {
    private final BaseWrite write;
    private final RequestMessage requestMessage;

    public GenericAsyncWriteOperation(final MongoNamespace namespace, final BaseWrite write, final RequestMessage requestMessage,
                                      final BufferPool<ByteBuffer> bufferPool) {
        super(namespace, bufferPool);
        this.write = write;
        this.requestMessage = requestMessage;
    }

    @Override
    protected RequestMessage createRequestMessage(final MessageSettings settings) {
        return requestMessage;
    }

    @Override
    public BaseWrite getWrite() {
        return write;
    }

    @Override
    public WriteConcern getWriteConcern() {
        return write.getWriteConcern();
    }
}