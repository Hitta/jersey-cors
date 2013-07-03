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

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.OPTIONS;

import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ResourceFilter;
import com.sun.jersey.spi.container.ResourceFilterFactory;

public class CorsFilterFactory implements ResourceFilterFactory
{
	@Override
	public List<ResourceFilter> create(AbstractMethod method) {
		
		List<ResourceFilter> filters = new ArrayList<ResourceFilter>();

		if(method.isAnnotationPresent(CORS.class))
		{
			if(method.isAnnotationPresent(OPTIONS.class))
			{
				filters.add(new CorsPreflightResponseFilter(method));
			}
			else
			{
				filters.add(new CorsRequestResponseFilter(method));
			}
		}
		return filters;
	}
}
