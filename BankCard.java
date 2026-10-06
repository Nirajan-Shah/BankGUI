public class BankCard
{
    private int cardId;
    private String clientName;
    private String issuerBank;
    private String bankAccount;
    private int balanceAmount; 

    /* Constructor for passing four parameters and initializing the instance variables with the values passed as a parameter or
     * to a default value.
     */
    public BankCard(int balanceAmount, int cardId, String bankAccount, String issuerBank){                           
        this.clientName= "";                                                                    
        this.balanceAmount = balanceAmount;                                                      
        this.cardId = cardId;                                                                    
        this.bankAccount = bankAccount;                                                          
        this.issuerBank = issuerBank;                                                       
    }

    //Start of the section of getter methods for getting the values of each instance variables.
    public int getCardId(){                     
        return this.cardId;     
    }

    public String getClientName(){
        return this.clientName;
    }

    public String getIssuerBank(){
        return this.issuerBank;
    }

    public String getBankAccount(){
        return this.bankAccount;
    }

    public int getBalanceAmount(){            
        return this.balanceAmount;
    }                                          
    //End of the section of getter methods for getting the values of each instance variables.

    //Setter method where the value of instance variable clientName is set to the value passed as a parameter.
    public void setClientName(String clientName){       
        this.clientName = clientName;                        
    }

    //Same as the above setter method but the parameter passed this time is for balance amount.
    public void setBalanceAmount(int balanceAmount){     
        this.balanceAmount = balanceAmount;
    }

    //Display method for displaying the values of all the instance variables with suitable annotation.
    public void display(){                               
        if(clientName.equals("")){  //Checking if the variable clientName is initialized to a non-empty string or if it is still an empty string.  
            System.out.println("No client name has been assigned.");
        }
        else{                       //In the case where clientName is initialized. 
            System.out.println("The name of the client is " + this.clientName); 
            System.out.println("The card ID is " + this.cardId);
            System.out.println("The name of the issuer bank is " + this.issuerBank);
            System.out.println("The bank account number is " + this.bankAccount);
            System.out.println("The current balance is " + this.balanceAmount);
        }
    }
}
