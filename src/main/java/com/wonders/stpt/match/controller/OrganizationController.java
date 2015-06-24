package com.wonders.stpt.match.controller;

import com.wonders.stpt.match.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2014/9/9.
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private IOrganizationService organizationService;

    @RequestMapping(method = RequestMethod.GET)
    public void organization(Model model) throws Exception{
        model.addAttribute(organizationService.getDepartments("2500"));
    }

    @RequestMapping(method = RequestMethod.GET,value = "{deptId}/leader")
    public void leader(@PathVariable String deptId,Model model) throws Exception{
        model.addAttribute(organizationService.getDepartmentLeader(deptId));
    }

    @RequestMapping(method = RequestMethod.GET,value = "{deptId}/employee")
    public void employee(@PathVariable String deptId,Model model) throws Exception{
        model.addAttribute(organizationService.getEmployee(deptId));
    }
}
