package com.wonders.stpt.match.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2014/9/12.
 */
@Controller
public class IndexController {
    @RequestMapping(method = RequestMethod.GET,value = "/index")
    public void index(){
        System.out.println("---");
    }
    @RequestMapping(method = RequestMethod.GET,value = "/header")
    public void header(){

    }
    @RequestMapping(method = RequestMethod.GET,value = "/main")
    public void main(){

    }
}
