package ir.ut.burs;
import java.util.List;


public class MPO extends ServerTypeRun {
	
	public MPO ( Request req, List<Customer> cstrs, List<Stock> stks ) {
		// TODO Auto-generated constructor stub
		currentReq = req;
		stocks = stks;
		customers = cstrs;
	}
	
	
	public boolean checkFullDealingMPO ( Stock stock ) {
		    
		    int i = 0, sumOfVolume = 0;
		    String mode = "";
		    if ( currentReq.reqMode.equals("BUY") ) mode = "SELL";
		    else mode = "BUY";
		    List<Request> temp = stock.getList(mode);
		    
		    
		    int sumOfPrice = 0;
		    
		    while ( i != temp.size() ) {
			    
			    int queuedReqVolume = temp.get(i).volume;
			    
			    while ( queuedReqVolume != 0 && sumOfVolume < currentReq.volume ) {
				    
				    sumOfVolume++; 
				    queuedReqVolume--;
			    }
			    
			    if ( sumOfVolume < currentReq.volume ) sumOfPrice += temp.get(i).volume*temp.get(i).pricePerOne;
			    else { sumOfPrice += (temp.get(i).volume - queuedReqVolume)*temp.get(i).pricePerOne; break;}
			    
			    i++;
		    }
		    
		    
		    if ( sumOfVolume < currentReq.volume ) return false;
		    else if ( searchCustomer(currentReq.customerID).credit < sumOfPrice ) return false;
		    else return true;
		    
	    }
	    
	public String serverRun () {
	    
		String logStr = "";
	    Stock stock = searchStock(currentReq.sign);
	    Customer reqCustomer = searchCustomer(currentReq.customerID);
	    
	    if ( !checkFullDealingMPO(stock) ) {
		    reqCustomer.deniadRequests.add(currentReq);
		    reqCustomer.runningRequests.remove(currentReq);
		    return "درخواست شما رد شد<br>به علت نبودن حجم کافی یا نبودن بوجه کافی شما قادر به انجام این درخواست نمی باشید";
	    }

	    if ( currentReq.reqMode.equals("BUY") ) {
		    
		    Request headOfSell = stock.getHeadOfList("SELL");
		    while ( currentReq.volume != 0 ){ 
			    currentReq.pricePerOne = headOfSell.pricePerOne;
			    logStr += buyCalculate(headOfSell, reqCustomer, stock);
			    headOfSell = stock.getHeadOfList("SELL");
		    }
	    }
	    
	    else {
		    
		    Request headOfBuy = stock.getHeadOfList("BUY");
		    while ( currentReq.volume != 0 ) {
			    currentReq.pricePerOne = headOfBuy.pricePerOne;
			    logStr += sellCalculate(headOfBuy, reqCustomer, stock);
			    headOfBuy = stock.getHeadOfList("BUY");
		    }
	    }
	    
	    return logStr;
	    
    }
	
}
