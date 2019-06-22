/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class DataTest {
    public static void main(String[] args) {
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateValue="26-05-2019 18:00:00";
        try {
            Date date=dateFormat.parse(dateValue);
        
        }catch(ParseException ex){
           
        }
        
        System.out.println("now:"+dateFormat.format(new Date()));
    }
}
