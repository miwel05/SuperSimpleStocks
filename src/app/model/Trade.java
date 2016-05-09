package app.model;

import java.util.Date;


public class Trade {
	
	private Stock stock;
	private Date timeStamp;
	private String tradeIndicator;
	private int quantity;
	private double price;
	
	public Trade(){
		super();
	}

	public Trade(Stock stock, Date timeStamp, String tradeIndicator,
			int quantity, double price) {
		super();
		this.stock = stock;
		this.timeStamp = timeStamp;
		this.tradeIndicator = tradeIndicator;
		this.quantity = quantity;
		this.price = price;

	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getTradeIndicator() {
		return tradeIndicator;
	}

	public void setTradeIndicator(String tradeIndicator) {
		this.tradeIndicator = tradeIndicator;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	
	
	
}
