package com.insoul.copartner.api.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insoul.copartner.constant.CommonConstant;
import com.insoul.copartner.constant.GlobalProperties;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.exception.DataValidationException;
import com.insoul.copartner.service.IMediaService;
import com.insoul.copartner.util.FileUtil;
import com.insoul.copartner.util.MD5Encrypt;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.ImageAddRequest;

@Controller
public class ImageController extends BaseController {

    @Resource
    private IMediaService mediaService;

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadImg(@Valid ImageAddRequest imageAddRequest, BindingResult validResult)
            throws CException {
        if (validResult.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }
        Map<String, String> result = mediaService.uploadImage(imageAddRequest.getImage());

        return ResponseUtil.jsonSucceed(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/image", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteImg(@RequestParam String image) throws CException {
        if (StringUtils.isBlank(image)) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }
        mediaService.deleteImage(image);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "image/{path:.+}", method = RequestMethod.GET)
    public ResponseEntity<String> getInventoryStatusList(@PathVariable("path") String path, HttpServletRequest request,
            HttpServletResponse response) throws CException {
        String fileType = FileUtil.getFileType(path);
        StringBuilder basePath = new StringBuilder(GlobalProperties.CDN_LOCAL_PATH.trim())
                .append(CommonConstant.SEPARATOR);

        String absolutePath = basePath.toString().concat(CommonConstant.SEPARATOR).concat(path);
        response.setContentType(getContentType(fileType));
        @SuppressWarnings("unchecked")
        Enumeration<String> attributes = request.getParameterNames();
        if (attributes.hasMoreElements()) {
            // imageView2/1/w/<Width>/h/<Height>
            String attribute = attributes.nextElement();
            String[] params = attribute.split("/");
            if (params.length >= 5) {
                int width = Integer.valueOf(params[3]);
                int height = Integer.valueOf(params[5]);
                String cachePath = basePath.append("cache").append(CommonConstant.SEPARATOR).append(width)
                        .append(CommonConstant.SEPARATOR).append(height).append(CommonConstant.SEPARATOR).toString();
                String cacheFileName = new StringBuilder().append(MD5Encrypt.MD5Encode(path)).append(".")
                        .append(fileType).toString();
                File file = new File(cachePath.concat(cacheFileName));
                if (!file.isFile()) {
                    try {
                        BufferedImage originalImage = ImageIO.read(new File(absolutePath));
                        BufferedImage thumbnailImage = FileUtil.scaleAndCutImage(originalImage, width, height);
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ImageIO.write(thumbnailImage, fileType, os);
                        InputStream in = new ByteArrayInputStream(os.toByteArray());
                        FileUtil.saveFile(in, cachePath, cacheFileName);
                    } catch (IOException e) {
                        throw CExceptionFactory.getException(CException.class, ResponseCode.SERVER_ERROR);
                    }
                }
                absolutePath = cachePath.concat(cacheFileName);
            }
        }

        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            BufferedImage bi = ImageIO.read(new File(absolutePath));
            ImageIO.write(bi, fileType, out);
            out.flush();
        } catch (IOException e) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.SERVER_ERROR);
        } finally {
            try {
                if (null != out)
                    out.close();
            } catch (IOException e) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.SERVER_ERROR);
            }
        }

        return null;
    }

    private String getContentType(String fileType) {
        String contentType = null;
        if (fileType.equalsIgnoreCase("gif")) {
            contentType = "image/gif";
        } else if (fileType.equalsIgnoreCase("jpg") || fileType.equalsIgnoreCase("jpeg")
                || fileType.equalsIgnoreCase("jpe")) {
            contentType = "image/jpeg";
        } else if (fileType.equalsIgnoreCase("png")) {
            contentType = "image/png";
        } else if (fileType.equalsIgnoreCase("bmp")) {
            contentType = "image/x-xbitmap";
        }

        return contentType;
    }
}
