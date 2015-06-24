package com.wonders.stpt.match.service.impl;

import com.wonders.stpt.match.dao.MatchDao;
import com.wonders.stpt.match.domain.Attachment;
import com.wonders.stpt.match.domain.Match;
import com.wonders.stpt.match.service.IAttachmentService;
import com.wonders.stpt.match.service.IMatchService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageBounds;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/8/25.
 */
@Service
public class MatchServiceImpl implements IMatchService {

    @Autowired
    private MatchDao matchDao;

    @Autowired
    private IAttachmentService attachmentService;


    @Override
    public PageList<Match> getMatches(Match match, Integer pageIndex, Integer pageSize) throws Exception {

        if (pageIndex == null || pageSize == null) {
            return matchDao.select(match, new PageBounds());
        } else
            return matchDao.select(match, new PageBounds(pageIndex, pageSize));
    }

    @Override
    public PageList<Match> getMatches(Match match) throws Exception {
        return getMatches(match, null, null);
    }

    @Override
    public Match getMatch(String matchId) throws Exception {

        Match match =  matchDao.selectById(matchId);
//        ArrayList<Attachment> attachments = new ArrayList<Attachment>();
//        if(match.getAttachments() != null){
//            for (Attachment attachment : match.getAttachments()) {
//                if(Attachment.NORMAL.equals(attachment.getRemoved()))
//                    attachments.add(attachment);
//            }
//        }
//        match.setAttachments(attachments);
        return match;
    }

    @Override
    public int delete(List<String> matchIds) throws Exception {
        int i = 0;
        for (String matchId : matchIds) {
            Match match = new Match();
            match.setMatchId(matchId);
            match.setRemoved(Match.DELETE);
            update(match, true);
            i++;
        }
        return i;
    }

    @Override
    public int delete(String matchId) throws Exception {
        ArrayList<String> matchIds = new ArrayList<String>();
        matchIds.add(matchId);
        return delete(matchIds);
    }

    @Override
    public void save(Match match, String[] excludes) throws Exception {
        if (StringUtils.isNotBlank(match.getMatchId())) {
            Match pMatch = getMatch(match.getMatchId());
            BeanUtils.copyProperties(match, pMatch, excludes);
            update(pMatch, false);

            saveAttachments(match.getMatchId(),match.getAttachments());

        } else {
            matchDao.insert(match);
            saveAttachments(match.getMatchId(),match.getAttachments());
        }
    }

    private void saveAttachments(String matchId,List<Attachment> attachments) throws Exception{
        if (attachments != null){
            for (Attachment attachment : attachments) {
                attachment.setObjectId(matchId);
                attachment.setAttachType(Match.class.getName());
                attachmentService.update(attachment, true);
            }
        }
    }

    @Override
    public void commit(List<Match> matches) throws Exception {
        for (Match match : matches) {
            commit(match);
        }
    }

    @Override
    public void commit(Match match) throws Exception {
        match.setStatus(Match.WAIT_STATUS);
        update(match, true);
    }

    @Override
    public void update(Match match, boolean isDynamic) throws Exception {
        matchDao.update(match, isDynamic);
    }



}
