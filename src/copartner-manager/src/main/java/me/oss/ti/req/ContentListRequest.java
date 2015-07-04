package me.oss.ti.req;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public class ContentListRequest extends PageRequest {
	
	private Integer type;// 1 政策解读, 2 公共资源

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}