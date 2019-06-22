/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj.media.controller;

import com.google.gson.Gson;
import com.learning.bean.MathResault;
import com.learning.model.Person;
import com.learning.model.ApiModel;
import com.learning.service.LanguageService;
import com.learning.service.PersonService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Admin
 */
//@Controller
@Controller
//@RestControllerEnableWebMvc
@RequestMapping("/api")
//@SessionScope
//@Scope("request")
//@Scope("session")
public class ApiController {

    @RequestMapping(value = {"/start"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String onSntart(Model model) {
        model.addAttribute("msg", "hello");

        return "api/index";
    }

    @RequestMapping(value = {"/chiadu"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String laydu(Model model) {
        return "api/laydu";
    }

//    @RequestMapping(value = {"/sendMessage"}, method = RequestMethod.GET,
//            produces = "application/json; charset=utf-8")
//    public String sendMessage(Model model, HttpServletRequest request,
//            @RequestParam(value = "message", defaultValue = "") String message) {
//
//        model.addAttribute("msg", message);
//
//        return "api/index";
//    }
    
      @RequestMapping(value = {"/sendMessage"}, method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    public String sendMessage(Model model, HttpServletRequest request,
            @ModelAttribute(name="obj") ApiModel api ) {

        model.addAttribute("msg",api.getMessageT());

        return "api/index";
    }

    @RequestMapping(value = {"/sum"}, method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public String sum(Model model, HttpServletRequest request,
            @RequestParam(value = "firstNumber", defaultValue = "0") Integer firstNumber,
            @RequestParam(value = "secondNumber", defaultValue = "0") Integer secondNumber) {

        return String.valueOf(firstNumber + secondNumber);
    }

    @RequestMapping(value = {"/laydu"}, method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String chiadu(Model model,
            @RequestParam(value = "firstNumber", defaultValue = "0") Integer firstNumber,
            @RequestParam(value = "secondNumber", defaultValue = "0") Integer secondNumber) {

        Gson gson = new Gson();
        MathResault mr = new MathResault();

        mr.setDu(firstNumber % secondNumber);
        mr.setThuong(firstNumber / secondNumber);

        return gson.toJson(mr);

    }

    @RequestMapping(value = {"/pathVariableTest/key/{keyVal}/value/{dataVal}"},
             method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String pathVariableTest(Model model,
            @PathVariable(name = "keyVal", required = false) String keyVal,
            @PathVariable(name = "dataVal", required = false) String dataVal) {

        model.addAttribute("msg", "keyVal :" + keyVal + " dataVal " + dataVal);
        return "api/index";
    }

}
