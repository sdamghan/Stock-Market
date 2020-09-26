package ir.ut.burs;

import java.util.*;


public class Stock {

	public int price;
	public String sign;
	
	private List<Request> buyQueue;
	private List<Request> sellQueue;
	
	Stock( String sSign, int sPrice ) {
		
		sign = sSign;
		price = sPrice;
		
		buyQueue = new LinkedList<Request>();
		sellQueue = new LinkedList<Request>();
	}
	
	public void addToQueue ( Request req ) {
		
		if ( req.reqMode.equals("BUY") ) {
			buyQueue.add ( findIndex("BUY", req.pricePerOne), req );
		}
		else if ( req.reqMode.equals("SELL") )
			sellQueue.add ( findIndex("SELL", req.pricePerOne) , req );
	}
	private int findIndex ( String sortMode, int price ) {
		if ( sortMode.equals("BUY") ) {
			
			if ( buyQueue.size() == 0 ) return 0;
			else if ( buyQueue.size() == 1 ) {
				if ( buyQueue.get(0).pricePerOne < price ) return 0;
				else return 1;
			}
			else {
				int i = 0;
				while ( i < buyQueue.size() ) {
					if ( price > buyQueue.get(i).pricePerOne  ) return i;
					else i++;
				}
				if ( i == buyQueue.size() ) return (buyQueue.size());
			}
			
		}
		
		else if ( sortMode.equals("SELL") ) {
			
			if ( sellQueue.size() == 0 ) return 0;
			else if ( sellQueue.size() == 1 ) {
				if ( sellQueue.get(0).pricePerOne > price ) return 0;
				else return 1;
			}
			else {
				System.err.println("tuyesell" + price);
				int i = 0;
				while ( i < sellQueue.size() ) {
					if ( price < sellQueue.get(i).pricePerOne  ) return i;
					else i++;
				}
				
				if ( i == sellQueue.size() ) return (sellQueue.size());
			}
			
		}
		
		return -1;
	}
	public Request getHeadOfList ( String str ) {
		
		if ( str.equals ("BUY") ) {
			if(buyQueue.size()!=0) return buyQueue.get(0);
			else return null;
			}
		else {  
			if ( sellQueue.size()!=0 ) return sellQueue.get(0);
			else return null;}
	}
	public void remove ( String mode, Request req ) {
		
		if ( mode.equals("BUY") ) buyQueue.remove(req);
		else sellQueue.remove(req);
	}
	public List<Request> getList(String mode) {
		
		if(mode.equals("BUY")) return buyQueue;
		else return sellQueue;
	}
}
