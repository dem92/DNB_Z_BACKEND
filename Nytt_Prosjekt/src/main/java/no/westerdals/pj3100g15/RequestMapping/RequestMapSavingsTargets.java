package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.DBService.DBServiceSavingsTargets;
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
        //DBServiceConnection.closeConnection();
        return savingsTargets;
    }

    @RequestMapping(value = "/createsavingstargets/{name}/{customerId}/{kroner}/{oere}")
    @ResponseBody
    public boolean createSavingsTargets(@PathVariable(value = "name") String name,
                                        @PathVariable(value = "customerId") int customerId,
                                        @PathVariable(value = "kroner")BigInteger kroner,
                                        @PathVariable(value = "oere")int oere){
        if(DBServiceSavingsTargets.createSavingsTarget(kroner,oere,customerId,name)){
            //DBServiceConnection.closeConnection();
            return true;
        }
        //DBServiceConnection.closeConnection();
        return false;
    }

    // -------------------    Update savings targets under this line    ---------------------

    @RequestMapping(value = "/updatesavingstarget/{savingsTargetId}/name/{name}")
    @ResponseBody
    public boolean updateTargetName(@PathVariable(value = "savingsTargetId") int savingsTargetId,
                              @PathVariable(value = "name") String name){
        if(DBServiceSavingsTargets.updateTargetName(savingsTargetId, name)){
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/updatesavingstarget/{savingsTargetId}/goal/{kroner}/{oere}")
    @ResponseBody
    public boolean updateTargetGoal(@PathVariable(value = "savingsTargetId") int savingsTargetId,
                              @PathVariable(value = "kroner") BigInteger kroner,
                              @PathVariable(value = "oere") int oere){
        if(DBServiceSavingsTargets.updateTargetGoal(savingsTargetId, kroner, oere)){
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/addtotarget/{accountNo}/{savingsTargetId}/{kroner}/{oere}")
    @ResponseBody
    public boolean addToTarget(@PathVariable(value = "accountNo") String accountNumber,
                               @PathVariable(value = "savingsTargetId") int savingsTargetId,
                               @PathVariable(value = "kroner") BigInteger kroner,
                               @PathVariable(value = "oere") int oere){
        if(DBServiceSavingsTargets.addToTarget(accountNumber, savingsTargetId, kroner, oere)){
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/subtractfromtarget/{accountNo}/{savingsTargetId}/{kroner}/{oere}")
    @ResponseBody
    public boolean addToTarget(@PathVariable(value = "accountNo") String accountNumber,
                               @PathVariable(value = "savingsTargetId") int savingsTargetId,
                               @PathVariable(value = "kroner") BigInteger kroner,
                               @PathVariable(value = "oere") int oere){
        if(DBServiceSavingsTargets.subtractFromTarget(accountNumber, savingsTargetId, kroner, oere)){
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/deletesavingstargets/{savingsTargetsId}")
    @ResponseBody
    public boolean deleteSavingsTarggets(@PathVariable(value = "savingsTargetsId")int savingsTargetsId){
        if(DBServiceSavingsTargets.deleteSavingsTarget(savingsTargetsId)){
            return true;
        }
        return false;
    }
}
