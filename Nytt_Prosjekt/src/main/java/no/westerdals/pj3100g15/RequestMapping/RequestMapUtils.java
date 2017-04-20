package no.westerdals.pj3100g15.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestMapUtils {

    /**
     * This request map runs a method that checks if there is an open connection to the database
     * @return a boolean that returns true if the connection is open or false if it's closed
     */
    @RequestMapping(value = "/check")
    @ResponseBody
    public boolean check() {
        return true;
    }
}
