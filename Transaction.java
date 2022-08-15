import java.util.Date;

public class Transaction {
   // fields
   private double amount;     // the transaction amount
   private Date timestamp;    // the date of the transaction
   private String memo;       // a description of the transaction
   private Account inAccount; // the account this transaction occurred.
   
   // Create a new Transaction
   // @param amount  the amount transferred
   // @ inAccount    the account the transaction belongs to
   public Transaction(double amount, Account inAccount) {   // transaction with no memo
      this.amount = amount;
      this.inAccount = inAccount;
      this.timestamp = new Date();
      this.memo = "";
   }
   
   // Create a new Transaction
   // @param amount        the amount transferred
   // @param memo          the memo for the transaction
   // @param inAccount     the account the transaction belongs to
   public Transaction(double amount, String memo, Account inAccount) {  // transaction with a memo
      // call the two-arg constructor first
      this(amount, inAccount);
      // then set the memo
      this.memo = memo;
   }
   
   // gets the amount of the transaction
   // @return this.amount  the amount associated with the transaction
   public double getAmount() {
      return this.amount;
   }
   
   // Get a string summarizing the transaction.
   // @return the summary string
   public String getSummaryLine() {
      if (this.amount >= 0) {
         return String.format("%s : $%.02f : %s", this.timestamp.toString(), 
                  this.amount, this.memo);
      } else {
         return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), 
                  -this.amount, this.memo);
      }
   }
}