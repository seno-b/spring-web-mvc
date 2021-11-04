package me.senob.springwebmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Event getEvents(@Valid @ModelAttribute Event event, BindingResult bindingResult) {
        if ( bindingResult.hasErrors() ){
            for (ObjectError allError : bindingResult.getAllErrors()) {
                System.out.println("allError = " + allError);
            }
        }
        return event;
    }
}
