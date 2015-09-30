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
    private String province;// 省

    @NotNull
    private String city;// 市

    private String area;// 区

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
        return new StringBuffer(province).append(" ")
                .append(city).append(" ")
                .append(getArea()).append(" ")
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return StringUtils.defaultString(area);
    }

    public void setArea(String area) {
        this.area = area;
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