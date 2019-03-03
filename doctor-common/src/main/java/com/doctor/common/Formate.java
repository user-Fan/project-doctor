package com.doctor.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formate {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse("2008-08-08");
            System.out.println( date.toString());
        }catch (Exception e){
            e.getMessage();
        }

    }
    public static Date format(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse("2008-08-08");
            return date;
        }catch (Exception e){
            e.getMessage();
        }
        return date;
    }
}
