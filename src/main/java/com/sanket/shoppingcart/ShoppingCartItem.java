package com.sanket.shoppingcart;

import java.io.Serializable;

public class ShoppingCartItem implements Comparable<ShoppingCartItem>,
		Serializable {
	private static final long serialVersionUID = 8129862712521396802L;

	private Product product;
	private int quantity;

	public ShoppingCartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void incrementQuantity() {
		quantity++;
	}

	public int getUnitPriceInCents() {
		return product.getPriceInCents();
	}

	public String getUnitPriceInDollars() {
		return product.getPriceInDollars();
	}

	public int getTotalPriceInCents() {
		return (quantity * getUnitPriceInCents());
	}

	public String getTotalPriceInDollars() {
		return Util.getPriceInDollars(getTotalPriceInCents());
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof ShoppingCartItem
				&& product.equals(((ShoppingCartItem) o).product);
	}

	@Override
	public int hashCode() {
		return product.hashCode();
	}

	@Override
	public int compareTo(ShoppingCartItem item) {
		return product.compareTo(item.product);
	}
}
