public class CreditCard extends BankCard{
    
    private int cvcNumber; 
    private double creditLimit;
    private double interestRate;
    private String expirationDate;
    private int gracePeriod;
    private boolean isGranted;

    /*
     * In the constructor below, some of the parameters passed to it will again be passed as a parameter to the constructor 
     * of the super class and to the setter method of the super class. The values of the instance variables cvc number, interest rate and expiration
     * date is set to the value passed as a parameter in the constructor of this class through 'this' keyword. 
     */
    public CreditCard(int cardId, String clientName, String issuerBank, String bankAccount, int balanceAmount, int cvcNumber, double interestRate, String expirationDate){
        super(balanceAmount, cardId, bankAccount, issuerBank);
        super.setClientName(clientName);
        this.cvcNumber = cvcNumber;
        this.interestRate = interestRate;
        this.expirationDate = expirationDate;
        isGranted = false; 
    }

    //Start of the section of getter methods for getting the values of each instance variables.
    public int getCvcNumber(){         
        return this.cvcNumber;
    }

    public double getCreditLimit(){
        return this.creditLimit; 
    }

    public double getInterestRate(){ 
        return this.interestRate;
    }

    public String getExpirationDate(){
        return this.expirationDate;
    }

    public int getGracePeriod(){
        return this.gracePeriod; 
    }

    public boolean getIsGranted(){     
        return this.isGranted;
    }       
    //End of the section of getter methods for getting the values of each instance variables.

    //Setter method which sets the values of instance variables only under a certain condition.
    public void setCreditLimit(double creditLimit, int gracePeriod){  
        if(creditLimit <= 2.5 * getBalanceAmount()){
            this.creditLimit = creditLimit;
            this.gracePeriod = gracePeriod; 
            this.isGranted = true;
        }
        else{                   //Value of instance variable creditLimit is not set if the above condition is not fulfilled. 
            System.out.println("As per our bank's terms and conditions, we are unable to issue credit to you.");
            this.isGranted = false; 
        }
    }

    //Method for cancelling credit card i.e., initializing certain instance variables to zero or false.
    public void cancelCreditCard(){  
        this.cvcNumber = 0;
        this.creditLimit = 0;
        this.gracePeriod = 0; 
        this.isGranted = false;
    }

    /* Display method which calls the display method of super class and displays the instance variables of this class itself
     * with suitable annotations if the client has been granted credit.
     */
    @Override
    public void display(){                                         
        if(this.isGranted == true){                //Checking if credit has been granted to the client. 
            super.display();                       //Calling the display method of super class if credit has been granted.
            System.out.println("The credit limit is " + this.creditLimit);
            System.out.println("The grace period is for " + this.gracePeriod + " days"); 
            System.out.println("The cvc number is " + this.cvcNumber);
            System.out.println("The expiration date of the credit card is " + this.expirationDate);
            
        }
        else{                                       //In the case where credit has not been granted. 
            System.out.println("You have not been granted credit as of now.");
            System.out.println("The cvc number is " + this.cvcNumber);
            System.out.println("The expiration date of the credit card is " + this.expirationDate);
        }
    }
}