import java.util.*;

public class Bank {
   // fields
   private String name;                   // the name of the Bank
   private ArrayList<User> users;         // List of all users associated with the bank
   private ArrayList<Account> accounts;   // all accounts that users have made (universal)
   
   // Constructor
   // Creates a new Bank object with empty lists of users and accounts
   // @param name    the name of the bank
   public Bank(String name) {
      this.name = name;
      this.users = new ArrayList<User>();
      this.accounts = new ArrayList<Account>();
   }
   
   // Generate a new universally unique ID for a user.
   // @return the uuid
   public String getNewUserUUID() {
      //initialize 
      String uuid;
      Random rng = new Random(); // random number generator
      int length = 6;
      boolean nonUnique;
      
      // while we don't have a unique UUID, keep generating a new one until we have a unique one
      do {
         // generate the number
         uuid = "";
         for (int c = 0; c < length; c++) {
            uuid += ((Integer)rng.nextInt(10)).toString(); // appending a random digit from 0-9
                                                           // in string form onto the current uuid
         }
         
         // check to make sure it's a unique UUID
         nonUnique = false;
         for (User u : this.users) { // for each user object in the the ArrayList users
            if (uuid.compareTo(u.getUUID()) == 0) { // compares the newly genearated UUID to each 
               nonUnique = true;                    // UUID currently in the system
               break; // breaks out of loop 
            }
         }
      } while (nonUnique);
      
      return uuid;
   }
   
   // Generates a universally unique ID for the account of the user.
   // @return the UUID
   public String getNewAccountUUID() {
      //initialize 
      String uuid;
      Random rng = new Random(); // random number generator
      int length = 10;
      boolean nonUnique;
      
      // while we don't have a unique UUID, keep generating a new one until we have a unique one
      do {
         // generate the number
         uuid = "";
         for (int c = 0; c < length; c++) {
            uuid += ((Integer)rng.nextInt(10)).toString(); // appending a random digit from 0-9
                                                           // in string form onto the current uuid
         }
         
         // check to make sure it's a unique UUID
         nonUnique = false;
         for (Account a : this.accounts) { // for each user object in the the ArrayList users
            if (uuid.compareTo(a.getUUID()) == 0) { // compares the newly genearated UUID to each 
               nonUnique = true;                    // UUID currently in the system
               break; // breaks out of loop 
            }
         }
      } while (nonUnique);
      
      return uuid;
   }
   
   // Adds an account
   // @param account    the account to add
   public void addAccount(Account account) {
      this.accounts.add(account);
   }
   
   // Creates a new user of the bank along with a Savings account for them
   // @param firstName  the user's first name
   // @param lastName   the user's last name
   // @param pin        the user's pin
   // @return           the new User object
   public User addUser(String firstName, String lastName, String pin) {
      // create a new User object and add it to our list
      User newUser = new User(firstName, lastName, pin, this); // this = current Bank object
      this.users.add(newUser);
      
      // create a savings account for the new user
      Account newAccount = new Account("Savings", newUser, this);
      // add Account to lists of accounts of holder and lists of accounts (universal) of bank
      newUser.addAccount(newAccount);
      this.accounts.add(newAccount);
      
      return newUser;
   }
   
   // Get the User object associated with a particular UserID and pin, if they are valid
   // otherwise returns null
   // @param userID  the UUID of the user to log in
   // @param pin     the pin of the user
   // @return        the User object if login is successful, or null if not
   public User userLogin(String userID, String pin) {
      // search through list of users
      for (User u : this.users) {
         // check if userID matches
         if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
            return u;
         }
      }
      // if we haven't found the user or have an incorrect pin
      return null;
   }
   
   // @returns the name of the bank 
   public String getName() {
      return this.name;
   }
}