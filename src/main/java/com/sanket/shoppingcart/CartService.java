package com.sanket.shoppingcart;

import java.util.List;

public interface CartService {
	public ShoppingCart getShoppingCart();

	public List<Product> getProducts();

	public List<Product> getRecommendations();

	public Product getProduct(long productId);

	public List<String> getShippingOptions();

	public void submitOrderForPayment();
}
