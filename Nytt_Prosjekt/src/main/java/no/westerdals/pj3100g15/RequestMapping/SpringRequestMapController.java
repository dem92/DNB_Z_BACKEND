package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.DBService.*;
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
        Account account1 = DBServiceAccount.getAccount(account);
        DBServiceConnection.closeConnection();
        return account1;
    }

    @RequestMapping(value = "/user/{id}/account/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAccounts(@PathVariable(value = "id") int customerId) {
        List<Account> accounts = DBServiceAccount.getCustomerAccounts(customerId);
        DBServiceConnection.closeConnection();
        return accounts;
    }

    @RequestMapping(value = "/user/all/account/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAllAccounts() {
        List<Account> accounts = DBServiceAccount.getAllAccounts();
        DBServiceConnection.closeConnection();
        return accounts;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomer(@PathVariable(value = "id") int customerId) {
        Customer customer = DBServiceCustomer.getCustomer(customerId);
        DBServiceConnection.closeConnection();
        return customer;
    }

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getAllCustomers() {
        List<Customer> customers = DBServiceCustomer.getAllCustomers();
        DBServiceConnection.closeConnection();
        return customers;
    }

    @RequestMapping(value = "/user/{id}/auth", method = RequestMethod.GET)
    @ResponseBody
    public String[] getPasswords(@PathVariable(value = "id") String birthdayNumber) {
        String[] password = DBServiceUserPassword.getPassword(birthdayNumber);
        DBServiceConnection.closeConnection();
        return password;
    }

    @RequestMapping(value = "/newuser/{firstname}/{surname}/{email}/{birthdaynumber}", method = RequestMethod.GET)
    @ResponseBody
    public boolean createNewUser(
            @PathVariable(value = "firstname") String firstName,
            @PathVariable(value = "surname") String surName,
            @PathVariable(value = "birthdaynumber") String birthdaynumber,
            @PathVariable(value = "email") String email) {
        if (DBServiceCustomer.addCustomer(firstName, surName, birthdaynumber, email)) {
            DBServiceConnection.closeConnection();
            return true;
        }
        DBServiceConnection.closeConnection();
        return false;
    }

    @RequestMapping(value = "/user/{id}/newaccount/{accounttype}", method = RequestMethod.GET)
    @ResponseBody
    public boolean createAccount(
            @PathVariable(value = "id") int customerId,
            @PathVariable(value = "accounttype") String accountType) {
        if (DBServiceAccount.addAccount(customerId, accountType)) {
            DBServiceConnection.closeConnection();
            return true;
        }
        DBServiceConnection.closeConnection();
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
        if (DBServiceSendMoney.sendMoney(accountNumber, accountNumber2, kroner, oere)) {
            DBServiceLoggedTransaction.logTransfer(accountNumber, accountNumber2, kroner, oere, message);

            if (recurring)
                DBServiceRecurringTransfer.addRecurringTransfer(accountNumber, accountNumber2, kroner, oere, message, interval, endDate);
            DBServiceConnection.closeConnection();
            return true;
        } else {
            DBServiceConnection.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/{accountNumber}/transfer")
    @ResponseBody
    public List<LoggedTransaction> getTransferFromAccount(
            @PathVariable(value = "accountNumber") String accountNumber
    ) {
        List<LoggedTransaction> transfers = DBServiceLoggedTransaction.getAllLoggedTransactionsFromAccount(accountNumber).stream()
                .filter(payment -> payment.getTransactionType().equals("transfer"))
                .sorted(Comparator.comparing(LoggedTransaction::getId))
                .collect(Collectors.toList());
        Collections.reverse(transfers);
        DBServiceConnection.closeConnection();
        return transfers;
    }

    @RequestMapping(value = "/{accountNumber}/card")
    @ResponseBody
    public List<LoggedTransaction> getCardFromAccount(
            @PathVariable(value = "accountNumber") String accountNumber
    ) {
        List<LoggedTransaction> cards = DBServiceLoggedTransaction.getAllLoggedTransactionsFromAccount(accountNumber).stream()
                .filter(payment -> payment.getTransactionType().equals("card"))
                .sorted(Comparator.comparing(LoggedTransaction::getId))
                .collect(Collectors.toList());
        Collections.reverse(cards);
        DBServiceConnection.closeConnection();
        return cards;
    }


    @RequestMapping(value = "/{accountNumber}/payment")
    @ResponseBody
    public List<LoggedTransaction> getPaymentFromAccount(
            @PathVariable(value = "accountNumber") String accountNumber
    ) {
        List<LoggedTransaction> payments = DBServiceLoggedTransaction.getAllLoggedTransactionsFromAccount(accountNumber).stream()
                .filter(payment -> payment.getTransactionType().equals("payment"))
                .sorted(Comparator.comparing(LoggedTransaction::getId))
                .collect(Collectors.toList());
        Collections.reverse(payments);
        DBServiceConnection.closeConnection();
        return payments;
    }

    @RequestMapping(value = "/{accountNumber}/all", method = RequestMethod.GET)
    @ResponseBody
    public List<LoggedTransaction> getAllLoggedTransactionsFromAccount(
            @PathVariable(value = "accountNumber") String accountNumber) {
        List<LoggedTransaction> transactions = DBServiceLoggedTransaction.getAllLoggedTransactionsFromAccount(accountNumber).stream()
                .sorted(Comparator.comparing(LoggedTransaction::getId))
                .collect(Collectors.toList());
        Collections.reverse(transactions);
        DBServiceConnection.closeConnection();
        return transactions;
    }

    @RequestMapping(value = "/{accountNumber}/recurringtransfers/account")
    @ResponseBody
    public List<RecurringTransfer> getAllRecurringTransfersForAccount(
            @PathVariable(value = "accountNumber") String accountNumber) {
        List<RecurringTransfer> recurringTransfers = DBServiceRecurringTransfer.getAllRecurringTransfersForAccount(accountNumber);
        DBServiceConnection.closeConnection();
        return recurringTransfers;
    }

    @RequestMapping(value = "/{customerId}/recurringtransfers/user")
    @ResponseBody
    public List<RecurringTransfer> getAllRecurringTransfersForUser(
            @PathVariable(value = "customerId") int customerId) {
        List<String> accountNumbers = DBServiceAccount
                .getCustomerAccounts(customerId).stream()
                .map(account -> account.getAccountNumber())
                .collect(Collectors.toList());

       List<RecurringTransfer> recurringTransfers = new ArrayList<>();
        for (String accountNumber : accountNumbers) {
            recurringTransfers.addAll(DBServiceRecurringTransfer.getAllRecurringTransfersForAccount(accountNumber));
        }
        DBServiceConnection.closeConnection();
        return recurringTransfers;
    }

    @RequestMapping(value = "/{savingstargetId}/savingstargets")
    @ResponseBody
    public SavingsTargets getSavingsTarget(
            @PathVariable(value = "savingstargetId") int savingstargetId) {
        SavingsTargets savingsTargets = DBServiceSavingsTargets.getSavingsTarget(savingstargetId);
        DBServiceConnection.closeConnection();
        return savingsTargets;
    }

    @RequestMapping(value = "/{customerId}/allsavingstargets")
    @ResponseBody
    public List<SavingsTargets> getAllSavingsTargetsForUser(
            @PathVariable(value = "customerId") int customerId) {
        List<SavingsTargets> savingsTargets = DBServiceSavingsTargets.getAllSavingsTargetsForUser(customerId);
        DBServiceConnection.closeConnection();
        return savingsTargets;
    }



    // -------------------    Oppdatere customer under denne streken   ---------------------

    @RequestMapping(value = "/updateuser/{customerid}/surname/{surname}")
    @ResponseBody
    public boolean updateCustomerSurname(@PathVariable(value = "customerid") int customerId,
                                         @PathVariable(value = "surname") String surname) {

        if (DBServiceCustomer.updateSurname(customerId, surname)) {
            DBServiceConnection.closeConnection();
            return true;
        } else {
            DBServiceConnection.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/firstname/{firstname}")
    @ResponseBody
    public boolean updateCustomerFirstName(@PathVariable(value = "customerid") int customerId,
                                           @PathVariable(value = "firstname") String firstName) {

        if (DBServiceCustomer.updateFirstname(customerId, firstName)) {
            DBServiceConnection.closeConnection();
            return true;
        } else {
            DBServiceConnection.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/address/{address}")
    @ResponseBody
    public boolean updateCustomerAddress(@PathVariable(value = "customerid") int customerId,
                                         @PathVariable(value = "address") String address) {

        if (DBServiceCustomer.updateAddress(customerId, address)) {
            DBServiceConnection.closeConnection();
            return true;
        } else {
            DBServiceConnection.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/postalcode/{postalcode}")
    @ResponseBody
    public boolean updateCustomerPostalCode(@PathVariable(value = "customerid") int customerId,
                                            @PathVariable(value = "postalcode") int postalCode) {

        if (DBServiceCustomer.updatePostalcode(customerId, postalCode)) {
            DBServiceConnection.closeConnection();
            return true;
        } else {
            DBServiceConnection.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/email/{email}")
    @ResponseBody
    public boolean updateCustomerEmail(@PathVariable(value = "customerid") int customerId,
                                       @PathVariable(value = "email") String email) {

        if (DBServiceCustomer.updateEmail(customerId, email)) {
            DBServiceConnection.closeConnection();
            return true;
        } else {
            DBServiceConnection.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/phonenumber/{phone}")
    @ResponseBody
    public boolean updateCustomerPhone(@PathVariable(value = "customerid") int customerId,
                                       @PathVariable(value = "phone") int phone) {

        if (DBServiceCustomer.updatePhone(customerId, phone)) {
            DBServiceConnection.closeConnection();
            return true;
        } else {
            DBServiceConnection.closeConnection();
            return false;
        }
    }


    // --------------------    oppdatering av konto under denne streken    -------------------
    @RequestMapping(value = "/updateaccount/{accountid}/main/{main}")
    @ResponseBody
    public boolean updateAccountMain(@PathVariable(value = "accountid") String accountId,
                                     @PathVariable(value = "main") int main) {

        if (DBServiceAccount.updateMain(accountId, main)) {
            DBServiceConnection.closeConnection();
            return true;
        } else {
            DBServiceConnection.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateaccount/{accountid}/accountname/{accountname}")
    @ResponseBody
    public boolean updateAccountMain(@PathVariable(value = "accountid") String accountId,
                                     @PathVariable(value = "accountname") String accountname) {

        if (DBServiceAccount.updateAccountname(accountId, accountname)) {
            DBServiceConnection.closeConnection();
            return true;
        } else {
            DBServiceConnection.closeConnection();
            return false;
        }
    }

    // ------------ sletting av bruker eller konto  ----------------

    @RequestMapping(value = "/deleteuser/{customerid}")
    @ResponseBody
    public boolean deleteCustomer(@PathVariable(value = "customerid") int customerId) {
        if (DBServiceCustomer.deleteUser(customerId)) {
            DBServiceConnection.closeConnection();
            return true;
        }
        DBServiceConnection.closeConnection();
        return false;
    }

    @RequestMapping(value = "/deleteaccount/{accountid}")
    @ResponseBody
    public boolean deleteAccount(@PathVariable(value = "accountid") String accountId) {
        if (DBServiceAccount.deleteAccount(accountId)) {
            DBServiceConnection.closeConnection();
            return true;
        }
        DBServiceConnection.closeConnection();
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
        if(DBServiceSavingsTargets.createSavingsTarget(kroner,oere,customerId,name)){
            DBServiceConnection.closeConnection();
            return true;
        }
        DBServiceConnection.closeConnection();
        return false;
    }
}
