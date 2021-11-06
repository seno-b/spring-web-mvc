package me.senob.springwebmvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.xpath.XPathExpressionException;
import java.time.LocalDateTime;
import java.util.Map;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getEvent() throws Exception {
        mockMvc.perform(get("/events/form"))
                .andDo(print())
                .andExpect(view().name("/events/form"))
                .andExpect(model().attributeExists("event"))
                .andExpect(request().sessionAttribute("event", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void postEvent() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                post("/events")
                        .param("limit", "-10")
                        .param("name", "shinho")
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());

        MvcResult mvcResult = resultActions.andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        Map<String, Object> model = modelAndView.getModel();
        System.out.println("model = " + model.size());
    }

    @Test
    public void getEvents() throws Exception {
        Event event = new Event();
        event.setName("Winter is coming.");
        event.setLimit(1000);

        mockMvc.perform(get("/events/list")
                .sessionAttr("visitTime", LocalDateTime.now())
                .flashAttr("newEvent", event))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//p").nodeCount(2));
    }
}