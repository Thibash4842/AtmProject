package AtmProject1;

import java.sql.*;
import java.util.Scanner;

public class Atm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/atm","root","");
			Statement stmt=con.createStatement();
			try (Scanner scan = new Scanner(System.in)) {
				System.out.println("Hey Welcome To All in One ATM");
				System.out.println("-------------------------------");
				System.out.println("Enter Your Pin Number :");
				int pin =scan.nextInt();
				ResultSet rs=stmt.executeQuery("SELECT * FROM atm WHERE Account= "+pin);
				String name=null;
				int balance = 0;
				int count=0;
				while(rs.next()) {
					name=rs.getString(3);
					balance=rs.getInt(4);
					count++;
				}

				int choice,amount=0,take_amount=0;
				
				if(count>0) {
					System.out.println("Hello "+name+"\n");
					while(true) {
						System.out.println("Press 1 to 'Check Balance'");
						System.out.println("Press 2 to 'Deposit'");
						System.out.println("Press 3 to 'Withdraw'");
						System.out.println("Press 4 to 'Print the Recipt'");
						System.out.println("Press 5 to 'Exit / Quit'");
						System.out.println("\nEnter Your Choice :");
						choice=scan.nextInt();
						
						switch(choice) {
						case 1:
							System.out.println("Your choosing option 'Balance Enquery'");
							System.out.println("Your Current Balance is : "+balance);
							break;
						case 2:
							System.out.println("Your choosing option 'Deposit'");
							System.out.println("How much amount did you want to Deposit ?");
							amount=scan.nextInt();
							balance=balance+amount;
							stmt.executeUpdate("UPDATE atm SET Balance = "+balance+" WHERE Account= "+pin);
							System.out.println("Your Successfully Deposit..! \nAdded now your current balance is "+balance);
							break;
						case 3:
							System.out.println("Your choosing option 'Withdraw'");
							System.out.println("How much amount did you want to take ?");
							take_amount=scan.nextInt();
							if(take_amount>balance) {
								System.out.println("Your balance is 'Insufficient'");
							}else {
								balance=balance-take_amount;
								stmt.executeUpdate("UPDATE atm SET Balance ="+balance+" WHERE Account= "+pin);
								System.out.println(" Your Money Successfully Withdraw...! \n Now your current balance is "+balance);
							}
							break;
						case 4:
							System.out.println("***Thanks for Coming***");
							System.out.println("Your current balance is " +balance);
							System.out.println("Amount added : " +amount);
							System.out.println("Amount taken : " +take_amount);
							break;
						}
						if(choice==5) {
							break;
						}
					}
				}else {
					System.out.println("Wrong Pin Number");
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}

}
