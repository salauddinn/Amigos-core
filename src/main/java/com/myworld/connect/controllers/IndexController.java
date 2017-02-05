//package com.myworld.connect.controllers;
//
//
//import org.springframework.boot.autoconfigure.web.ErrorController;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class IndexController implements ErrorController {
//
//    private static final String ERROR_PATH = "/error";
//
//    @Override
//    public String getErrorPath() {
//        return ERROR_PATH;
//    }
//
//    @RequestMapping(value = ERROR_PATH)
//    public String showError() {
//        return "Requested Page Doesn't Exist\n";
//    }
//}
