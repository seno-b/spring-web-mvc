package me.senob.springwebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
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
public class EventController {

//    @ModelAttribute
//    public void categories(Model model) {
//        model.addAttribute("categories", List.of("study", "book", "hobby"));
//    }

    @ExceptionHandler
    public String eventExceptionHandler(EventException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "error";
    }

    @InitBinder
    public void InitEventBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
        webDataBinder.addValidators(new EventValidator());
    }

    @ModelAttribute("categories")
    public List<String> categories(){
        return List.of("study", "book", "hobby");
    }

    @GetMapping("/events/form/name")
    public String eventsFormName(Model model) {
        throw new EventException("dkdkdkdkdkd");
//        model.addAttribute("event", new Event());
//        return "/events/form-name";
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

        redirectAttributes.addFlashAttribute("newEvent", event);

        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model, @ModelAttribute("newEvent") Event newEvent, @SessionAttribute LocalDateTime visitTime) {

        System.out.println("visitTime = " + visitTime);

        Event defaultEvent = new Event();
        defaultEvent.setName("Spring");
        defaultEvent.setLimit(10);

//        Event newEvent = (Event) model.getAttribute("newEvent");

        List<Event> eventList = new ArrayList<>();
        eventList.add(defaultEvent);
        eventList.add(newEvent);

        model.addAttribute("eventList", eventList);

        return "/events/list";
    }
}
