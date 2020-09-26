package ir.ut.burs;
import java.util.List;


public class IOC extends ServerTypeRun {
	
	public IOC ( Request req, List<Customer> cstrs, List<Stock> stks ) {
		// TODO Auto-generated constructor stub
		currentReq = req;
		stocks = stks;
		customers = cstrs;
	}
	
	
	public boolean checkFullDealingIOC ( Stock stock ) {
	    
	    int sumOfVolume = 0;
	    List<Request> tempList;
	    
	    if ( currentReq.reqMode.equals("BUY") ) {
		    int i = 0;
		    tempList = stock.getList("SELL");
		    while ( i != tempList.size() && tempList.get(i).pricePerOne <= currentReq.pricePerOne ) {
			    sumOfVolume += tempList.get(i).volume;
			    i++;
		    }
		    
		    if ( sumOfVolume < currentReq.volume ) return false;
	    }
	    
	    else {
		    int i = 0;
		    tempList = stock.getList("BUY");
		    while ( i != tempList.size() && tempList.get(i).pricePerOne >= currentReq.pricePerOne ) {
			    sumOfVolume += tempList.get(i).volume;
			    i++;
		    }
		    if ( sumOfVolume < currentReq.volume ) return false;
	    }
	    
	    return true;
	    
    }
	
	public String serverRun () {
	    
		String logStr = "";
	    Stock stock = searchStock(currentReq.sign);
	    Customer reqCustomer = searchCustomer(currentReq.customerID);
	    
	    if ( currentReq.reqMode.equals("BUY") ) {

		    Request headOfSell = stock.getHeadOfList("SELL");
		    
		    if ( headOfSell == null || currentReq.pricePerOne < headOfSell.pricePerOne || !checkFullDealingIOC(stock) ) {
			    reqCustomer.deniadRequests.add(currentReq);
			    reqCustomer.runningRequests.remove(currentReq);
			    if ( headOfSell == null )
			    	return "درخواست شما رد شد<br>فروشنده ای در صف فروش وجود ندارد.";
			    else if ( currentReq.pricePerOne < headOfSell.pricePerOne )
			    	return "درخواست شما رد شد<br>قیمت پیشنهادی کمتر از فروش می باشد.";
			    else 
			    	return "درخواست شما رد شد<br>با توجه به صف فروش فعلی قادر به انجم این درخواست نمی باشید.";
		    }
		    
		    while ( currentReq.volume != 0 ){
			    logStr += buyCalculate(headOfSell, reqCustomer, stock);
			    headOfSell = stock.getHeadOfList("SELL");
		    }
	    }
	    
	    else {
		    
		    Request headOfBuy = stock.getHeadOfList("BUY");
		    if ( headOfBuy == null || currentReq.pricePerOne > headOfBuy.pricePerOne || !checkFullDealingIOC(stock) ) {
			    reqCustomer.deniadRequests.add(currentReq);
			    reqCustomer.runningRequests.remove(currentReq);
			    if ( headOfBuy == null )
			    	return "درخواست شما رد شد<br>فروشنده ای در صف فروش وجود ندارد.";
			    else if ( currentReq.pricePerOne > headOfBuy.pricePerOne )
			    	return "درخواست شما رد شد<br>قیمت پیشنهادی کمتر از فروش می باشد.";
			    else 
			    	return "درخواست شما رد شد<br>با توجه به صف خرید فعلی قادر به انجم این درخواست نمی باشید.";
		    }
		    
		    while ( currentReq.volume != 0 ) {
			    logStr += sellCalculate(headOfBuy, reqCustomer, stock);
			    headOfBuy = stock.getHeadOfList("BUY");
		    }
	    }
	    return logStr;
	    
    }
	
}
