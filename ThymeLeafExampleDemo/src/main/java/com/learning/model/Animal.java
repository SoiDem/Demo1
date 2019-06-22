/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.model;

import java.util.ArrayList;
import java.util.List;





/**
 *
 * @author DELL
 */
public class Animal {
    public String name;
    public List<Integer> data= new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
    
    

 
    
    
    
}
