package com.insoul.copartner.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.insoul.copartner.exception.CException;

public interface IMediaService {

    Map<String, String> uploadImage(MultipartFile imageFile, boolean needThumbnail) throws CException;

    Map<String, String> uploadVedio(MultipartFile vedio) throws CException;

    Map<String, String> uploadDoc(MultipartFile doc) throws CException;

    boolean deleteMedia(String media) throws CException;
}
