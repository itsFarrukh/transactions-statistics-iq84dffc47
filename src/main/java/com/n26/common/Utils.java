package com.n26.common;

import java.time.Instant;

public class Utils {

    public static boolean isValidDate(Instant dateTime){
         return dateTime.isAfter(Instant.now());
    }

}
