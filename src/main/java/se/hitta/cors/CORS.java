package se.hitta.cors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CORS {
	String[] methods() default {"OPTIONS", "GET"};
	String[] headers() default {"*"};
	String origin() default "*";
	int maxAge() default 3600 * 24; //one day 
}
