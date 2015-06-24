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
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wonders.stpt.match.domain.MProjectPrize;
import com.wonders.stpt.match.service.IProjectPrizeService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

@Controller
@RequestMapping("/projectPrize")
public class ProjectPrizeController extends BaseController {
	@Autowired
	private IProjectPrizeService projectPrizeService;
	/**
	 * 分页显示
	 * @param projectPrize
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET,value="/projectPrizes")
	public String projectPrizes(MProjectPrize projectPrize,@RequestParam(required = false, defaultValue = "1") int pageIndex,
            @RequestParam(required = false, defaultValue = "15") int pageSize,Model model)throws Exception{
		PageList<MProjectPrize> projectPrizeList=projectPrizeService.getProjectPrizes(projectPrize, pageIndex, pageSize);
		model.addAttribute("projectPrize", projectPrize);
		addPageAttr(model, projectPrizeList);
		return "/projectPrize/projectPrizes";
	}
	/**
	 * 跳转到新增页面
	 * @param projectPrize
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String form(MProjectPrize projectPrize,Model model)throws Exception{
		model.addAttribute("projectPrize",projectPrize);
		return "/projectPrize/projectPrize";
	}
	/**
	 * 添加
	 * @param projectPrize
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String save(MProjectPrize projectPrize,Model model,RedirectAttributes redirectAttributes)throws Exception{
		projectPrizeService.save(projectPrize);
		redirectAttributes.addFlashAttribute("success",getMessage("save.success"));

        return "redirect:/projectPrize?prize.prizeId="+projectPrize.getPrizeId()+"&prize.matchId="+projectPrize.getPrize().getMatchId();
	}
	/**
	 * 根据主键查找
	 * @param projectPrizeId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET,value="{projectPrizeId}")
	public String projectPrize(@PathVariable String projectPrizeId,Model model)throws Exception{
		model.addAttribute("projectPrize", projectPrizeService.getProjectPrize(projectPrizeId));
		return "/projectPrize/projectPrize";
	}
	/**
	 * 更新
	 * @param projectPrizeId
	 * @param projectPrize
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.PUT,value="{projectPrizeId}")
	public String update(@PathVariable String projectPrizeId,MProjectPrize projectPrize,Model model,RedirectAttributes redirectAttributes)throws Exception{
		projectPrizeService.update(projectPrize, new String[]{"creator","createTime"}, false);
		redirectAttributes.addFlashAttribute("success",getMessage("save.success"));
        return "redirect:/match/"+projectPrize.getPrize().getMatchId()+"/detail?tab=2";
	}
	/**
	 * 删除记录(包含批量删除)
	 * @param projectPrizeId
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.DELETE,value="{projectPrizeId}")
	public String delete(@PathVariable String projectPrizeId,Model model,RedirectAttributes redirectAttributes)throws Exception{
        List list = Arrays.asList(projectPrizeId.split(","));
		projectPrizeService.delete(list);
		redirectAttributes.addFlashAttribute("success",getMessage("delete.success"));
        MProjectPrize projectPrize = projectPrizeService.getProjectPrize((String)list.get(0));
        return "redirect:/match/"+projectPrize.getPrize().getMatchId()+"/detail?tab=2";
	}
}
