package me.senob.springwebmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SampleController {

    @GetHelloMapping
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @PostMapping("/hello")
    @ResponseBody
    public String helloPost() {
        return "hello";
    }

    @GetMapping("/events")
    @ResponseBody
    public String getEvents() {
        return "events";
    }

    @GetMapping("/events/{id}")
    @ResponseBody
    public String getEventsWithId(@PathVariable(value = "id") String id) {
        return "events";
    }

    @DeleteMapping("/events/{id}")
    @ResponseBody
    public String deleteEventsWithId(@PathVariable(value = "id") String id) {
        return "events";
    }


}
