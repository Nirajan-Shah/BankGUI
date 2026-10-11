The method of use of this project is listed here. You may need to need to download and install BlueJ for a good user experience. 

Add a Debit card -
Enter the details for debit Card and credit card. Enter your own or use the sample below.

balanceAmount=50000 <br/>
cardId=17320943  <br/>
bankAccount="325013KTM907" <br/> 
issuerBank="Sky Banks" <br/>
clientName="John Taylor" <br/>
pinNumber=5340 <br/>
cvcNumber=890 <br/>
interestRate=16.0 <br/>

Click on "Add" to the right in the Debit card panel.
Click on "Add" to the right in the Credit card panel. 
Note that you cannot add the same card twice.

- Feature 1:

 1. Enter a certain balanceAmount. You can click on "Display" button on the right of the debit card panel to display your
    entered balance amount. 
 2. Enter a certain withdrawal amount and set the date before clicking on the "Withdraw" button.   
 3. Click on the "Display" button. 

- Feature 2:

> [!NOTE]
> When valid values for card ID, withdrawal amount, withdrawal date and pin number is re-entered, a display box displays all       the entered value. You must have withdrawn before for this to work.

  1. Erase all the entered values.
  2. Re-enter the values for card ID, withdrawal amount, withdrawal date and pin number. 

- Feature 3: 
  1. Enter a certain credit limit and the value for gracePeriod. Do not enter a value greater than 2.5 times the balanceAmount. 
  2. Click on the "Display" button.

- Feature 4:
> [!NOTE]
> When a valid amount for card ID is re-entered, a display box display the credit limit amount and the value for grace period. Note that you must set credit limit beforehand for this to work. 
	 
  1. Set credit limit
  2. Erase all fields. 
  3. Re-enter the card ID of the credit card.
  
Feature 5:
  1. Click on "Cancel" button to cancel the credit card. 

For form validation, the fields for which you entered wrong values or left it empty when adding a debit card or credit card, the error message will inform you about those specific fields.
