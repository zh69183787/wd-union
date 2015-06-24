package com.wonders.stpt.match.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wonders.stpt.match.domain.MatchTheme;
import com.wonders.stpt.match.service.IMatchThemeService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

/**
 * Created by Administrator on 2014/8/19.
 */
@Controller
@RequestMapping("/theme")
public class MatchThemeController extends BaseController {


    @Autowired
    private IMatchThemeService matchThemeService;

    @RequestMapping(method= RequestMethod.GET,value="/themes")
    public String themes(MatchTheme theme,@RequestParam(required = false, defaultValue = "1") int pageIndex,
                         @RequestParam(required = false, defaultValue = "15") int pageSize,Model model)throws Exception{

    	PageList<MatchTheme> themeList=matchThemeService.getThemes(theme,pageIndex,pageSize );
    	model.addAttribute("theme", theme);
        addPageAttr(model,themeList);
        return "/theme/themes";
    }

    /**
     * 根据主键查找
     * @param matchThemeId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(method= RequestMethod.GET,value="{matchThemeId}")
    public String theme(@PathVariable String matchThemeId,Model model)throws Exception{
    	model.addAttribute("theme", matchThemeService.getMatchTheme(matchThemeId));
    	return "/theme/theme";
    }
    
    /**
     * 跳转到新增页面
     * @param matchTheme
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(method=RequestMethod.GET)
    public String news(MatchTheme matchTheme,Model model)throws Exception{
    	
    	return "/theme/theme";
    }
    /**
     * 添加
     * @param matchTheme
     * @param model
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(method=RequestMethod.POST)
    public String save(MatchTheme matchTheme,Model model,RedirectAttributes redirectAttributes)throws Exception{
    	matchThemeService.save(matchTheme, null);
    	redirectAttributes.addFlashAttribute("success",getMessage("save.success"));
    	return "redirect:theme/themes";
    }
    /**
     * 更新
     * @param matchThemeId
     * @param matchTheme
     * @param model
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(method=RequestMethod.PUT,value="{matchThemeId}")
    public String update(@PathVariable String matchThemeId,MatchTheme matchTheme,Model model,RedirectAttributes redirectAttributes)throws Exception{
    	matchThemeService.save(matchTheme, new String[]{"creator","createTime"});
    	redirectAttributes.addFlashAttribute("success",getMessage("save.success"));
    	return "redirect:themes";
    }
    /**
     * 删除(含批量删除)
     * @param matchThemeId
     * @param model
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(method=RequestMethod.DELETE,value="{matchThemeId}")
    public String delete(@PathVariable String matchThemeId,Model model,RedirectAttributes redirectAttributes)throws Exception{
    	matchThemeService.deletes(Arrays.asList(matchThemeId.split(",")));
    	redirectAttributes.addFlashAttribute("success",getMessage("delete.success"));
    	return "redirect:themes";
    }
}
