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

import com.wonders.stpt.match.domain.MTeamPrize;
import com.wonders.stpt.match.service.ITeamPrizeService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

/**
 * 集体资料申报
 * @author shanweifeng
 *
 */
@Controller
@RequestMapping("/teamPrize")
public class TeamPrizeController extends BaseController {
	@Autowired
	private ITeamPrizeService teamPrizeService;
	/**
	 * 分页显示
	 * @param teamPrize
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET,value="/teams")
	public String teams(MTeamPrize teamPrize,@RequestParam(required = false, defaultValue = "1") int pageIndex,
            @RequestParam(required = false, defaultValue = "15") int pageSize,Model model)throws Exception{
		PageList<MTeamPrize> teamPrizeList=teamPrizeService.getMTeamPrizes(teamPrize, pageIndex, pageSize);
		model.addAttribute("teamPrize", teamPrize);
		addPageAttr(model, teamPrizeList);
		return "/teamPrize/teams";
	}
	/**
	 * 跳转到新增页面
	 * @param teamPrize
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String form(MTeamPrize teamPrize,Model model)throws Exception{
        model.addAttribute("teamPrize",teamPrize);
		return "/teamPrize/team";
	}
	/**
	 * 新增
	 * @param teamPrize
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String save(MTeamPrize teamPrize,Model model,RedirectAttributes redirectAttributes)throws Exception{
		teamPrizeService.save(teamPrize);
		redirectAttributes.addFlashAttribute("success",getMessage("save.success"));
        return "redirect:/teamPrize?prize.prizeId="+teamPrize.getPrizeId()+"&prize.matchId="+teamPrize.getPrize().getMatchId();
	}
	/**
	 * 根据主键查找
	 * @param teamPrizeId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET,value="{teamPrizeId}")
	public String team(@PathVariable String teamPrizeId,Model model)throws Exception{
		model.addAttribute("teamPrize", teamPrizeService.getMTeamPrize(teamPrizeId));
		return "/teamPrize/team";
	}
	
	/**
	 * 更新
	 * @param teamPrizeId
	 * @param teamPrize
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.PUT,value="{teamPrizeId}")
	public String update(@PathVariable String teamPrizeId,MTeamPrize teamPrize,Model model,RedirectAttributes redirectAttributes)throws Exception{

        teamPrizeService.update(teamPrize, new String[]{"creator","createTime"}, false);
        redirectAttributes.addFlashAttribute("success", getMessage("save.success"));
        return "redirect:/match/"+teamPrize.getPrize().getMatchId()+"/detail?tab=1";
	}
	@RequestMapping(method=RequestMethod.DELETE,value="{teamPrizeId}")
	public String delete(@PathVariable String teamPrizeId,Model model,RedirectAttributes redirectAttributes)throws Exception{
        List list = Arrays.asList(teamPrizeId.split(","));
        MTeamPrize teamPrize = teamPrizeService.getMTeamPrize((String)list.get(0));
        teamPrizeService.delete(list);
		redirectAttributes.addFlashAttribute("success",getMessage("delete.success"));
        return "redirect:/match/"+teamPrize.getPrize().getMatchId()+"/detail?tab=1";
	}
}
