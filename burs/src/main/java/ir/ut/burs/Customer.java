package ir.ut.burs;

import java.util.*;

public class Customer {

	public int id;
	public double credit;
	public String name, family;
	
	public LinkedList<customerStock> myStocks;
	public LinkedList<Request> doneRequests;
	public LinkedList<Request> runningRequests;
	public LinkedList<Request> deniadRequests;
	
	public class customerStock {	
		public String sign;
		public int volume;
		
		customerStock ( String s, int v ) {
			sign = s;
			volume = v;
		}
	}
	
	
	Customer ( int sID, double sCredit, String sName, String sFamily ) {
		
		id = sID;
		credit = sCredit;
		name = sName;
		family = sFamily;
		
		myStocks = new LinkedList<customerStock>();
		doneRequests = new LinkedList<Request>();
		runningRequests = new LinkedList<Request>();
		deniadRequests = new LinkedList<Request>();	
	}
	
	public void addStockToMyStocks( String sign, int volume ) { myStocks.add( new customerStock( sign, volume ) ); }

	public void creditManage( String mode, double amount,  Statement st ) { 





		ResultSet rs = st.executeQuery("select * from customer");
		while(rs.next())
		{
			if ( id == Integer.parseInt(rs.getString("id")) )
				rs.getStirng("credit")
		}
		
		if ( mode.equals("DEPOSIT") )
			credit += amount;
		else if ( mode.equals("WITHDRAW") )
			credit -= amount;
	}
	public boolean haveStock ( String stockSign ) {
		for (int i = 0; i < myStocks.size(); i++) {
			if ( myStocks.get(i).sign.toUpperCase().equals(stockSign.toUpperCase()) ) return true;
		}
		return false;
	}
	public boolean enoughVolume ( String stockSign, int stockVolume ) {
		
		
		for (int i = 0; i < myStocks.size(); i++) {
			customerStock cs = myStocks.get(i);
			if ( cs.sign.toUpperCase().equals(stockSign) )
				if ( cs.volume < stockVolume ) return false;
				else break;
		}
		return true;
	}
	public void makeStockDetailChangeForBuy ( String buyStockSign, int buyStockVolume ) {
		
		customerStock cs = null;
		for (int i = 0; i < myStocks.size(); i++) {
			cs = myStocks.get(i);
			if ( cs.sign.equals( buyStockSign.toUpperCase()) ) break;
			else cs = null;
		}
		
		if ( cs == null )  { cs = new customerStock(buyStockSign, buyStockVolume); myStocks.add(cs);}
		else
			cs.volume += buyStockVolume;
	}
	public void makeStockDetailChangeForSell ( String sellStockSign, int sellStockVolume, int sellStockPrice ) {
		if ( id == 0 ) return;
		
		credit += (sellStockPrice*sellStockVolume);
		
		customerStock cs = null;
		for (int i = 0; i < myStocks.size(); i++) {
			cs = myStocks.get(i);
			if ( cs.sign.equals( sellStockSign.toUpperCase()) ) break;
		}
		
		cs.volume -= sellStockVolume;
		
		if ( cs.volume == 0 ) 
			myStocks.remove(cs);
	}

	
}
