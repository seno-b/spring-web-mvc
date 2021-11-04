package me.senob.springwebmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class SampleController {

    @GetMapping("/events/form")
    public String eventsForm(Model model) {
        Event attributeValue = new Event();
        attributeValue.setName("test");
        attributeValue.setLimit(50);
        model.addAttribute("event", attributeValue);
        return "events/form";
    }

    @PostMapping("/events")
    @ResponseBody
    public Event getEvents(@RequestParam String name,
                           @RequestParam Integer limit) {
        Event event = new Event();
        event.setName(name);
        event.setLimit(limit);
        return event;
    }
}
