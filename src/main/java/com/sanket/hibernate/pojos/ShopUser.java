package com.sanket.hibernate.pojos;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "SHOP_USER", catalog = "SHOPPINGCART", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ROW_ID"),
		@UniqueConstraint(columnNames = "USER_NAME") })
public class ShopUser implements Serializable {
	private static final long serialVersionUID = 4737514243144579723L;

	private String userName, password, isDeleted;
	private Integer rowId;
	private ShopUserDetails shopUserDetail;

	public ShopUser(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
		this.isDeleted = "No";
	}

	@Column(name = "IS_DELETED", nullable = false)
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public ShopUser() {
		super();
	}

	@Column(name = "USER_NAME", unique = true, nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASS_WORD", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, name = "ROW_ID")
	public Integer getRowId() {
		return rowId;
	}

	@Override
	public String toString() {
		return "ShopUser [userName=" + userName + ", password=" + password
				+ ", isDeleted=" + isDeleted + ", rowId=" + rowId
				+ ", shopUserDetail=" + "]";
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	// @OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade =
	// CascadeType.ALL)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "shopUser")
	public ShopUserDetails getShopUserDetail() {
		return shopUserDetail;
	}

	public void setShopUserDetail(ShopUserDetails shopUserDetail) {
		this.shopUserDetail = shopUserDetail;
	}
}