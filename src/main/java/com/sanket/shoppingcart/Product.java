package com.sanket.shoppingcart;

import java.io.Serializable;

public class Product implements Comparable<Product>, Serializable {
	private static final long serialVersionUID = -1150216531899294076L;
	private long id;
	private String description;
	private int priceInCents;
	private String imageUrl;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriceInCents() {
		return priceInCents;
	}

	public void setPriceInCents(int priceInCents) {
		this.priceInCents = priceInCents;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getPriceInDollars() {
		return Util.getPriceInDollars(priceInCents);
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Product && id == ((Product) o).id;
	}

	@Override
	public int hashCode() {
		return ((Long) id).hashCode();
	}

	@Override
	public int compareTo(Product p) {
		int c = description.compareTo(p.description);
		return (c == 0 ? ((Long) id).compareTo((Long) p.id) : c);
	}

	public Product(long id, String description, int priceInCents,
			String imageUrl) {
		super();
		this.id = id;
		this.description = description;
		this.priceInCents = priceInCents;
		this.imageUrl = imageUrl;
	}
}