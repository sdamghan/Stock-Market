package ir.ut.burs;

import java.io.*;
import java.util.*;
import java.sql.*;

public class ServiceProvider {

	protected static String username = "admin";
	protected static String password = "admin";

    protected static Request currentReq;
    protected static List<Stock> stocks;
    protected static List<Customer> customers;
    protected static List<DepositRequest> dreqs;
    protected static StringBuilder sb; 


    protected static Connection con;
    protected static Statement st;


	
    
	private static ServiceProvider theServiceProvider = new ServiceProvider();
    public static ServiceProvider getServiceProvider() {
    	return theServiceProvider;
    }

    //sql
    ServiceProvider() {

		currentReq=null;
		stocks = new LinkedList<Stock>();
		customers = new LinkedList<Customer>();
		dreqs = new LinkedList<DepositRequest>();
		sb = new StringBuilder();
		Customer admin = new Customer(0, Double.POSITIVE_INFINITY, "admin", "-");
		customers.add( admin );

		try 
		{
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException ex) {
			System.err.println("Unable to load HSQLDB JDBC driver");
		}

		try {
			// connecting to the database
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
			st = con.createStatement();
		} catch( SQLException e ){}

		
    }
	
    
	//sql
    public Customer searchCustomer ( int id ) throws SQLException {
	    
    	ResultSet rs = st.executeQuery("select * from customer");
    	while ( rs.next() )
    	{
    		if ( id == Integer.parseInt(rs.getString("id")) ) {
    			String name = rs.getString("name");
    			String family = rs.getString("family");
    			double credit = Double.parseDouble(rs.getString("credit"));
    			Customer tempCustomer = new Customer( id, credit, name, family );

    			//myStocks
    			ResultSet cusStocks = st.executeQuery("select * from customer_stocks");
    			while( cusStocks.next() )
    			{
    				if ( id == Integer.parseInt(cusStocks.getString("customer_id")) )
    				{

    					tempCustomer.addStockToMyStocks(
    													cusStocks.getString("sign"), 
										                Integer.parseInt(cusStocks.getString("volume"))
    													);

    					break;
    				}
    			}

    			//denaidRequests
    			ResultSet denReqs = st.executeQuery("select * from deniad_requests");
    			while( denReqs.next() )
    			{
    				if ( id == Integer.parseInt(denReqs.getString("customer_id")) )
    				{
    					tempCustomer.deniadRequests.add( new Request(
    													 denReqs.getString("reqmode"),
    													 Integer.parseInt(denReqs.getString("pricePerOne")),
    													 Integer.parseInt(denReqs.getString("volume")),
    													 denReqs.getString("sign"),
    													 denReqs.getString("type"),
    													 denReqs.getString("state"),
    													 id
    													) );
    					break;
    				}
    			}

    			//doneRequests
    			ResultSet doneReqs = st.executeQuery("select * from done_requests");
    			while( doneReqs.next() )
    			{
    				if ( id == Integer.parseInt(doneReqs.getString("customer_id")) )
    				{
    					tempCustomer.doneRequests.add( new Request(
    													 doneReqs.getString("reqmode"),
    													 Integer.parseInt(doneReqs.getString("pricePerOne")),
    													 Integer.parseInt(doneReqs.getString("volume")),
    													 doneReqs.getString("sign"),
    													 doneReqs.getString("type"),
    													 doneReqs.getString("state"),
    													 id
    													) );
    					break;
    				}
    			}

    			//runningRequests
    			ResultSet runReqs = st.executeQuery("select * from running_requests");
    			while( runReqs.next() )
    			{
    				if ( id == Integer.parseInt(runReqs.getString("customer_id")) )
    				{
    					tempCustomer.runningRequests.add( new Request(
    													 runReqs.getString("reqmode"),
    													 Integer.parseInt(runReqs.getString("pricePerOne")),
    													 Integer.parseInt(runReqs.getString("volume")),
    													 runReqs.getString("stock_sign"),
    													 runReqs.getString("type"),
    													 runReqs.getString("state"),
    													 id
    													) );
    					break;
    				}
    			}
    			return tempCustomer;


    		}
    	}

	    return null;
    }
    public  Stock searchStock ( String stockSign ) throws SQLException {
	    
    	ResultSet rs = st.executeQuery("select * from stocks");
    	while ( rs.next() )
    	{

    		if ( stockSign.equalsTo( rs.getString("sign") ) )
    		{
    			Stock tempStock = new Stock( stockSign, Integer.parseInt(rs.getString("price")) );

    			//runningRequests
    			ResultSet runReqs = st.executeQuery("select * from running_requests");
    			while( runReqs.next() )
    			{
    				if ( stockSign.equalsTo( runReqs.getString("stock_sign")) ) )
    				{

    					if ( runReqs.getString("type").toUpperCase().equalsTo("BUY") )
    					{
    					tempStock.buyQueue.add( new Request(
    													 runReqs.getString("reqmode"),
    													 Integer.parseInt(runReqs.getString("pricePerOne")),
    													 Integer.parseInt(runReqs.getString("volume")),
    													 runReqs.getString("stock_sign"),
    													 runReqs.getString("type"),
    													 runReqs.getString("state"),
    													 id
    													) );
    					}

    					else
    					{
    						tempStock.sellQueue.add( new Request(
    													 runReqs.getString("reqmode"),
    													 Integer.parseInt(runReqs.getString("pricePerOne")),
    													 Integer.parseInt(runReqs.getString("volume")),
    													 runReqs.getString("stock_sign"),
    													 runReqs.getString("type"),
    													 runReqs.getString("state"),
    													 id
    													) );
    					}
    					break;
    				}
    			}

    		}

    		return tempStock;
    	}

