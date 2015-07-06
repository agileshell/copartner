package com.insoul.ti.controller;

import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.FileUtil;
import com.insoul.ti.WebBase;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/editor")
public class EditorController extends WebBase {
	
	private boolean isImageFile(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			return false;
		}
		String contentType = file.getContentType();
		if (contentType.equals("image/pjpeg") 
			|| contentType.equals("image/jpeg") 
			|| contentType.equals("image/png")
			|| contentType.equals("image/gif") 
			|| contentType.equals("image/bmp")
			|| contentType.equals("image/x-png")
			) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/upload")
	public void editor_upload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("upload") MultipartFile upload) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(!isImageFile(upload)) {
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
			if(StringUtils.isBlank(uri)) {
				StringBuffer ret = new StringBuffer("<font color=\"red\"size=\"2\">*文件上传失败！</font>")
				.append("<script type=\"text/javascript\">")
				.append("alert('上传失败！');")
				.append("</script>")
				;
		        out.print(ret.toString());
		        return;
			}
			uri = "http://7xjbd9.com1.z0.glb.clouddn.com/" + uri;
			String callback = request.getParameter("CKEditorFuncNum");
			StringBuffer ret = new StringBuffer()
			.append("<font color=\"red\"size=\"2\">*文件上传成功！</font>")
			.append("<script type=\"text/javascript\">")
			.append("var image = window.parent.document.getElementById('cke_70_previewImage');")
			.append("var txt = window.parent.document.getElementById('cke_75_textInput');")
			.append("if(image == undefined){image = window.parent.document.getElementById('cke_68_previewImage');}")
			.append("if(txt == undefined){txt = window.parent.document.getElementById('cke_73_textInput');}")
			.append("image.style.display = '';")
			.append("image.src = '" + uri + "';")
			.append("txt.value = '" + uri + "';")
			;
			//callback
			ret.append("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + uri + "','');")
			.append("</script>")
			;
	        out.print(ret.toString());    
		} catch (Exception e) {
			log.error("Ckeditor Upload Error.", e);
		} finally {
			if(out != null) {
				out.flush();
				out.close();
				out = null;
			}
		}
	}
}