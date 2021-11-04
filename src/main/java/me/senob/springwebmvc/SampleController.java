package me.senob.springwebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SampleController {

    @GetMapping("/events/form")
    public String eventsForm(Model model) {
        Event attributeValue = new Event();
        attributeValue.setName("test");
        attributeValue.setLimit(50);
        model.addAttribute("event", attributeValue);
        return "/events/form";
    }

    @PostMapping("/events")
    public String createEvents(@Valid @ModelAttribute Event event, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/events/form";
        }
        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model) {
        Event event = new Event();
        event.setName("Spring");
        event.setLimit(10);

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        model.addAttribute("eventList", eventList);

        return "/events/list";
    }
}
