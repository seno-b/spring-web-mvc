package me.senob.springwebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes(value = {"event"}) // model attribute 에 value 즉 'event' 라는 이름이 존재하면 HttpSession에 등록?저장 된다.
public class SampleController {

    @GetMapping("/events/form")
    public String eventsForm(Model model, HttpSession httpSession) {
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
