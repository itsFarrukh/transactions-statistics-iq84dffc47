package com.n26.common;

import com.n26.exception.UnprocessableException;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.now;

public class Utils {
    public static LocalDateTime getDateTime (String dateStr) throws UnprocessableException {
        try {
            return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(Constants.ISO_8601_FORMAT));
        }catch (Exception e){
            throw new UnprocessableException();
        }

    }
    public static BigDecimal getAmount(String amountValue) throws UnprocessableException{
        try{
            return new BigDecimal(amountValue);
        }catch (Exception e){
            throw new UnprocessableException();
        }
    }
    public static boolean isValidDate(Instant dateTime){
        LocalDate localDate = dateTime.atZone(ZoneOffset.UTC).toLocalDate();
       /* if(localDate.isBefore(now(ZoneOffset.UTC))){
            return true;

        }else if(localDate.isEqual(now(ZoneOffset.UTC))){
            if(dateTime.getEpochSecond()> Instant.now().getEpochSecond()){
                return false;
            }else
                return true;
        }else
            return false;*/
       return localDate.isBefore(now(ZoneOffset.UTC))?true:false;
    }

}
