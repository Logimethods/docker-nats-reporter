<!-- 
/*******************************************************************************
 * Copyright (c) 2016 Logimethods
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MIT License (MIT)
 * which accompanies this distribution, and is available at
 * http://opensource.org/licenses/MIT
 *******************************************************************************/
 -->
<!DOCTYPE html>
<html>
<head><title>NATS Messages</title></head>
<body>
<h1>NATS Messages</h1>
<%@ page import="java.util.*,com.logimethods.nats.*" %>
<h2>Time on server: <%= new Date() %></h2>
<p>
<% 
	// Set refresh, autoload time as 5 seconds
	response.setIntHeader("Refresh", 5);
	for (String message: NatsMessages.getMessages()) {
		out.println(message + "<BR>");
	}
%>
</p>
</body></html>