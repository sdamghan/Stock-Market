package ir.ut.burs;

public class Request {

	public int customerID;
	public int pricePerOne;
	public int volume;
	public int staticVolume;
	public String sign;
	public String type;
	public String state;
	public String reqMode;
	
	Request( String sReqMode, int sPrice, int sVolume, String sSign, String sType, String sState, int sCustomerID ) {
	
	 reqMode = sReqMode; // buy/sell
	 pricePerOne = sPrice;
	 volume = sVolume;
	 sign = sSign;
	 type = sType;
	 state = sState;
	 customerID = sCustomerID;
	 staticVolume = sVolume;
	}
	
}
