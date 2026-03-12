import java.sql.*;

public class BankService {

    public static void createAccount(String name,String phone,String email){

        try{

            Connection conn = DBConnection.getConnection();

            String q1 = "INSERT INTO customers(name,phone,email) VALUES(?,?,?)";

            PreparedStatement ps = conn.prepareStatement(q1,Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,name);
            ps.setString(2,phone);
            ps.setString(3,email);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            int customerId = 0;

            if(rs.next()){
                customerId = rs.getInt(1);
            }

            int accNo = AccountGenerator.generateAccountNumber();

            String q2 = "INSERT INTO accounts(account_no,customer_id,balance) VALUES(?,?,0)";

            PreparedStatement ps2 = conn.prepareStatement(q2);

            ps2.setInt(1,accNo);
            ps2.setInt(2,customerId);

            ps2.executeUpdate();

            System.out.println("Account Created Successfully");
            System.out.println("Account Number: " + accNo);

        }catch(Exception e){
            e.printStackTrace();
        }

    }



    public static void deposit(int accountNo,double amount){

        try{

            Connection conn = DBConnection.getConnection();

            String q = "UPDATE accounts SET balance=balance+? WHERE account_no=?";

            PreparedStatement ps = conn.prepareStatement(q);

            ps.setDouble(1,amount);
            ps.setInt(2,accountNo);

            ps.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement(
                    "INSERT INTO transactions(account_no,type,amount) VALUES(?,?,?)");

            ps2.setInt(1,accountNo);
            ps2.setString(2,"Deposit");
            ps2.setDouble(3,amount);

            ps2.executeUpdate();

            System.out.println("Deposit Successful");

        }catch(Exception e){
            e.printStackTrace();
        }

    }



    public static void withdraw(int accountNo,double amount){

        try{

            Connection conn = DBConnection.getConnection();

            String q = "UPDATE accounts SET balance=balance-? WHERE account_no=?";

            PreparedStatement ps = conn.prepareStatement(q);

            ps.setDouble(1,amount);
            ps.setInt(2,accountNo);

            ps.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement(
                    "INSERT INTO transactions(account_no,type,amount) VALUES(?,?,?)");

            ps2.setInt(1,accountNo);
            ps2.setString(2,"Withdraw");
            ps2.setDouble(3,amount);

            ps2.executeUpdate();

            System.out.println("Withdraw Successful");

        }catch(Exception e){
            e.printStackTrace();
        }

    }



    public static void transfer(int from,int to,double amount){

        try{

            Connection conn = DBConnection.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement ps1 = conn.prepareStatement(
                    "UPDATE accounts SET balance=balance-? WHERE account_no=?");

            ps1.setDouble(1,amount);
            ps1.setInt(2,from);

            ps1.executeUpdate();


            PreparedStatement ps2 = conn.prepareStatement(
                    "UPDATE accounts SET balance=balance+? WHERE account_no=?");

            ps2.setDouble(1,amount);
            ps2.setInt(2,to);

            ps2.executeUpdate();

            conn.commit();

            System.out.println("Transfer Successful");

        }catch(Exception e){
            e.printStackTrace();
        }

    }



    public static void showTransactions(int accountNo){

        try{

            Connection conn = DBConnection.getConnection();

            String q = "SELECT * FROM transactions WHERE account_no=?";

            PreparedStatement ps = conn.prepareStatement(q);

            ps.setInt(1,accountNo);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                System.out.println(
                        rs.getString("type") + " | " +
                        rs.getDouble("amount") + " | " +
                        rs.getTimestamp("date")
                );

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}