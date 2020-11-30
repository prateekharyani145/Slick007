package dao;
import dto.Customer;

public class CustomerDAO {
	
	
	// REGISTRATION -------------------------------------------------------------
	public boolean registerCustomer(Customer obj)
	{
		boolean b= false;
		try {
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("\n Problem in Registration Check in dao.registerCustomer \n");
		}
		return b;

	}
	// LOGIN ------------------------------------------------------------------------------
	public boolean checkCustomerCredentials(Customer obj) {
		boolean b = false;
		try {
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("\n Problem in Login Check in doa.checkCustomerCredentials \n");
		}
		return b;
	}

	

}
