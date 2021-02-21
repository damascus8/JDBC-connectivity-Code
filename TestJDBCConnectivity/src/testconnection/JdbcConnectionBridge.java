package testconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcConnectionBridge implements Info {
	static int id;
	static String sql;
	static Scanner sc = new Scanner(System.in);
	static String fName;
	static String lName;
	static int rollNo;
	static String text;
	static Statement st=null;
	static int x;
	public static void main(String args[]) {
		Connection con = null;
		
		try {
			Class.forName(mysql_pkg);

			con = DriverManager.getConnection(url, user, password);

			System.out.println("Connected");

			int repeat = 1;
			do {
				System.out.println("choose the operation you want to perform");

				System.out.println("Press 1 to insert records in table\n" + "Press 2 to update records in table\n"
						+ "Press 3 to delete records in table\n"
						+"Press 4 to get table data\n");
				int sw = sc.nextInt();
				switch (sw) {
				case 1: {
					System.out.println("how many records you want to enter");
					int count=sc.nextInt();
					 for(int i=0;i<count;i++)
					 {
						 sc.nextLine();
						System.out.println("Enter first name");
						 fName = sc.nextLine();
						System.out.println("Enter last name");
						 lName = sc.nextLine();
						System.out.println("Enter rollno");
						 rollNo = sc.nextInt();
						 sql = "insert into Students(FirstName,LastName,RollNo) values('" + fName + "','" + lName
									+ "','" + rollNo + "')";
						 st=con.createStatement();
							x=st.executeUpdate(sql);
							if(x!=0)
							{
								text="data inserted successfully !";
								System.out.println(text);
							}
					 } 					
				}
					break;
				case 2: {

					System.out.println("Update Table rows...");
					 sc.nextLine();
					 System.out.println("Enter id to update");
					 	id = sc.nextInt();
						 sc.nextLine();

						System.out.println("Enter first name");
						 fName = sc.nextLine();
						System.out.println("Enter last name");
						 lName = sc.nextLine();
						System.out.println("Enter rollno");
						 rollNo = sc.nextInt();
					
					sql="update students set FirstName='"+fName+"' , LastName = '"+lName+"' , rollno ='"+rollNo+"' where id='"+id+"' ";
					
					st=con.createStatement();
					x=st.executeUpdate(sql);
					
					if(x!=0)
					{
						System.out.println("Updated Successfully!!");

					}
					else
					{
						System.out.println("Somethings went wrong..");
					}
				}
					break;
				case 3: {
					sc.nextLine();
					System.out.println("Enter id you want to delete !");
					String del=sc.nextLine();
					sql="delete from students where id ='"+del+"'";
					
					st=con.createStatement();
					System.out.println("s"+del);
					x=st.executeUpdate(sql);
					if(x!=0)
					{
						System.out.println("Deleted Successfully!!");
					}
				}
					break;
				case 4: {
					 sql = "select * from students";
					 st=con.createStatement();
						ResultSet rs=st.executeQuery(sql);
						while(rs.next())
						{
							System.out.println("\n"+"Id: "+rs.getInt("id")+"\tFirstName: "+rs.getString("FirstName")+"\tLastName: "+rs.getString("LastName")+"\t Rollno: "+rs.getString("rollno"));
						}
				}
					break;
										
				default:
					System.out.println("Wrong option");
				}
				
				
				System.out.println("TO continue press 1 OR 0 to Exit.");
				repeat = sc.nextInt();
			} while (repeat==1);

			// insert
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			st.close();
			con.close();
			System.out.println("Connection closed !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
