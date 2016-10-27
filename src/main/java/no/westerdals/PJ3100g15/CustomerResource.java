package no.westerdals.PJ3100g15;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.function.Supplier;

@Controller
@RequestMapping("/customers")
public class CustomerResource {
    private final Database database = new Database();

    @RequestMapping
    @ResponseBody
    public List<Customer> getAllCustomers() {
        return database.getAllCustomers();
    }

    @RequestMapping("{id}")
    @ResponseBody
    public Customer getCustomerById(
            @PathVariable("id") final Long id
    ) {
        return database.getAllCustomers().stream()
                .filter(customer -> customer.getFoedselsnummer() == id)
                .findAny()
                .orElseThrow(() -> new CustomerNotFoundException("No user found with id '" + id + "'"));
    }
}
