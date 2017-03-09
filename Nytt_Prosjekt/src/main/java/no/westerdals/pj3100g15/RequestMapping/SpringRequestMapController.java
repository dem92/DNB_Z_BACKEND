package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.ORM.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SpringRequestMapController {

    @RequestMapping(value = "/user/account/{accountID}", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccount(@PathVariable(value = "accountID") String account) {
        Account account1 = DBService.getAccount(account);
        DBService.closeConnection();
        return account1;
    }

    @RequestMapping(value = "/user/{id}/account/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAccounts(@PathVariable(value = "id") int customerId) {
        List<Account> accounts = DBService.getCustomerAccounts(customerId);
        DBService.closeConnection();
        return accounts;
    }

    @RequestMapping(value = "/user/all/account/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAllAccounts() {
        List<Account> accounts = DBService.getAllAccounts();
        DBService.closeConnection();
        return accounts;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomer(@PathVariable(value = "id") int customerId) {
        Customer customer = DBService.getCustomer(customerId);
        DBService.closeConnection();
        return customer;
    }

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getAllCustomers() {
        List<Customer> customers = DBService.getAllCustomers();
        DBService.closeConnection();
        return customers;
    }

    @RequestMapping(value = "/user/{id}/auth", method = RequestMethod.GET)
    @ResponseBody
    public String[] getPasswords(@PathVariable(value = "id") String birthdayNumber) {
        String[] password = DBService.getPassword(birthdayNumber);
        DBService.closeConnection();
        return password;
    }

    @RequestMapping(value = "/newuser/{firstname}/{surname}/{email}/{birthdaynumber}", method = RequestMethod.GET)
    @ResponseBody
    public boolean createNewUser(
            @PathVariable(value = "firstname") String firstName,
            @PathVariable(value = "surname") String surName,
            @PathVariable(value = "birthdaynumber") String birthdaynumber,
            @PathVariable(value = "email") String email) {
        if (DBService.addCustomer(firstName, surName, birthdaynumber, email)) {
            DBService.closeConnection();
            return true;
        }
        DBService.closeConnection();
        return false;
    }

    @RequestMapping(value = "/user/{id}/newaccount/{accounttype}", method = RequestMethod.GET)
    @ResponseBody
    public boolean createAccount(
            @PathVariable(value = "id") int customerId,
            @PathVariable(value = "accounttype") String accountType) {
        if (DBService.addAccount(customerId, accountType)) {
            DBService.closeConnection();
            return true;
        }
        DBService.closeConnection();
        return false;
    }

    @RequestMapping(value = "/sendmoney/{message}/{sendersAccount}/{recieversAccount}/{kroner}/{oere}/{recurring}/{interval}/{endDate}", method = RequestMethod.GET)
    @ResponseBody
    public boolean sendMoney(
            @PathVariable(value = "message") String message,
            @PathVariable(value = "sendersAccount") String accountNumber,
            @PathVariable(value = "recieversAccount") String accountNumber2,
            @PathVariable(value = "kroner") BigInteger kroner,
            @PathVariable(value = "oere") int oere,
            @PathVariable(value = "recurring") boolean recurring,
            @PathVariable(value = "interval") String interval,
            @PathVariable(value = "endDate") long endDate
    ) {
        if (DBService.sendMoney(accountNumber, accountNumber2, kroner, oere)) {
            DBService.logTransfer(accountNumber, accountNumber2, kroner, oere, message);

            if (recurring)
                DBService.addRecurringTransfer(accountNumber, accountNumber2, kroner, oere, message, interval, endDate);
            DBService.closeConnection();
            return true;
        } else {
            DBService.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/{accountNumber}/transfer")
    @ResponseBody
    public List<LoggedTransaction> getTransferFromAccount(
            @PathVariable(value = "accountNumber") String accountNumber
    ) {
        List<LoggedTransaction> transfers = DBService.getAllLoggedTransactionsFromAccount(accountNumber).stream()
                .filter(payment -> payment.getTransactionType().equals("transfer"))
                .sorted(Comparator.comparing(LoggedTransaction::getId))
                .collect(Collectors.toList());
        Collections.reverse(transfers);
        DBService.closeConnection();
        return transfers;
    }

    @RequestMapping(value = "/{accountNumber}/card")
    @ResponseBody
    public List<LoggedTransaction> getCardFromAccount(
            @PathVariable(value = "accountNumber") String accountNumber
    ) {
        List<LoggedTransaction> cards = DBService.getAllLoggedTransactionsFromAccount(accountNumber).stream()
                .filter(payment -> payment.getTransactionType().equals("card"))
                .sorted(Comparator.comparing(LoggedTransaction::getId))
                .collect(Collectors.toList());
        Collections.reverse(cards);
        DBService.closeConnection();
        return cards;
    }


    @RequestMapping(value = "/{accountNumber}/payment")
    @ResponseBody
    public List<LoggedTransaction> getPaymentFromAccount(
            @PathVariable(value = "accountNumber") String accountNumber
    ) {
        List<LoggedTransaction> payments = DBService.getAllLoggedTransactionsFromAccount(accountNumber).stream()
                .filter(payment -> payment.getTransactionType().equals("payment"))
                .sorted(Comparator.comparing(LoggedTransaction::getId))
                .collect(Collectors.toList());
        Collections.reverse(payments);
        DBService.closeConnection();
        return payments;
    }

    @RequestMapping(value = "/{accountNumber}/all", method = RequestMethod.GET)
    @ResponseBody
    public List<LoggedTransaction> getAllLoggedTransactionsFromAccount(
            @PathVariable(value = "accountNumber") String accountNumber) {
        List<LoggedTransaction> transactions = DBService.getAllLoggedTransactionsFromAccount(accountNumber).stream()
                .sorted(Comparator.comparing(LoggedTransaction::getId))
                .collect(Collectors.toList());
        Collections.reverse(transactions);
        DBService.closeConnection();
        return transactions;
    }

    @RequestMapping(value = "/{accountNumber}/recurringtransfers/account")
    @ResponseBody
    public List<RecurringTransfer> getAllRecurringTransfersForAccount(
            @PathVariable(value = "accountNumber") String accountNumber) {
        List<RecurringTransfer> recurringTransfers = DBService.getAllRecurringTransfersForAccount(accountNumber);
        DBService.closeConnection();
        return recurringTransfers;
    }

    @RequestMapping(value = "/{customerId}/recurringtransfers/user")
    @ResponseBody
    public List<RecurringTransfer> getAllRecurringTransfersForUser(
            @PathVariable(value = "customerId") int customerId) {
        List<String> accountNumbers = DBService
                .getCustomerAccounts(customerId).stream()
                .map(account -> account.getAccountNumber())
                .collect(Collectors.toList());

       List<RecurringTransfer> recurringTransfers = new ArrayList<>();
        for (String accountNumber : accountNumbers) {
            recurringTransfers.addAll(DBService.getAllRecurringTransfersForAccount(accountNumber));
        }
        DBService.closeConnection();
        return recurringTransfers;
    }

    @RequestMapping(value = "/{savingstargetId}/savingstargets")
    @ResponseBody
    public SavingsTargets getSavingsTarget(
            @PathVariable(value = "savingstargetId") int savingstargetId) {
        SavingsTargets savingsTargets = DBService.getSavingsTarget(savingstargetId);
        DBService.closeConnection();
        return savingsTargets;
    }

    @RequestMapping(value = "/{customerId}/allsavingstargets")
    @ResponseBody
    public List<SavingsTargets> getAllSavingsTargetsForUser(
            @PathVariable(value = "customerId") int customerId) {
        List<SavingsTargets> savingsTargets = DBService.getAllSavingsTargetsForUser(customerId);
        DBService.closeConnection();
        return savingsTargets;
    }



    // -------------------    Oppdatere customer under denne streken   ---------------------

    @RequestMapping(value = "/updateuser/{customerid}/surname/{surname}")
    @ResponseBody
    public boolean updateCustomerSurname(@PathVariable(value = "customerid") int customerId,
                                         @PathVariable(value = "surname") String surname) {

        if (DBService.updateSurname(customerId, surname)) {
            DBService.closeConnection();
            return true;
        } else {
            DBService.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/firstname/{firstname}")
    @ResponseBody
    public boolean updateCustomerFirstName(@PathVariable(value = "customerid") int customerId,
                                           @PathVariable(value = "firstname") String firstName) {

        if (DBService.updateFirstname(customerId, firstName)) {
            DBService.closeConnection();
            return true;
        } else {
            DBService.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/address/{address}")
    @ResponseBody
    public boolean updateCustomerAddress(@PathVariable(value = "customerid") int customerId,
                                         @PathVariable(value = "address") String address) {

        if (DBService.updateAddress(customerId, address)) {
            DBService.closeConnection();
            return true;
        } else {
            DBService.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/postalcode/{postalcode}")
    @ResponseBody
    public boolean updateCustomerPostalCode(@PathVariable(value = "customerid") int customerId,
                                            @PathVariable(value = "postalcode") int postalCode) {

        if (DBService.updatePostalcode(customerId, postalCode)) {
            DBService.closeConnection();
            return true;
        } else {
            DBService.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/email/{email}")
    @ResponseBody
    public boolean updateCustomerEmail(@PathVariable(value = "customerid") int customerId,
                                       @PathVariable(value = "email") String email) {

        if (DBService.updateEmail(customerId, email)) {
            DBService.closeConnection();
            return true;
        } else {
            DBService.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/phonenumber/{phone}")
    @ResponseBody
    public boolean updateCustomerPhone(@PathVariable(value = "customerid") int customerId,
                                       @PathVariable(value = "phone") int phone) {

        if (DBService.updatePhone(customerId, phone)) {
            DBService.closeConnection();
            return true;
        } else {
            DBService.closeConnection();
            return false;
        }
    }


    // --------------------    oppdatering av konto under denne streken    -------------------
    @RequestMapping(value = "/updateaccount/{accountid}/main/{main}")
    @ResponseBody
    public boolean updateAccountMain(@PathVariable(value = "accountid") String accountId,
                                     @PathVariable(value = "main") int main) {

        if (DBService.updateMain(accountId, main)) {
            DBService.closeConnection();
            return true;
        } else {
            DBService.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateaccount/{accountid}/accountname/{accountname}")
    @ResponseBody
    public boolean updateAccountMain(@PathVariable(value = "accountid") String accountId,
                                     @PathVariable(value = "accountname") String accountname) {

        if (DBService.updateAccountname(accountId, accountname)) {
            DBService.closeConnection();
            return true;
        } else {
            DBService.closeConnection();
            return false;
        }
    }

    // ------------ sletting av bruker eller konto  ----------------

    @RequestMapping(value = "/deleteuser/{customerid}")
    @ResponseBody
    public boolean deleteCustomer(@PathVariable(value = "customerid") int customerId) {
        if (DBService.deleteUser(customerId)) {
            DBService.closeConnection();
            return true;
        }
        DBService.closeConnection();
        return false;
    }

    @RequestMapping(value = "/deleteaccount/{accountid}")
    @ResponseBody
    public boolean deleteAccount(@PathVariable(value = "accountid") String accountId) {
        if (DBService.deleteAccount(accountId)) {
            DBService.closeConnection();
            return true;
        }
        DBService.closeConnection();
        return false;
    }

    // ------------ sjekke om server er oppe  ----------------

    @RequestMapping(value = "/check")
    @ResponseBody
    public boolean check() {
        return true;
    }

    @RequestMapping(value = "/createsavingstargets/{name}/{customerId}/{kroner}/{oere}")
    @ResponseBody
    public boolean createSavingsTargets(@PathVariable(value = "name") String name,
                                        @PathVariable(value = "customerId") int customerId,
                                        @PathVariable(value = "kroner")BigInteger kroner,
                                        @PathVariable(value = "oere")int oere){
        if(DBService.createSavingsTarget(kroner,oere,customerId,name)){
            DBService.closeConnection();
            return true;
        }
        DBService.closeConnection();
        return false;
    }
}
