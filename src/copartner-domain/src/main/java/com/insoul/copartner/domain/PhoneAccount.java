package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "phone_account", catalog = "copartner")
@NamedQueries({ @NamedQuery(name = "PhoneAccount.findByMobile", query = "FROM PhoneAccount WHERE mobile = :mobile") })
public class PhoneAccount extends BaseEntity {

	private static final long serialVersionUID = 1963626798546243909L;

	@Column(name = "friendly_name")
	private String friendlyName;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "client_number")
	private String clientNumber;

	@Column(name = "client_pwd")
	private String clientPwd;

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getClientPwd() {
		return clientPwd;
	}

	public void setClientPwd(String clientPwd) {
		this.clientPwd = clientPwd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((clientNumber == null) ? 0 : clientNumber.hashCode());
		result = prime * result + ((clientPwd == null) ? 0 : clientPwd.hashCode());
		result = prime * result + ((friendlyName == null) ? 0 : friendlyName.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
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
		PhoneAccount other = (PhoneAccount) obj;
		if (clientNumber == null) {
			if (other.clientNumber != null)
				return false;
		} else if (!clientNumber.equals(other.clientNumber))
			return false;
		if (clientPwd == null) {
			if (other.clientPwd != null)
				return false;
		} else if (!clientPwd.equals(other.clientPwd))
			return false;
		if (friendlyName == null) {
			if (other.friendlyName != null)
				return false;
		} else if (!friendlyName.equals(other.friendlyName))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		return true;
	}

}
