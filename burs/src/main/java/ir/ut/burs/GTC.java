package ir.ut.burs;
import java.util.List;


public class GTC extends ServerTypeRun {
	
	public GTC ( Request req, List<Customer> cstrs, List<Stock> stks ) {
		// TODO Auto-generated constructor stub
		currentReq = req;
		stocks = stks;
		customers = cstrs;
	}
	
	public String serverRun () {
	    
	    Stock stock = searchStock(currentReq.sign);
    
	    Request headOfBuy = stock.getHeadOfList("BUY");
	    Request headOfSell = stock.getHeadOfList("SELL");
	    

	    if ( headOfBuy == null || headOfSell == null || headOfBuy.pricePerOne < headOfSell.pricePerOne )
		    return "درخواست شما از نوع GTC در صف قرار گرفت.";
		  
	    
	    sb.setLength(0);
	    while ( (headOfBuy!=null) && (headOfSell!=null) && headOfBuy.pricePerOne >= headOfSell.pricePerOne ) {
		    
		    Customer buyHeadCustomer = searchCustomer(headOfBuy.customerID);
		    Customer sellHeadCustomer = searchCustomer(headOfSell.customerID);
		    
		    if ( headOfBuy.volume > headOfSell.volume ) {
		    
			    
			    buyHeadCustomer.makeStockDetailChangeForBuy ( currentReq.sign, headOfSell.volume );
			    sellHeadCustomer.makeStockDetailChangeForSell( currentReq.sign, headOfSell.volume, headOfBuy.pricePerOne );	
				    
			    headOfBuy.volume -= headOfSell.volume;
			    
			    headOfSell.state = "DONE";
			    sellHeadCustomer.doneRequests.add(headOfSell);
			    sellHeadCustomer.runningRequests.remove(headOfSell);
			    
			    stock.remove("SELL", headOfSell);
			    
		    	
			    sb.append("فروشنده به شناسه ");
			    sb.append(sellHeadCustomer.id);
			    sb.append(" میزان ");
			    sb.append(headOfSell.volume);
			    sb.append(" سهم از ");
			    sb.append(currentReq.sign);
			    sb.append(" را به قیمت ");
			    sb.append(headOfBuy.pricePerOne);
			    sb.append(" به خریدار به شناسه "); 
			    sb.append(buyHeadCustomer.id);
			    sb.append(" فروخت.<br>");
			    deals.add( buyHeadCustomer.id + "," + sellHeadCustomer.id + "," + currentReq.sign + "," + 
		    		   currentReq.type + "," + headOfSell.volume + "," + buyHeadCustomer.credit + "," +
		    		   sellHeadCustomer.credit );
		    }
		    
		    else {
			    
			    if ( headOfBuy.volume == headOfSell.volume ) {
				    
				    buyHeadCustomer.makeStockDetailChangeForBuy ( currentReq.sign, headOfSell.volume );
				    sellHeadCustomer.makeStockDetailChangeForSell( currentReq.sign, headOfSell.volume, headOfBuy.pricePerOne );
				    
				    headOfBuy.state = "DONE";
				    buyHeadCustomer.doneRequests.add(headOfBuy);
				    buyHeadCustomer.runningRequests.remove(headOfBuy);
				    
				    headOfSell.state = "DONE";
				    sellHeadCustomer.doneRequests.add(headOfSell);
				    sellHeadCustomer.runningRequests.remove(headOfSell);
				    
				    stock.remove("BUY", headOfBuy);
				    stock.remove("SELL", headOfSell);
			    }
			    
			    else {
				    
				    buyHeadCustomer.makeStockDetailChangeForBuy ( currentReq.sign, headOfBuy.volume );
				    sellHeadCustomer.makeStockDetailChangeForSell( currentReq.sign, headOfBuy.volume, headOfBuy.pricePerOne );
				    
				    headOfBuy.state = "DONE";
				    buyHeadCustomer.doneRequests.add(headOfBuy);
				    buyHeadCustomer.runningRequests.remove(headOfBuy);
				    
				    headOfSell.volume -= headOfBuy.volume;
				    
				    stock.remove("BUY", headOfBuy);
				    
			    }
			    
			    
			    sb.append("فروشنده به شناسه ");
			    sb.append(sellHeadCustomer.id);
			    sb.append(" میزان ");
			    sb.append(headOfBuy.volume);
			    sb.append(" سهم از ");
			    sb.append(currentReq.sign);
			    sb.append(" را به قیمت ");
			    sb.append(headOfBuy.pricePerOne);
			    sb.append(" به خریدار به شناسه "); 
			    sb.append(buyHeadCustomer.id);
			    sb.append(" فروخت.<br>");
			    deals.add( buyHeadCustomer.id + "," + sellHeadCustomer.id + "," + currentReq.sign + "," + 
		    		   currentReq.type + "," + headOfBuy.volume + "," + buyHeadCustomer.credit + "," +
		    		   sellHeadCustomer.credit );
		    }
		    
		    headOfBuy = stock.getHeadOfList("BUY");
		    headOfSell = stock.getHeadOfList("SELL");
		    
	    }
	    return sb.toString();
	    
    }
	
}
