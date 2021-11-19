package me.senob.springwebmvc;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/events")
public class EventApi {

//    @ExceptionHandler
//    public ResponseEntity eventExceptionHandler() {
//        return ResponseEntity.badRequest().body("something... error ...");
//    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event, BindingResult bindingResult) {

        for (ObjectError allError : bindingResult.getAllErrors()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }
}
