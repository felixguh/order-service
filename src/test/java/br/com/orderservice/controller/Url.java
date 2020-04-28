package br.com.orderservice.controller;

public enum Url {
	BASE_URL("/v1/orders"), 
	ORDER_NUMBER(BASE_URL.getUrl().concat("/order/1"));

	private final String url;

	private Url(final String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}


}
