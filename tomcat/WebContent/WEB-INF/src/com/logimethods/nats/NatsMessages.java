/*******************************************************************************
 * Copyright (c) 2016 Logimethods
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MIT License (MIT)
 * which accompanies this distribution, and is available at
 * http://opensource.org/licenses/MIT
 *******************************************************************************/
package com.logimethods.nats;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import io.nats.client.*;

/**
 * @author laugimethods
 *
 */
public class NatsMessages {
	
	static final List<String> messages = new LinkedList<String>();
	
	public static void createConnection(String natsUrl, String subject) {
		ConnectionFactory cf = new ConnectionFactory(natsUrl);
		try {
			messages.add("CREATE CONNECTION to " + natsUrl);
			
			Connection nc = cf.createConnection();
			
			nc.subscribe(subject, m -> {
				messages.add(0, new String(m.getData()));
			});
			
			messages.add("READY");
		} catch (IOException | TimeoutException e) {
			messages.add(e.getMessage());
		}
	}
	
	public static List<String> getMessages() {
//		messages.add("messAge");
		return messages;
	}
}
