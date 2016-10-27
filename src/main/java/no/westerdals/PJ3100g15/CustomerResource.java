package no.westerdals.PJ3100g15;

import java.util.List;

public class UserResource {
    public List<Customer> getAllCustomers() {
        Database database = new Database();
        return database.getAllCustomers();
    }
}
