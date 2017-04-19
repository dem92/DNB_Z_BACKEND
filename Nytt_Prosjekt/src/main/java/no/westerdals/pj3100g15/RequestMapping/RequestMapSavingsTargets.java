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
    @RequestMapping(value = "/{savingstargetId}/savingstargets")
    @ResponseBody
    public SavingsTargets getSavingsTarget(
            @PathVariable(value = "savingstargetId") int savingstargetId) {
        SavingsTargets savingsTargets = DBServiceSavingsTargets.getSavingsTarget(savingstargetId);
        //DBServiceConnection.closeConnection();
        return savingsTargets;
    }

    @RequestMapping(value = "/{customerId}/allsavingstargets")
    @ResponseBody
    public List<SavingsTargets> getAllSavingsTargetsForUser(
            @PathVariable(value = "customerId") int customerId) {
        List<SavingsTargets> savingsTargets = DBServiceSavingsTargets.getAllSavingsTargetsForUser(customerId);
        return savingsTargets;
    }

    @RequestMapping(value = "/createsavingstargets/{name}/{customerId}/{kroner}/{oere}")
    @ResponseBody
    public boolean createSavingsTargets(@PathVariable(value = "name") String name,
                                        @PathVariable(value = "customerId") int customerId,
                                        @PathVariable(value = "kroner")BigInteger kroner,
                                        @PathVariable(value = "oere")int oere){
        return DBServiceSavingsTargets.createSavingsTarget(kroner,oere,customerId,name);
    }

    // -------------------    Update savings targets under this line    ---------------------

    @RequestMapping(value = "/updatesavingstarget/{savingsTargetId}/name/{name}")
    @ResponseBody
    public boolean updateTargetName(@PathVariable(value = "savingsTargetId") int savingsTargetId,
                              @PathVariable(value = "name") String name){
        return DBServiceSavingsTargets.updateTargetName(savingsTargetId, name);
    }

    @RequestMapping(value = "/updatesavingstarget/{savingsTargetId}/goal/{kroner}/{oere}")
    @ResponseBody
    public boolean updateTargetGoal(@PathVariable(value = "savingsTargetId") int savingsTargetId,
                              @PathVariable(value = "kroner") BigInteger kroner,
                              @PathVariable(value = "oere") int oere){
        return DBServiceSavingsTargets.updateTargetGoal(savingsTargetId, kroner, oere);
    }

    @RequestMapping(value = "/addtotarget/{accountNo}/{savingsTargetId}/{kroner}/{oere}")
    @ResponseBody
    public boolean addToTarget(@PathVariable(value = "accountNo") String accountNumber,
                               @PathVariable(value = "savingsTargetId") int savingsTargetId,
                               @PathVariable(value = "kroner") BigInteger kroner,
                               @PathVariable(value = "oere") int oere){
        return DBServiceSendMoney.sendMoneyFromAccountToSavingsTarget(accountNumber, savingsTargetId, kroner, oere);
    }

    @RequestMapping(value = "/subtractfromtarget/{accountNo}/{savingsTargetId}/{kroner}/{oere}")
    @ResponseBody
    public boolean subtractFromTarget(@PathVariable(value = "accountNo") String accountNumber,
                               @PathVariable(value = "savingsTargetId") int savingsTargetId,
                               @PathVariable(value = "kroner") BigInteger kroner,
                               @PathVariable(value = "oere") int oere){
        return DBServiceSendMoney.sendMoneyFromSavingsTargetToAccount(accountNumber, savingsTargetId, kroner, oere);
    }

    @RequestMapping(value = "/deletesavingstargets/{savingsTargetsId}")
    @ResponseBody
    public boolean deleteSavingsTarggets(@PathVariable(value = "savingsTargetsId")int savingsTargetsId){
        return DBServiceSavingsTargets.deleteSavingsTarget(savingsTargetsId);
    }
}
