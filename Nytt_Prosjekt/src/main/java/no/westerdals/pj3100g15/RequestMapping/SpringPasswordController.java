package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.ORM.DBService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Eva Dahlo on 24/11/2016.
 */

@RestController
public class SpringPasswordController {
    @RequestMapping(value = "/user/{id}/auth", method = RequestMethod.GET)
    @ResponseBody
    public String[] getAccounts(@PathVariable(value = "id") String userID){
        return DBService.getPassword(userID);
    }
}
