import java.util.Scanner;
class BankAccount{
    double balance;
    BankAccount(double initialBalance){
        balance = initialBalance;
    }

    void withdraw(double amount){
        if(amount>0 && amount<=balance){
            balance-=amount;
            System.out.println("Amount withdrawn successfully: Rs."+amount);
            System.out.println("Balance: Rs."+balance);
        }else{
            System.out.println("Insufficient balance to withdraw amount");
        }
    }
    void deposit(double amount){
        if(amount>0){
            balance+=amount;
            System.out.println("Amount deposited successfully: Rs. " +amount);
            System.out.println("Balance : Rs. "+balance);
        }else{
            System.out.println("Invalid input");
        }
    }
    double checkBalance(){
        return balance;
    }
}
class Atm{
    BankAccount account;
    Atm(BankAccount acc){
        account = acc;
    }
    void showmenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Displaying the options!");
        System.out.println("1.Withdraw\n" + "2.Deposit\n" + "3.Check Balance\n" + "4.Exit");
        System.out.println("Choose the options:");
        int choice = sc.nextInt();

        if(choice!=0 && choice>4){
            System.out.println("Please enter correct option");
        }
        else{
            switch(choice){
            case 1:
                System.out.println("Enter the amount to withdraw");
                double withdrawAmount = sc.nextDouble();
                account.withdraw(withdrawAmount);
                break;

            case 2:
                System.out.println("Enter the amount to deposit");
                double depositAmount = sc.nextDouble();
                account.deposit(depositAmount);
                break;

            case 3:
                System.out.println("Your balance is : "+account.checkBalance());
                break;

            case 4:
                System.out.println("Thankyou,Visit Again..");
                break;
            
            default:
                System.out.println("You have chosed a invalid option,Try again.");
                break;
        }
        }
        
    }
}
public class MainClass{
    public static void main(String[] args){
        BankAccount userAccount = new BankAccount(5000.0);
        Atm atm = new Atm(userAccount);
        atm.showmenu();
    }
}