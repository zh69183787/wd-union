package com.wonders.stpt.match.controller;

import com.wonders.stpt.match.domain.Attachment;
import com.wonders.stpt.match.service.IAttachmentService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by Administrator on 2014/8/29.
 */
@Controller
@RequestMapping("/attachment")
public class AttachmentController extends BaseController {

    @Autowired
    private IAttachmentService attachmentService;

    @RequestMapping(method = RequestMethod.POST)
    public void upload(@RequestParam(value = "filedata", required = false) CommonsMultipartFile file, HttpServletRequest request, Model model) throws Exception {
        Attachment attachment = new Attachment();
        attachment.setAttachName(file.getOriginalFilename());

        long size = file.getSize();
        if(0<size && size < 1024){
            attachment.setAttachSize(size+"B");
        }else if(1024<=size && size < 1024*1024){
            attachment.setAttachSize((long)Math.ceil((double)size/1024)+"KB");
        }else if(1024*1024<=size && size < 1024*1024*1024){
            attachment.setAttachSize((long)Math.ceil((double)size/1024/1024)+"MB");
        }else if(1024*1024*1024<=size && size < 1024*1024*1024*1024){
            attachment.setAttachSize((long)Math.ceil((double)size/1024/1024/1024)+"GB");
        }
        attachment.setAttachmentId(UUID.randomUUID().toString().replaceAll("-", ""));
        attachment.setAttachExtName(StringUtils.substringAfterLast(file.getOriginalFilename(), "."));
        StringBuffer url = new StringBuffer();
        url.append(request.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort()).append(request.getContextPath()).append("/attachment/").append(attachment.getAttachmentId());
        attachment.setAttachUrl(url.toString());
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(request.getSession().getServletContext().getRealPath("/WEB-INF/upload/" + attachment.getAttachmentId()+"."+attachment.getAttachExtName())));


        attachmentService.save(attachment, null);
        model.addAttribute("attachment", attachment);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "{attachmentId}")
    public void delete(@PathVariable(value = "attachmentId") String attachmentId,Model model)throws Exception{

        attachmentService.delete(Arrays.asList(attachmentId.split(",")));
    }

    @RequestMapping(method = RequestMethod.GET,value = "{attachmentId}")
    public ResponseEntity<byte[]> attachment(@PathVariable(value = "attachmentId") String attachmentId, HttpServletRequest request,Model model) throws Exception {
        Attachment attachment = attachmentService.getAttachment(attachmentId);
        File file = new File(request.getSession().getServletContext().getRealPath("/WEB-INF/upload/" + attachment.getAttachmentId()+"."+attachment.getAttachExtName()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        headers.setContentDispositionFormData("attachment", URLEncoder.encode(attachment.getAttachName(), "UTF-8"));
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "test")
    public String test()throws Exception{

            return "test";
    }
}
