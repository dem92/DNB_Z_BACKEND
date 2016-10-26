package no.westerdals.PJ3100g15;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Eva Dahlo on 29/09/2016.
 */
public abstract class Log {
    int logCount;

    abstract LogItem logEvent();
}
