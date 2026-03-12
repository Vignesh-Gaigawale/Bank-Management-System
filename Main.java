import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println("\n--- BANK MANAGEMENT SYSTEM ---");

            System.out.println("1 Create Account");
            System.out.println("2 Deposit");
            System.out.println("3 Withdraw");
            System.out.println("4 Transfer");
            System.out.println("5 Transaction History");
            System.out.println("6 Exit");

            int choice = sc.nextInt();

            if(choice==1){

                sc.nextLine();

                System.out.println("Enter Name:");
                String name = sc.nextLine();

                System.out.println("Enter Phone:");
                String phone = sc.nextLine();

                System.out.println("Enter Email:");
                String email = sc.nextLine();

                BankService.createAccount(name,phone,email);

            }

            else if(choice==2){

                System.out.println("Account Number:");
                int acc = sc.nextInt();

                System.out.println("Amount:");
                double amt = sc.nextDouble();

                BankService.deposit(acc,amt);

            }

            else if(choice==3){

                System.out.println("Account Number:");
                int acc = sc.nextInt();

                System.out.println("Amount:");
                double amt = sc.nextDouble();

                BankService.withdraw(acc,amt);

            }

            else if(choice==4){

                System.out.println("From Account:");
                int from = sc.nextInt();

                System.out.println("To Account:");
                int to = sc.nextInt();

                System.out.println("Amount:");
                double amt = sc.nextDouble();

                BankService.transfer(from,to,amt);

            }

            else if(choice==5){

                System.out.println("Account Number:");
                int acc = sc.nextInt();

                BankService.showTransactions(acc);

            }

            else{
                System.out.println("Thank you!");
                break;
            }

        }

    }

}