package com.insoul.ti.req;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public class PioneerParkRequest {

	@NotNull
	private String name;// 名称

    @NotNull
    private String provinceV;// 省

    @NotNull
    private String cityV;// 市

    private String areaV;// 区

    @NotNull
    private String addressDetail;// 详细地址

    @NotNull
    private BigDecimal longitude;// 经度

    @NotNull
    private BigDecimal latitude;// 纬度

    /**
     * 简介
     */
    private String content;
    
    public String buildAddress() {
        return new StringBuffer(provinceV).append(" ")
                .append(cityV).append(" ")
                .append(getAreaV()).append(" ")
                .append(addressDetail).toString();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceV() {
        return provinceV;
    }

    public void setProvinceV(String provinceV) {
        this.provinceV = provinceV;
    }

    public String getCityV() {
        return cityV;
    }

    public void setCityV(String cityV) {
        this.cityV = cityV;
    }

    public String getAreaV() {
        return StringUtils.defaultString(areaV);
    }

    public void setAreaV(String areaV) {
        this.areaV = areaV;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}