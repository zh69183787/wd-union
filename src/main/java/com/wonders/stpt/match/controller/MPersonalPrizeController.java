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

import com.wonders.stpt.match.domain.MPersonalPrize;
import com.wonders.stpt.match.service.IMPersonalPrizeService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

/**
 * 个人材料申报
 * @author shanweifeng
 *
 */
@Controller
@RequestMapping("/personPrize")
public class MPersonalPrizeController extends BaseController {
	@Autowired
	private IMPersonalPrizeService mPersonalPrizeService;
	/**
	 * 分页查找显示
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/persons")
	public String persons(MPersonalPrize personalPrize,@RequestParam(required = false, defaultValue = "1") int pageIndex,
            @RequestParam(required = false, defaultValue = "15") int pageSize,Model model)throws Exception{
			PageList<MPersonalPrize> personalPrizeList=mPersonalPrizeService.getMPersonalPrizes(personalPrize, pageIndex, pageSize);
			model.addAttribute("personalPrize", personalPrize);
			addPageAttr(model,personalPrizeList);
			return null;
	}
	/**
	 * 跳转到新增页面
	 * @param personalPrize
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String form(MPersonalPrize personalPrize,Model model)throws Exception{
        model.addAttribute("personalPrize",personalPrize);
		return "/personPrize/person";
	}
	
	/**
	 * 新增
	 * @param personalPrize
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String save(MPersonalPrize personalPrize,Model model,RedirectAttributes redirectAttributes)throws Exception{
		mPersonalPrizeService.save(personalPrize);
        model.addAttribute("success", getMessage("save.success"));
		return "redirect:/personPrize?prize.prizeId="+personalPrize.getPrizeId()+"&prize.matchId="+personalPrize.getPrize().getMatchId();
	}
	/**
	 * 根据主键查找(跳转到修改页面)
	 * @param personalPrizeId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET,value="{personalPrizeId}")
	public String person(@PathVariable String personalPrizeId,Model model)throws Exception{
		model.addAttribute("personalPrize", mPersonalPrizeService.getMPersonalPrize(personalPrizeId));
		return "/personPrize/person";
	}
	
	/**
	 * 更新
	 * @param personalPrizeId
	 * @param personalPrize
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.PUT,value="{personalPrizeId}")
	public String update(@PathVariable String personalPrizeId,MPersonalPrize personalPrize,Model model,RedirectAttributes redirectAttributes)throws Exception{
		mPersonalPrizeService.update(personalPrize, new String[]{"creator","createTime"}, false);
        redirectAttributes.addFlashAttribute("success", getMessage("save.success"));
		return "redirect:/match/"+personalPrize.getPrize().getMatchId()+"/detail";
	}
	/**
	 * 删除记录(含批量删除)
	 * @param personalPrizeId
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.DELETE,value="{personalPrizeId}")
	public String delete(@PathVariable String personalPrizeId,Model model,RedirectAttributes redirectAttributes)throws Exception{
        List list = Arrays.asList(personalPrizeId.split(","));
        MPersonalPrize personalPrize = mPersonalPrizeService.getMPersonalPrize((String)list.get(0));
		mPersonalPrizeService.delete(list);
		redirectAttributes.addFlashAttribute("success",getMessage("delete.success"));
        return "redirect:/match/"+personalPrize.getPrize().getMatchId()+"/detail";
	}
	
	
}
