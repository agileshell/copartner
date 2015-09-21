package com.insoul.copartner.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

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
    public Map<String, String> uploadImage(MultipartFile imageFile, boolean needThumbnail) throws CException {
        String fileType = FileUtil.getFileType(imageFile.getOriginalFilename());

        InputStream in = null;
        if (needThumbnail) {
            try {
                BufferedImage image = ImageIO.read(imageFile.getInputStream());
                int imageWidth = image.getWidth();
                int imageHeitht = image.getHeight();
                int a = CommonConstant.IMAGE_STANDARD;
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                Builder<BufferedImage> builder = null;
                if (1.0f != (float) imageWidth / imageHeitht) {
                    if (imageWidth > imageHeitht) {
                        image = Thumbnails.of(imageFile.getInputStream()).height(a).asBufferedImage();
                    } else {
                        image = Thumbnails.of(imageFile.getInputStream()).width(a).asBufferedImage();
                    }
                    builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, a, a).size(a, a);
                } else {
                    builder = Thumbnails.of(image).size(a, a);
                }

                builder.outputFormat(fileType).toOutputStream(out);
                in = new ByteArrayInputStream(out.toByteArray());
            } catch (Exception e) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.FILE_UPLOAD_ERROR);
            }
        } else {
            Map<String, String> siteSettings = SystemUtil.getSettings(SettingConstant.GROUP_TYPE_IMAGE);
            if (null != siteSettings && siteSettings.size() > 0) {
                long maxSize = FileUtil.string2bytes(siteSettings.get(SettingConstant.IMAGE_MAX_SIZE));
                long minSize = FileUtil.string2bytes(siteSettings.get(SettingConstant.IMAGE_MIN_SIZE));
                String[] imageTyps = siteSettings.get(SettingConstant.IMAGE_TYPE_LIMIT).split(",");
                int maxWidth = Integer.valueOf(siteSettings.get(SettingConstant.IMAGE_DIMENSION_MAX_WIDTH));
                int maxHeight = Integer.valueOf(siteSettings.get(SettingConstant.IMAGE_DIMENSION_MAX_HEIGHT));
                int minWidth = Integer.valueOf(siteSettings.get(SettingConstant.IMAGE_DIMENSION_MIN_WIDTH));
                int minHeight = Integer.valueOf(siteSettings.get(SettingConstant.IMAGE_DIMENSION_MIN_HEIGHT));

                FileUtil.validateImage(imageFile, maxSize, minSize, imageTyps, maxWidth, minWidth, maxHeight, minHeight);
            }

            try {
                in = imageFile.getInputStream();
            } catch (IOException e) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.FILE_UPLOAD_ERROR);
            }
        }

        String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
        String path = CDNUtil.uploadFile(in, fileName);

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
