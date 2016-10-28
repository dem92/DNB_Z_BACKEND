package no.westerdals.PJ3100g15;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    private final Database database;

    @Autowired
    public PaymentController(final Database database) {
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
        return payment.toString();
    }
}
