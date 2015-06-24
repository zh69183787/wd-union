package com.wonders.stpt.match.controller;

import java.util.Arrays;

import com.wonders.stpt.match.domain.PrizeRule;
import com.wonders.stpt.match.service.IApplicantDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wonders.stpt.match.domain.MPrize;
import com.wonders.stpt.match.domain.Match;
import com.wonders.stpt.match.service.IMatchService;
import com.wonders.stpt.match.service.IPrizeService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

/**
 * Created by Administrator on 2014/9/4.
 */
@Controller
@RequestMapping("/match/{matchId}/prize")
public class PrizeController extends BaseController {

    @Autowired
    private IMatchService matchService;

    @Autowired
    private IPrizeService prizeService;

    @Autowired
    private IApplicantDepartmentService applicantDepartmentService;


    @RequestMapping(method = RequestMethod.GET,value="{prizeId}/applicantInfo")
    public String applicant(@PathVariable String prizeId) throws Exception {

        MPrize prize = prizeService.getPrize(prizeId);
        String forward = "";

        if(MPrize.PRIZE_TYPE_PERSON.equals(prize.getPrizeType())){
            forward = "redirect:/personPrize";
        }

        if(MPrize.PRIZE_TYPE_TEAM.equals(prize.getPrizeType())){
            forward = "redirect:/teamPrize";
        }

        if(MPrize.PRIZE_TYPE_PROJECT.equals(prize.getPrizeType())){
            forward = "redirect:/projectPrize";
        }

        if(MPrize.PRIZE_TYPE_PROJECT_ACHIEVEMENT.equals(prize.getPrizeType())){
            forward = "redirect:/projectAchievement";
        }

        return forward+"?prize.prizeId="+prizeId+"&prize.matchId="+prize.getMatchId();
    }


    @RequestMapping(method = RequestMethod.GET, value = "prizes")
    public String prizes(@PathVariable String matchId, @RequestParam(required = false, defaultValue = "1") int pageIndex,
                         @RequestParam(required = false, defaultValue = "15") int pageSize, Model model) throws Exception {
        model.addAttribute(matchService.getMatch(matchId));
        MPrize prize = new MPrize();
        prize.setMatchId(matchId);
        PageList<MPrize> prizeList = prizeService.getPrizes(prize);
        model.addAttribute("list", prizeList);
        return "match/prize/prizes";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String form(MPrize prize, Model model) throws Exception {
        model.addAttribute("prize", prize);
        return "match/prize/prize";
    }

    /**
     * 添加
     *
     * @param prize
     * @param model
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST)
    public String save(MPrize prize, Model model, RedirectAttributes redirectAttributes){
        try{
            prizeService.save(prize,null);
        }catch (Exception ex){
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("prize",prize);
            return "match/prize/prize";
        }

        redirectAttributes.addFlashAttribute("success", getMessage("save.success"));
        return "redirect:/match/{matchId}/detail";
    }

    @RequestMapping(method = RequestMethod.GET, value = "{prizeId}")
    public String prize(@PathVariable String prizeId, Model model) throws Exception {
        MPrize prize = prizeService.getPrize(prizeId);
        model.addAttribute("prize", prize);
        model.addAttribute("match", matchService.getMatches(new Match()));
        return "match/prize/prize";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{prizeId}")
    public String update(@PathVariable String prizeId, MPrize prize, Model model, RedirectAttributes redirectAttributes) throws Exception {

        try{
            prizeService.save(prize, new String[]{"creator", "createTime"});
        }catch (Exception ex){
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("prize",prize);
            return "match/prize/prize";
        }
        redirectAttributes.addFlashAttribute("success", getMessage("save.success"));
        return "redirect:/match/{matchId}/detail";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{prizeId}")
    public String delete(@PathVariable String prizeId, Model model, RedirectAttributes redirectAttributes) throws Exception {
        prizeService.delete(Arrays.asList(prizeId.split(",")));
        redirectAttributes.addFlashAttribute("success", getMessage("save.success"));
        return "redirect:/match/{matchId}/detail";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/applicant")
    public void saveDepartment( MPrize prize, Model model) throws Exception {

        applicantDepartmentService.save(prize.getApplicationDepartmentList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "{prizeId}/applicant")
    public void applicantDepartment(@PathVariable String prizeId, Model model) throws Exception {
        model.addAttribute("appDept", applicantDepartmentService.getApplicantDepartment(prizeId));
    }

}
