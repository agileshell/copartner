package com.insoul.copartner.util.cdn;

import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

import com.insoul.copartner.constant.CommonConstant;
import com.insoul.copartner.constant.GlobalProperties;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.util.ValidationUtil;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.net.CallRet;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;

public final class QiniuCDN implements CDN {

    private static final Mac MAC;

    static {
        Config.ACCESS_KEY = GlobalProperties.CDN_ACCESS_KEY.trim();
        Config.SECRET_KEY = GlobalProperties.CDN_SECRET_KEY.trim();
        MAC = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
    }

    @Override
    public String uploadFile(final InputStream in, final String fileName) throws CException {
        PutExtra extra = new PutExtra();
        PutRet putRet = null;
        putRet = IoApi.Put(getUptoken(), fileName, in, extra);
        if (!putRet.ok()) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.FILE_UPLOAD_ERROR);
        }

        return putRet.getKey();
    }

    @Override
    public boolean deleteFile(final String path) {
        CallRet callRet = new RSClient(MAC).delete(GlobalProperties.CDN_BUCKET_NAME.trim(), path);

        return callRet.ok();
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

    private String getUptoken() throws CException {
        PutPolicy putPolicy = new PutPolicy(GlobalProperties.CDN_BUCKET_NAME.trim());
        String uptoken = null;
        try {
            uptoken = putPolicy.token(MAC);
        } catch (Exception e) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.SERVER_ERROR);
        }

        return uptoken;
    }

}
