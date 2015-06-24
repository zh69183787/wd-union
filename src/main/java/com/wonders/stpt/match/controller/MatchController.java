package com.wonders.stpt.match.controller;

import com.wonders.stpt.match.domain.MPrize;
import com.wonders.stpt.match.domain.Match;
import com.wonders.stpt.match.service.IMatchService;
import com.wonders.stpt.match.service.IPrizeService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

/**
 * Created by Administrator on 2014/8/25.
 */
@Controller
@RequestMapping("/" + MatchController.NAMESPACE)
public class MatchController extends BaseController {

    @Autowired
    private IMatchService matchService;

    @Autowired
    private IPrizeService prizeService;

    protected static final String NAMESPACE = "match";

    @RequestMapping(method = RequestMethod.GET, value = "matches")
    public String matches(Match match, @RequestParam(required = false, defaultValue = "1") int pageIndex,
                          @RequestParam(required = false, defaultValue = "15") int pageSize, Model model) throws Exception {
        PageList<Match> matchList = matchService.getMatches(match, pageIndex, pageSize);
        addPageAttr(model, matchList);
        return "match/matches";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String form(Match match, Model model) {

        model.addAttribute("match", match);
        return "match/match";
    }

    @RequestMapping(method = RequestMethod.GET, value = "{matchId}")
    public String match(@PathVariable String matchId, Model model) throws Exception {
        model.addAttribute(matchService.getMatch(matchId));
        return "match/match";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{matchId}")
    public String delete(@PathVariable String matchId, String matchThemeId, RedirectAttributes redirectAttributes) throws Exception {
        matchService.delete(Arrays.asList(matchId.split(",")));
        redirectAttributes.addFlashAttribute("success", getMessage("delete.success"));
        return "redirect:matches?matchThemeId="+matchThemeId;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{matchId}")
    public String update(@PathVariable String matchId, Match match, Model model, RedirectAttributes redirectAttributes) throws Exception {

        matchService.save(match, new String[]{"creator", "createTime", "attachments", "matchTheme"});
        redirectAttributes.addFlashAttribute("success", getMessage("save.success"));
        return "redirect:matches?matchThemeId=" + match.getMatchThemeId();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(Match match, Model model, RedirectAttributes redirectAttributes) throws Exception {

        matchService.save(match, null);
        redirectAttributes.addFlashAttribute("success", getMessage("save.success"));

        return "redirect:match/matches?matchThemeId=" + match.getMatchThemeId();
    }


    @RequestMapping(method = RequestMethod.PUT, value = "{matchId}/operator")
    public String operator(@PathVariable String matchId, Match match, Model model) throws Exception {
        matchService.update(match, true);
        model.addAttribute("result", "1");
        return "";
    }


    @RequestMapping(method = RequestMethod.GET, value = "{matchId}/detail")
    public String detail(@PathVariable String matchId, Model model) throws Exception {
        MPrize prize = new MPrize();
        prize.setMatchId(matchId);
        PageList<MPrize> prizeList = prizeService.getPrizes(prize);
        model.addAttribute(matchService.getMatch(matchId));
        model.addAttribute("list", prizeList);
        return "match/detail";
    }

}
