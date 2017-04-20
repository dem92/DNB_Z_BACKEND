package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.DBService.DBServiceSavingsTargets;
import no.westerdals.pj3100g15.DBService.DBServiceSendMoney;
import no.westerdals.pj3100g15.ORM.SavingsTargets;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;

@Controller
public class RequestMapSavingsTargets {

    /**
     * This request map runs a method that gets a savings target
     * @param savingstargetId is a unique ID for a savings target
     * @return a SavingsTargets-object
     */
    @RequestMapping(value = "/{savingstargetId}/savingstargets")
    @ResponseBody
    public SavingsTargets getSavingsTarget(
            @PathVariable(value = "savingstargetId") int savingstargetId) {
        SavingsTargets savingsTargets = DBServiceSavingsTargets.getSavingsTarget(savingstargetId);
        //DBServiceConnection.closeConnection();
        return savingsTargets;
    }

    /**
     * This request map runs a method that gets all savings targets for a customer
     * @param customerId is a unique ID for a customer
     * @return a List<> with savings targets for a specified user
     */
    @RequestMapping(value = "/{customerId}/allsavingstargets")
    @ResponseBody
    public List<SavingsTargets> getAllSavingsTargetsForUser(
            @PathVariable(value = "customerId") int customerId) {
        List<SavingsTargets> savingsTargets = DBServiceSavingsTargets.getAllSavingsTargetsForUser(customerId);
        return savingsTargets;
    }

    /**
     * This request map runs a method that creates a new savings target
     * @param name is a string with the name for the new savings target
     * @param customerId is a unique ID for a customer
     * @param kroner is the goal amount (kroner) for the new savings target
     * @param oere is the goal amount (øre) for the new savings target
     * @return a boolean to see if the savings target was successfully created
     */
    @RequestMapping(value = "/createsavingstargets/{name}/{customerId}/{kroner}/{oere}")
    @ResponseBody
    public boolean createSavingsTargets(@PathVariable(value = "name") String name,
                                        @PathVariable(value = "customerId") int customerId,
                                        @PathVariable(value = "kroner")BigInteger kroner,
                                        @PathVariable(value = "oere")int oere){
        return DBServiceSavingsTargets.createSavingsTarget(kroner,oere,customerId,name);
    }

    // -------------------    Update savings targets under this line    ---------------------

    /**
     * This request map runs a method that updates a savings targets name
     * @param savingsTargetId is a unique ID for a savings target
     * @param name is the new name for the savings target
     * @return a boolean to see if the change was successful
     */
    @RequestMapping(value = "/updatesavingstarget/{savingsTargetId}/name/{name}")
    @ResponseBody
    public boolean updateTargetName(@PathVariable(value = "savingsTargetId") int savingsTargetId,
                              @PathVariable(value = "name") String name){
        return DBServiceSavingsTargets.updateTargetName(savingsTargetId, name);
    }

    /**
     * This request map runs a method that updates a savings targets goal amount
     * @param savingsTargetId is a unique ID for a savings target
     * @param kroner is the new goal amount (kroner) for the savings target
     * @param oere is the new goal amount (øre) for the savings target
     * @return a boolean to see if the change was successful
     */
    @RequestMapping(value = "/updatesavingstarget/{savingsTargetId}/goal/{kroner}/{oere}")
    @ResponseBody
    public boolean updateTargetGoal(@PathVariable(value = "savingsTargetId") int savingsTargetId,
                              @PathVariable(value = "kroner") BigInteger kroner,
                              @PathVariable(value = "oere") int oere){
        return DBServiceSavingsTargets.updateTargetGoal(savingsTargetId, kroner, oere);
    }

    /**
     * This request map runs a method that adds an amount of money to a specified savings target
     * @param accountNumber is the account number of the account sending money
     * @param savingsTargetId is a unique ID for a savings target receiving money
     * @param kroner is the amount of money (kroner) being sent to the savings target
     * @param oere is the amount of money (øre) being sent to the savings target
     * @return a boolean to see if the transaction was successful
     */
    @RequestMapping(value = "/addtotarget/{accountNo}/{savingsTargetId}/{kroner}/{oere}")
    @ResponseBody
    public boolean addToTarget(@PathVariable(value = "accountNo") String accountNumber,
                               @PathVariable(value = "savingsTargetId") int savingsTargetId,
                               @PathVariable(value = "kroner") BigInteger kroner,
                               @PathVariable(value = "oere") int oere){
        return DBServiceSendMoney.sendMoneyFromAccountToSavingsTarget(accountNumber, savingsTargetId, kroner, oere);
    }

    /**
     * This request map runs a method that subtracts money from a savings target
     * @param accountNumber is the account number for the account receiving the money
     * @param savingsTargetId is a unique ID for a savings target sending money
     * @param kroner is the amount of money (kroner) being sent from the savings target
     * @param oere is the amount of money (øre) being sent from the savings target
     * @return a boolean to see if the transaction was successful
     */
    @RequestMapping(value = "/subtractfromtarget/{accountNo}/{savingsTargetId}/{kroner}/{oere}")
    @ResponseBody
    public boolean subtractFromTarget(@PathVariable(value = "accountNo") String accountNumber,
                               @PathVariable(value = "savingsTargetId") int savingsTargetId,
                               @PathVariable(value = "kroner") BigInteger kroner,
                               @PathVariable(value = "oere") int oere){
        return DBServiceSendMoney.sendMoneyFromSavingsTargetToAccount(accountNumber, savingsTargetId, kroner, oere);
    }

    /**
     * This request map runs a method that deletes a specified savings target
     * @param savingsTargetsId is a unique ID for a savings target
     * @return a boolean to see if the savings target was successfully deleted
     */
    @RequestMapping(value = "/deletesavingstargets/{savingsTargetsId}")
    @ResponseBody
    public boolean deleteSavingsTarggets(@PathVariable(value = "savingsTargetsId")int savingsTargetsId){
        return DBServiceSavingsTargets.deleteSavingsTarget(savingsTargetsId);
    }
}
