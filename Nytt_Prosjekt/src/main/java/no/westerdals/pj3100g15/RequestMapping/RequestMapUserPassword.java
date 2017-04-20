package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.DBService.DBServiceUserPassword;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestMapUserPassword {

    /**
     * This request map runs a method that shows a specified customers password (useful for admin or debugging purposes)
     * @param birthdayNumber is a customers social security number
     * @return a string with the password for the specified user
     */
    @RequestMapping(value = "/user/{id}/auth", method = RequestMethod.GET)
    @ResponseBody
    public String[] getPasswords(@PathVariable(value = "id") String birthdayNumber) {
        String[] password = DBServiceUserPassword.getPassword(birthdayNumber);
        //DBServiceConnection.closeConnection();
        return password;
    }
}
