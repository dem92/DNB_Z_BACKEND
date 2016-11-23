package no.westerdals.pj3100g15;

import org.springframework.stereotype.Controller;

@Controller
public class SpringAccountController {

    //Account testAccountObject = new Account(5, new Customer("54321", "hei", "der", "blah", 1234, "hello@gmail.com", 12345687, 123));
/* TODO Henrik
    @RequestMapping
    @ResponseBody
    public String testFindingInterestRate() {
        return "" + testAccountObject.getInterestRate();
    }


    @RequestMapping("/accounttype")
    @ResponseBody
    public String testFindingAccountType() {
        return testAccountObject.getAccountType();
    }

    @RequestMapping("/account")
    @ResponseBody
    public String getSaldoForAccount(@RequestParam(value = "kontonummer", defaultValue = "80766645136") String kontonummer) {
        DBServiceAccount dbServiceAccount=new DBServiceAccount();
        String text ="";
        try{
        text = dbServiceAccount.saldo(kontonummer);}catch (SQLException e){e.printStackTrace();}
        return text;
    }*/
}