	    // Stock stock;
	    // for (int i = 0; i < stocks.size(); i++) {
		   //  stock = stocks.get(i);
		   //  if ( stock.sign.equals(stockSign) ) return stock;
	    // }
	    return null;
    }
    public DepositRequest findDepositRequest( String id, String amount ) throws SQLException {
		for ( DepositRequest dreq : dreqs ) 
			if ( dreq.getId() == Integer.parseInt(id) && dreq.getAmount() == Double.parseDouble(amount) )
			return dreq;

		return null; 	
	}
    public String addToDepositList ( String id, String amount ) throws SQLException {

    	Customer customer;
	    try {
	    	customer = searchCustomer(Integer.parseInt(id));
	    }catch ( NumberFormatException e ) {
	    	return "شناسه باید به صورت عددی وارد شود.";
	    }
	    if ( customer == null )
		    return "شماره شناسه کاربری موجود نمی باشد.";
		else {
	    	try{
	    		dreqs.add( new DepositRequest( Integer.parseInt(id), Double.parseDouble(amount) ) );
	    	}catch ( NumberFormatException e ){
	    		return "مقدار اعتبار باید به صورت عددی وارد شود.";
	    	}
		}

	    return "درخواست شما ثبت و بررسی می شود";
    }
    public String acceptDepositRequest ( DepositRequest dreq ) throws SQLException {

		Customer customer = searchCustomer ( dreq.getId() );
	    
	    customer.creditManage("DEPOSIT", dreq.getAmount());
	    // sb.setLength(0);
	    // sb.append( "عملیات با موفقیت انجام شد.");
	    // sb.append("<b>مقدار ");
	    // sb.append(dreq.getAmount());
	    // sb.append("به حسات کاربری به شناسه ");
	    // sb.append(dreq.getId());
	    // sb.append(" افزوده گردید.<b>");
    	dreqs.remove( dreq );
    
	    return sb.toString();
    }
    public String denayDepositRequest ( DepositRequest dreq ) throws SQLException {

    	// sb.setLength(0);
	    // sb.append("درخواست افزایش اعتبار کاربر ");
	    // sb.append(dreq.getId());
	    // sb.append("به میزان ");
	    // sb.append(dreq.getAmount());
	    // sb.append(" رد شد.");
    	dreqs.remove( dreq );
    
	    return sb.toString();
    }
    //sql
    public String createNewUser ( String id, String name, String family ) throws SQLException {
	    Customer customer;
	    try {
	    	customer = searchCustomer(Integer.parseInt(id));
	    	if ( name.equals("") )
	    		return "نام باید وارد شود.";

	    	if ( family.equals("") )
	    		return "نام خانوادگی باید وارد شود.";

	    }catch ( NumberFormatException e ) {
	    	return "شناسه باید به صورت عددی وارد شود.";
	    }
	    if ( customer == null ) {
		    //customers.add(new Customer(Integer.parseInt(id), name, family));
		    st.executeUpdate("insert into customer values('" +
		    											   id+
		    											   "', '"+
		    											   		name+
		    											   			"','"+
		    											   				family+
		    											   					"','"+
		    											   						0+
		    											   						"')");
		    sb.setLength(0);
		    sb.append("ثبت نام با موفقیت انجام شد.");
		    sb.append("<br>");
		    sb.append("- شناسه :");
		    sb.append(id);
		    sb.append("<br>");
		    sb.append(" - نام :");
		    sb.append(name);
		    sb.append("<br>");
		    sb.append(" - نام خانوادگی : ");
		    sb.append(family);

		    return sb.toString();
	    }
	    else 
		    return "خطا, شماره شناسه تکراری می باشد.";
	}
    public String withDrawUser ( String id, String amount ) throws SQLException {
	    
	    Customer customer;
	    try {
	    	customer = searchCustomer(Integer.parseInt(id));
	    }catch ( NumberFormatException e ) {
	    	return "شناسه باید به صورت عددی وارد شود.";
	    }
	    if ( customer == null ) 
	    	return "شماره شناسه کاربری موجود نمی باشد." ;
	    
	    else if ( customer.credit < Double.parseDouble(amount) )
	    	return "موجودی حساب شما کافی نمی باشد.";
	    
	    else {
		    customer.creditManage("WITHDRAW", Double.parseDouble(amount), st);
		    sb.setLength(0);
		    sb.append("عملیات با موفقیت انجام شد.");
			sb.append("<pre><b>");
			sb.append(Double.parseDouble(amount));
			sb.append("از حسات کاربری به شناسه ");
			sb.append(Integer.parseInt(id));
			sb.append(" کسر گردید.<b></pre>");
		    return sb.toString();
	    }
    }
    public String makeRequest ( String reqMode, int reqUserId, String reqStockSign, int reqStockPrice, int reqStockVolume, String reqType ) throws SQLException {
	    
    	Customer customer = searchCustomer(reqUserId);
		if ( customer == null ) 
		    return "شماره شناسه کاربری موجود نمی باشد.";

	    Request tempReq = null;
	    Stock stock = searchStock(reqStockSign);
	    if ( stock == null && reqUserId == 0 && reqMode.equals("SELL") ) {
	      stock = new Stock ( reqStockSign, reqStockPrice );
	      stocks.add ( stock );
	    }
	    
	    if ( stock == null && reqUserId != 0 ) 
		    return "سهام مورد نظر وجود ندارد.";

	    
	    if ( reqType.equals("MPO") && reqStockPrice != 0 )
		    return "درخواست شما قابل اجرا نمی باشد<br>در درخواست نوع MPO می بایست قیمت صفر وارد شود.";

		    
	    if ( reqMode.equals("BUY") ) {
		    
		    if ( customer.id != 0 ) {
		    
			    if ( customer.credit < (reqStockPrice*reqStockVolume) /*|| ( reqType.equals("MPO") && customer.credit == 0 )*/ )
				    return "موجودی حساب شما کافی نمی باشد.";

				    
			    else customer.credit -= (reqStockPrice*reqStockVolume);
			    
			    tempReq = new Request("BUY", reqStockPrice, reqStockVolume, reqStockSign, reqType, "RUNNING", reqUserId);
			    customer.runningRequests.add( tempReq );
			    if ( reqType.toUpperCase().equals("GTC") ) 
				    stock.addToQueue( tempReq );
			    currentReq = tempReq;
		    }
		    
		    else 
			    return "ادمین قادر به خریداری سهامی نمی باشید.";
	    }
	    
	    else {
	    
		    if ( customer.id != 0 ) {
			    if ( !customer.haveStock(reqStockSign) )
				    return "سهام مورد نظر برای شما موجود نمی باشد.";
			    
			    if ( !customer.enoughVolume(reqStockSign, reqStockVolume) )
				    return "شما حجم کافی از سهام " + reqStockSign + "برای فروش ندارید" ;
			    
			    tempReq = new Request("SELL", reqStockPrice, reqStockVolume, reqStockSign, reqType, "RUNNING", reqUserId);
			    customer.runningRequests.add( tempReq );
			    if ( reqType.toUpperCase().equals("GTC") )
				    stock.addToQueue( tempReq );
			    currentReq = tempReq;
		    }
		    
		    else{
			    tempReq = new Request("SELL", reqStockPrice, reqStockVolume, reqStockSign, reqType, "RUNNING", reqUserId);
			    customer.runningRequests.add( tempReq );
			    if ( reqType.toUpperCase().equals("GTC") )
				     stock.addToQueue( tempReq ); 
			    currentReq = tempReq;
		    }
		    
	    }
	    
	    return "";
    }
    public String ordering ( String reqMode, String reqUserId, String reqStockSign, String reqStockPrice, String reqStockVolume, String reqType ) throws SQLException {

    	try{Integer.parseInt(reqUserId);}catch(NumberFormatException e) { return "شناسه باید به صورت عددی وارد شود."; }
    	try{Integer.parseInt(reqStockPrice);}catch(NumberFormatException e) { return "قیمت باید به صورت عددی وارد شود."; }
    	try{Integer.parseInt(reqStockVolume);}catch(NumberFormatException e) { return "حجم باید به صورت عددی وارد شود."; }
    	//MAKE REQUEST
		String requestErr = makeRequest(reqMode.toUpperCase(), 
										Integer.parseInt(reqUserId),
										reqStockSign, 
										Integer.parseInt(reqStockPrice), 
										Integer.parseInt(reqStockVolume), 
										reqType);
		if ( !requestErr.equals("") ) 
			return requestErr;
		
		//SERVER RUN
		if 		( reqType.toUpperCase().equals("GTC") )	{ 
			
			if ( customers.size() > 1 || Integer.parseInt(reqUserId) == 0 ){
				return new GTC( currentReq, customers, stocks ).serverRun();
			}
			else
				return "شماره شناسه کاربری موجود نمی باشد." ;
		}
		
		else if ( reqType.toUpperCase().equals("IOC") )	{ 
						
			if ( customers.size() > 1 || Integer.parseInt(reqUserId) == 0 ){
				return new IOC( currentReq, customers, stocks ).serverRun();
			}
			else
				return "شماره شناسه کاربری موجود نمی باشد." ;
		}
		
		else if ( reqType.toUpperCase().equals("MPO") )	{ 
			
			if ( customers.size() > 1 || Integer.parseInt(reqUserId) == 0 )
				return new MPO( currentReq, customers, stocks ).serverRun() ;
			else
				return "شماره شناسه کاربری موجود نمی باشد." ;
		}

		return "خطا در بارگذاری کلاس.";    	
    }
    public void backup() throws SQLException {
    	try{
    		PrintWriter writer = new PrintWriter( new File("../webapps/CA5/backup.csv") );
    		writer.println("Buyer,Seller,instrument,type of trade,quantity,Buyer Remained Money,Seller Current Money");
    		List<String> tempList = ServerTypeRun.getDeals();
    		for ( String tempStr : tempList )
    			writer.println( tempStr );
    		writer.close();
    	} catch(  FileNotFoundException e ) {
    		return;
    	}
    }



    public List<Stock> getStocks()  {
		
    	ResultSet rs = st.executeQuery("select * from stocks");
		List<Stock> list = new ArrayList<Stock>();
		while (rs.next()) 
			list.add( searchStock(rs.getString("sign")) );

		return list;
	}
	public List<Customer> getCustomers() throws SQLException {

		ResultSet rs = st.executeQuery("select * from customer");
		List<Customer> list = new ArrayList<Customer>();
		while (rs.next()) 
			list.add( searchCustomer(rs.getString("id")) );

		return list;

	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}

	public void setUsername( String u ) {
		username = u;
	}
	public void setPassword( String p ) {
		password = p;
	}
	public List<DepositRequest> getDepositRequests() {
		return dreqs;
	}
    
}
