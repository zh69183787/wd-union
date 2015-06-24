package com.wonders.stpt.match.service.impl;

import com.wonders.stpt.match.dao.AttachmentDao;
import com.wonders.stpt.match.domain.Attachment;
import com.wonders.stpt.match.service.IAttachmentService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageBounds;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/8/29.
 */
@Service
public class AttachmentServiceImpl implements IAttachmentService {

    @Autowired
    private AttachmentDao attachmentDao;

    @Override
    public PageList<Attachment> getAttachments(Attachment attachment, Integer pageIndex, Integer pageSize) throws Exception {
        if (pageIndex == null || pageSize == null) {
            return attachmentDao.select(attachment, new PageBounds());
        } else
            return attachmentDao.select(attachment, new PageBounds(pageIndex, pageSize));
    }

    @Override
    public PageList<Attachment> getAttachments(Attachment attachment) throws Exception {
        return getAttachments(attachment,null,null);
    }

    @Override
    public Attachment getAttachment(String attachmentId) throws Exception {
        Attachment attachment = new Attachment();
        attachment.setRemoved(Attachment.NORMAL);
        attachment.setAttachmentId(attachmentId);
        PageList<Attachment> attachments =getAttachments(attachment);

        if(attachments != null && attachments.size() == 1){
            return attachments.get(0);
        }
        return null;
    }

    @Override
    public int delete(List<String> attachmentIds) throws Exception {
        int i = 0;
        for (String attachmentId : attachmentIds) {
            Attachment attachment = new Attachment();
            attachment.setAttachmentId(attachmentId);
            attachment.setRemoved(Attachment.DELETE);
            update(attachment,false);
            i++;
        }
        return i;
    }

    @Override
    public int delete(String attachmentId) throws Exception {
        ArrayList<String> attachmentIds = new ArrayList<String>();
        attachmentIds.add(attachmentId);
        return delete(attachmentIds);
    }

    @Override
    public void save(Attachment attachment, String[] excludes) throws Exception {
            attachmentDao.insert(attachment);
    }

    @Override
    public void update(Attachment attachment, boolean isDynamic) throws Exception {
        attachmentDao.update(attachment,isDynamic);
    }
}
