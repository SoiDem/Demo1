/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.serviceImpl;

import com.learning.service.LanguageService;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Admin
 */
public class VietnameseLanguageImpl implements LanguageService{

    @Override
    
    public String sayHello() {
        return "Xin chao! Ban khoe khong";
    }
    
}
