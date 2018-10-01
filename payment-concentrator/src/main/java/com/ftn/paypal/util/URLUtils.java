package com.ftn.paypal.util;

import javax.servlet.http.HttpServletRequest;

public class URLUtils {

	public static String getBaseURl(HttpServletRequest request, String ip) {
	    String scheme = request.getScheme();
	    String serverName = ip;//request.getServerName();
	    int serverPort = request.getServerPort();
	    String contextPath = request.getContextPath();
	    StringBuffer url =  new StringBuffer();
	    url.append(scheme).append("://").append(serverName);
	    if ((serverPort != 80) && (serverPort != 443)) {
	        url.append(":").append(serverPort);
	    }
	    url.append(contextPath);
	    if(url.toString().endsWith("/")){
	    	url.append("/");
	    }
	    return url.toString();
	}
	
}
