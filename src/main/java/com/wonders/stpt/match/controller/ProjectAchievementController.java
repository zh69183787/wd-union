package com.wonders.stpt.match.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wonders.stpt.match.domain.MProjectAchievement;
import com.wonders.stpt.match.service.IProjectAchievementService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

@Controller
@RequestMapping("/projectAchievement")
public class ProjectAchievementController extends BaseController {
	@Autowired
	private IProjectAchievementService projectAchievementService;
	/**
	 * 分页查询
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET,value="/projectAchievements")
	public String projectAchievements(MProjectAchievement projectAchievement,@RequestParam(required = false, defaultValue = "1") int pageIndex,
            @RequestParam(required = false, defaultValue = "15") int pageSize,Model model)throws Exception{
		PageList<MProjectAchievement> projectAchievementList=projectAchievementService.getProjectAchievements(projectAchievement, pageIndex, pageSize);
		model.addAttribute("projectAchievement", projectAchievement);
		addPageAttr(model, projectAchievementList);
		return "/projectAchievement/projectAchievements";
	}
	/**
	 * 跳转到新增页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String form(MProjectAchievement projectAchievement,Model model)throws Exception{
        model.addAttribute("projectAchievement",projectAchievement);
		return "/projectAchievement/projectAchievement";
	}
	/**
	 * 添加
	 * @param projectAchievement
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String save(MProjectAchievement projectAchievement,Model model,RedirectAttributes redirectAttributes)throws Exception{
		projectAchievementService.save(projectAchievement);
		redirectAttributes.addFlashAttribute("success",getMessage("save.success"));
        return "redirect:/projectAchievement?prize.prizeId="+projectAchievement.getPrizeId()+"&prize.matchId="+projectAchievement.getPrize().getMatchId();
	}
	/**
	 * 根据主键查找
	 * @param projectAchievementId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET,value="{projectAchievementId}")
	public String projectAchievement(@PathVariable String projectAchievementId,Model model)throws Exception{
		model.addAttribute("projectAchievement", projectAchievementService.getProjectAchievement(projectAchievementId));
		return "/projectAchievement/projectAchievement";
	}
	/**
	 * 更新
	 * @param projectAchievementId
	 * @param projectAchievement
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.PUT,value="{projectAchievementId}")
	public String update(@PathVariable String projectAchievementId,MProjectAchievement projectAchievement,Model model,RedirectAttributes redirectAttributes)throws Exception{
		projectAchievementService.update(projectAchievement, new String[]{"creator","createTime"}, false);
        redirectAttributes.addFlashAttribute("success", getMessage("save.success"));
        return "redirect:/match/"+projectAchievement.getPrize().getMatchId()+"/detail?tab=3";
	}
	/**
	 * 删除(包含批量删除)
	 * @param projectAchievementId
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="{projectAchievementId}")
	public String delete(@PathVariable String projectAchievementId,Model model,RedirectAttributes redirectAttributes)throws Exception{
        List list = Arrays.asList(projectAchievementId.split(","));
		projectAchievementService.delete(list);
		redirectAttributes.addFlashAttribute("success",getMessage("delete.success"));
        MProjectAchievement projectPrize = projectAchievementService.getProjectAchievement((String)list.get(0));

        return "redirect:/match/"+projectPrize.getPrize().getMatchId()+"/detail?tab=3";
	}
}
