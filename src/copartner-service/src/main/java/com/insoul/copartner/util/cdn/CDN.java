package com.insoul.copartner.util.cdn;

import java.io.InputStream;

import com.insoul.copartner.exception.CException;

public interface CDN {

    String uploadFile(InputStream in, String fileName) throws CException;

    boolean deleteFile(String path);

    String getHttpPath(String path);
}
