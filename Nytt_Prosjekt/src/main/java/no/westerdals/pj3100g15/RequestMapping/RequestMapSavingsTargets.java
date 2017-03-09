package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.DBService.DBServiceConnection;
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
