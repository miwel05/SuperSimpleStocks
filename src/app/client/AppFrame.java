package app.client;


import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import app.model.GlobalConstants;
import app.model.Stock;
import app.model.Trade;

public class AppFrame extends JFrame implements ActionListener{
	
	private LinkedHashMap<String,Stock> stocks;
	
	
	
	//Constants identification panels
	private static final String EXCHANGE_PANEL = "0";
	private static final String NEW_STOCK_PANEL = "1";
	private static final String TRADES_PANEL = "2";
	private static final String CALCULATIONS_PANEL = "3";
	private static final String GBCE_PANEL = "4";
	


	
    //Dimensions jframe
    private static final int WINDTH_JFRAME = 800;
    private static final int HEIGHT_JFRAME = 600;
    
	//menus
	private JMenuBar mb;
	private JMenu m1;
	private JMenu m2;

	private JMenuItem mi1;
	private JMenuItem mi2;
	private JMenuItem mi3;
	private JMenuItem mi4;
	private JMenuItem mi5;
	
	
	//layout
	private CardLayout cardLayout;
	
	//panels
	private JPanel mainPanel, exchangePanel, newStockPanel, tradesPanel, calculationsPanel, gbcePanel;
	
	//components new stock panel
	private JLabel labelTitleNewStockPanel;
	private JPanel panel;
	private JLabel lblName;
	private JTextField textFieldName;
	private JPanel panel_1;
	private JLabel lblSymbol;
	private JTextField textFieldSymbol;
	private JPanel panel_2;
	private JLabel lblType;
	private JComboBox <String> comboBoxType;
	private JPanel panel_3;
	private JLabel lblLastDividend;
	private JTextField textFieldLastDividend;
	private JPanel panel_4;
	private JLabel lblFixedDividend;
	private JTextField textFieldFixedDividend;
	private JPanel panel_5;
	private JLabel lblParValue;
	private JTextField textFieldParValue;
	private JPanel panel_6;
	private JLabel lblTickerPrice;
	private JTextField textFieldTickerPrice;
	private JPanel panel_7;
	private JButton btnSaveNewStock;
	private JButton btnCancelNewStock;
	
	
	//components exchange panel
	private JLabel lblUpExchangePanel, lblCenterExchangePanel, lblDownExchangePanel;
	private JTable tableUp, tableCenter;
	private DefaultTableModel modelUp, modelCenter;
	private JScrollPane scrollPaneUp, scrollPaneCenter;
	private JPanel upExchangePanel;
	private JPanel centerExchangePanel;
	private JPanel downExchangePanel;
	private JPanel actionsPanel;
	private JComboBox comboBoxStocksActionsPanel;
	private JLabel lblStockActionsPanel, lblQuantityActionsPanel;
	private JTextField textFieldQuantityActionsPanel;
	private JButton buttonPlusActionsPanel;
	private JButton buttonMinusActionsPanel;
	private JLabel lblPriceActionsPanel;
	private JTextField textFieldPriceActionsPanel;
	private JButton btnBuyActionsPanel;
	private JPanel buttonsActionsPanel;
	private JButton btnSellActionsPanel;

	
	//components trades panel
	private JLabel lblTradesPanel;
	private DefaultTableModel modelTrades;
	private JTable tableTrades;
	private JScrollPane scrollPaneTrades;
	private JButton btnCloseTradesPanel;	
	
	//components calculations panel
	private JLabel lblCalculationsPanel;
	private JLabel lblSelectStockCalculationsPanel;
	private JPanel selectStockCalculationsPanel;
	private JComboBox comboBoxSelecteStockCalculationsPanel;
	private JPanel dividendYieldCalculationsPanel;
	private JLabel lblDividendYieldCalcualtionsPanel;
	private JTextField textFieldDividendYieldCalculationsPanel;
	private JPanel peRatioCalculationsPanel;
	private JLabel lblPeRatioCalculationsPanel;
	private JTextField textFieldPeRatioCalculationsPanel;
	private JButton btnCloseCalculationPanel;
	private JPanel stockPriceCalculationsPanel;
	private JLabel lblStockPriceLast;
	private JTextField textFieldStockPriceCalculationsPanel;
	
	
	
	//components gbce panel
	private JLabel lblGBCEPanel;
	private JPanel GBCEAllShareIndexPanel;
	private JLabel lblGbceAllShare;
	private JTextField textFieldGBCEAllShare;
	private JButton btnCloseGBCE;
	
	
	
	
	/**************************************************** CONSTRUCTOR ***********************************************************/
	
