package com.insoul.ti.controller;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.insoul.copartner.constant.GlobalProperties;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.FileUtil;
import com.insoul.ti.WebBase;
import com.insoul.ti.shiro.Permission;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/editor")
@Permission("authc")
public class EditorController extends WebBase {

    @RequestMapping(value = "file_upload", method = RequestMethod.POST)
    public void fileUploadForKindeditor(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        // 定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
        // 最大文件大小
        long maxSize = 10000000;
        response.setContentType("text/plain;charset=UTF-8");
        if (!ServletFileUpload.isMultipartContent(request)) {
            out.print(getError("请选择文件。").toString());
            return;
        }
        DefaultMultipartHttpServletRequest mrequest = (DefaultMultipartHttpServletRequest) request;
        Map<?, MultipartFile> map = mrequest.getFileMap();
        Collection<MultipartFile> c = map.values();
        Iterator<MultipartFile> it = c.iterator();
        for (; it.hasNext();) {
            MultipartFile file = (MultipartFile) it.next();
            if (!file.isEmpty()) {
                long fileSize = file.getSize();
                String fileName = file.getOriginalFilename();
                String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                if (fileSize > maxSize) {
                    out.print(getError("上传文件大小超过限制。").toString());
                    return;
                }
                if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                    out.print(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。").toString());
                    return;
                }
                JSONObject json = new JSONObject();
                try {
                    String uri = CDNUtil.uploadFile(file.getInputStream(), fileName);
                    json.put("error", 0);
                    json.put("url", GlobalProperties.CDN_DOMAIN + uri);
                    out.print(json.toString());
                    return;
                } catch (Exception e) {
                    log.error("UploadFile Error.", e);
                    json.put("error", 1);
                    json.put("message", "上传文件失败。");
                    out.print(json.toString());
                    return;
                }
            }
        }
    }

    private JSONObject getError(String message) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("error", 1);
        json.put("message", message);
        return json;
    }


    private boolean isImageFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return false;
        }
        String contentType = file.getContentType();
        if (contentType.equals("image/pjpeg") || contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/gif") || contentType.equals("image/bmp")
                || contentType.equals("image/x-png")) {
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/upload")
    public void editor_upload(HttpServletRequest request, HttpServletResponse response, @RequestParam("upload") MultipartFile upload) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (!isImageFile(upload)) {
                out.print("<font color=\"red\"size=\"2\">*文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）</font>");
                return;
            }
            String uri = null;
            String fileType = FileUtil.getFileType(upload.getOriginalFilename());
            String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
            try {
                uri = CDNUtil.uploadFile(upload.getInputStream(), fileName);
            } catch (Exception e) {
                log.error("UploadFile Error.", e);
            }
            if (StringUtils.isBlank(uri)) {
                StringBuffer ret = new StringBuffer("<font color=\"red\"size=\"2\">*文件上传失败！</font>").append("<script type=\"text/javascript\">").append("alert('上传失败！');").append("</script>");
                out.print(ret.toString());
                return;
            }
            uri = GlobalProperties.CDN_DOMAIN + uri;
            String callback = request.getParameter("CKEditorFuncNum");
            StringBuffer ret = new StringBuffer().append("<font color=\"red\"size=\"2\">*文件上传成功！</font>").append("<script type=\"text/javascript\">")
                    .append("var image = window.parent.document.getElementById('cke_70_previewImage');").append("var txt = window.parent.document.getElementById('cke_75_textInput');")
                    .append("if(image == undefined){image = window.parent.document.getElementById('cke_68_previewImage');}")
                    .append("if(txt == undefined){txt = window.parent.document.getElementById('cke_73_textInput');}").append("image.style.display = '';").append("image.src = '" + uri + "';")
                    .append("txt.value = '" + uri + "';");
            // callback
            ret.append("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + uri + "','');").append("</script>");
            out.print(ret.toString());
        } catch (Exception e) {
            log.error("Ckeditor Upload Error.", e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
                out = null;
            }
        }
    }
}
