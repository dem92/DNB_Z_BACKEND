package no.westerdals.PJ3100g15;

import no.westerdals.PJ3100g15.users.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerResource {
    private final Database database;

    @Autowired
    public CustomerResource(final Database database) {
        this.database = database;
    }

    @RequestMapping
    @ResponseBody
    public List<Customer> getAllCustomers() {
        return database.getAllCustomers();
    }

    @RequestMapping("{id}")
    @ResponseBody
    public Customer getCustomerById(
            @PathVariable("id") final String id
    ) {
        return database.getAllCustomers().stream()
                .filter(customer -> customer.getFoedselsnummer().equals(id))
                .findAny()
                .orElseThrow(() -> new CustomerNotFoundException("No user found with id '" + id + "'"));
    }
}
