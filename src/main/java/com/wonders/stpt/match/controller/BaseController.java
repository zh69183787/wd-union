package com.wonders.stpt.match.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;


import com.wonders.stpt.utils.common.CustomTimestampEditor;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


public class BaseController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        timestampFormat.setLenient(true);
        binder.registerCustomEditor(Timestamp.class, new CustomTimestampEditor(timestampFormat, true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    public String getMessage(String key){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", Locale.getDefault());
        if(resourceBundle.containsKey(key)){
            return resourceBundle.getString(key);
        }
        return null;
    }

    public void addPageAttr(Model model,PageList pageList,String listKey){
        model.addAttribute("totalPages",pageList.getPageInfo().getTotalPages());
        model.addAttribute("totalRows",pageList.getPageInfo().getTotalRows());
        model.addAttribute("pageSize",pageList.getPageInfo().getPageSize());
        model.addAttribute("pageIndex",pageList.getPageInfo().getPageIndex());
        if(StringUtils.isBlank(listKey)){
            listKey = "list";
        }
        model.addAttribute(listKey,pageList);
    }

    public void addPageAttr(Model model,PageList pageList){
        addPageAttr(model,pageList,null);
    }
}