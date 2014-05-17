package com.sanket.shoppingcart;

public class Util {

	public static String getPriceInDollars(int priceInCents) {
		int dollars = priceInCents / 100;
		int cents = priceInCents % 100;
		return "$" + dollars + "."
				+ (String.valueOf(cents) + "0").substring(0, 2);
	}
}
