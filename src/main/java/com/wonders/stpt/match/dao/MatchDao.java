package com.wonders.stpt.match.dao;

import com.wonders.stpt.match.domain.Match;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageBounds;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

import java.util.List;

/**
 * Created by Administrator on 2014/8/22.
 */
public interface MatchDao extends GenericDAO<Match> {
    Match selectById(String matchId);
}
