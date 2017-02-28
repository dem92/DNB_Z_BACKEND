package no.westerdals.pj3100g15.RequestMapping;


import no.westerdals.pj3100g15.Database;
import no.westerdals.pj3100g15.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/payments")
public class SpringPaymentController {
    private final Database database;

    @Autowired
    public SpringPaymentController(final Database database) {
        this.database = database;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String mathias() {
        return "Snuppen";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String newPayment(
            @RequestBody final Payment payment
    ) {
        return payment.toString() + "\n\n \t\t Sven er jättekul";
    }
}