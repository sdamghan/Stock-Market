package ir.ut.burs;

import java.io.*;
import java.util.*;


public abstract class ServerTypeRun {

	protected Request currentReq;
	protected List<Stock> stocks;
	protected List<Customer> customers;
	protected static StringBuilder sb = new StringBuilder();
	protected static List<String> deals = new LinkedList();
	
	public static List<String> getDeals () {
		return deals;
	}
	
	public abstract String serverRun ();
	
	protected Customer searchCustomer ( int id ) {
	    for (int i = 0; i < customers.size(); i++) {
		    Customer tempCus = customers.get(i);
		    if ( id == tempCus.id ) return tempCus;
	    }
	    return null;
    }
	protected  Stock searchStock ( String stockSign ) {
	    
	    Stock stock;
	    for (int i = 0; i < stocks.size(); i++) {
		    stock = stocks.get(i);
		    if ( stock.sign.equals(stockSign) ) return stock;
	    }
	    return null;
    }
	protected String buyCalculate ( Request headOfSell, Customer reqCustomer, Stock stock ) {
	    
	    Customer sellHeadCustomer = searchCustomer(headOfSell.customerID);
	    
	    if ( currentReq.volume > headOfSell.volume ) {
		    
		    reqCustomer.makeStockDetailChangeForBuy(currentReq.sign, headOfSell.volume);
		    if ( currentReq.type.equals("MPO") ) reqCustomer.creditManage("WITHDRAW", headOfSell.volume*currentReq.pricePerOne);
		    sellHeadCustomer.makeStockDetailChangeForSell(currentReq.sign, headOfSell.volume, currentReq.pricePerOne);
		    
		    currentReq.volume -= headOfSell.volume;
		    
		    headOfSell.state = "DONE";
		    sellHeadCustomer.doneRequests.add(headOfSell);
		    sellHeadCustomer.runningRequests.remove(headOfSell);
		    
		    stock.remove("SELL", headOfSell);
		    
		    sb.setLength(0);
		    sb.append("فروشنده به شناسه ");
		    sb.append(sellHeadCustomer.id);
		    sb.append(" میزان ");
		    sb.append(headOfSell.volume);
		    sb.append(" سهم از ");
		    sb.append(currentReq.sign);
		    sb.append(" را به قیمت ");
		    sb.append(currentReq.pricePerOne);
		    sb.append(" به خریدار به شناسه "); 
		    sb.append(reqCustomer.id);
		    sb.append(" فروخت.<br>");
		    deals.add( reqCustomer.id + "," + sellHeadCustomer.id + "," + currentReq.sign + "," + 
		    		   currentReq.type + "," + headOfSell.volume + "," + reqCustomer.credit + "," +
		    		   sellHeadCustomer.credit );
	    }
	    
	    
	    else {
		    
		    if ( currentReq.volume == headOfSell.volume ) {
			    
			    reqCustomer.makeStockDetailChangeForBuy ( currentReq.sign, headOfSell.volume );
			    if ( currentReq.type.equals("MPO") ) reqCustomer.creditManage("WITHDRAW", headOfSell.volume*currentReq.pricePerOne);
			    sellHeadCustomer.makeStockDetailChangeForSell( currentReq.sign, headOfSell.volume, currentReq.pricePerOne );
			    
			    currentReq.state = "DONE";
			    reqCustomer.doneRequests.add(currentReq);
			    reqCustomer.runningRequests.remove(currentReq);
			    
			    headOfSell.state = "DONE";
			    sellHeadCustomer.doneRequests.add(headOfSell);
			    sellHeadCustomer.runningRequests.remove(headOfSell);
			    
			    stock.remove("SELL", headOfSell);
		    }
		    
		    else {
			    
			    reqCustomer.makeStockDetailChangeForBuy ( currentReq.sign, currentReq.volume );
			    if ( currentReq.type.equals("MPO") ) reqCustomer.creditManage("WITHDRAW", currentReq.volume*currentReq.pricePerOne);
			    sellHeadCustomer.makeStockDetailChangeForSell( currentReq.sign, currentReq.volume, currentReq.pricePerOne );
			    
			    currentReq.state = "DONE";
			    reqCustomer.doneRequests.add(currentReq);
			    reqCustomer.runningRequests.remove(currentReq);
			    
			    headOfSell.volume -= currentReq.volume;
		    }
		    
		    sb.setLength(0);
		    sb.append("فروشنده به شناسه ");
		    sb.append(sellHeadCustomer.id);
		    sb.append(" میزان ");
		    sb.append(currentReq.volume);
		    sb.append(" سهم از ");
		    sb.append(currentReq.sign);
		    sb.append(" را به قیمت ");
		    sb.append(currentReq.pricePerOne);
		    sb.append(" به خریدار به شناسه "); 
		    sb.append(reqCustomer.id);
		    sb.append(" فروخت.<br>");
		    deals.add( reqCustomer.id + "," + sellHeadCustomer.id + "," + currentReq.sign + "," + 
		    		   currentReq.type + "," + currentReq.volume + "," + reqCustomer.credit + "," +
		    		   sellHeadCustomer.credit );

		    currentReq.volume = 0;
	    }
    
	    return sb.toString();
    }
    protected String sellCalculate ( Request headOfBuy, Customer reqCustomer, Stock stock ) {
    
	    Customer buyHeadCustomer = searchCustomer(headOfBuy.customerID);
	    
	    if ( currentReq.volume > headOfBuy.volume ) {
		    
		    reqCustomer.makeStockDetailChangeForSell(currentReq.sign, headOfBuy.volume, headOfBuy.pricePerOne);
		    if ( currentReq.type.equals("MPO") ) reqCustomer.creditManage("WITHDRAW", headOfBuy.volume*headOfBuy.pricePerOne);
		    buyHeadCustomer.makeStockDetailChangeForBuy(currentReq.sign, headOfBuy.volume);
		    
		    currentReq.volume -= headOfBuy.volume;
		    
		    headOfBuy.state = "DONE";
		    buyHeadCustomer.doneRequests.add(headOfBuy);
		    buyHeadCustomer.runningRequests.remove(headOfBuy);
		    
		    stock.remove("BUY", headOfBuy);
		    
		    sb.setLength(0);
	    	sb.append("فروشنده به شناسه ");
		    sb.append(reqCustomer.id);
		    sb.append(" میزان ");
		    sb.append(headOfBuy.volume);
		    sb.append(" سهم از ");
		    sb.append(currentReq.sign);
		    sb.append(" را به قیمت ");
		    sb.append(headOfBuy.pricePerOne);
		    sb.append(" به خریدار به شناسه "); 
		    sb.append(buyHeadCustomer.id);
		    sb.append(" فروخت.<br>");
		    deals.add( buyHeadCustomer.id + "," + reqCustomer.id + "," + currentReq.sign + "," + 
		    		   currentReq.type + "," + headOfBuy.volume + "," + buyHeadCustomer.credit + "," +
		    		   reqCustomer.credit );
	    }
	    
	    
	    else {
		    
		    if ( currentReq.volume == headOfBuy.volume ) {
			    
			    reqCustomer.makeStockDetailChangeForSell ( currentReq.sign, currentReq.volume, headOfBuy.pricePerOne );
			    if ( currentReq.type.equals("MPO") ) reqCustomer.creditManage("WITHDRAW", headOfBuy.volume*headOfBuy.pricePerOne);
			    buyHeadCustomer.makeStockDetailChangeForBuy( currentReq.sign, currentReq.volume );
			    
			    currentReq.state = "DONE";
			    reqCustomer.doneRequests.add(currentReq);
			    reqCustomer.runningRequests.remove(currentReq);
			    
			    headOfBuy.state = "DONE";
			    buyHeadCustomer.doneRequests.add(headOfBuy);
			    buyHeadCustomer.runningRequests.remove(headOfBuy);
			    
			    stock.remove("BUY", headOfBuy);
		    }
		    
		    else {
			    
			    reqCustomer.makeStockDetailChangeForSell ( currentReq.sign, currentReq.volume, headOfBuy.pricePerOne );
			    if ( currentReq.type.equals("MPO") ) reqCustomer.creditManage("WITHDRAW", currentReq.volume*headOfBuy.pricePerOne);
			    buyHeadCustomer.makeStockDetailChangeForBuy( currentReq.sign, currentReq.volume );
			    
			    currentReq.state = "DONE";
			    reqCustomer.doneRequests.add(currentReq);
			    reqCustomer.runningRequests.remove(currentReq);
			    
			    headOfBuy.volume -= currentReq.volume;
		    }
		    
		    sb.setLength(0);
		    sb.append("فروشنده به شناسه ");
		    sb.append(reqCustomer.id);
		    sb.append(" میزان ");
		    sb.append(currentReq.volume);
		    sb.append(" سهم از ");
		    sb.append(currentReq.sign);
		    sb.append(" را به قیمت ");
		    sb.append(headOfBuy.pricePerOne);
		    sb.append(" به خریدار به شناسه "); 
		    sb.append(buyHeadCustomer.id);
		    sb.append(" فروخت.<br>");
		    deals.add( buyHeadCustomer.id + "," + reqCustomer.id + "," + currentReq.sign + "," + 
		    		   currentReq.type + "," + currentReq.volume + "," + buyHeadCustomer.credit + "," +
		    		   reqCustomer.credit );
		    
		    currentReq.volume = 0;
	    
	    }
    
	    return sb.toString();
    }

}
