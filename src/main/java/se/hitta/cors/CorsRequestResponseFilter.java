package se.hitta.cors;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;

public class CorsRequestResponseFilter extends CorsResponseFilter {

	public CorsRequestResponseFilter(AbstractMethod method) {
		super(method);
	}

	@Override
	public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {

		ResponseBuilder responseBuilder = Response.fromResponse(response.getResponse());
		
		responseBuilder.header(ACCESS_CONTROL_ALLOW_ORIGIN, this.origin);
					
		response.setResponse(responseBuilder.build());
		
		return response;
	}
}
