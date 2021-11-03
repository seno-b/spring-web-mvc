package me.senob.springwebmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class SampleController {

    @GetMapping("/events")
    @ResponseBody
    public Event getEvents(@RequestParam Map<String, String> para) {
        Event event = new Event();
        event.setName(para.get("name"));
        return event;
    }
}
