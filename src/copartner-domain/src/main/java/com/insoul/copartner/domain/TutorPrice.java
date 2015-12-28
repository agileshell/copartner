package com.insoul.copartner.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tutor_price", catalog = "copartner")
@NamedQueries({ @NamedQuery(name = "TutorPrice.getByTutorId", query = "FROM TutorPrice WHERE tutorId = :tutorId") })
public class TutorPrice extends BaseEntity {

	private static final long serialVersionUID = -5899243914190503695L;

	@Column(name = "tutor_id", nullable = false)
	private Long tutorId; // 导师

	@Column(name = "price", nullable = false, precision = 15, scale = 4)
	private BigDecimal price;// 价格

	@Column(nullable = false)
	private Integer minutes = 0;// 时间

	public Long getTutorId() {
		return tutorId;
	}

	public void setTutorId(Long tutorId) {
		this.tutorId = tutorId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((minutes == null) ? 0 : minutes.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((tutorId == null) ? 0 : tutorId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TutorPrice other = (TutorPrice) obj;
		if (minutes == null) {
			if (other.minutes != null)
				return false;
		} else if (!minutes.equals(other.minutes))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (tutorId == null) {
			if (other.tutorId != null)
				return false;
		} else if (!tutorId.equals(other.tutorId))
			return false;
		return true;
	}

}
