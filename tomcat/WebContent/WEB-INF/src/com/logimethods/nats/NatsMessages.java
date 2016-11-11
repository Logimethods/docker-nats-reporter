/*******************************************************************************
 * Copyright (c) 2016 Logimethods
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MIT License (MIT)
 * which accompanies this distribution, and is available at
 * http://opensource.org/licenses/MIT
 *******************************************************************************/
package com.logimethods.nats;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import io.nats.client.*;

/**
 * @author laugimethods
 *
 */
public class NatsMessages {
	
	static List<String> messages;
	static Connection nc;
	
	public static void createConnection(String natsUrl, String subject, int limit) {
		ConnectionFactory cf = new ConnectionFactory(natsUrl);
		try {
			if (messages != null) {
				messages.clear();
			}
			
			messages = new LimitedQueue<String>(limit);
			messages.add("CREATE CONNECTION to " + natsUrl);
			
			if (nc != null) {
				nc.close();
			}
			
			nc = cf.createConnection();
			
			nc.subscribe(subject, m -> {
				messages.add(0, "("+ m.getSubject() + ", " + new String(m.getData()) + ')');
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
