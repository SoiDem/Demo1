/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thanh_Long
 */
public class LineChartModel {

    public List<String> categories = new ArrayList<>();
    public List<Object> series = new ArrayList<>();

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Object> getSeries() {
        return series;
    }

    public void setSeries(List<Object> series) {
        this.series = series;
    }

    

    
  

}
