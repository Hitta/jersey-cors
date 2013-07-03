/*
 * Copyright 2013 Hittapunktse AB (http://www.hitta.se/)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package se.hitta.cors;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.lang3.StringUtils;

import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;

public class CorsPreflightResponseFilter extends CorsResponseFilter {

	public CorsPreflightResponseFilter(AbstractMethod method) {
		super(method);
	}

	@Override
	public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
		
		ResponseBuilder responseBuilder = Response.fromResponse(response.getResponse());
		
		responseBuilder
					.header(ACCESS_CONTROL_ALLOW_ORIGIN, this.origin)
					.header(ACCESS_CONTROL_ALLOW_METHODS, this.methods)
					.header(ACCESS_CONTROL_MAX_AGE, this.maxAge);
		
		if("*".equals(this.headers))
		{
			String requestHeaders = request.getHeaderValue(ACCESS_CONTROL_REQUEST_HEADERS);
			if(!StringUtils.isBlank(requestHeaders))
			{
				responseBuilder.header(ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders);				
			}
		}
		else
		{
			responseBuilder.header(ACCESS_CONTROL_ALLOW_HEADERS, this.headers);							
		}
		
		response.setResponse(responseBuilder.build());
		
		return response;
	}
	
}
