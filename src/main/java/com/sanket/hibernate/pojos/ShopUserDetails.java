package com.sanket.hibernate.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "SHOP_USER_DETAILS", catalog = "SHOPPINGCART", uniqueConstraints = { @UniqueConstraint(columnNames = "ROW_ID") })
public class ShopUserDetails implements Serializable {
	private static final long serialVersionUID = 6826317475818261904L;

	private String country, mobile, emailId, isDeleted;
	private Integer rowId;
	private ShopUser shopUser;

	public ShopUserDetails() {
		super();
	}

	@Override
	public String toString() {
		return "ShopUserDetails [country=" + country + ", mobile=" + mobile
				+ ", emailId=" + emailId + ", isDeleted=" + isDeleted
				+ ", rowId=" + rowId + ", shopUser=" + shopUser.getUserName()
				+ "]";
	}

	public ShopUserDetails(String country, String mobile, String emailId) {
		super();
		this.country = country;
		this.mobile = mobile;
		this.emailId = emailId;
		this.isDeleted = "No";
	}

	@Column(name = "IS_DELETED", nullable = false)
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "shopUser"))
	@OneToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	public ShopUser getShopUser() {
		return shopUser;
	}

	public void setShopUser(ShopUser shopUser) {
		this.shopUser = shopUser;
	}

	@Column(name = "COUNTRY", nullable = false)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "MOBILE", unique = true, nullable = false)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "EMAIL_ID", unique = true, nullable = false)
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, name = "ROW_ID")
	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

}
