/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj.media.controller;

import com.google.gson.Gson;
import com.learning.bean.LineChartModel;
import com.learning.bean.LineObj;
import com.learning.model.Animal;
import com.learning.service.LanguageService;
import com.pj.media.util.StudentUtils;
import com.pj.media.util.Utils;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tools.ant.taskdefs.XSLTProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author thanhlong
 */
@Controller
public class HomeControllder {

    @Autowired
    @Qualifier("tienganh")
    private LanguageService languageService;

    @Value("${IMAGE_FOLDER}")
    public String IMAGE_FOLDER;

    @Value("${IMAGE_URL}")
    public String IMAGE_URL;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String onSntart(Model model) {
        //return "redirect:/person/list";
        model.addAttribute("msg", languageService.sayHello());
        return "index";
    }

    @RequestMapping(value = {"/fragments"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String fragments(Model model) {
        model.addAttribute("data", StudentUtils.buildStudents());
        return "fragments";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String login(Model model) {

        return "login";
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {

        Utils.removeUsersInSession(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @RequestMapping(value = {"/chart"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String chart(Model model) {
        Gson gson = new Gson();

        LineChartModel chartModel = new LineChartModel();
        List<String> categories = new ArrayList<>();

        categories.add("Aug");
        categories.add("Sep");
        categories.add("Oct");
        categories.add("Nov");
        categories.add("Dec");
        chartModel.setCategories(categories);

        LineObj lineObj1 = new LineObj();
        lineObj1.setName("Ha Noi");
        List<Double> data1 = new ArrayList<>();

        data1.add(8D);
        data1.add(9.2D);
        data1.add(10D);
        data1.add(11D);
        data1.add(12D);
        lineObj1.setData(data1);
        chartModel.getSeries().add(lineObj1);

        LineObj lineObj2 = new LineObj();
        lineObj2.setName("HCM");
        List<Double> data2 = new ArrayList<>();
        data2.add(11D);
        data2.add(12D);
        data2.add(null);
        data2.add(22D);
        data2.add(30D);
        lineObj2.setData(data2);
        chartModel.getSeries().add(lineObj2);

        model.addAttribute("chartModel", gson.toJson(chartModel));
        return "/chart/index";
    }

    @RequestMapping(value = {"/home/sum"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String sum(Model model,
            @RequestParam(value = "num1", defaultValue = "0") Integer num1,
            @RequestParam(value = "num2", defaultValue = "0") Integer num2,
            @RequestParam(value = "ten", defaultValue = "") String ten3
    ) {

        int total = num1 + num2;
        String ten = ten3;

        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("total", total);
        model.addAttribute("ten", ten);
        return "ct/page";
    }

    @RequestMapping(value = {"/demoChart"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String demoChart(Model model) {
        LineChartModel chartModel = new LineChartModel();
        List<String> categories = new ArrayList<>();
        Gson gson = new Gson();
        categories.add("2012");
        categories.add("2013");
        categories.add("2014");
        chartModel.setCategories(categories);

        Animal dog = new Animal();
        dog.setName("dog");
        dog.getData().add(10);
        dog.getData().add(20);
        dog.getData().add(30);
        chartModel.getSeries().add(dog);

        Animal cat = new Animal();
        cat.setName("cat");
        cat.getData().add(10);
        cat.getData().add(50);
        cat.getData().add(20);

        chartModel.getSeries().add(cat);
        Animal ga = new Animal();
        ga.setName("ga");
        ga.getData().add(10);
        ga.getData().add(2);
        ga.getData().add(20);
        chartModel.getSeries().add(ga);

        model.addAttribute("chartModel", gson.toJson(chartModel));

        return "chart/demoChart";
    }

    @RequestMapping(value = {"/uploadFile"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")

    public String uploadFileDemoGer() {

        return "uploadExample/index";

    }

    @RequestMapping(value = {"/uploadFileDemo"}, method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")

    public String uploadFile(Model model,
            @RequestParam(value = "uploadFile") MultipartFile uploadedFile,
            @RequestParam(value = "uploadFile1") MultipartFile uploadedFile1) {

        if (uploadedFile != null && !uploadedFile.isEmpty() && uploadedFile != null && !uploadedFile.isEmpty()) {

            try {
                byte[] bytes = uploadedFile.getBytes();
                byte[] bytes1 = uploadedFile1.getBytes();

                String newFileName = uploadedFile.getOriginalFilename();
                String newFileName1 = uploadedFile1.getOriginalFilename();

                Path path = Paths.get(IMAGE_FOLDER + File.separator + newFileName);
                Path path1 = Paths.get("F:\\software\\xampp\\tomcat\\webapps\\image" + File.separator + newFileName1);
                Files.write(path, bytes);
                Files.write(path1, bytes1);

                model.addAttribute("msg", "upload Thanh cong");

                model.addAttribute("url",IMAGE_URL + newFileName);
                model.addAttribute("url1", "http://localhost:8081/image/" + newFileName1);

            } catch (Exception e) {

                e.printStackTrace();

                model.addAttribute("msg", "upload That bai");

            }

            return "uploadExample/index";

        } else {

            model.addAttribute("msg", "Chua upload file");

            return "uploadExample/index";

        }

    }

    @RequestMapping(value = {"/uploadFileDemoAjax"},
            method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public String uploadFileDemoAjax(
            @RequestParam(value = "uploadFile") MultipartFile uploadedFile) {

        if (uploadedFile != null && !uploadedFile.isEmpty()) {

            try {

                byte[] bytes = uploadedFile.getBytes();

                String newFileName = uploadedFile.getOriginalFilename();
                int indx = newFileName.lastIndexOf(".");
                String fileType = newFileName.substring(indx);
                
             
                        
                            
               
                Path path = Paths.get("F:\\software\\xampp\\tomcat\\webapps\\image" + File.separator + newFileName);
                Files.write(path, bytes);
                return "Success|" + "http://localhost:8081/image/" + newFileName;
                
               
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "Fail";

        } else {

            return "Fail|File upload null";

        }
    }

}
