package me.senob.springwebmvc;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice(assignableTypes = {EventController.class, EventApi.class})
public class BaseController {

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
}
