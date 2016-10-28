package no.westerdals.PJ3100g15;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Eva Dahlo on 29/09/2016.
 */
public abstract class LogItem {
    final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    final Date time;
    final String timeString;
    final String id;

    protected LogItem(int logCount, String customerID){
        time = new Date();
        id = customerID + " " + logCount;
        timeString = dateFormat.format(time);
    }
}
