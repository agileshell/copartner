package com.insoul.copartner.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.insoul.copartner.exception.CException;

public interface IMediaService {

    Map<String, String> uploadImage(MultipartFile image) throws CException;

    Map<String, String> uploadVedio(MultipartFile vedio) throws CException;

    boolean deleteMedia(String media) throws CException;
}
