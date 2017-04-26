/*
 * Copyright (c) 2017. Group 15, PJ-3100, Westerdals Oslo ACT
 */

package no.westerdals.pj3100g15.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestMapUtils {

    /**
     * This request map just returns true. It is just a short URL to check if the server is still running.
     * @return a boolean that returns true if the connection is open or false if it's closed
     */
    @RequestMapping(value = "/check")
    @ResponseBody
    public boolean check() {
        return true;
    }
}
