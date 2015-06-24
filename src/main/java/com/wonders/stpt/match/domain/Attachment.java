package com.wonders.stpt.match.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/8/29.
 */
public class Attachment extends BaseDomain implements Serializable {
    private String attachmentId;
    private String attachName;
    private String attachSize;
    private String attachExtName;
    private String attachType;
    private String objectId;
    private String attachUrl;

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getAttachSize() {
        return attachSize;
    }

    public void setAttachSize(String attachSize) {
        this.attachSize = attachSize;
    }

    public String getAttachExtName() {
        return attachExtName;
    }

    public void setAttachExtName(String attachExtName) {
        this.attachExtName = attachExtName;
    }

    public String getAttachType() {
        return attachType;
    }

    public void setAttachType(String attachType) {
        this.attachType = attachType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }
}
