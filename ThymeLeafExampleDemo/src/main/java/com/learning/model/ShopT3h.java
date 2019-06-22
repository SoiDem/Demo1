/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "Shop")
public class ShopT3h implements Serializable {

    private Long id;
    private String shopCode;
    private String shopName;
    private Set<StaffT3h> staffs;

    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "SHOP_CODE")
    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    @Column(name = "SHOP_NAME")
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop", cascade = CascadeType.ALL)

    public Set<StaffT3h> getStaffs() {
        return staffs;
    }

    public void setStaffs(Set<StaffT3h> staffs) {
        this.staffs = staffs;
    }

}
