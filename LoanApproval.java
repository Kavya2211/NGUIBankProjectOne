import java.util.*;

class User_Details{
	String fname,lname;
	long phone;
	int cust_id;
	double loan_amt,balance,emi=0;
	String approval="NO",type="NIL";
	ArrayList<String> address = new ArrayList<String>();
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public int getEmpid() {
		return cust_id;
	}
	public void setEmpid(int empid) {
		this.cust_id = empid;
	}
}
interface loan{
	static int checkLoanAmt(long amt) {
		return 0;
	}
	
	static int checkBalance(double amt,int choice){
		return 0;
	}
	static double calculateEmi(double p, double r, int t) {
		return 0;
	}
}
public class LoanApproval extends User_Details implements loan{
	static int checkLoanAmt(double loan_amt){
		if(loan_amt>10000000)
		  return 0;
		else return 1;
		
	}
	
	static int checkBalance(double amt,int choice){
		if(choice==1 && amt<1000000) return 1; 
		else if(choice==2 && amt<500000) return 1;
		else if(choice==4 && amt<5000000) return 1;
		else return 0;
	}
	
	static double calculateEmi(double p, double r, int t) {
		double emi;   
        r = r / (12 * 100); // one month interest 
        t = t * 12; // one month period 
        emi = (p * r * (float)Math.pow(1 + r, t))/ (float)(Math.pow(1 + r, t) - 1); 
        return (emi); 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ArrayList<User_Details> report = new ArrayList<User_Details>();
		int cont=1;
		while(cont==1){
		User_Details udetails = new User_Details();
		System.out.println("Enter User Details:");
		System.out.println("Enter the first Name:");
		udetails.fname = sc.next();
		System.out.println("Enter the last Name:");
		udetails.lname = sc.next();
		System.out.println("Enter the Phone Number:");
		udetails.phone = sc.nextLong();
		//ArrayList<String> addr = new ArrayList<String>(4);
		System.out.println("Enter the Address(House No.,Street,City,State):");
		for(int i=0;i<4;i++){
			udetails.address.add(sc.next());
		}
		System.out.println("Enter the Loan Amount:");
		udetails.loan_amt = sc.nextInt();
		if(checkLoanAmt(udetails.loan_amt)==0){
			System.out.println("Exceeds the loan limit");
		}
		else{
			System.out.println("Purpose of Loan: \n 1.Housing \n 2.Education \n 3.Personal Loan \n 4.Travel");
			int choice = sc.nextInt();
			double emi,minbalance;
			switch(choice){
			case 1:{
				System.out.println("Enter your account balance:");
				udetails.balance = sc.nextDouble();
				if(checkBalance(udetails.balance,choice)==1){
					System.out.println("Loan NOT Approved since Balance less than 10 lakhs !!!");
				}
				else{
					udetails.emi = calculateEmi(udetails.loan_amt,6,5);
					System.out.println("Housing Loan Approved !! \nEMI to be paid:"+udetails.emi+" \nTotal Amount to be Repaid:"+udetails.emi*5*12);
					udetails.approval="YES";
					udetails.type="Housing";
				}
				break;
			}
			case 2:{
				System.out.println("Enter your parents account balance:");
				udetails.balance = sc.nextDouble();
				if(checkBalance(udetails.balance,choice)==1){
					System.out.println("Balance less than 5 lakhs !!!");
				}
				else{
					udetails.emi = calculateEmi(udetails.loan_amt,8,6);
					System.out.println("Education Loan Approved !! \nEMI to be paid:"+udetails.emi+" \nTotal Amount to be Repaid:"+udetails.emi*6*12);
					udetails.approval="YES";
					udetails.type="Education";
				}
				break;
			}
			case 3:{
				udetails.emi = calculateEmi(udetails.loan_amt,5,1);
				System.out.println("Personal Loan Approved !! \nEMI to be paid:"+udetails.emi+" \nTotal Amount to be Repaid:"+udetails.emi*12);
				udetails.approval="YES";
				udetails.type="Personal";
				break;
			}
			case 4:{
				System.out.println("Enter your account balance:");
				udetails.balance = sc.nextDouble();
				if(checkBalance(udetails.balance,choice)==1){
					System.out.println("Balance less than 50 lakhs !!!");
				}
				else{ 
					udetails.emi = udetails.loan_amt/12+ udetails.loan_amt/100;
					System.out.println("Travel Loan Approved !! \nEMI to be paid:"+udetails.emi+" \nTotal Amount to be Repaid:"+udetails.emi*12);
					udetails.approval="YES";
					udetails.type="Travel";
				}
				break;
			}
			}
		}
		report.add(udetails);
		System.out.println("Do you want to continue(1/0):");
		cont = sc.nextInt();
		}
		System.out.println("REPORT_GENERATED:");
			for (User_Details user : report) {
			
			System.out.println(user.fname+" "+user.lname+" "+user.phone+" "+user.address+" "+user.balance+" "+user.loan_amt+" "+user.approval+" "+user.type+" "+user.emi);
		}
	}
}
