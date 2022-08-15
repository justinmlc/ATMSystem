import java.util.*;

public class Account {
   // fields
   private String name;                         // Name of account
   private String uuid;                         // Account's UUID (separate from User's UUID)
   private User holder;                         // the User that owns this account
   private ArrayList<Transaction> transactions; // All account's transactions
   
   // Creates a new Account
   // @param name    the name of the account
   // @param holder  the User object that holds this account
   // @param theBank the bank that issues the account
   public Account(String name, User holder, Bank theBank) {
      // set the account name and holder
      this.name = name;
      this.holder = holder;
      
      // get new account UUID
      this.uuid = theBank.getNewAccountUUID();
      
      // initialize transaction 
      this.transactions = new ArrayList<Transaction>();
      
   }
   
   // Returns the user's UUID
   // @return the uuid
   public String getUUID() {
      return this.uuid;
   }
   
   // Get summary line for the account
   // @return the string summary
   public String getSummaryLine() {
      // get the account's balance
      double balance = this.getBalance();
      
      // format the summary line, depending on whether the balance is negative
      if (balance >= 0) {
         return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
      } else {
         return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
      }
   }
   
   // Gets the balance of this account by adding the amounts of the transactions
   // @return the balance value
   public double getBalance() {
      double balance = 0;
      // add up each transaction's amount that is logged in this account
      for (Transaction t : this.transactions) {
         balance += t.getAmount();
      }
      return balance;
   }
   
   // Print the transaction history of the account 
   public void printTransHistory() {
      System.out.printf("\nTransaction history for account %s\n", this.uuid);
      // latest transaction to first 
      for (int t = this.transactions.size() - 1; t >= 0; t--) {
         System.out.println(this.transactions.get(t).getSummaryLine());
      }
      System.out.println();
   }
   
   // Add a new transaction in this account
   // @param amount     the amount transacted
   // @param memo       the transaction memo
   public void addTransaction(double amount, String memo) {
      // create new transaction object and add to our list
      // "this" parameter is the account object associated with the transaction
      Transaction newTrans = new Transaction(amount, memo, this);
      this.transactions.add(newTrans);
   }
}