import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
   // fields
   private String firstName;             // User's first name
   private String lastName;              // User's last name
   private String uuid;                  // unique universal identifier (id code for user)
   private byte pinHash[];               // the pin of the user to log in to atm 
                                         //(doesn't store actual pin, hash version of pin)
   private ArrayList<Account> accounts;  // all users accounts
   
   // Creates a new user
   // @param firstName  the user's first name
   // @param lastName   the user's last name
   // @param pin        the user's account pin number
   // @param theBank    the Bank object that the user is a customer of
   public User(String firstName, String lastName, String pin, Bank theBank) {
      // set user's name
      this.firstName = firstName;
      this.lastName = lastName;
      
      // store the pin's MD5 hash, rather than the original pin value for 
      // security purposes
      try {
         MessageDigest md = MessageDigest.getInstance("MD5"); // tries this
         this.pinHash = md.digest(pin.getBytes()); // gets the bytes of our pins, digesting them 
                                                   // in MD5 algorithm, returns array of bytes
                                                   
      } catch (NoSuchAlgorithmException e) { // if "catches"  error, then does catch block
         System.err.println("error, caught NoSuchAlgorithmException");
         e.printStackTrace();
         System.exit(1);
      }
      
      // get a new, unique universal ID for the user 
      this.uuid = theBank.getNewUserUUID();  
      
      // create empty list of accounts
      this.accounts = new ArrayList<Account>();
      
      // print log message 
      System.out.printf("New user %s, %s with ID %s created. \n", lastName, firstName, this.uuid);
   }
   
   // Adds an account for the user
   // @param account    the account that will be added
   public void addAccount(Account account) {
      // letting someone else add an account (since accounts is private)
      this.accounts.add(account);
   }
   
   // Returns the user's UUID
   // @return the uuid
   public String getUUID() { // getter method for UUID
      return this.uuid;
   }
   
   // Check whether given pin matches the User pin
   // @param pin  the pin to check
   // @return     whether the pin is valid or not 
   public boolean validatePin(String pin) {
      try {
         MessageDigest md = MessageDigest.getInstance("MD5");
         // get bytes of inputted pin, digest it, and then see if digest = pinHash that we stored
         return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinHash); 
      } catch (NoSuchAlgorithmException e) {
         // Auto-generated catch block
         System.err.println("error, caught NoSuchAlgorithmException");
         e.printStackTrace();
         System.exit(1);
      }
      return false;
   }
   
   // Retrieves user's first name
   // @returns the first name of the user
   public String getFirstName() {
      return this.firstName;
   }
   
   // Print summaries for the accounts of this user.
   public void printAccountsSummary() {
      System.out.printf("\n\n%s's accounts summary\n", this.firstName);
      for (int a = 0; a < this.accounts.size(); a++) {
         // %d gets a account number (a + 1) , %s gets a string
         // getSummaryLine will be from each account and will generate own summary line
         System.out.printf("  %d) %s\n", a + 1,
                this.accounts.get(a).getSummaryLine());
      }
      System.out.println();
   }
   
   // Get the number of accounts of the user
   // @return the number of accounts
   public int numAccounts() {
      return this.accounts.size();
   }
   
   // Print transaction history for a particular account
   // @param acctIndex  the index of the account to use
   public void printAcctTransHistory(int index) {
      this.accounts.get(index).printTransHistory();
   }
   
   // Get the balance of a particular account
   // @param acctIdx    the index of the account to use
   // @return           the balance of the account
   public double getAcctBalance(int acctIdx) {
      return this.accounts.get(acctIdx).getBalance();
   }
   
   // Get the UUID of a particular account
   // @param acctIdx    the index of the account to use
   // @return           the UUID of the account
   public String getAcctUUID(int acctIdx) {
      return this.accounts.get(acctIdx).getUUID();
   }
   
   // Add a transaction to a particular account
   // @param acctIdx     the index of the account
   // @param amount      the amount of the transaction
   // @param memo        the memo of the transaction
   public void addAcctTransaction(int acctIdx, double amount, String memo) {
      this.accounts.get(acctIdx).addTransaction(amount, memo);
   }
}