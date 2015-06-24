package com.wonders.stpt.match.service;

import com.wonders.stpt.match.domain.Attachment;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

import java.util.List;

/**
 * Created by Administrator on 2014/8/29.
 */
public interface IAttachmentService {
    PageList<Attachment> getAttachments(Attachment attachment,Integer pageIndex,Integer pageSize) throws Exception;

    PageList<Attachment> getAttachments(Attachment attachment) throws Exception;

    Attachment getAttachment(String attachmentId) throws Exception;

    int delete(List<String> attachmentIds) throws Exception;

    int delete(String attachmentId) throws Exception;

    void save(Attachment attachment,String[] excludes) throws Exception;

    void update(Attachment attachment,boolean isDynamic) throws Exception;
}
