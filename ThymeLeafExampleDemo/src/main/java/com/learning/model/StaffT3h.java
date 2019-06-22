/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "STAFF")
public class StaffT3h implements Serializable {

    private Long id;
    private String staffCode;
    private String staffName;
    private ShopT3h shop;

    @Id
    @Column(name = "STAFF_ID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "STAFF_CODE")
    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    @Column(name = "STAFF_NAME")
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    //@ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOP_ID", referencedColumnName = "ID",
            insertable = false,updatable = false, 
            nullable = true)
    //@JoinColumn(name = "isbn",referencedColumnName = "isbn")

    public ShopT3h getShop() {
        return shop;
    }

    public void setShop(ShopT3h shop) {
        this.shop = shop;
    }
   

 
    }
