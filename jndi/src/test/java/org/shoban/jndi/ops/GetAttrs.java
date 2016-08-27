package org.shoban.jndi.ops;
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

import javax.naming.*;
import javax.naming.directory.*;

import java.util.Hashtable;

/**
 * Demonstrates how to retrieve selected attributes of a named object.
 *
 * usage: java GetAttrs
 */
class GetAttrs {
    public static void main(String[] args) {

	// Set up the environment for creating the initial context
        Hashtable<String, Object> env = new Hashtable<String, Object>(11);
	env.put(Context.INITIAL_CONTEXT_FACTORY, 
	    "com.sun.jndi.ldap.LdapCtxFactory");
	env.put(Context.PROVIDER_URL, "ldap://localhost:389/o=JNDITutorial");

	try {
	    // Create initial context
	    DirContext ctx = new InitialDirContext(env);

	    // Specify the ids of the attributes to return
	    String[] attrIDs = {"sn", "telephonenumber", "golfhandicap", "mail"};

	    // Get the attributes requested
	    Attributes answer = 
		ctx.getAttributes("cn=Ted Geisel, ou=People", attrIDs);

	    // Print the answer
	    printAttrs(answer);

	    // Close the context when we're done
	    ctx.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    static void printAttrs(Attributes attrs) {
        if (attrs == null) {
            System.out.println("No attributes");
        } else {
            /* Print each attribute */
            try {
                for (NamingEnumeration ae = attrs.getAll();
                     ae.hasMore();) {
                    Attribute attr = (Attribute)ae.next();
                    System.out.println("attribute: " + attr.getID());

                    /* print each value */
                    for (NamingEnumeration e = attr.getAll();
                         e.hasMore();
                         System.out.println("value: " + e.next()))
                        ;
                }
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

}
