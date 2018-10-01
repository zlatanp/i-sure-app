package com.ftn.filters;

import com.netflix.zuul.ZuulFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleFilter extends ZuulFilter {

	 @Override
	  public String filterType() {
	    return "pre";
	  }

	  @Override
	  public int filterOrder() {
	    return 1;
	  }

	  @Override
	  public boolean shouldFilter() {
	    return true;
	  }

	  @Override
	  public Object run() {
//	    RequestContext ctx = RequestContext.getCurrentContext();
//	    HttpServletRequest request = ctx.getRequest();
//
//	    System.out.println("PROKSI 8080");
//	    System.out.println(request.getMethod() + " " + request.getRequestURL().toString() );
//	    
//	    if(request.getRemoteAddr().equals("127.0.0.1")){
//	    	ctx.setResponseStatusCode(HttpServletResponse.SC_OK);
//	        // first StaticResponseFilter instance to match wins, others do not set body and/or status
//	        if (ctx.getResponseBody() == null) {
//	            ctx.setResponseBody("static content");
//	            ctx.setSendZuulResponse(false);
//	        }
//	    }
	    System.out.println("Proxy filter");
	    return null;
	  }

}
