package com.insoul.copartner.util.cdn;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

import com.insoul.copartner.constant.CommonConstant;
import com.insoul.copartner.constant.GlobalProperties;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.util.FileUtil;
import com.insoul.copartner.util.ValidationUtil;

public final class LocalCDN implements CDN {

    @Override
    public String uploadFile(final InputStream in, final String fileName) throws CException {
        String savePath = new StringBuilder(GlobalProperties.CDN_LOCAL_PATH.trim()).append(CommonConstant.SEPARATOR)
                .toString();
        try {
            FileUtil.saveFile(in, savePath, fileName);
        } catch (IOException e) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.FILE_UPLOAD_ERROR);
        }

        return fileName;
    }


    @Override
    public boolean deleteFile(final String path) {
        File file = new File(GlobalProperties.CDN_LOCAL_PATH.trim() + CommonConstant.SEPARATOR + path);
        if (file.isFile()) {
            file.delete();
            return true;
        } else {
            return false;
        }
    }


    @Override
    public String getHttpPath(final String path) {
        String url = null;
        if (ValidationUtil.isFullURL(path)) {
            url = path;
        } else if (StringUtils.isNotBlank(path)) {
            url = GlobalProperties.CDN_DOMAIN.concat(
                    GlobalProperties.CDN_DOMAIN.endsWith(CommonConstant.SEPARATOR) ? "" : CommonConstant.SEPARATOR)
                    .concat(path);
        }
        return url;
    }

}
