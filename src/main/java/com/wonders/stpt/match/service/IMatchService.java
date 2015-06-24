package com.wonders.stpt.match.service;

import com.wonders.stpt.match.domain.MApplicantDepartment;
import com.wonders.stpt.match.domain.Match;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

import java.util.List;

/**
 * Created by Administrator on 2014/8/25.
 */
public interface IMatchService {

    PageList<Match> getMatches(Match match,Integer pageIndex,Integer pageSize) throws Exception;

    PageList<Match> getMatches(Match match) throws Exception;

    Match getMatch(String matchId) throws Exception;

    int delete(List<String> matchIds) throws Exception;

    int delete(String matchId) throws Exception;

    void save(Match match,String[] excludes) throws Exception;

    void commit(List<Match> matches) throws Exception;

    void commit(Match match) throws Exception;

    void update(Match match ,boolean isDynamic)throws Exception;


}
