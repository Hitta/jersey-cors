package se.hitta.cors;

import org.apache.commons.lang3.StringUtils;

import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

public abstract class CorsResponseFilter implements ContainerResponseFilter, ResourceFilter
{
	protected static final String ACCESS_CONTROL_REQUEST_METHOD = "Access-Control-Request-Method";
	protected static final String ACCESS_CONTROL_REQUEST_HEADERS = "Access-Control-Request-Headers";
	protected static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
	
	protected static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
	protected static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
	protected static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
	
	protected static final String ORIGIN = "Origin";
	
	protected final String origin;
	protected final String methods;
	protected final String headers;
	protected final int maxAge;
	
	public CorsResponseFilter(AbstractMethod method)
	{
		CORS corsAnnotation = method.getAnnotation(CORS.class);
		
		this.origin = corsAnnotation.origin();
		this.methods = StringUtils.join(corsAnnotation.methods(), ",");
		this.headers = StringUtils.join(corsAnnotation.headers(), ",");
		this.maxAge = corsAnnotation.maxAge();
	}
	
	@Override
	public ContainerRequestFilter getRequestFilter() {
		return null;
	}
	
	@Override
	public ContainerResponseFilter getResponseFilter() {
		return this;
	}
}
