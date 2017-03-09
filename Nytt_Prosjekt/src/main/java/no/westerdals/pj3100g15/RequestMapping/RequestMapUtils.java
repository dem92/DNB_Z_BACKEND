package no.westerdals.pj3100g15.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestMapUtils {
    @RequestMapping(value = "/check")
    @ResponseBody
    public boolean check() {
        return true;
    }
}
