package com.insoul.copartner.util;

import java.io.InputStream;

import org.apache.commons.lang.StringUtils;

import com.insoul.copartner.constant.GlobalProperties;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.util.cdn.CDN;
import com.insoul.copartner.util.cdn.LocalCDN;
import com.insoul.copartner.util.cdn.QiniuCDN;

public final class CDNUtil {

    private static final String cdn_type_qiniu = "qiniu";

    private static final CDN cdn;

    static {
        if (GlobalProperties.CDN_PROVIDER.equals(cdn_type_qiniu)) {
            cdn = new QiniuCDN();
        } else {
            cdn = new LocalCDN();
        }
    }

    public static String uploadFile(final InputStream in, final String fileName) throws CException {
        return cdn.uploadFile(in, fileName);
    }

    public static boolean deleteFile(final String path) {
        return cdn.deleteFile(path);
    }

    public static String getFullPath(final String path) {
        return cdn.getHttpPath(path);
    }

    public static String getThumbnail(final String path, final String width, final String height) {
        if (StringUtils.isNotBlank(path)) {
            StringBuilder url = new StringBuilder();
            url.append(getFullPath(path));
            if (StringUtils.isNotEmpty(width) || StringUtils.isNotEmpty(height)) {
                url.append("?imageView2/1/w/").append(width).append("/h/").append(height);
            }

            return url.toString();
        } else {
            return null;
        }
    }
}
