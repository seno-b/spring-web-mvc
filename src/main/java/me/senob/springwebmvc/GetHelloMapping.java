package me.senob.springwebmvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.GET, value="/hello")
public @interface GetHelloMapping {
}
