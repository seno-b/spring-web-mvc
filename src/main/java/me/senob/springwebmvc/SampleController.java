package me.senob.springwebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes(value = {"event"}) // model attribute 에 value 즉 'event' 라는 이름이 존재하면 HttpSession에 등록?저장 된다.
public class SampleController {

    @GetMapping("/events/form/name")
    public String eventsFormName(Model model) {
        model.addAttribute("event", new Event());
        return "/events/form-name";
    }

    @PostMapping("/events/form/name")
    public String eventsFormNameSubmit(@Valid @ModelAttribute Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/events/form-name";
        }

        return "redirect:/events/form/limit";
    }

    @GetMapping("/events/form/limit")
    public String eventsFormLimit(@ModelAttribute Event event, Model model, HttpSession httpSession) {

        Event event1 = (Event) httpSession.getAttribute("event");

        model.addAttribute("event", event);
        return "/events/form-limit";
    }

    @PostMapping("/events/form/limit")
    public String eventsFormLimitSubmit(@Valid @ModelAttribute Event event,
                                        BindingResult bindingResult,
                                        SessionStatus sessionStatus,
                                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/events/form-limit";
        }
        sessionStatus.setComplete();

        redirectAttributes.addAttribute("name", event.getName());
        redirectAttributes.addAttribute("limit", event.getLimit());

        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model, @SessionAttribute LocalDateTime visitTime,
                            @ModelAttribute(value = "newEvent") Event event) {

        System.out.println("visitTime = " + visitTime);

        Event defaultEvent = new Event();
        defaultEvent.setName("Spring");
        defaultEvent.setLimit(10);

        List<Event> eventList = new ArrayList<>();
        eventList.add(defaultEvent);
        eventList.add(event);

        model.addAttribute("eventList", eventList);

        return "/events/list";
    }
}
