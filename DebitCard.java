public class DebitCard extends BankCard{

    private int pinNumber; 
    private int withdrawalAmount;
    private String dateOfWithdrawal;
    private boolean hasWithdrawn; 

    /*
     * In the constructor below, some of the parameters passed to it will again be passed as a parameter to the constructor 
     * of the super class and to the setter method of the super class. The value of the instance variable pinNumber
     * is set to the value passed as a parameter in the constructor of this class through 'this' keyword. 
     */
    public DebitCard(int balanceAmount, int cardId, String bankAccount, String issuerBank, String clientName, int pinNumber){
        super(balanceAmount, cardId, bankAccount, issuerBank);
        super.setClientName(clientName);
        this.pinNumber = pinNumber;
        this.hasWithdrawn = false; 
    }

    //Start of the section of getter methods for getting the values of each instance variables.
    public int getPinNumber(){                  
        return this.pinNumber;
    }

    public int getWithdrawalAmount(){
        return this.withdrawalAmount;
    }

    public String getDateOfWithdrawal(){
        return this.dateOfWithdrawal;
    }

    public boolean getHasWithdrawn(){           
        return this.hasWithdrawn;
    }                                          
    //End of the section of getter methods for getting the values of each instance variables.

    //Setter method where the value of instance variable withdrawalAmount is set to the value passed as a parameter.
    public void setWithdrawalAmount(int withdrawalAmount){    
        this.withdrawalAmount = withdrawalAmount;
    }

    // Method for withdrawing from balance amount if and only if the client has sufficient balance and has entered a valid withdrawal amount.

    public void withdraw(int withdrawalAmount, String dateOfWithdrawal, int pinNumber){   
        if (this.pinNumber == pinNumber){                                                //Validating pin number. 
            if(withdrawalAmount > 0 && getBalanceAmount() >= withdrawalAmount){    
                setBalanceAmount(getBalanceAmount() - withdrawalAmount);
                setWithdrawalAmount(withdrawalAmount);
                this.dateOfWithdrawal = dateOfWithdrawal; 
                this.hasWithdrawn = true; 
            }
            else if(getBalanceAmount() < withdrawalAmount){   //In the case where the client does not have sufficient balance amount.
                System.out.println("You do not have sufficient balance for withdrawal.");
                this.hasWithdrawn = false; 
            }
            else{                                                   //Else condition when the client tries to withdraw 0 amount. 
                System.out.println("You did not enter a valid withdrawal amount.");
                this.hasWithdrawn = false; 
            }
        }
        else{                                                       //Print statement for wrong pin number entered. 
            System.out.println("The pin number entered was wrong.");
            this.hasWithdrawn = false; 
        }
    }

    /* Display method which calls the display method of super class and displays the instance variables of this class itself
     * with suitable annotations if the client has withdrawn a certain amount.
     */
    @Override
    public void display(){         
        if(this.hasWithdrawn == false){                             //Checking if the client has withdrawn or not. 
            System.out.println("The current balance is " + getBalanceAmount());
        }
        else{                                                                            
            super.display();
            System.out.println("The pin number is " + this.pinNumber); 
            System.out.println("The amount that was withdrawn is " + this.withdrawalAmount);
            System.out.println("The date of withdrawal is " + this.dateOfWithdrawal);
        }
    }
}
 