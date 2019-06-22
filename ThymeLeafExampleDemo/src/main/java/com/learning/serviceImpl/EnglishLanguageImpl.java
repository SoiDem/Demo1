/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.serviceImpl;

import com.learning.service.LanguageService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
//@Repository
//@Component
public class EnglishLanguageImpl implements LanguageService {

    @Override

    public String sayHello() {
        return "Hello! How are you";
    }

}
