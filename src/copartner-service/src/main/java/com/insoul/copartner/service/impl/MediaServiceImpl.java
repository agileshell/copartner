package com.insoul.copartner.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.insoul.copartner.constant.CommonConstant;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.constant.SettingConstant;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IMediaService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.FileUtil;
import com.insoul.copartner.util.SystemUtil;

@Service
public class MediaServiceImpl extends BaseServiceImpl implements IMediaService {

    @Override
    public Map<String, String> uploadImage(final MultipartFile image) throws CException {

        Map<String, String> siteSettings = SystemUtil.getSettings(SettingConstant.GROUP_TYPE_IMAGE);
        if (null != siteSettings && siteSettings.size() > 0) {
            long maxSize = FileUtil.string2bytes(siteSettings.get(SettingConstant.IMAGE_MAX_SIZE));
            long minSize = FileUtil.string2bytes(siteSettings.get(SettingConstant.IMAGE_MIN_SIZE));
            String[] imageTyps = siteSettings.get(SettingConstant.IMAGE_TYPE_LIMIT).split(",");
            int maxWidth = Integer.valueOf(siteSettings.get(SettingConstant.IMAGE_DIMENSION_MAX_WIDTH));
            int maxHeight = Integer.valueOf(siteSettings.get(SettingConstant.IMAGE_DIMENSION_MAX_HEIGHT));
            int minWidth = Integer.valueOf(siteSettings.get(SettingConstant.IMAGE_DIMENSION_MIN_WIDTH));
            int minHeight = Integer.valueOf(siteSettings.get(SettingConstant.IMAGE_DIMENSION_MIN_HEIGHT));

            FileUtil.validateImage(image, maxSize, minSize, imageTyps, maxWidth, minWidth, maxHeight, minHeight);
        }

        String fileType = FileUtil.getFileType(image.getOriginalFilename());
        String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
        String path = null;
        try {
            path = CDNUtil.uploadFile(image.getInputStream(), fileName);
        } catch (IOException e) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.FILE_UPLOAD_ERROR);
        }

        Map<String, String> result = new HashMap<String, String>();
        result.put(CommonConstant.IMAGE_PATH, path);
        result.put(CommonConstant.ORIGINAL_IMAGE_URL, CDNUtil.getFullPath(path));
        return result;
    }

    @Override
    public Map<String, String> uploadVedio(MultipartFile vedio) throws CException {
        Map<String, String> siteSettings = SystemUtil.getSettings(SettingConstant.GROUP_TYPE_VEDIO);
        if (null != siteSettings && siteSettings.size() > 0) {
            long maxSize = FileUtil.string2bytes(siteSettings.get(SettingConstant.VEDIO_MAX_SIZE));
            long minSize = FileUtil.string2bytes(siteSettings.get(SettingConstant.VEDIO_MIN_SIZE));
            String[] imageTyps = siteSettings.get(SettingConstant.VEDIO_TYPE_LIMIT).split(",");

            FileUtil.validateFile(vedio, maxSize, minSize, imageTyps);
        }

        String fileType = FileUtil.getFileType(vedio.getOriginalFilename());
        String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
        String path = null;
        try {
            path = CDNUtil.uploadFile(vedio.getInputStream(), fileName);
        } catch (IOException e) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.FILE_UPLOAD_ERROR);
        }

        Map<String, String> result = new HashMap<String, String>();
        result.put(CommonConstant.PATH, path);
        result.put(CommonConstant.ORIGINAL_URL, CDNUtil.getFullPath(path));
        return result;
    }

    @Override
    public boolean deleteMedia(String media) throws CException {
        return CDNUtil.deleteFile(media);
    }

}
