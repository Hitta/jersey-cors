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
