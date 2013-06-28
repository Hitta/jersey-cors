jersey-cors
=========

Enable CORS (Cross Origin Resource Sharing) in Jersey applications.


### Usage
To override Jersey's default handling of the OPTIONS verb, an accompanying @OPTIONS annotated resource method has to be created in addition to the existing resource method / methods. All methods has to be annotated with the CORS annotation.

Example:
```java
@CORS
@GET
@Path("{id}")
public Person get(@PathParam("id") int id){
	...
}

//make sure you match the path pattern.
@CORS
@OPTIONS
@Path("{id}")
public Response get(){
	return Response.ok().build();
}
```


The CORS filter is based on Jersey's ContainerResponseFilter and ResourceFilter. To activate the CORS filter, the CorsFilterFactory has to be registered with Jersey e.g.:

```java
params.put(ResourceConfig.PROPERTY_RESOURCE_FILTER_FACTORIES, CorsFilterFactory.class.getName()); //activate CORS-filter
```
or in web.xml
```XML
<init-param>
    <param-name>com.sun.jersey.spi.container.ResourceFilters</param-name>
    <param-value>se.hitta.cors.CorsFilterFactory</param-value>
</init-param>
```

### CORS annotation
The CORS annotation comes with sensible (allowing) defaults. You can override these by simply assigning new values in the annotaion e.g.:
```java
CORS
@OPTIONS(methods={"OPTIONS,"POST"}, origin="www.mydomain.com", maxAge=3600)
@Path("{id}")
public Response get(){
	return Response.ok().build();
}
```
Only the CORS-preflight (enabled by the OPTIONS annotated method) makes use of the "methods", "headers" and "maxAge" values, so there is no reason to override these for your resourse methods.


````
Note!
The maven project contains dependencies to a private repository.
To compile outside of hitta.se, remove these dependencies and replace with whatever is needed.
````
