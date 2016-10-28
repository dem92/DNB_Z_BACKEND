package no.westerdals.PJ3100g15;

import no.westerdals.PJ3100g15.users.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestingShit {

    Account testAccountObject = new Account(5, new Customer("54321", "hei", "der", "blah", 1234, "hello@gmail.com", 12345687, 123));

    /*@RequestMapping
    @ResponseBody
    public String testFindingInterestRate(){
        return "" + testAccountObject.getInterestRate();
    }
*/

    @RequestMapping("/accounttype")
    @ResponseBody
    public String testFindingAccountType(){
        return testAccountObject.getAccountType();
    }


}
