package com.insoul.copartner.vo.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class ImageAddRequest implements Serializable {

    private static final long serialVersionUID = -5269375142688516111L;

    @NotNull
    private MultipartFile image;

    private Boolean needThumbnail = false;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Boolean getNeedThumbnail() {
        return needThumbnail;
    }

    public void setNeedThumbnail(Boolean needThumbnail) {
        this.needThumbnail = needThumbnail;
    }

}
