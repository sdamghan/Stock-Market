package ir.ut.burs;

public class DepositRequest {

	private int id;
	private double amount;

	DepositRequest ( int i, double a ) {
		id = i;
		amount = a;
	}

	public int getId () { return id; }
	public double getAmount () { return amount; }
	
}
