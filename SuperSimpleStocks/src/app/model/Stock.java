package app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class Stock {
	
	private int ID;
	private String name;
	private String symbol;
	private String type;
	private double lastDividend;
	private Double fixedDividend = null;
	private double parValue;
	private double tickerPrice;
	private List<Trade> listTrades;
	
	private static int counter = 0; //counter instances, is used to initialize the unique id without manually enter
	
	/**
	 * Default Constructor
	 */
	public Stock() {
		super();
		
		listTrades = new ArrayList<>();
		counter++;
		
	}

	//Constructor 
	public Stock(String name, String symbol, String type,
			double lastDividend, Double fixedDividend, double parValue, double tickerPrice) {
		
		super();
		this.ID = counter;
		this.name = name;
		this.symbol = symbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
		this.tickerPrice = tickerPrice;
		this.listTrades = new ArrayList<>();
		
		counter++;
	}

	
	
	public double getDividendYield() {
		double dividendYield = 0.0;
	
		if(type==GlobalConstants.STOCK_TYPE_COMMON){
			dividendYield = lastDividend / tickerPrice;
		}else{//Preferred
			dividendYield = (fixedDividend.doubleValue() * parValue ) /  tickerPrice;
		}
		
		return dividendYield;
	}
	
	public double getPeRatio() {
		double peRatio = 0.0;
		

		peRatio = tickerPrice / getDividendYield();	
	
		
		return peRatio;
	}

	
	
	public void recordTrade(Trade trade) {
		listTrades.add(trade);
	}
	
	
	public double calculateStockPrice(int minutesRange) {
		double stockPrice = 0.0;

		List<Trade> lastTradesList = new ArrayList<>();

		Date currentTime = new Date();
		for (Trade t: listTrades) {
			
			long tradeTimestampMilisecond = t.getTimeStamp().getTime();
			long currentTimeMilisecond = currentTime.getTime();
			long minutesRangeMilisecond = minutesRange*60*1000;

			if (tradeTimestampMilisecond>=currentTimeMilisecond-minutesRangeMilisecond) lastTradesList.add(t);

		}
		
		
		int quantitySum = 0;
		double tradePriceSum = 0.0;
		for(Trade trade : lastTradesList){
			//Sum Trade Price * Quantity
			tradePriceSum += (trade.getPrice() * trade.getQuantity());
			// Sum Quantities
			quantitySum += trade.getQuantity();
		}

		// calculate the stock price
		if(quantitySum > 0){
			stockPrice = tradePriceSum / quantitySum;	
		}


		return stockPrice;
	}
	
	
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public Double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public double getParValue() {
		return parValue;
	}

	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	public double getTickerPrice() {
		return tickerPrice;
	}

	public void setTickerPrice(double tickerPrice) {
		this.tickerPrice = tickerPrice;
	}

	public List<Trade> getListTrades() {
		return listTrades;
	}

	public void setListTrades(List<Trade> listTrades) {
		this.listTrades = listTrades;
	}



	



		

}
