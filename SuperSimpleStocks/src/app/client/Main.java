package app.client;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import app.model.GlobalConstants;
import app.model.Stock;



public class Main {

	public static void main(String[] args) {
		
		LinkedHashMap<String, Stock> listStocks = new LinkedHashMap();
		
		//initials stocks
		Stock stock1 = new Stock("Tea", "TEA", GlobalConstants.STOCK_TYPE_COMMON, 10, null, 100, 40);
		Stock stock2 = new Stock("Pop", "POP", GlobalConstants.STOCK_TYPE_COMMON, 8, null, 100, 30);
		Stock stock3 = new Stock("Ale Beer", "ALE", GlobalConstants.STOCK_TYPE_COMMON, 23, null, 60, 80);
		Stock stock4 = new Stock("Gin", "GIN", GlobalConstants.STOCK_TYPE_PREFERRED, 8, 2.0, 100, 150);
		Stock stock5 = new Stock("Joe coffee", "JOE", GlobalConstants.STOCK_TYPE_COMMON, 13, null, 250, 50);
		
		listStocks.put(stock1.getSymbol(), stock1);
		listStocks.put(stock2.getSymbol(), stock2);
		listStocks.put(stock3.getSymbol(), stock3);
		listStocks.put(stock4.getSymbol(), stock4);
		listStocks.put(stock5.getSymbol(), stock5);
		
		
		AppFrame appFrame = new AppFrame(listStocks);
    	
    	appFrame.LaunchFrame();

	}

}
