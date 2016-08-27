package org.shoban.java.io;
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

/* 
 * This class is an example only. A "final" version of 
 * this class should implement the DataInput interface
 * and provide implementations for the methods declared in
 * DataInput.
 */ 
import java.io.DataInput;
import java.io.IOException;

public class CheckedDataInput {
    private Checksum cksum;
    private DataInput in;

    public CheckedDataInput(DataInput in, Checksum cksum) {
        this.cksum = cksum;
        this.in = in;
    }

    public byte readByte() throws IOException {
        byte b = in.readByte();
        cksum.update(b);
        return b;
    }

    public void readFully(byte[] b) throws IOException {
        in.readFully(b, 0, b.length);
        cksum.update(b, 0, b.length);
    }

    public void readFully(byte[] b, int off, int len) throws IOException {
        in.readFully(b, off, len);
        cksum.update(b, off, len);
    }

    public Checksum getChecksum() {
        return cksum;
    }
}