	public AppFrame(LinkedHashMap<String, Stock> listStocks) throws HeadlessException {
		super("Super Simple Stocks");	
		
		this.stocks = listStocks;
		
		//creating menus 
		mb = new JMenuBar();
	    m1 = new JMenu("File");
	    m2 = new JMenu("View");
	 
	    mb.add(m1);
	    mb.add(m2);
	
	    
	    setJMenuBar(mb);
	    
	    mi1 = new JMenuItem("New stock");
	    mi1.addActionListener(this);
	    mi2 = new JMenuItem("Exit");
	    mi2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    System.exit(0);
            }
        });
	    
	    
	    m1.add(mi1);
	    m1.addSeparator();
	    m1.add(mi2);
	    
	   	  
	    mi3 = new JMenuItem("Trades");
	    mi3.addActionListener(this);
	    m2.add(mi3);
	    
	    mi4 = new JMenuItem("Calculations");
	    mi4.addActionListener(this);
	    m2.add(mi4);
	    
	    mi5 = new JMenuItem("GBCE All Share Index");
	    mi5.addActionListener(this);
	    m2.add(mi5);

	    //building panels
	    buildExchangePanel();
	    buildNewStockPanel();
	    buildTradesPanel();
	    buildCalculationsPanel();
	    buildGBCEPanel();
	    
	    buildMainPanel();
	    
		

		getContentPane().add(mainPanel);

	    
		
	}
	
	/**************************************************** LAUNCHING ***********************************************************/
	
	//Show jframe in screen
	public void LaunchFrame(){

		setAlwaysOnTop(true);
		//place the jframe in the center of the screen
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    int height = screen.height;
	    int width = screen.width;
	    setLocation(width/2-WINDTH_JFRAME/2, height/2-HEIGHT_JFRAME/2);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(WINDTH_JFRAME, HEIGHT_JFRAME);
        setVisible(true);
        
	}

	//Collect menu events and launches the appropriate option
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		 if (ae.getActionCommand().equals("New stock")) {	    		    	
		    	launchNewStock();
		 }else if (ae.getActionCommand().equals("Trades")) {	    		    	
		    	launchTrades();
		 }else if (ae.getActionCommand().equals("Calculations")) {	    		    	
		    	launchCalculations();
		 }else if (ae.getActionCommand().equals("GBCE All Share Index")) {	    		    	
		    	launchGBCE();
		 }

	    
	}
	
	
	//Launch new stock panel
	private void launchNewStock() {
		activationMenus(false);
		cardLayout.show(mainPanel, NEW_STOCK_PANEL);	
	}
	
	//Launch trades panel
	private void launchTrades() {
		//activationMenus(false);
		cardLayout.show(mainPanel, TRADES_PANEL);	
	}

	
	//Launch calculations panel
	private void launchCalculations() {
		//activationMenus(false);
		cardLayout.show(mainPanel, CALCULATIONS_PANEL);	
	}
	
	//Launch gbce panel
	private void launchGBCE() {
		//activationMenus(false);
		cardLayout.show(mainPanel, GBCE_PANEL);	
	}
	
	/**************************************************** BUILDING PANELS ***********************************************************/
	
	
	//built panel used to display other panels depending on the choice
	private void buildMainPanel(){
		
		mainPanel = new JPanel();
		cardLayout = new CardLayout();
		
		mainPanel.setLayout(cardLayout);
		
		//Here we add all the corresponding panels to display the application as needed
		
		mainPanel.add(exchangePanel, EXCHANGE_PANEL);
		mainPanel.add(newStockPanel, NEW_STOCK_PANEL);
		mainPanel.add(tradesPanel, TRADES_PANEL);
		mainPanel.add(calculationsPanel, CALCULATIONS_PANEL);
		mainPanel.add(gbcePanel, GBCE_PANEL);
		
		
	}
	
	
	
	//built the exchange panel
	private void buildExchangePanel(){
		exchangePanel = new JPanel();
		exchangePanel.setLayout(new BoxLayout(exchangePanel, BoxLayout.Y_AXIS));
		
		//up
		
		upExchangePanel = new JPanel();
		upExchangePanel.setLayout(new BoxLayout(upExchangePanel, BoxLayout.Y_AXIS));
		
		
		lblUpExchangePanel = new JLabel("EXCHANGE PANEL");
		lblUpExchangePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblUpExchangePanel.setHorizontalAlignment(SwingConstants.CENTER);
		upExchangePanel.add(lblUpExchangePanel);
		
		
		
		
		modelUp = new DefaultTableModel();
		tableUp = new JTable();
		tableUp.setModel(modelUp);
		modelUp.addColumn("Name");
		modelUp.addColumn("Symbol");
		modelUp.addColumn("Type");
		modelUp.addColumn("Last Dividend");
		modelUp.addColumn("Fixed Dividend");
		modelUp.addColumn("Par Value");
		modelUp.addColumn("Ticker Price");	

		
		tableUp.getColumn("Name").setResizable(false);
		tableUp.getColumn("Symbol").setResizable(false);
		tableUp.getColumn("Type").setResizable(false);
		tableUp.getColumn("Last Dividend").setResizable(false);
		tableUp.getColumn("Fixed Dividend").setResizable(false);
		tableUp.getColumn("Par Value").setResizable(false);
		tableUp.getColumn("Ticker Price").setResizable(false);

		

		
		scrollPaneUp = new JScrollPane(tableUp);
				
		upExchangePanel.add(scrollPaneUp);
		
		exchangePanel.add(upExchangePanel);
		
		
		//center
		
		centerExchangePanel = new JPanel();
		centerExchangePanel.setLayout(new BoxLayout(centerExchangePanel, BoxLayout.Y_AXIS));
		
		
		lblCenterExchangePanel = new JLabel("TOTAL TRADES PANEL");
		lblCenterExchangePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblCenterExchangePanel.setHorizontalAlignment(SwingConstants.CENTER);
		centerExchangePanel.add(lblCenterExchangePanel);
		
		
		
		
		modelCenter = new DefaultTableModel();
		tableCenter = new JTable();
		tableCenter.setModel(modelCenter);
		modelCenter.addColumn("Stock");
		modelCenter.addColumn("Quantity");
		modelCenter.addColumn("All Trades Total Price");

		
		tableCenter.getColumn("Stock").setResizable(false);
		tableCenter.getColumn("Quantity").setResizable(false);
		tableCenter.getColumn("All Trades Total Price").setResizable(false);

		scrollPaneCenter = new JScrollPane(tableCenter);
	
		centerExchangePanel.add(scrollPaneCenter);
		
		exchangePanel.add(centerExchangePanel);
		
		
		//down
		
		downExchangePanel = new JPanel();
		downExchangePanel.setLayout(new BoxLayout(downExchangePanel, BoxLayout.Y_AXIS));
		
		
		lblDownExchangePanel = new JLabel("ACTIONS");
		lblDownExchangePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblDownExchangePanel.setHorizontalAlignment(SwingConstants.CENTER);
		downExchangePanel.add(lblDownExchangePanel);
		
		
		
		
		actionsPanel = new JPanel();
		downExchangePanel.add(actionsPanel);
		
		lblStockActionsPanel = new JLabel("Stock");
		actionsPanel.add(lblStockActionsPanel);
		
		DefaultComboBoxModel stocksModel = new DefaultComboBoxModel();
		for(Map.Entry<String, Stock> entry : stocks.entrySet()) {
			stocksModel.addElement(entry.getValue().getSymbol());	
		}

		
		comboBoxStocksActionsPanel = new JComboBox(stocksModel);
		comboBoxStocksActionsPanel.setMaximumRowCount(3);
		actionsPanel.add(comboBoxStocksActionsPanel);
		

		
		
		lblQuantityActionsPanel = new JLabel("Quantity");
		actionsPanel.add(lblQuantityActionsPanel);
		
		textFieldQuantityActionsPanel = new JTextField();
		textFieldQuantityActionsPanel.setEditable(false);
		textFieldQuantityActionsPanel.setText("1");
		actionsPanel.add(textFieldQuantityActionsPanel);
		textFieldQuantityActionsPanel.setColumns(3);
		
		buttonPlusActionsPanel = new JButton("+");
		actionsPanel.add(buttonPlusActionsPanel);
		buttonPlusActionsPanel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int quantity = 1;
				
				try {
					quantity = Integer.parseInt(textFieldQuantityActionsPanel.getText());
					quantity++;
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				textFieldQuantityActionsPanel.setText(""+quantity);
				
				
				
			}
		});
		
		buttonMinusActionsPanel = new JButton("-");
		actionsPanel.add(buttonMinusActionsPanel);
		buttonMinusActionsPanel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int quantity = 1;
				
				try {
					quantity = Integer.parseInt(textFieldQuantityActionsPanel.getText());
					quantity = (quantity<2)?1:--quantity;
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				textFieldQuantityActionsPanel.setText(""+quantity);
			
				
			}
		});
		
		lblPriceActionsPanel = new JLabel("Price");
		actionsPanel.add(lblPriceActionsPanel);
		
		
		
		Iterator <Map.Entry<String,Stock>> it = stocks.entrySet().iterator();
		Stock stock = it.next().getValue();
		
		
		
		textFieldPriceActionsPanel = new JTextField();
		textFieldPriceActionsPanel.setText(""+0.0);
		actionsPanel.add(textFieldPriceActionsPanel);
		textFieldPriceActionsPanel.setColumns(10);
		
		
		
		buttonsActionsPanel = new JPanel();
		downExchangePanel.add(buttonsActionsPanel);
		
		
		btnBuyActionsPanel = new JButton("Buy");
		btnBuyActionsPanel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int quantity = 1;
				
				try {
					quantity = Integer.parseInt(textFieldQuantityActionsPanel.getText());
							} catch (Exception e2) {
					// TODO: handle exception
				}
				String s = comboBoxStocksActionsPanel.getSelectedItem().toString();
				
				double price = 0.0;
				boolean error = false;
				try {
					price = Double.parseDouble(textFieldPriceActionsPanel.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(exchangePanel,
							"The price entered is not a number.",
							"Error in Price",
							JOptionPane.WARNING_MESSAGE);
					error = true;
				}
				if (!error && price<0) {
					JOptionPane.showMessageDialog(newStockPanel,
							"The price entered is not a positive number.",
							"Error in price",
							JOptionPane.WARNING_MESSAGE);

					error = true;
				}else if (!error && price==0) {
					JOptionPane.showMessageDialog(newStockPanel,
							"The price entered has to be greater than 0.",
							"Error in price",
							JOptionPane.WARNING_MESSAGE);

					error = true;
				}
				
				
				
				if (!error) {

					Stock stock = stocks.get(s);
					Trade trade = new Trade(stock, new Date(), GlobalConstants.TRADE_INDICATOR_BUY, quantity, price);			
					stock.recordTrade(trade);
					
					stock.setTickerPrice(stock.calculateStockPrice(15));
					
					textFieldDividendYieldCalculationsPanel.setText(""+stocks.get(comboBoxSelecteStockCalculationsPanel.getSelectedItem().toString()).getDividendYield());
					textFieldPeRatioCalculationsPanel.setText(""+stocks.get(comboBoxSelecteStockCalculationsPanel.getSelectedItem().toString()).getPeRatio());
					
					textFieldStockPriceCalculationsPanel.setText(""+stocks.get(comboBoxSelecteStockCalculationsPanel.getSelectedItem().toString()).calculateStockPrice(15));				
					
					textFieldGBCEAllShare.setText(""+calculateAllShareIndex());
					
					updateTradesTable();
					updateMySharesTable();
					updateExchangeTable();
					
				}
				
				
		
				
			}
		});
		buttonsActionsPanel.add(btnBuyActionsPanel);
		
		btnSellActionsPanel = new JButton("Sell");
		btnSellActionsPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int quantity = 1;
				
				try {
					quantity = Integer.parseInt(textFieldQuantityActionsPanel.getText());
				} catch (Exception e2) {
					// TODO: handle exception
				}
				String s = comboBoxStocksActionsPanel.getSelectedItem().toString();
				
		
				double price = 0.0;
				boolean error = false;
				try {
					price = Double.parseDouble(textFieldPriceActionsPanel.getText());
				} catch (Exception e3) {
					JOptionPane.showMessageDialog(exchangePanel,
							"The price entered is not a number.",
							"Error in Price",
							JOptionPane.WARNING_MESSAGE);
					error = true;
				}
				if (!error && price<0) {
					JOptionPane.showMessageDialog(newStockPanel,
							"The price entered is not a positive number.",
							"Error in price",
							JOptionPane.WARNING_MESSAGE);

					error = true;
				}else if (!error && price==0) {
					JOptionPane.showMessageDialog(newStockPanel,
							"The price entered has to be greater than 0.",
							"Error in price",
							JOptionPane.WARNING_MESSAGE);

					error = true;
				}
				
				
				
				if (!error) {

					Stock stock = stocks.get(s);
					Trade trade = new Trade(stock, new Date(), GlobalConstants.TRADE_INDICATOR_SELL, quantity, price);			
					stock.recordTrade(trade);
					
					stock.setTickerPrice(stock.calculateStockPrice(15));
					
					textFieldDividendYieldCalculationsPanel.setText(""+stocks.get(comboBoxSelecteStockCalculationsPanel.getSelectedItem().toString()).getDividendYield());
					textFieldPeRatioCalculationsPanel.setText(""+stocks.get(comboBoxSelecteStockCalculationsPanel.getSelectedItem().toString()).getPeRatio());
					
					textFieldStockPriceCalculationsPanel.setText(""+stocks.get(comboBoxSelecteStockCalculationsPanel.getSelectedItem().toString()).calculateStockPrice(15));
					
					textFieldGBCEAllShare.setText(""+calculateAllShareIndex());
					
					updateTradesTable();
					updateMySharesTable();
					updateExchangeTable();
				}
				
				

				
				

			}
		});
		buttonsActionsPanel.add(btnSellActionsPanel);
		

		
		exchangePanel.add(downExchangePanel);
		
		updateExchangeTable();

	}
	
	
	//built new stock panel
	private void buildNewStockPanel(){
		newStockPanel = new JPanel();
		newStockPanel.setLayout(new BoxLayout(newStockPanel, BoxLayout.Y_AXIS));

		labelTitleNewStockPanel = new JLabel("NEW STOCK");
		labelTitleNewStockPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		newStockPanel.add(labelTitleNewStockPanel);

		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		newStockPanel.add(panel);

		lblName = new JLabel("Name");
		panel.add(lblName);

		textFieldName = new JTextField();
		panel.add(textFieldName);
		textFieldName.setColumns(15);

		panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		newStockPanel.add(panel_1);

		lblSymbol = new JLabel("Symbol");
		panel_1.add(lblSymbol);

		textFieldSymbol = new JTextField();
		panel_1.add(textFieldSymbol);
		textFieldSymbol.setColumns(5);

		panel_2 = new JPanel();
		newStockPanel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblType = new JLabel("Type");
		panel_2.add(lblType);

		DefaultComboBoxModel types = new DefaultComboBoxModel();
	    types.addElement(GlobalConstants.STOCK_TYPE_COMMON);
	    types.addElement(GlobalConstants.STOCK_TYPE_PREFERRED);
	      
		comboBoxType = new JComboBox(types);
		panel_2.add(comboBoxType);
		comboBoxType.setSelectedIndex(0);

		comboBoxType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (comboBoxType.getSelectedItem().toString().equals(GlobalConstants.STOCK_TYPE_PREFERRED)) {
					lblFixedDividend.setEnabled(true);
					textFieldFixedDividend.setEnabled(true);
					textFieldFixedDividend.setText("");
				}else {
					lblFixedDividend.setEnabled(false);
					textFieldFixedDividend.setEnabled(false);
					textFieldFixedDividend.setText("");
				}
			}
		});
		
		panel_3 = new JPanel();
		newStockPanel.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblLastDividend = new JLabel("Last Dividend");
		panel_3.add(lblLastDividend);

		textFieldLastDividend = new JTextField();
		panel_3.add(textFieldLastDividend);
		textFieldLastDividend.setColumns(8);

		panel_4 = new JPanel();
		newStockPanel.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblFixedDividend = new JLabel("Fixed Dividend");
		lblFixedDividend.setEnabled(false);
		panel_4.add(lblFixedDividend);

		textFieldFixedDividend = new JTextField();
		textFieldFixedDividend.setEnabled(false);
		panel_4.add(textFieldFixedDividend);
		textFieldFixedDividend.setColumns(5);

		panel_5 = new JPanel();
		newStockPanel.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblParValue = new JLabel("Par Value");
		panel_5.add(lblParValue);

		textFieldParValue = new JTextField();
		panel_5.add(textFieldParValue);
		textFieldParValue.setColumns(8);

		panel_6 = new JPanel();
		newStockPanel.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblTickerPrice = new JLabel("Ticker Price");
		panel_6.add(lblTickerPrice);
		
		textFieldTickerPrice = new JTextField();
		panel_6.add(textFieldTickerPrice);
		textFieldTickerPrice.setColumns(8);


		panel_7 = new JPanel();
		newStockPanel.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnSaveNewStock = new JButton("Save");
		btnSaveNewStock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (createNewStock()){				

					JOptionPane.showMessageDialog(newStockPanel,
							"The stock has been created successfully.",
							"New Stock",
							JOptionPane.INFORMATION_MESSAGE);

					updateStocksCombo(textFieldSymbol.getText());
					
					cleanNewStockPanel();
				
					updateExchangeTable();

				}else {					
					JOptionPane.showMessageDialog(newStockPanel,
							"There have been problems. The stock has not been able to register.",
							"New Stock Error",
							JOptionPane.ERROR_MESSAGE);

				}
			}
		});


		panel_7.add(btnSaveNewStock);

		btnCancelNewStock = new JButton("Cancel");
		btnCancelNewStock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cleanNewStockPanel();				
			}
		});
		panel_7.add(btnCancelNewStock);


	}


	
	//built trades panel
	private void buildTradesPanel(){
		tradesPanel = new JPanel();
		tradesPanel.setLayout(new BoxLayout(tradesPanel, BoxLayout.Y_AXIS));
		

		lblTradesPanel = new JLabel("TRADES PANEL");
		lblTradesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTradesPanel.setHorizontalAlignment(SwingConstants.CENTER);
		tradesPanel.add(lblTradesPanel);
		

		modelTrades = new DefaultTableModel();
		tableTrades = new JTable();
		tableTrades.setModel(modelTrades);
		modelTrades.addColumn("Stock");
		modelTrades.addColumn("Timestamp");
		modelTrades.addColumn("Trade Indicator");
		modelTrades.addColumn("Quantity");
		modelTrades.addColumn("Price");
		modelTrades.addColumn("Total Price");


		tableTrades.getColumn("Stock").setResizable(false);
		tableTrades.getColumn("Timestamp").setResizable(false);
		tableTrades.getColumn("Trade Indicator").setResizable(false);
		tableTrades.getColumn("Quantity").setResizable(false);
		tableTrades.getColumn("Price").setResizable(false);
		tableTrades.getColumn("Total Price").setResizable(false);



		
		scrollPaneTrades = new JScrollPane(tableTrades);
				
		tradesPanel.add(scrollPaneTrades);
		
		btnCloseTradesPanel = new JButton("Close");
		btnCloseTradesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCloseTradesPanel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(mainPanel, EXCHANGE_PANEL);
				
			}
		});
		
		tradesPanel.add(btnCloseTradesPanel);
	


	}
	
	
	//built calculations panel
	private void buildCalculationsPanel(){
		calculationsPanel = new JPanel();
		calculationsPanel.setLayout(new BoxLayout(calculationsPanel, BoxLayout.Y_AXIS));
		
		lblCalculationsPanel = new JLabel("CALCULATIONS PANEL");
		lblCalculationsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		calculationsPanel.add(lblCalculationsPanel);
		
		selectStockCalculationsPanel = new JPanel();
		calculationsPanel.add(selectStockCalculationsPanel);
		
		lblSelectStockCalculationsPanel = new JLabel("Select Stock");
		selectStockCalculationsPanel.add(lblSelectStockCalculationsPanel);
		lblSelectStockCalculationsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		DefaultComboBoxModel stocksModel = new DefaultComboBoxModel();
		for(Map.Entry<String, Stock> entry : stocks.entrySet()) {
			stocksModel.addElement(entry.getValue().getSymbol());	
		}

		
		comboBoxSelecteStockCalculationsPanel = new JComboBox(stocksModel);
		comboBoxSelecteStockCalculationsPanel.setMaximumRowCount(3);
		selectStockCalculationsPanel.add(comboBoxSelecteStockCalculationsPanel);
		comboBoxSelecteStockCalculationsPanel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String s = comboBoxSelecteStockCalculationsPanel.getSelectedItem().toString();
				Stock stock = stocks.get(s);
				textFieldDividendYieldCalculationsPanel.setText(""+stock.getDividendYield());
				textFieldPeRatioCalculationsPanel.setText(""+stock.getPeRatio());
				textFieldStockPriceCalculationsPanel.setText(""+stock.calculateStockPrice(15));

				
			}
		});
		
		dividendYieldCalculationsPanel = new JPanel();
		calculationsPanel.add(dividendYieldCalculationsPanel);
		
		lblDividendYieldCalcualtionsPanel = new JLabel("Dividend Yield");
		dividendYieldCalculationsPanel.add(lblDividendYieldCalcualtionsPanel);
		
		textFieldDividendYieldCalculationsPanel = new JTextField();
		textFieldDividendYieldCalculationsPanel.setEditable(false);
		dividendYieldCalculationsPanel.add(textFieldDividendYieldCalculationsPanel);
		textFieldDividendYieldCalculationsPanel.setColumns(15);

		peRatioCalculationsPanel = new JPanel();
		calculationsPanel.add(peRatioCalculationsPanel);
		
		lblPeRatioCalculationsPanel = new JLabel("P/E Ratio");
		peRatioCalculationsPanel.add(lblPeRatioCalculationsPanel);
		
		textFieldPeRatioCalculationsPanel = new JTextField();
		textFieldPeRatioCalculationsPanel.setEditable(false);
		peRatioCalculationsPanel.add(textFieldPeRatioCalculationsPanel);
		textFieldPeRatioCalculationsPanel.setColumns(15);
		
		btnCloseCalculationPanel = new JButton("Close");
		btnCloseCalculationPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(mainPanel, EXCHANGE_PANEL);
			}
		});
		
		stockPriceCalculationsPanel = new JPanel();
		calculationsPanel.add(stockPriceCalculationsPanel);
		
		lblStockPriceLast = new JLabel("Stock Price based on trades recorded in past 15 minutes");
		stockPriceCalculationsPanel.add(lblStockPriceLast);
		
		textFieldStockPriceCalculationsPanel = new JTextField();
		textFieldStockPriceCalculationsPanel.setEditable(false);
		stockPriceCalculationsPanel.add(textFieldStockPriceCalculationsPanel);
		textFieldStockPriceCalculationsPanel.setColumns(15);
		btnCloseCalculationPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		calculationsPanel.add(btnCloseCalculationPanel);
		
		String s = comboBoxSelecteStockCalculationsPanel.getSelectedItem().toString();
		Stock stock = stocks.get(s);
		textFieldDividendYieldCalculationsPanel.setText(""+stock.getDividendYield());
		textFieldPeRatioCalculationsPanel.setText(""+stock.getPeRatio());
		textFieldStockPriceCalculationsPanel.setText(""+stock.calculateStockPrice(15));

		
		

	}
	
	

	//built gbce panel
	private void buildGBCEPanel(){
		gbcePanel = new JPanel();
		gbcePanel.setLayout(new BoxLayout(gbcePanel, BoxLayout.Y_AXIS));
		
		lblGBCEPanel = new JLabel("GBCE PANEL");
		lblGBCEPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		gbcePanel.add(lblGBCEPanel);
		
		GBCEAllShareIndexPanel = new JPanel();
		gbcePanel.add(GBCEAllShareIndexPanel);
		
		lblGbceAllShare = new JLabel("GBCE All Share Index ");
		GBCEAllShareIndexPanel.add(lblGbceAllShare);
		
		textFieldGBCEAllShare = new JTextField();
		textFieldGBCEAllShare.setEditable(false);
		GBCEAllShareIndexPanel.add(textFieldGBCEAllShare);
		textFieldGBCEAllShare.setColumns(15);
		textFieldGBCEAllShare.setText(""+calculateAllShareIndex());
		
		btnCloseGBCE = new JButton("Close");
		btnCloseGBCE.setAlignmentX(Component.CENTER_ALIGNMENT);
		gbcePanel.add(btnCloseGBCE);
		
		btnCloseGBCE.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				cardLayout.show(mainPanel, EXCHANGE_PANEL);
			}
		});




	}
	
	/**************************************************** MANAGEMENT OPERATIONS ***********************************************************/
	
	//create new stock
	private boolean createNewStock() {
		boolean resultado = false;	

		String name = textFieldName.getText();

		String symbol = textFieldSymbol.getText();
		if (symbol.length()>3) {
			JOptionPane.showMessageDialog(newStockPanel,
					"Symbol too long, 50 characters maximum.",
					"Error in symbol",
					JOptionPane.WARNING_MESSAGE);

			return false;
		}

		String type = comboBoxType.getSelectedItem().toString();
	

		double lastDividend = 0;
		try {
			lastDividend = Double.parseDouble(textFieldLastDividend.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(newStockPanel,
					"Last Dividend entered is not a number.",
					"Error in Last Dividend",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (lastDividend<0) {
			JOptionPane.showMessageDialog(newStockPanel,
					"Last Dividend entered is not a positive number.",
					"Error in Last Dividend",
					JOptionPane.WARNING_MESSAGE);

			return false;
		}

		Double fixedDividend = null;
		if (type.equals(GlobalConstants.STOCK_TYPE_COMMON)) fixedDividend=null;
		else{
			try {
				fixedDividend = Double.parseDouble(textFieldFixedDividend.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(newStockPanel,
						"Fixed Dividend entered is not a number.",
						"Error in Fixed Dividend",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
			if (fixedDividend<0) {
				JOptionPane.showMessageDialog(newStockPanel,
						"Fixed Dividend entered is not a positive number.",
						"Error in Fixed Dividend",
						JOptionPane.WARNING_MESSAGE);

				return false;
			}
		}
		
		double parValue = 0;
		try {
			parValue = Double.parseDouble(textFieldParValue.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(newStockPanel,
					"Par Value entered is not a number.",
					"Error in Par Value",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (parValue<0) {
			JOptionPane.showMessageDialog(newStockPanel,
					"Par Value entered is not a positive number.",
					"Error in Par Value",
					JOptionPane.WARNING_MESSAGE);

			return false;
		}
		
		double tickerPrice = 0;
		try {
			tickerPrice = Double.parseDouble(textFieldTickerPrice.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(newStockPanel,
					"Ticker Price entered is not a number.",
					"Error in Ticker Price",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (tickerPrice<0) {
			JOptionPane.showMessageDialog(newStockPanel,
					"Ticker Price entered is not a positive number.",
					"Error in Ticker Price",
					JOptionPane.WARNING_MESSAGE);

			return false;
		}

		
		
		Stock newStock = new Stock(name, symbol, type, lastDividend, fixedDividend, parValue, tickerPrice);
		stocks.put(symbol, newStock);
		
		


		return true;
	}

	
	

	
	/**************************************************** CLEANING SCREEN ***********************************************************/
	
	//Change the blank panel and resets the values entered in newStockPanel
	private void cleanNewStockPanel(){
		cardLayout.show(mainPanel, EXCHANGE_PANEL);
		
		//initialise the text fields for the next time it is opened
		textFieldName.setText("");
		textFieldSymbol.setText("");
		comboBoxType.setSelectedIndex(0);
		textFieldLastDividend.setText("");
		textFieldFixedDividend.setText("");
		textFieldParValue.setText("");
		textFieldTickerPrice.setText("");
		
		activationMenus(true);

	}
	
	
	
	//Enables or disables all menus to not interfere with the operation that I am doing
	private void activationMenus(boolean op){
    	m1.setEnabled(op);
    	m2.setEnabled(op);

	}
	
	
	
	/**************************************************** UPDATE INFO ***********************************************************/
	
	
	
	private void updateExchangeTable() {
		
		while (modelUp.getRowCount()>0) modelUp.removeRow(0);
		
		for(Map.Entry<String, Stock> entry : stocks.entrySet()) {
		
			Stock s = entry.getValue();
			
			String[] row= new String [7];
			row [0] = s.getName();
			row [1] = s.getSymbol();
			row [2] = s.getType();
			row [3] = ""+s.getLastDividend();
			row [4] = (s.getFixedDividend()==null? "" : ""+s.getFixedDividend());
			row [5] = ""+s.getParValue();
			row [6] = ""+s.getTickerPrice();
			
			modelUp.addRow(row);							
			
		}
		
		
	}
	
	
	private void updateMySharesTable() {
		
		while (modelCenter.getRowCount()>0) modelCenter.removeRow(0);
		
		
		for(Map.Entry<String, Stock> entry : stocks.entrySet()) {

			Stock stock = entry.getValue();
			List<Trade> listTrades = stock.getListTrades();
			
			int totalQuantity = 0;
			for(Trade trade: listTrades) {
				totalQuantity += trade.getQuantity();
			}
			
			double totalPrice = 0;
			for(Trade trade: listTrades) {
				totalPrice += (double)trade.getQuantity()*trade.getPrice();
			}
			
			if (totalQuantity>0) {
			
				String[] row= new String [3];
				row [0] = stock.getSymbol();
				row [1] = ""+totalQuantity;
				row [2] = ""+totalPrice;
				
				
				modelCenter.addRow(row);	
			}
			
		}
		
		
	}
	
	private void updateTradesTable() {
		
		while (modelTrades.getRowCount()>0) modelTrades.removeRow(0);
		
		ArrayList<Trade> listTradesTotal = new ArrayList<>();
		for(Map.Entry<String, Stock> entry : stocks.entrySet()) {
			Stock stock = entry.getValue();	
			List <Trade> listTrades = stock.getListTrades();

			listTradesTotal.addAll(listTrades);
		}
		
		Collections.sort(listTradesTotal, new Comparator<Trade>() {

			@Override
			public int compare(Trade t1, Trade t2) {
				
				return new Long(t1.getTimeStamp().getTime()).compareTo(new Long(t2.getTimeStamp().getTime()));
			}
		});
		
		for (int i=0; i<listTradesTotal.size(); i++){
			Trade trade = listTradesTotal.get(i);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");			
			
			String[] row= new String [6];
			row [0] = trade.getStock().getSymbol();
			row [1] = ""+sdf.format(trade.getTimeStamp());
			row [2] = trade.getTradeIndicator();
			row [3] = ""+trade.getQuantity();
			row [4] = ""+trade.getPrice();
			row [5] = ""+trade.getPrice()*trade.getQuantity();
			
			
			modelTrades.addRow(row);							
			
		}
		
		
	}
	
	private void updateStocksCombo(String stock) {

		comboBoxStocksActionsPanel.addItem(stock);
		comboBoxSelecteStockCalculationsPanel.addItem(stock);
	}
	
	
	/**************************************************** CALCULATE GBCE ***********************************************************/
	
	// Calculate GBCE All Share Index for all stocks
	
	public Double calculateAllShareIndex() {
		Double allShareIndex = 1.0;
		for(Map.Entry<String, Stock> entry : stocks.entrySet()) {
			Stock stock = entry.getValue();
			allShareIndex*=stock.getTickerPrice();
		}
		return Math.pow(allShareIndex, 1.0 / stocks.size());
	}

	
}
