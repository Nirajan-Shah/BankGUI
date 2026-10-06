import javax.swing.*;   
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import java.awt.FlowLayout; 
import java.awt.Image;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator; 

public class BankGUI implements ActionListener, KeyListener{

    /*
     * Declaring the needed instance variable outside the constructor and inside the class so that the action performed method
     * can access and/or change properties and state of them.
     */

    private int i, k; 

    private ArrayList<BankCard> arrayList = new ArrayList<BankCard>(); 

    private String rightPanel2Year, rightPanel2Month, rightPanel2Day, rightPanel3Year, rightPanel3Month, rightPanel3Day;

    private Timer timer1;

    private JButton minimizeButton, maximizeButton, closeButton, detailsButton, debitCardButton, creditCardButton, settingButton, rightPanel1ClearButton; 

    private JButton displayDebit, clearDebit, addDebit, withdraw;

    private JButton addCredit, clearCredit1, clearCredit2, setCreditLimit, displayCredit, cancelCredit; 

    private JFrame loadingFrame, mainFrame ;

    private JLabel loadingFrameLabel1, loadingFrameLabel2, leftPanelLabel1, rightPanel1Header; 

    private JLabel cardIdLabel, clientNameLabel, issuerBankLabel, bankAccountLabel, balanceAmountLabel;
    private JTextField cardIdField, clientNameField, issuerBankField, bankAccountField, balanceAmountField;

    private JLabel rightPanel2Header, pinNumberLabel, withdrawalAmountLabel, withdrawalDateLabel;
    private JTextField pinNumberField, withdrawalAmountField; 

    private JLabel rightPanel3Header, cvcNumber, interestRate, expirationDate, creditLimit, gracePeriod;
    private JTextField cvcNumberField, interestRateField, creditLimitField, gracePeriodField; 

    private JLabel rightPanel4Header, theme; 

    private JComboBox rightPanel2YearComboBox, rightPanel2MonthComboBox, rightPanel2DayComboBox; 
    private JComboBox rightPanel3YearComboBox, rightPanel3MonthComboBox, rightPanel3DayComboBox;

    private JProgressBar progressBar; 
    private JPanel topPanel, leftPanel, leftPanelPanel, rightPanel1, rightPanel2, rightPanel3, rightPanel4, currentPanel; 

    private JRadioButton darkMode, lightMode; 

    private ButtonGroup buttonGroup; 

    private boolean validCardIdDebit = false;
    private boolean validCardIdCredit = false; 

    private boolean validWithdrawalAmount = false; 
    private boolean validPinNumber = false; 

    public BankGUI() {

        //---------------------------------- Start of the section of the loadingFrame. -----------------------------------------//

        /*
         * The Image class sources image for the background from image object of ImageIcon type. The image1 object is then
         * sent to the constructor of ImageIcon and the this newly created object is reffered to by reference variable image.
         * The automatic garbage collection of java must have destroyed the first object which is not being pointed to by 
         * any reference variable.
         */

        loadingFrameLabel2 = new JLabel();
        ImageIcon image = new ImageIcon("Background.jpg"); 
        Image image1 = image.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH);
        image = new ImageIcon(image1);
        loadingFrameLabel2.setIcon(image); 
        loadingFrameLabel2.setBounds(0,0, 500, 300);

        loadingFrame = new JFrame();                           // Inititalizing the frames. 
        mainFrame = new JFrame(); 

        progressBar = new JProgressBar(0, 100);                // Initializing the progress bar. 
        progressBar.setForeground(new Color(83, 145, 101)); 
        progressBar.setBounds(0, 288, 500, 13);
        progressBar.setStringPainted(true); 

        loadingFrameLabel1 = new JLabel("Loading.");           // Initializing the label. 
        loadingFrameLabel1.setBounds(160, 100, 200, 50);
        loadingFrameLabel1.setFont(new Font("Arial", Font.BOLD, 40)); 
        loadingFrameLabel1.setForeground(new Color(83, 145, 101)); 

        this.timer1 = new Timer(40, this);                     // This timer is of the swing class. 
        timer1.start();

        loadingFrame.add(progressBar);               // Adding the necessary components to the loading frames and setting its attributes. 
        loadingFrame.add(loadingFrameLabel1); 
        loadingFrame.add(loadingFrameLabel2); 
        loadingFrame.setResizable(false); 
        loadingFrame.getContentPane().setBackground(new Color(38,58, 41));
        loadingFrame.setUndecorated(true); 
        loadingFrame.setLayout(null);
        loadingFrame.setSize(500, 300);
        loadingFrame.setVisible(true);
        loadingFrame.setDefaultCloseOperation(loadingFrame.EXIT_ON_CLOSE); 
        loadingFrame.setLocationRelativeTo(null);

        String yearArray[] = {"2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}; 
        String monthArray[] = new String[12];
        String dayArray[] = new String[31];

        /*
         * The loops assign values to the respective indexes. 
         */

        for(int i = 1; i <= 12  ; i++){
            monthArray[i-1] = String.valueOf(i);   //First iteration: month[0] = 1. Final iteration: month[11] = 12
        }

        for(int i = 1; i < 32 ; i++){
            dayArray[i-1] = String.valueOf(i);    //First iteration: month[0] = 1. Final iteration: month[30] = 31
        }

        //---------------------------------- End of the section of the loading frame. ----------------------------------------//

        //---------------------------------- Start of the section of the top panel. ------------------------------------------//
        topPanel = new JPanel(); 
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0)); 

        ImageIcon minimizeImage = new ImageIcon("Minimize.png");                     
        Image scaledMinimizeImage =  minimizeImage.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        minimizeImage = new ImageIcon(scaledMinimizeImage); 

        topPanel.setBounds(250,0,650,40); 
        topPanel.setBackground(new Color(111, 85, 12));
        mainFrame.add(topPanel); 

        minimizeButton = new JButton();                       //Initializing the minimize button and configuring its attributes before adding to the top panel.                                
        minimizeButton.setBackground(new Color(111, 85, 12));
        minimizeButton.setBorderPainted(false); 
        minimizeButton.setFocusable(false); 
        minimizeButton.setIcon(minimizeImage); 
        minimizeButton.setPreferredSize(new Dimension(40,40)); 
        topPanel.add(minimizeButton); 

        minimizeButton.addActionListener(this);

        ImageIcon maximizeImage = new ImageIcon("Maximize.png");
        Image scaledMaximizeImage =  maximizeImage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        maximizeImage = new ImageIcon(scaledMaximizeImage); 

        maximizeButton = new JButton();                      //Initializing the maximize button and configuring its attributes before adding to the top panel.                                
        maximizeButton.setBackground(new Color(111, 85, 12));
        maximizeButton.setBorderPainted(false); 
        maximizeButton.setIcon(maximizeImage); 
        maximizeButton.setPreferredSize(new Dimension(40, 40));
        maximizeButton.addActionListener(this);
        topPanel.add(maximizeButton); 

        ImageIcon closeImage = new ImageIcon("Close.png"); 
        Image scaledCloseImage = closeImage.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
        closeImage = new ImageIcon(scaledCloseImage); 

        closeButton = new JButton();                             //Initializing the close button and configuring its attributes before adding to the top panel.                                
        closeButton.setBackground(new Color(111, 85, 12));
        closeButton.setFocusable(false);
        closeButton.setBorderPainted(false); 
        closeButton.setIcon(closeImage); 
        closeButton.setPreferredSize(new Dimension(40, 40)); 
        topPanel.add(closeButton);

        closeButton.addActionListener(this); 

        //---------------------------------- End of the section of top panel. -----------------------------------------------//

        //---------------------------------- Start of the section of left panel. -----------------------------------------------//
        /*
         * In a consistent manner, components have been intialized first and have their attributes configured before they are
         * added to appropriate panels or frames. 
         */

        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBounds(0,0, 250, 650);
        leftPanel.setBackground(new Color(54,21, 0));
        mainFrame.add(leftPanel);                             //Adding left panel to the frame. 

        leftPanelLabel1 = new JLabel("Bank");           
        leftPanelLabel1.setBounds(75,100, 100, 40);
        leftPanelLabel1.setFont(new Font("Arial", Font.BOLD, 40));
        leftPanelLabel1.setForeground(new Color(230, 200, 136));
        leftPanel.add(leftPanelLabel1);

        //Using grid layout for components with equal importance. 

        leftPanelPanel = new JPanel();                
        leftPanelPanel.setLayout(new GridLayout(4,1));       

        detailsButton = new JButton("Customer Details");
        detailsButton.setForeground(new Color(230, 200, 136));
        detailsButton.setBackground(new Color(54,21, 0));
        detailsButton.setFont(new Font("Arial", Font.PLAIN, 20)); 
        detailsButton.setFocusable(false);
        detailsButton.setBorderPainted(false);
        detailsButton.addActionListener(this);
        leftPanelPanel.add(detailsButton);

        debitCardButton = new JButton("Debit Card");
        debitCardButton.setBackground(new Color(54,21, 0));
        debitCardButton.setForeground(new Color(230, 200, 136)); 
        debitCardButton.setFont(new Font("Arial", Font.PLAIN, 20));
        debitCardButton.setFocusable(false);
        debitCardButton.setBorderPainted(false);
        debitCardButton.addActionListener(this);
        leftPanelPanel.add(debitCardButton);

        creditCardButton = new JButton("Credit Card");
        creditCardButton.setBackground(new Color(54,21, 0));
        creditCardButton.setForeground(new Color(230, 200, 136));
        creditCardButton.setFont(new Font("Arial", Font.PLAIN, 20));
        creditCardButton.setFocusable(false);
        creditCardButton.setBorderPainted(false);
        creditCardButton.addActionListener(this); 
        leftPanelPanel.add(creditCardButton); 

        settingButton = new JButton("Setting");
        settingButton.setBackground(new Color(54,21, 0));
        settingButton.setForeground(new Color(230, 200, 136));
        settingButton.setFont(new Font("Arial", Font.PLAIN, 20));
        settingButton.setFocusable(false);
        settingButton.setBorderPainted(false);
        settingButton.addActionListener(this);
        leftPanelPanel.add(settingButton);

        leftPanel.add(leftPanelPanel);
        leftPanelPanel.setBounds(0,200,250,300);

        //---------------------------------- End of the section of left Panel. -----------------------------------------------//

        //---------------------------------- Start of the section of rightpane1. ---------------------------------------------//
        /*
         * In a consistent manner, components have been intialized first and have their attributes configured before they are
         * added to appropriate panels or frames. 
         */

        rightPanel1 = new JPanel();
        rightPanel1.setLayout(null);
        rightPanel1.setBounds(250,40,650,610);
        rightPanel1.setBackground(new Color(83, 55, 16));
        rightPanel1.setVisible(true); 
        mainFrame.add(rightPanel1); 

        rightPanel1Header = new JLabel("Customer Details", JLabel.CENTER);
        rightPanel1Header.setForeground(new Color(230, 200, 136));
        rightPanel1Header.setFont(new Font("Arial", Font.PLAIN, 30));
        rightPanel1Header.setBounds(0, 50, 650, 40); 
        rightPanel1.add(rightPanel1Header); 

        clientNameLabel = new JLabel("Client name");
        clientNameLabel.setForeground(new Color(230, 200, 136));
        clientNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        clientNameLabel.setBounds(50,160, 160, 30); 
        rightPanel1.add(clientNameLabel);

        clientNameField = new JTextField(); 
        clientNameField.setBackground(new Color(83, 55, 16)); 
        clientNameField.setForeground(new Color(230, 200, 136));
        clientNameField.setFont(new Font("Arial", Font.PLAIN, 20));
        clientNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(230, 200, 136)));
        clientNameField.setCaretColor(new Color(230, 200, 136));
        clientNameField.setBounds(210,160, 125, 30);  
        rightPanel1.add(clientNameField);

        cardIdLabel = new JLabel("Card Id"); 
        cardIdLabel.setForeground(new Color(230, 200, 136));
        cardIdLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        cardIdLabel.setBounds(360,160, 120, 30); 
        rightPanel1.add(cardIdLabel);

        cardIdField = new JTextField(); 
        cardIdField.setBackground(new Color(83, 55, 16)); 
        cardIdField.setForeground(new Color(230, 200, 136));
        cardIdField.setFont(new Font("Arial", Font.PLAIN, 20));
        cardIdField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(230, 200, 136)));
        cardIdField.setCaretColor(new Color(230, 200, 136));
        cardIdField.setBounds(480,160, 125, 30);
        cardIdField.addKeyListener(this); 
        rightPanel1.add(cardIdField);

        bankAccountLabel = new JLabel("Bank account");
        bankAccountLabel.setForeground(new Color(230, 200, 136));
        bankAccountLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        bankAccountLabel.setBounds(50,230, 160, 30); 
        rightPanel1.add(bankAccountLabel);

        bankAccountField = new JTextField();
        bankAccountField.setBackground(new Color(83, 55, 16)); 
        bankAccountField.setForeground(new Color(230, 200, 136));
        bankAccountField.setFont(new Font("Arial", Font.PLAIN, 20));
        bankAccountField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(230, 200, 136)));
        bankAccountField.setCaretColor(new Color(230, 200, 136));
        bankAccountField.setBounds(210,230, 125, 30);  
        rightPanel1.add(bankAccountField);

        issuerBankLabel = new JLabel("Issuer bank");
        issuerBankLabel.setForeground(new Color(230, 200, 136));
        issuerBankLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        issuerBankLabel.setBounds(360,230, 120, 30); 
        rightPanel1.add(issuerBankLabel);

        issuerBankField = new JTextField();
        issuerBankField.setBackground(new Color(83, 55, 16)); 
        issuerBankField.setForeground(new Color(230, 200, 136));
        issuerBankField.setFont(new Font("Arial", Font.PLAIN, 20));
        issuerBankField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(230, 200, 136)));
        issuerBankField.setCaretColor(new Color(230, 200, 136));
        issuerBankField.setBounds(480,230, 125, 30);  
        rightPanel1.add(issuerBankField);

        balanceAmountLabel = new JLabel("Balance amount");
        balanceAmountLabel.setForeground(new Color(230, 200, 136));
        balanceAmountLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        balanceAmountLabel.setBounds(50,300, 160, 30); 
        rightPanel1.add(balanceAmountLabel);

        balanceAmountField = new JTextField(); 
        balanceAmountField.setBackground(new Color(83, 55, 16)); 
        balanceAmountField.setForeground(new Color(230, 200, 136));
        balanceAmountField.setFont(new Font("Arial", Font.PLAIN, 20));
        balanceAmountField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(230, 200, 136)));
        balanceAmountField.setCaretColor(new Color(230, 200, 136));
        balanceAmountField.setBounds(210,300, 125, 30);  
        rightPanel1.add(balanceAmountField);

        rightPanel1ClearButton = new JButton("Clear");
        rightPanel1ClearButton.setForeground(new Color(230, 200, 136));
        rightPanel1ClearButton.setBackground(new Color(83, 55, 16));
        rightPanel1ClearButton.setBorder(BorderFactory.createLineBorder(new Color(230, 200, 136), 4));
        rightPanel1ClearButton.setFont(new Font("Arial", Font.PLAIN, 20));
        rightPanel1ClearButton.setBounds(50, 370, 160, 50);
        rightPanel1ClearButton.setFocusable(false);
        rightPanel1ClearButton.addActionListener(this);
        rightPanel1.add(rightPanel1ClearButton);

        currentPanel = rightPanel1;      

        //---------------------------------- End of the section of rightpanel1. ----------------------------------------------//

        //---------------------------------- Start of the section of rightpanel2. ----------------------------------------------//
        /*
         * In a consistent manner, components have been intialized first and have their attributes configured before they are
         * added to appropriate panels or frames. 
         */

        rightPanel2 = new JPanel();
        rightPanel2.setLayout(null);
        rightPanel2.setBounds(250,40,650,610);
        rightPanel2.setBackground(new Color(83, 55, 16));
        rightPanel2.setVisible(false); 
        mainFrame.add(rightPanel2); 

        rightPanel2Header = new JLabel("Debit Card", JLabel.CENTER);
        rightPanel2Header.setForeground(new Color(230, 200, 136));
        rightPanel2Header.setFont(new Font("Arial", Font.PLAIN, 30));
        rightPanel2Header.setBounds(0, 50, 650, 40); 
        rightPanel2.add(rightPanel2Header);

        pinNumberLabel = new JLabel("PIN number");
        pinNumberLabel.setForeground(new Color(230, 200, 136));
        pinNumberLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        pinNumberLabel.setBounds(50,160, 160, 30); 
        rightPanel2.add(pinNumberLabel);

        pinNumberField = new JTextField(); 
        pinNumberField.setBackground(new Color(83, 55, 16)); 
        pinNumberField.setForeground(new Color(230, 200, 136));
        pinNumberField.setFont(new Font("Arial", Font.PLAIN, 20));
        pinNumberField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(230, 200, 136)));
        pinNumberField.setCaretColor(new Color(230, 200, 136));
        pinNumberField.setBounds(216,160, 125, 30);
        pinNumberField.addKeyListener(this); 
        rightPanel2.add(pinNumberField);

        withdrawalAmountLabel = new JLabel("Withdrawal amount"); 
        withdrawalAmountLabel.setForeground(new Color(230, 200, 136));
        withdrawalAmountLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        withdrawalAmountLabel.setBounds(50,230, 180, 30); 
        rightPanel2.add(withdrawalAmountLabel);

        withdrawalAmountField = new JTextField(); 
        withdrawalAmountField.setBackground(new Color(83, 55, 16)); 
        withdrawalAmountField.setForeground(new Color(230, 200, 136));
        withdrawalAmountField.setFont(new Font("Arial", Font.PLAIN, 20));
        withdrawalAmountField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(230, 200, 136)));
        withdrawalAmountField.setCaretColor(new Color(230, 200, 136));
        withdrawalAmountField.setBounds(235,230, 100, 30);
        withdrawalAmountField.addActionListener(this);
        withdrawalAmountField.addKeyListener(this);
        rightPanel2.add(withdrawalAmountField);

        withdrawalDateLabel = new JLabel("Withdrawal date");
        withdrawalDateLabel.setForeground(new Color(230, 200, 136));
        withdrawalDateLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        withdrawalDateLabel.setBounds(50,300, 170, 30); 
        rightPanel2.add(withdrawalDateLabel);

        rightPanel2YearComboBox = new JComboBox<String>(yearArray);
        rightPanel2YearComboBox.setBackground(new Color(160, 120, 50));
        rightPanel2YearComboBox.setForeground(new Color(230, 200, 136));
        rightPanel2YearComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        rightPanel2YearComboBox.setFocusable(false);
        rightPanel2YearComboBox.setBounds(216, 300, 90, 30);
        rightPanel2YearComboBox.addActionListener(this); 
        rightPanel2.add(rightPanel2YearComboBox); 

        rightPanel2MonthComboBox = new JComboBox<String>(monthArray);
        rightPanel2MonthComboBox.setBackground(new Color(160, 120, 50));
        rightPanel2MonthComboBox.setForeground(new Color(230, 200, 136));
        rightPanel2MonthComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        rightPanel2MonthComboBox.setFocusable(false);
        rightPanel2MonthComboBox.setBounds(306, 300, 50, 30);
        rightPanel2MonthComboBox.addActionListener(this);
        rightPanel2.add(rightPanel2MonthComboBox); 

        rightPanel2DayComboBox = new JComboBox<String>(dayArray);
        rightPanel2DayComboBox.setBackground(new Color(160, 120, 50));
        rightPanel2DayComboBox.setForeground(new Color(230, 200, 136));
        rightPanel2DayComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        rightPanel2DayComboBox.setFocusable(false);
        rightPanel2DayComboBox.setBounds(356, 300, 50, 30);
        rightPanel2DayComboBox.addActionListener(this);
        rightPanel2.add(rightPanel2DayComboBox);

        addDebit = new JButton("Add");
        addDebit.setForeground(new Color(230, 200, 136));
        addDebit.setBackground(new Color(83, 55, 16));
        addDebit.setBorder(BorderFactory.createLineBorder(new Color(230, 200, 136), 4));
        addDebit.setFont(new Font("Arial", Font.PLAIN, 20));
        addDebit.setBounds(430, 160, 160, 50);
        addDebit.setFocusable(false);
        addDebit.addActionListener(this);
        rightPanel2.add(addDebit);

        withdraw = new JButton("Withdraw");
        withdraw.setForeground(new Color(230, 200, 136));
        withdraw.setBackground(new Color(83, 55, 16));
        withdraw.setBorder(BorderFactory.createLineBorder(new Color(230, 200, 136), 4));
        withdraw.setFont(new Font("Arial", Font.PLAIN, 20));
        withdraw.setBounds(50, 370, 160, 50);
        withdraw.setFocusable(false);
        withdraw.addActionListener(this);
        rightPanel2.add(withdraw);

        displayDebit = new JButton("Display");
        displayDebit.setForeground(new Color(230, 200, 136));
        displayDebit.setBackground(new Color(83, 55, 16));
        displayDebit.setBorder(BorderFactory.createLineBorder(new Color(230, 200, 136), 4));
        displayDebit.setFont(new Font("Arial", Font.PLAIN, 20));
        displayDebit.setBounds(430, 370, 160, 50);
        displayDebit.setFocusable(false);
        displayDebit.addActionListener(this);
        rightPanel2.add(displayDebit);

        clearDebit = new JButton("Clear"); 
        clearDebit.setForeground(new Color(230, 200, 136));
        clearDebit.setBackground(new Color(83, 55, 16));
        clearDebit.setBorder(BorderFactory.createLineBorder(new Color(230, 200, 136), 4));
        clearDebit.setFont(new Font("Arial", Font.PLAIN, 20));
        clearDebit.setBounds(430, 235, 160, 50);
        clearDebit.setFocusable(false);
        clearDebit.addActionListener(this);
        rightPanel2.add(clearDebit);

        //---------------------------------- End of the section of rightpanel2. ----------------------------------------------//

        //---------------------------------- Start of the section of rightpanel3. --------------------------------------------//
        /*
         * In a consistent manner, components have been intialized first and have their attributes configured before they are
         * added to appropriate panels or frames. 
         */

        rightPanel3 = new JPanel(); 
        rightPanel3.setLayout(null);
        rightPanel3.setBounds(250,40,650,610);
        rightPanel3.setBackground(new Color(83, 55, 16));
        rightPanel3.setVisible(false); 
        mainFrame.add(rightPanel3); 

        rightPanel3Header = new JLabel("Credit Card", JLabel.CENTER);
        rightPanel3Header.setForeground(new Color(230, 200, 136));
        rightPanel3Header.setFont(new Font("Arial", Font.PLAIN, 30));
        rightPanel3Header.setBounds(0, 20, 650, 40); 
        rightPanel3.add(rightPanel3Header);

        cvcNumber = new JLabel("CVC Number");
        cvcNumber.setForeground(new Color(230, 200, 136));
        cvcNumber.setFont(new Font("Arial", Font.PLAIN, 20));
        cvcNumber.setBounds(50,130, 160, 30); 
        rightPanel3.add(cvcNumber);

        cvcNumberField = new JTextField(); 
        cvcNumberField.setBackground(new Color(83, 55, 16)); 
        cvcNumberField.setForeground(new Color(230, 200, 136));
        cvcNumberField.setFont(new Font("Arial", Font.PLAIN, 20));
        cvcNumberField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(230, 200, 136)));
        cvcNumberField.setCaretColor(new Color(230, 200, 136));
        cvcNumberField.setBounds(210,130, 125, 30);  
        rightPanel3.add(cvcNumberField);

        interestRate = new JLabel("Interest rate"); 
        interestRate.setForeground(new Color(230, 200, 136));
        interestRate.setFont(new Font("Arial", Font.PLAIN, 20));
        interestRate.setBounds(50,190, 120, 30); 
        rightPanel3.add(interestRate);

        interestRateField = new JTextField(); 
        interestRateField.setBackground(new Color(83, 55, 16)); 
        interestRateField.setForeground(new Color(230, 200, 136));
        interestRateField.setFont(new Font("Arial", Font.PLAIN, 20));
        interestRateField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(230, 200, 136)));
        interestRateField.setCaretColor(new Color(230, 200, 136));
        interestRateField.setBounds(210,190, 125, 30);  
        rightPanel3.add(interestRateField);

        expirationDate = new JLabel("Expiration Date");
        expirationDate.setForeground(new Color(230, 200, 136));
        expirationDate.setFont(new Font("Arial", Font.PLAIN, 20));
        expirationDate.setBounds(50,250, 160, 30); 
        rightPanel3.add(expirationDate);

        rightPanel3YearComboBox = new JComboBox<String>(yearArray);
        rightPanel3YearComboBox.setBackground(new Color(160, 120, 50));
        rightPanel3YearComboBox.setForeground(new Color(230, 200, 136));
        rightPanel3YearComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        rightPanel3YearComboBox.setFocusable(false);
        rightPanel3YearComboBox.setBounds(205, 250, 90, 30);
        rightPanel3YearComboBox.addActionListener(this); 
        rightPanel3.add(rightPanel3YearComboBox); 

        rightPanel3MonthComboBox = new JComboBox<String>(monthArray);
        rightPanel3MonthComboBox.setBackground(new Color(160, 120, 50));
        rightPanel3MonthComboBox.setForeground(new Color(230, 200, 136));
        rightPanel3MonthComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        rightPanel3MonthComboBox.setFocusable(false);
        rightPanel3MonthComboBox.setBounds(294, 250, 50, 30);
        rightPanel3MonthComboBox.addActionListener(this); 
        rightPanel3.add(rightPanel3MonthComboBox); 

        rightPanel3DayComboBox = new JComboBox<String>(dayArray);
        rightPanel3DayComboBox.setBackground(new Color(160, 120, 50));
        rightPanel3DayComboBox.setForeground(new Color(230, 200, 136));
        rightPanel3DayComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        rightPanel3DayComboBox.setFocusable(false);
        rightPanel3DayComboBox.setBounds(344, 250, 50, 30);
        rightPanel3DayComboBox.addActionListener(this); 
        rightPanel3.add(rightPanel3DayComboBox);

        addCredit = new JButton("Add"); 
        addCredit.setForeground(new Color(230, 200, 136)); 
        addCredit.setBackground(new Color(83, 55, 16));
        addCredit.setBorder(BorderFactory.createLineBorder(new Color(230, 200, 136), 4));
        addCredit.setFont(new Font("Arial", Font.PLAIN, 20));
        addCredit.setBounds(430, 110, 160, 50);
        addCredit.setFocusable(false); 
        addCredit.addActionListener(this); 
        rightPanel3.add(addCredit); 

        clearCredit1 = new JButton("Clear");
        clearCredit1.setForeground(new Color(230, 200, 136));
        clearCredit1.setBackground(new Color(83, 55, 16));
        clearCredit1.setBorder(BorderFactory.createLineBorder(new Color(230, 200, 136), 4));
        clearCredit1.setFont(new Font("Arial", Font.PLAIN, 20));
        clearCredit1.setBounds(430, 175, 160, 50);
        clearCredit1.setFocusable(false);
        clearCredit1.addActionListener(this);
        rightPanel3.add(clearCredit1);

        creditLimit = new JLabel("Credit limit");
        creditLimit.setForeground(new Color(230, 200, 136));
        creditLimit.setFont(new Font("Arial", Font.PLAIN, 20));
        creditLimit.setBounds(50,340, 160, 30); 
        rightPanel3.add(creditLimit);

        creditLimitField = new JTextField();
        creditLimitField.setBackground(new Color(83, 55, 16)); 
        creditLimitField.setForeground(new Color(230, 200, 136));
        creditLimitField.setFont(new Font("Arial", Font.PLAIN, 20));
        creditLimitField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(230, 200, 136)));
        creditLimitField.setCaretColor(new Color(230, 200, 136));
        creditLimitField.setBounds(210,340, 125, 30);
        creditLimitField.addKeyListener(this); 
        rightPanel3.add(creditLimitField);

        gracePeriod = new JLabel("Grace period");
        gracePeriod.setForeground(new Color(230, 200, 136));
        gracePeriod.setFont(new Font("Arial", Font.PLAIN, 20));
        gracePeriod.setBounds(50,400, 160, 30); 
        rightPanel3.add(gracePeriod);

        gracePeriodField = new JTextField();
        gracePeriodField.setBackground(new Color(83, 55, 16)); 
        gracePeriodField.setForeground(new Color(230, 200, 136));
        gracePeriodField.setFont(new Font("Arial", Font.PLAIN, 20));
        gracePeriodField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(230, 200, 136)));
        gracePeriodField.setCaretColor(new Color(230, 200, 136));
        gracePeriodField.setBounds(210,400, 125, 30);  
        rightPanel3.add(gracePeriodField);

        clearCredit2 = new JButton("Clear");
        clearCredit2.setForeground(new Color(230, 200, 136));
        clearCredit2.setBackground(new Color(83, 55, 16));
        clearCredit2.setBorder(BorderFactory.createLineBorder(new Color(230, 200, 136), 4));
        clearCredit2.setFont(new Font("Arial", Font.PLAIN, 20));
        clearCredit2.setBounds(430, 320, 160, 50);
        clearCredit2.setFocusable(false);
        clearCredit2.addActionListener(this);
        rightPanel3.add(clearCredit2);

        cancelCredit = new JButton("Cancel");
        cancelCredit.setForeground(new Color(230, 200, 136));
        cancelCredit.setBackground(new Color(83, 55, 16));
        cancelCredit.setBorder(BorderFactory.createLineBorder(new Color(230, 200, 136), 4));
        cancelCredit.setFont(new Font("Arial", Font.PLAIN, 20));
        cancelCredit.setBounds(430, 385, 160, 50);
        cancelCredit.setFocusable(false);
        cancelCredit.addActionListener(this);
        rightPanel3.add(cancelCredit);

        setCreditLimit = new JButton("Set Limit");
        setCreditLimit.setForeground(new Color(230, 200, 136));
        setCreditLimit.setBackground(new Color(83, 55, 16));
        setCreditLimit.setBorder(BorderFactory.createLineBorder(new Color(230, 200, 136), 4));
        setCreditLimit.setFont(new Font("Arial", Font.PLAIN, 20));
        setCreditLimit.setBounds(50, 450, 160, 50);
        setCreditLimit.setFocusable(false);
        setCreditLimit.addActionListener(this);
        rightPanel3.add(setCreditLimit);

        displayCredit = new JButton("Display");
        displayCredit.setForeground(new Color(230, 200, 136));
        displayCredit.setBackground(new Color(83, 55, 16));
        displayCredit.setBorder(BorderFactory.createLineBorder(new Color(230, 200, 136), 4));
        displayCredit.setFont(new Font("Arial", Font.PLAIN, 20));
        displayCredit.setBounds(430, 450, 160, 50);
        displayCredit.setFocusable(false);
        displayCredit.addActionListener(this);
        rightPanel3.add(displayCredit);

        //---------------------------------- End of the section of rightpanel3. ----------------------------------------------//

        /*
         * In a consistent manner, components have been intialized first and have their attributes configured before they are
         * added to appropriate panels or frames. 
         */

        rightPanel4 = new JPanel(); 
        rightPanel4.setLayout(null); 
        rightPanel4.setBounds(250, 40, 650, 610); 
        rightPanel4.setBackground(new Color(83, 55, 16));
        rightPanel4.setVisible(false); 
        mainFrame.add(rightPanel4); 

        rightPanel4Header = new JLabel("Setting", JLabel.CENTER);
        rightPanel4Header.setForeground(new Color(230, 200, 136));
        rightPanel4Header.setFont(new Font("Arial", Font.PLAIN, 30)); 
        rightPanel4Header.setBounds(0, 50, 650, 40);
        rightPanel4.add(rightPanel4Header);

        theme = new JLabel("Theme");
        theme.setForeground(new Color(230, 200, 136));
        theme.setFont(new Font("Arial", Font.PLAIN, 20)); 
        theme.setBounds(50, 160, 160, 30);
        rightPanel4.add(theme); 

        darkMode = new JRadioButton("Dark Mode");
        darkMode.addActionListener(this);
        darkMode.setSelected(true); 
        darkMode.setBounds(180,160, 130, 30);
        darkMode.setBackground(new Color(83, 55, 16));
        darkMode.setFont(new Font("Arial", Font.PLAIN, 20));
        darkMode.setForeground(new Color(230, 200, 136)); 
        darkMode.setFocusable(false);  

        lightMode = new JRadioButton("Light Mode");
        lightMode.addActionListener(this);
        lightMode.setBounds(320, 160, 160, 30);
        lightMode.setBackground(new Color(83, 55, 16));
        lightMode.setFont(new Font("Arial", Font.PLAIN, 20));
        lightMode.setForeground(new Color(230, 200, 136));
        lightMode.setFocusable(false); 

        buttonGroup = new ButtonGroup(); 
        buttonGroup.add(darkMode); 
        buttonGroup.add(lightMode); 

        rightPanel4.add(darkMode);
        rightPanel4.add(lightMode);

        mainFrame.setResizable(true);      //Configuring the attributes of main panel. 
        mainFrame.setUndecorated(true); 
        mainFrame.setLayout(null); 
        mainFrame.setSize(900, 650);
        mainFrame.setVisible(false); 
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e){ 

        /*
         * Many variables are declared here so that they are accessible inside if's, else if's. 
         */

        double interestRate;
        double creditLimit;

        int cvcNumber;        
        int balanceAmount; 
        int cardId; 
        int pinNumber; 
        int withdrawalAmount; 
        int gracePeriod; 

        boolean catchBalanceAmount = false;
        boolean catchCardId = false;
        boolean catchPinNumber = false; 
        boolean catchCvcNumber = false; 
        boolean catchInterestRate = false;

        boolean emptyBalanceAmount = false; 
        boolean emptyCardId = false; 
        boolean emptyPinNumber = false; 
        boolean emptyBankAccount = false; 
        boolean emptyIssuerBank = false; 
        boolean emptyClientName = false; 
        boolean emptyCvcNumber = false; 
        boolean emptyInterestRate = false; 
        boolean emptyWithdrawalAmount = false; 
        boolean emptyCreditLimit = false; 
        boolean emptyGracePeriod = false; 

        boolean isEmptyForDebit = false; 
        boolean isEmptyForCredit = false; 
        boolean isEmptyForWithdrawal = false; 
        boolean isEmptyForSetLimit = false; 

        boolean incorrectValueForDebit =false; 
        boolean incorrectValueForCredit = false;
        boolean incorrectValueForWithdrawal = false; 
        boolean incorrectValueForSetLimit = false; 

        boolean catchBankAccount = false; 
        boolean catchIssuerBank = false; 
        boolean catchClientName = false; 
        boolean catchWithdrawalAmount = false; 
        boolean catchCreditLimit = false; 
        boolean catchGracePeriod = false; 

        boolean exception = false; 
        boolean isMatch = false;

        if(e.getSource() == addCredit || e.getSource() == addDebit){

            // Getting text outside the if returned an error. 

            /*
             * The program was made with the intention to capture exactly what values were entered wrong and what values were 
             * left empty. A value can also be wrong and be left empty at the same time. Thefore these pairs of try and catches
             * evaluate what numeric values were entered wrong. And if wrong numeric values were entered, were they empty ? 
             */

            try{
                balanceAmount = Integer.parseInt(balanceAmountField.getText());
            }
            catch(NumberFormatException exception1){
                catchBalanceAmount = true; 
                if (balanceAmountField.getText().equals("")){
                    emptyBalanceAmount = true; 
                }
            }

            try{
                cardId = Integer.parseInt(cardIdField.getText());
            }
            catch(NumberFormatException exception1){
                catchCardId = false;
                if(cardIdField.getText().equals("")){
                    emptyCardId = true; 
                }
            }

            try{
                pinNumber = Integer.parseInt(pinNumberField.getText());
            }
            catch(NumberFormatException exception1){
                catchPinNumber = true;
                if(pinNumberField.getText().equals("")){
                    emptyPinNumber = true; 
                }
            }

            try{
                cvcNumber = Integer.parseInt(cvcNumberField.getText());
            }
            catch(NumberFormatException exception1){
                catchCvcNumber = true;
                if(cvcNumberField.getText().equals("")){
                    emptyCvcNumber = true; 
                }
            }

            try{
                interestRate = Double.parseDouble(interestRateField.getText());
            }
            catch(NumberFormatException exception1){
                catchInterestRate = true;
                if(interestRateField.getText().equals("")){
                    emptyInterestRate = true;
                }
            }

            try{
                withdrawalAmount = Integer.parseInt(withdrawalAmountField.getText()); 
            }
            catch(NumberFormatException exception1){
                catchWithdrawalAmount = true; 
                if(withdrawalAmountField.getText().equals("")){
                    emptyWithdrawalAmount = true; 
                }
            }

            try{
                creditLimit = Double.parseDouble(creditLimitField.getText()); 
            }
            catch(NumberFormatException exception1){
                catchCreditLimit = true; 
                if(withdrawalAmountField.getText().equals("")){
                    emptyCreditLimit = true; 
                }
            }

            try{
                gracePeriod = Integer.parseInt(gracePeriodField.getText()); 
            }
            catch(NumberFormatException exception1){
                catchGracePeriod = true; 
                if(withdrawalAmountField.getText().equals("")){
                    emptyGracePeriod = true; 
                }
            }

            /*
             * A string value can either be empty or correct. For the context of this GUI, they cannot be wrong.
             * Therefore, try and catch pairs were not needed. There was only the need to know if they were empty. 
             */

            if(bankAccountField.getText().equals("")){
                emptyBankAccount = true;
            }

            if(issuerBankField.getText().equals("")){
                emptyIssuerBank = true;
            }

            if(clientNameField.getText().equals("")){
                emptyClientName = true;
            }

            /*
             * Different buttons are related to specific text fields. For example, the program is only concered with the text 
             * fields bank account, issuer bank, client name, balance amount, card id and pin number when the user tries to 
             * add a debit card. It does not matter if all other fields are left empty or are filled up. For example, the
             * boolean variable isEmptyForDebit value is initialized to true if any of the text fields related to debitCard
             * are empty. 
             */

            if(emptyBankAccount || emptyIssuerBank || emptyClientName || emptyBalanceAmount || emptyCardId || emptyPinNumber){
                isEmptyForDebit = true; 
            }

            if(catchBalanceAmount || catchCardId || catchPinNumber){
                incorrectValueForDebit = true; 
            }

            if(emptyBankAccount || emptyIssuerBank || emptyClientName || emptyBalanceAmount || emptyCardId || emptyCvcNumber || emptyInterestRate){
                isEmptyForCredit = true; 
            }

            if(catchBalanceAmount || catchCardId || catchCvcNumber || catchInterestRate){
                incorrectValueForCredit = true;
            }
        }

        i++;

        /*
         * The action performed method is called by the time and each time it is called, the value of i is incremented by 1.
         * The text field is its text set to corresponding value of i. 
         * The timer does not stop until and unless i reaches to 100. 
         */

        if(i == 10){
            loadingFrameLabel1.setText("Loading..");
        }
        else if(i == 20){
            loadingFrameLabel1.setText("Loading...");
        }
        else if(i == 30){
            loadingFrameLabel1.setText("Loading."); 
        }
        else if(i == 40){
            loadingFrameLabel1.setText("Loading..");
        }
        else if(i == 50){
            loadingFrameLabel1.setText("Loading...");
        }
        else if(i == 55){
            loadingFrameLabel1.setText("Loading.");
        }
        else if(i == 70){
            loadingFrameLabel1.setText("Loading..");
        }
        else if(i == 80){
            loadingFrameLabel1.setText("Loading...");
        }
        else if(i == 90){
            loadingFrameLabel1.setText("Loading.");
        }
        else if(i == 100){
            loadingFrameLabel1.setText("Loading..");  
            timer1.stop();
            loadingFrame.dispose();
            mainFrame.setVisible(true);
        }
        progressBar.setValue(i);

        if(e.getSource() == closeButton){
            mainFrame.dispose();                       //Disposing the main frame on click. 
        }
        else if(e.getSource() == maximizeButton){

        }
        else if(e.getSource() == minimizeButton){
            mainFrame.setState(JFrame.ICONIFIED);      //Minimizing the frame on clickl. 
        }
        else if(e.getSource() == detailsButton){
            /*
             * There can be more than one instance variable referring to the same object. The current panel referts to the 
             * current object. If any of the buttons on left panel relating to different panel than the current one is clicked,
             * the current pannel reffered to by currentPanel has its visibility hidden and the new panel linked to the button
             * is made visible. 
             */

            currentPanel.setVisible(false);            
            currentPanel = rightPanel1; 
            rightPanel1.setVisible(true); 
        }
        else if(e.getSource() == debitCardButton){
            currentPanel.setVisible(false);
            currentPanel = rightPanel2; 
            rightPanel2.setVisible(true);
        }
        else if(e.getSource() == creditCardButton){
            currentPanel.setVisible(false); 
            currentPanel = rightPanel3;
            rightPanel3.setVisible(true);
        }
        else if(e.getSource() == settingButton){
            currentPanel.setVisible(false); 
            currentPanel = rightPanel4; 
            rightPanel4.setVisible(true); 
        }
        else if(e.getSource() == addDebit){
            try{

                //Throw an exception if any values relating to adding debit are either wrong or left empty or both. 

                if(emptyBankAccount || emptyIssuerBank || emptyClientName || emptyBalanceAmount || emptyCardId || emptyPinNumber || catchBalanceAmount || catchCardId || catchPinNumber){
                    throw new NumberFormatException();
                }

                String bankAccount = bankAccountField.getText(); // Extracting the needed values from the text fields. 
                String issuerBank = issuerBankField.getText();
                String clientName = clientNameField.getText(); 
                balanceAmount = Integer.parseInt(balanceAmountField.getText());  
                cardId = Integer.parseInt(cardIdField.getText());
                pinNumber = Integer.parseInt(pinNumberField.getText());

                Iterator itr = arrayList.iterator();       //Returning an object from array list class made reffered to by itr.

                if(arrayList.isEmpty()){          
                    /*
                     * There is no need to check for a duplicate entry by matching the card id if the database i.e., the 
                     * array list is itself emtpy. This if statement will run only once for each object of bankGUI when the 
                     * user adds an entry of debit card to the database for the first time. 
                     */

                    DebitCard obj = new DebitCard(balanceAmount, cardId, bankAccount, issuerBank, clientName, pinNumber); 
                    
                    
                    
                    
                    // Making an object of debit card by sending appropriate values to the constructor of the debit card class.

                    arrayList.add(obj); 
                    
                    

                    // Adding the newly created object to the array list. 

                    JOptionPane.showMessageDialog(mainFrame, "The debit card has been added successfully.", "Inforamtion", JOptionPane.INFORMATION_MESSAGE); 

                    // Display an appropriate message. 
                }
                else{
                    while(itr.hasNext()){  
                        /*
                         * For the first iteration, the current cursor points to the left of the first element. 
                         * itr.hasNext() returns true only when there is an element to the right of the cursor. 
                         */

                        BankCard bankCard = (BankCard)itr.next(); 

                        /*
                         * Two things are done by itr.next().
                         * It returns the current element which is right to the cursor in object type. 
                         * It advanced the cursor towards the right of first element and left of the second element.
                         * 
                         * The object is cast to bank card class. 
                         */

                        if(bankCard instanceof DebitCard){ 

                            /*
                             * Objects of both debit card and credit card may be added to the array list. However, the program 
                             * is only concerned with detecting duplicates in debit card and not in credit card. Therefore, the 
                             * instanceof operator checks whether the object was first a debit card or not before it was upcasted 
                             * to bankCard i.e., the instance of bank card. 
                             */

                            if(bankCard.getCardId() == cardId){
                                /*
                                 * There is a reason why if, else clause was not used to check if the card id entered by the 
                                 * user is equal to the card id of the bank card object. This is because the dialog box  
                                 * informing about wrong card id would keep popping up as many number of times as there are
                                 * entries in the array list. The main purpose here is to only check if there was a match, and 
                                 * if there was a match, then to immediately break the loop afterwards. 
                                 */

                                isMatch = true;
                                JOptionPane.showMessageDialog(mainFrame, "You cannot add the same debit card twice.", "Error", JOptionPane.ERROR_MESSAGE);
                                break; 
                            }
                        }
                    }
                    if (isMatch == false){
                        /*
                         * If the boolean value of isMatch was never intitialized to true, the iterator which has iterated 
                         * throughout the list did not find any card id matches. In that case, its safe to add a debit card 
                         * object. 
                         */

                        DebitCard obj = new DebitCard(balanceAmount, cardId, bankAccount, issuerBank, clientName, pinNumber); 
                        arrayList.add(obj);
                        JOptionPane.showMessageDialog(mainFrame, "The debit card has been added successfully.", "Inforamtion", JOptionPane.INFORMATION_MESSAGE); 
                    }
                }
            }
            catch(NumberFormatException exception1){

                /*
                 * For a single entry or single click on addDebit button, there can be both incorrect and empty, correct and 
                 * empty, or incorrect and non empty values. The procedure for adding debit is run in the try block for the 
                 * case where the values are both correct and not empty. 
                 */

                if(incorrectValueForDebit && isEmptyForDebit){
                    /*
                     * Ternary operators were used to assign string values to messageForIncorrect values.
                     * For example, A line of code (catchBalanceAmount) ? "balance amount," : "")  
                     * returns either "balance amount," or "". It returns balance amount if the balance amount was incorrect 
                     * i.e., there was an exception when trying to convert String to int.
                     */
                                                                                                                      
                    String messageForIncorrectValue = ((catchBalanceAmount) ? "balance amount," : "") + ((catchCardId) ? "card id," : "") + ((catchPinNumber) ? "pin number" : ""); 
                    String messageAfterSplit[]  = messageForIncorrectValue.split(",");

                    /*
                     * Commas were added so they could be split on the basis of commas. The number of entries of messageAfterSplit range 
                     * from 1 to 3. 
                     */

                    if(messageAfterSplit.length == 1){
                        /*
                         * If the length of messageAfterSplit was 1, then that means the messageForIncorrect values returned
                         * only a single entry which may be either balance amount, card id and pin number.
                         */

                        messageForIncorrectValue = "You have entered wrong values for " + messageAfterSplit[0] + ".";
                    }
                    else if(messageAfterSplit.length == 2){
                        messageForIncorrectValue = "You have entered wrong values for " + messageAfterSplit[0] + " and " + messageAfterSplit[1] + "."; ;
                    }
                    else if(messageAfterSplit.length == 3){
                        messageForIncorrectValue = "You have entered wrong values for " + messageAfterSplit[0] + ", " + messageAfterSplit[1] + " and " + messageAfterSplit[2] + ".";
                    }

                    /*
                     * The same logic of above is applied below. Only in this case, empty fields are checked instead of incorrect
                     * values. 
                     */

                    String messageForEmptyValue = ((emptyBankAccount) ? "bank account," : "") + ((emptyIssuerBank) ? "issuer bank," : "") + ((emptyClientName) ? "client name," : "") + ((emptyBalanceAmount) ? "balance amount," : "") + ((emptyCardId) ? "card id," : "") + ((emptyPinNumber) ? "pin number" : "");
                    String messageAfterSplit1[] = messageForEmptyValue.split(","); 

                    if(messageAfterSplit1.length == 1){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + ".";
                    }
                    else if(messageAfterSplit1.length == 2){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + " and " + messageAfterSplit1[1] + "."; ;
                    }
                    else if(messageAfterSplit1.length == 3){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + ", " + messageAfterSplit1[1] + " and " + messageAfterSplit1[2] + ".";
                    }
                    else if(messageAfterSplit1.length == 4){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + ", " + messageAfterSplit1[1] + ", " + messageAfterSplit1[2] + " and " + messageAfterSplit1[3] + ".";
                    }
                    else if(messageAfterSplit1.length == 5){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + ", " + messageAfterSplit1[1] + ", " + messageAfterSplit1[2] + ", " + messageAfterSplit1[3] + " and " + messageAfterSplit1[4] + ".";
                    }
                    else if(messageAfterSplit1.length == 6){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + ", " + messageAfterSplit1[1] + ", " + messageAfterSplit1[2] + ", " + messageAfterSplit1[3] + ", " + messageAfterSplit1[4] + " and " + messageAfterSplit1[5] + ".";
                    }

                    JOptionPane.showMessageDialog(mainFrame, messageForIncorrectValue + "\n" + messageForEmptyValue, "Error" , JOptionPane.ERROR_MESSAGE);
                }
                else if(!incorrectValueForDebit && isEmptyForDebit){
                    String messageForEmptyValue = ((emptyBankAccount) ? "bank account," : "") + ((emptyIssuerBank) ? "issuer bank," : "") + ((emptyClientName) ? "client name," : "") + ((emptyBalanceAmount) ? "balance amount," : "") + ((emptyCardId) ? "card id," : "") + ((emptyPinNumber) ? "pin number" : "");

                    String messageAfterSplit1[] = messageForEmptyValue.split(","); 

                    if(messageAfterSplit1.length == 1){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + ".";
                    }
                    else if(messageAfterSplit1.length == 2){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + " and " + messageAfterSplit1[1] + "."; ;
                    }
                    else if(messageAfterSplit1.length == 3){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + ", " + messageAfterSplit1[1] + " and " + messageAfterSplit1[2] + ".";
                    }

                    JOptionPane.showMessageDialog(mainFrame, messageForEmptyValue, "Error" , JOptionPane.ERROR_MESSAGE); 
                }
                else if(incorrectValueForDebit && !isEmptyForDebit){
                    String messageForIncorrectValue = ((catchBalanceAmount) ? "balance amount," : "") + ((catchCardId) ? "card id," : "") + ((catchPinNumber) ? "pin number" : ""); 

                    String messageAfterSplit[]  = messageForIncorrectValue.split(",");

                    if(messageAfterSplit.length == 1){
                        messageForIncorrectValue = "You have entered wrong values for " + messageAfterSplit[0] + ".";
                    }
                    else if(messageAfterSplit.length == 2){
                        messageForIncorrectValue = "You have entered wrong values for " + messageAfterSplit[0] + " and " + messageAfterSplit[1] + "."; ;
                    }
                    else if(messageAfterSplit.length == 3){
                        messageForIncorrectValue = "You have entered wrong values for " + messageAfterSplit[0] + ", " + messageAfterSplit[1] + " and " + messageAfterSplit[2] + ".";
                    }

                    JOptionPane.showMessageDialog(mainFrame, messageForIncorrectValue, "Error" , JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        //--------------------------------------------------------------------------------------------------------------------//
        else if(e.getSource() == addCredit){
            try{
                //Throw an exception if any of the values are empty or incorrect or both. 

                if(emptyBankAccount || emptyIssuerBank || emptyClientName || emptyBalanceAmount || emptyCardId || emptyCvcNumber || emptyInterestRate || catchBalanceAmount || catchCardId || catchCvcNumber || catchInterestRate){
                    throw new NumberFormatException();
                }

                String bankAccount = bankAccountField.getText();
                String issuerBank = issuerBankField.getText();
                String clientName = clientNameField.getText();

                rightPanel3Year = rightPanel3YearComboBox.getSelectedItem().toString();
                rightPanel3Month = rightPanel3MonthComboBox.getSelectedItem().toString();
                rightPanel3Day = rightPanel3DayComboBox.getSelectedItem().toString(); 
                String expirationDate = rightPanel3Year + "-" + rightPanel3Month + "-" + rightPanel3Day; 

                balanceAmount = Integer.parseInt(balanceAmountField.getText());
                cardId = Integer.parseInt(cardIdField.getText());
                cvcNumber = Integer.parseInt(cvcNumberField.getText());
                interestRate = Double.parseDouble(interestRateField.getText());

                Iterator itr = arrayList.iterator();

                if(arrayList.isEmpty()){
                    CreditCard obj = new CreditCard(cardId, clientName, issuerBank,  bankAccount, balanceAmount,  cvcNumber, interestRate, expirationDate); 
                    arrayList.add(obj);
                    
                    

                    JOptionPane.showMessageDialog(mainFrame, "The credit card has been added successfully.", "Inforamtion", JOptionPane.INFORMATION_MESSAGE); 
                }
                else{
                    while(itr.hasNext()){
                        BankCard bankCard = (BankCard)itr.next(); 
                        if(bankCard instanceof CreditCard){
                            if(bankCard.getCardId() == cardId){
                                isMatch = true;
                                JOptionPane.showMessageDialog(mainFrame, "You cannot add the same credit card twice.", "Error", JOptionPane.ERROR_MESSAGE);
                                break; 
                            }
                        }
                    }
                    if (isMatch == false){
                        CreditCard obj = new CreditCard(cardId, clientName, issuerBank,  bankAccount, balanceAmount,  cvcNumber, interestRate, expirationDate); 
                        arrayList.add(obj);
                        JOptionPane.showMessageDialog(mainFrame, "The credit card has been added successfully.", "Inforamtion", JOptionPane.INFORMATION_MESSAGE); 
                    }
                }

            }
            catch(NumberFormatException exception1){
                if(incorrectValueForCredit && isEmptyForCredit){

                    String messageForIncorrectValue = ((catchBalanceAmount) ? "balance amount," : "") + ((catchCardId) ? "card id," : "") + ((catchCvcNumber) ? "cvc number," : "") + ((catchInterestRate) ? "interest rate" : ""); 
                    String messageAfterSplit[]  = messageForIncorrectValue.split(",");

                    if(messageAfterSplit.length == 1){
                        messageForIncorrectValue = "You have entered wrong values for " + messageAfterSplit[0] + ".";
                    }
                    else if(messageAfterSplit.length == 2){
                        messageForIncorrectValue = "You have entered wrong values for " + messageAfterSplit[0] + " and " + messageAfterSplit[1] + "."; ;
                    }
                    else if(messageAfterSplit.length == 3){
                        messageForIncorrectValue = "You have entered wrong values for " + messageAfterSplit[0] + ", " + messageAfterSplit[1] + " and " + messageAfterSplit[2] + ".";
                    }
                    else if(messageAfterSplit.length == 4){
                        messageForIncorrectValue = "You have entered wrong values for " + messageAfterSplit[0] + ", " + messageAfterSplit[1] + ", " + messageAfterSplit[2] + " and " + messageAfterSplit[3] + ".";
                    }

                    String messageForEmptyValue = ((emptyBankAccount) ? "bank account," : "") + ((emptyIssuerBank) ? "issuer bank," : "") + ((emptyClientName) ? "client name," : "") + ((emptyBalanceAmount) ? "balance amount," : "") + ((emptyCardId) ? "card id," : "") + ((emptyCvcNumber) ? "cvc number," : "") + ((emptyInterestRate) ? "interest rate" : "");
                    String messageAfterSplit1[] = messageForEmptyValue.split(","); 

                    if(messageAfterSplit1.length == 1){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + ".";
                    }
                    else if(messageAfterSplit1.length == 2){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + " and " + messageAfterSplit1[1] + "."; ;
                    }
                    else if(messageAfterSplit1.length == 3){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + ", " + messageAfterSplit1[1] + " and " + messageAfterSplit1[2] + ".";
                    }
                    else if(messageAfterSplit1.length == 4){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + ", " + messageAfterSplit1[1] + ", " + messageAfterSplit1[2] + " and " + messageAfterSplit1[3] + ".";
                    }
                    else if(messageAfterSplit1.length == 5){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + ", " + messageAfterSplit1[1] + ", " + messageAfterSplit1[2] + ", " + messageAfterSplit1[3] + " and " + messageAfterSplit1[4] + ".";
                    }
                    else if(messageAfterSplit1.length == 6){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + ", " + messageAfterSplit1[1] + ", " + messageAfterSplit1[2] + ", " + messageAfterSplit1[3] + ", " + messageAfterSplit1[4] + " and " + messageAfterSplit1[5] + ".";
                    }
                    else if(messageAfterSplit1.length == 7){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + ", " + messageAfterSplit1[1] + ", " + messageAfterSplit1[2] + ", " + messageAfterSplit1[3] + ", " + messageAfterSplit1[4] + " and " + messageAfterSplit1[5] + ", " + messageAfterSplit1[6] + ".";
                    }

                    JOptionPane.showMessageDialog(mainFrame, messageForIncorrectValue + "\n" + messageForEmptyValue, "Error" , JOptionPane.ERROR_MESSAGE);
                }
                else if(!incorrectValueForCredit && isEmptyForCredit){
                    String messageForEmptyValue = ((emptyBankAccount) ? "bank account," : "") + ((emptyIssuerBank) ? "issuer bank," : "") + ((emptyClientName) ? "client name," : "") + ((emptyBalanceAmount) ? "balance amount," : "") + ((emptyCardId) ? "card id," : "") + ((emptyCvcNumber) ? "cvc number," : "") + ((emptyInterestRate) ? "interest rate" : "");

                    String messageAfterSplit1[] = messageForEmptyValue.split(","); 

                    if(messageAfterSplit1.length == 1){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + ".";
                    }
                    else if(messageAfterSplit1.length == 2){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + " and " + messageAfterSplit1[1] + "."; ;
                    }
                    else if(messageAfterSplit1.length == 3){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + ", " + messageAfterSplit1[1] + " and " + messageAfterSplit1[2] + ".";
                    }
                    else if(messageAfterSplit1.length == 4){
                        messageForEmptyValue = "You have not entered values for " + messageAfterSplit1[0] + ", " + messageAfterSplit1[1] + ", " + messageAfterSplit1[2] + " and " + messageAfterSplit1[3] + ".";
                    }

                    JOptionPane.showMessageDialog(mainFrame, messageForEmptyValue, "Error" , JOptionPane.ERROR_MESSAGE);
                }
                else if(incorrectValueForCredit && !isEmptyForCredit){
                    String messageForIncorrectValue = ((catchBalanceAmount) ? "balance amount," : "") + ((catchCardId) ? "card id," : "") + ((catchCvcNumber) ? "cvc number," : "") + ((catchInterestRate) ? "interest rate" : ""); 

                    String messageAfterSplit[]  = messageForIncorrectValue.split(",");

                    if(messageAfterSplit.length == 1){
                        messageForIncorrectValue = "You have entered wrong values for " + messageAfterSplit[0] + ".";
                    }
                    else if(messageAfterSplit.length == 2){
                        messageForIncorrectValue = "You have entered wrong values for " + messageAfterSplit[0] + " and " + messageAfterSplit[1] + "."; ;
                    }
                    else if(messageAfterSplit.length == 3){
                        messageForIncorrectValue = "You have entered wrong values for " + messageAfterSplit[0] + ", " + messageAfterSplit[1] + " and " + messageAfterSplit[2] + ".";
                    }
                    else if(messageAfterSplit.length == 4){
                        messageForIncorrectValue = "You have entered wrong values for " + messageAfterSplit[0] + ", " + messageAfterSplit[1] + ", " + messageAfterSplit[2] + " and " + messageAfterSplit[3] + ".";
                    }

                    JOptionPane.showMessageDialog(mainFrame, messageForIncorrectValue, "Error" , JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        else if (e.getSource() == withdraw){
            try{
                cardId = Integer.parseInt(cardIdField.getText()); 
                pinNumber = Integer.parseInt(pinNumberField.getText()); 
                withdrawalAmount = Integer.parseInt(withdrawalAmountField.getText());
                
                boolean isMatchCardId = false; 
                boolean isMatchPinNumber = false; 

                rightPanel2Year = rightPanel2YearComboBox.getSelectedItem().toString(); 
                rightPanel2Month = rightPanel2MonthComboBox.getSelectedItem().toString();
                rightPanel2Day = rightPanel2DayComboBox.getSelectedItem().toString(); 
                String dateOfWithdrawal = rightPanel2Year + "-" + rightPanel2Month + "-" + rightPanel2Day;

                if(arrayList.isEmpty()){
                    /*
                     * How can the user withdraw when there is no debit card in the first place ? 
                     * Is the user attempting to withdraw from credit card ? There are no credit cards either.              
                     * Is the user attempting to withdraw money out of thin air ? 
                     */

                    JOptionPane.showMessageDialog(mainFrame, "There are no entries in the database. Please add an object of debit card before withdrawing.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    Iterator itr = arrayList.iterator(); 
                    while(itr.hasNext()){
                        BankCard bankCard = (BankCard)itr.next(); 
                        if(bankCard instanceof DebitCard){

                            /*
                             * If the object returned by the itr.next() is an instance of debit card, then its time for the 
                             * object to return back to its origin and be converted back to debit card i.e., be downcasted from 
                             * bank card to debit card. This is so that the object can access the withdraw method which was not
                             * possible after it was upcasted to bank card. 
                             */

                            DebitCard debitCard = (DebitCard)bankCard; 
                            if(debitCard.getCardId() == cardId || debitCard.getPinNumber() == pinNumber){
                                if(debitCard.getCardId() == cardId && debitCard.getPinNumber() != pinNumber){

                                    /*
                                     * This is like the login procedure for online accounts. The user may have entered a 
                                     * valid card id buy may have forgotten the pin number. Therefore, an appropriate message
                                     * is displayed outside the loop. Break is not specified in this block as the loop has to 
                                     * check more than one object.
                                     */

                                    isMatchCardId = true; 
                                    isMatchPinNumber = false;
                                    break; 
                                }
                                else if(debitCard.getCardId() != cardId){
                                    /*
                                     * There is no need to check for pin number if there is no entry of debit card of the 
                                     * card id entered by the user. Break is not specified in this block as the loop has to 
                                     * check more than one object. 
                                     */

                                    isMatchCardId = false; 
                                }
                                else if(debitCard.getCardId() == cardId && debitCard.getPinNumber() == pinNumber){
                                    /*
                                     * The withdrawal process is carried out if and only the user correct card id and correct
                                     * pin number.
                                     */

                                    debitCard.withdraw(withdrawalAmount, dateOfWithdrawal, pinNumber);
                                    isMatchCardId = true; 
                                    isMatchPinNumber = true; 

                                    if(debitCard.getHasWithdrawn() == true){
                                        JOptionPane.showMessageDialog(mainFrame, "The withdrawal was successful." + "\n" + "\n" + "The current balance amount is " + debitCard.getBalanceAmount() + "." + "\n" + "The date of withdrawal is " + debitCard.getDateOfWithdrawal() + ".", "Information", JOptionPane.INFORMATION_MESSAGE);  
                                    }
                                    else if(debitCard.getHasWithdrawn() == false){
                                        JOptionPane.showMessageDialog(mainFrame, "The withdrawal was not successful." + "\n" + "\n" + "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);  
                                    }
                                    break; 
                                }
                            }
                        }

                    }
                    if(isMatchCardId == false){
                        JOptionPane.showMessageDialog(mainFrame, "A valid card Id was not entered.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(isMatchCardId == true && isMatchPinNumber == false){
                        JOptionPane.showMessageDialog(mainFrame, "The pin number of the entered card Id was not valid.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            catch(NumberFormatException exception1){
                
                /*
                 * This catch block is run when user enters any values for any of the text fields of withdrawal amount, card id
                 * and pin number which are not of int datatype. 
                 */

                JOptionPane.showMessageDialog(mainFrame, "You have not entered a correct value. Please enter a correct value.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        else if (e.getSource() == setCreditLimit){
            try{
                cardId = Integer.parseInt(cardIdField.getText()); 
                gracePeriod = Integer.parseInt(gracePeriodField.getText());
                creditLimit = Double.parseDouble(creditLimitField.getText()); 
                boolean isMatchCredit = false; 

                if(arrayList.isEmpty()){
                    JOptionPane.showMessageDialog(mainFrame, "There are no entries in the database. Please add an object of credit card before setting its limit.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    Iterator itr = arrayList.iterator(); 
                    while(itr.hasNext()){
                        BankCard bankCard = (BankCard)itr.next(); 
                        if(bankCard instanceof CreditCard){
                            CreditCard creditCard = (CreditCard)bankCard; 
                            if(creditCard.getCardId() != cardId){
                                isMatchCredit = false; 
                            }
                            else if(creditCard.getCardId() == cardId){ 
                                //The credit limit is only set when the card id value entered by the user matches with the current credit card object's card card id. 

                                creditCard.setCreditLimit(creditLimit, gracePeriod); 
                                isMatchCredit = true;  
                                if(creditCard.getIsGranted() == true){
                                    JOptionPane.showMessageDialog(mainFrame, "The credit limit has been set successfully." +"\n" + "\n" + "Your new credit limit is " + creditCard.getCreditLimit() + "." + "\n" + "Your new grace period is " + creditCard.getGracePeriod() + ".", "Information", JOptionPane.INFORMATION_MESSAGE);
                                }
                                else if(creditCard.getIsGranted() == false){
                                    JOptionPane.showMessageDialog(mainFrame, "The credit limit could not be set." +"\n" +"\n" + "Please enter a valid credit limit. ", "Error", JOptionPane.ERROR_MESSAGE); 
                                }
                                break; 
                            }
                        }
                    } 
                    if(isMatchCredit == false){
                        JOptionPane.showMessageDialog(mainFrame, "There is no entry of credit card of entered card id in the database.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            catch(NumberFormatException exception1){
                JOptionPane.showMessageDialog(mainFrame, "You have not entered a correct value. Please enter a correct value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == cancelCredit){
            try{
                cardId = Integer.parseInt(cardIdField.getText()); 
                Iterator itr = arrayList.iterator(); 
                boolean isMatchCredit = false; 
                boolean isGranted = false; 
                
                if(arrayList.isEmpty()){
                    JOptionPane.showMessageDialog(mainFrame, "There are no entries in the database. Please add an object of credit card before cancelling its limit.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                while(itr.hasNext()){
                    BankCard bankCard = (BankCard)itr.next(); 
                    if(bankCard instanceof CreditCard){
                        CreditCard creditCard = (CreditCard) bankCard; //Downcasting to credit card type to access cancelCreditCard() method. 
                        if(creditCard.getCardId() != cardId){
                            isMatchCredit = false; 
                        }
                        else if(creditCard.getCardId() == cardId){
                            isMatchCredit = true;
                            if(creditCard.getIsGranted() == true){
                                //Only able to cancel when the credit card owner has been granted credit limit. 

                                creditCard.cancelCreditCard();
                                isGranted = true; 

                                JOptionPane.showMessageDialog(mainFrame, "The credit limit of the entered card id has been successfully cancelled." + "\n" + "\n" + "The cvc number is " + creditCard.getCvcNumber() + "." + "\n" + "The credit limit is " + creditCard.getCreditLimit() + "." + "\n" + "The grace period is " + creditCard.getGracePeriod() + ".", "Information", JOptionPane.INFORMATION_MESSAGE); 

                                break;
                            }
                            else{
                                isGranted = false; 
                            }
                        }
                    }
                }
                if(isMatchCredit == false){
                    JOptionPane.showMessageDialog(mainFrame, "There is no entry of credit card of the enterd card id in the database.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    if(isGranted == false){
                        JOptionPane.showMessageDialog(mainFrame, "Credit limit has not been granted or it has been cancelled before." + "\n" + "Hence, the system is unable to cancel the credit card." ,  "Error", JOptionPane.ERROR_MESSAGE); 
                    }
                }
            }
            }
            catch(NumberFormatException exception1){
                JOptionPane.showMessageDialog(mainFrame, "You have not entered a correct value. Please enter a correct value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == displayDebit){

            try{
                cardId = Integer.parseInt(cardIdField.getText());
                isMatch = false; 
                Iterator itr = arrayList.iterator(); 
                

                if(arrayList.isEmpty()){
                    JOptionPane.showMessageDialog(mainFrame, "There are no entries in the database." + "\n" + "Please add a debit card to the database before viewing the details.", "Error", JOptionPane.ERROR_MESSAGE); 
                }
                else{
                    while(itr.hasNext()){
                        BankCard bankCard = (BankCard)itr.next(); 
                        if(bankCard instanceof DebitCard){
                            DebitCard debitCard = (DebitCard)bankCard; //Downcasting to access display() method of debit card. 
                            if(debitCard.getCardId() == cardId){
                                debitCard.display(); 
                                isMatch = true;
                                
                                JOptionPane.showMessageDialog(mainFrame, "The details of the debit card were displayed successfully.", "Information", JOptionPane.INFORMATION_MESSAGE); 
                            }
                            else{
                                isMatch = false; 
                            }
                        }
                    }
                    if(isMatch == false){
                        JOptionPane.showMessageDialog(mainFrame, "There is no debit card of the entered card Id. Please enter a correct card id.", "Error", JOptionPane.ERROR_MESSAGE); 
                    }
                }
            }
            catch(NumberFormatException exception1){
                JOptionPane.showMessageDialog(mainFrame, "You have not entered a correct value. Please enter a correct value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == displayCredit){
            try{
                cardId = Integer.parseInt(cardIdField.getText());
                isMatch = false; 
                Iterator itr = arrayList.iterator(); 

                if(arrayList.isEmpty()){
                    JOptionPane.showMessageDialog(mainFrame, "There are no entries in the database." + "\n" + "Please add a credit card to the database before viewing the details.", "Error", JOptionPane.ERROR_MESSAGE); 
                }
                else{
                    while(itr.hasNext()){
                        BankCard bankCard = (BankCard)itr.next(); 
                        if(bankCard instanceof CreditCard){
                            CreditCard creditCard = (CreditCard)bankCard;    //Upcasting to access display() method of credit card. 
                            if(creditCard.getCardId() == cardId){ 
                                creditCard.display(); 
                                isMatch = true; 
                                
                                JOptionPane.showMessageDialog(mainFrame, "The details of the credit card were displayed successfully.", "Error", JOptionPane.ERROR_MESSAGE); 
                            }
                            else{
                                isMatch = false; 
                            }
                        }
                    }
                    if(isMatch == false){
                        JOptionPane.showMessageDialog(mainFrame, "There is no credit card of the entered card Id. Please enter a correct card id.", "Error", JOptionPane.ERROR_MESSAGE); 
                    }
                }
            }
            catch(NumberFormatException exception1){
                JOptionPane.showMessageDialog(mainFrame, "You have not entered a correct value. Please enter a correct value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == rightPanel1ClearButton){
            clientNameField.setText(""); // Setting the text field to an empty string clears out the field. 
            cardIdField.setText(""); 
            bankAccountField.setText(""); 
            issuerBankField.setText(""); 
            balanceAmountField.setText(""); 
        }
        else if(e.getSource() == clearDebit){
            pinNumberField.setText(""); 
            withdrawalAmountField.setText(""); 
        }
        else if(e.getSource() == clearCredit1){
            cvcNumberField.setText(""); 
            interestRateField.setText(""); 
        }
        else if(e.getSource() == clearCredit2){
            creditLimitField.setText(""); 
            gracePeriodField.setText("");
        }
        else if(e.getSource() == withdrawalAmountField){  
            try{
                /*
                 * When the user hits enter in withdrawal amount field and if valid card id and pin number have already been
                 * entered, a dialog box showing the valid values typed in text fields is displayed. 
                 */

                rightPanel2Year = rightPanel2YearComboBox.getSelectedItem().toString(); 
                rightPanel2Month = rightPanel2MonthComboBox.getSelectedItem().toString();
                rightPanel2Day = rightPanel2DayComboBox.getSelectedItem().toString(); 
                String dateOfWithdrawal = rightPanel2Year + "-" + rightPanel2Month + "-" + rightPanel2Day;

                
                
                
                
                
                
                
                
                withdrawalAmount = Integer.parseInt(withdrawalAmountField.getText());  
                cardId = Integer.parseInt(cardIdField.getText());
                Iterator itr = arrayList.iterator(); 

                if(arrayList.isEmpty()){
                }
                else{
                    while(itr.hasNext()){
                        BankCard bankCard = (BankCard)itr.next(); 
                        if(bankCard instanceof DebitCard && bankCard.getCardId() == cardId){
                            if(bankCard.getBalanceAmount() >= withdrawalAmount){
                                validWithdrawalAmount = true;
                                JOptionPane.showMessageDialog(mainFrame, "A valid withdrawal amount was entered.", "Information", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                            else{
                                validWithdrawalAmount = false; 
                            }
                        }
                    }
                }

                if (validCardIdDebit && validPinNumber && validWithdrawalAmount){
                    JOptionPane.showMessageDialog(mainFrame, "The values entered were correct." + "\n" + "\n" + "The card Id is " + cardIdField.getText() + "." + "\n" + "The withdrawal amount is " + withdrawalAmountField.getText() + "." + "\n" + "The date of withdrawal is " + dateOfWithdrawal + "." + "\n" + "The pin Number is " + pinNumberField.getText() + ".", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch(NumberFormatException exception1){
                JOptionPane.showMessageDialog(mainFrame, "A valid withdrawal amount was not entered.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getSource() == darkMode){
            /*
             * The color objects are passed to appropriate panels, buttons, fields and combo boxes to change their color to a darker tone.
             * This sets the theme of the gui to a darker one. 
             */

            Color darkModeColorLeftPanel = new Color(54, 21, 0);
            Color darkModeColorRightPanel = new Color(83, 55, 16); 
            Color darkModeColorComboBox = new Color(160, 120, 50); 

            leftPanel.setBackground(darkModeColorLeftPanel);
            detailsButton.setBackground(darkModeColorLeftPanel);
            debitCardButton.setBackground(darkModeColorLeftPanel);
            creditCardButton.setBackground(darkModeColorLeftPanel);
            settingButton.setBackground(darkModeColorLeftPanel);

            rightPanel1.setBackground(darkModeColorRightPanel);
            clientNameField.setBackground(darkModeColorRightPanel);
            cardIdField.setBackground(darkModeColorRightPanel);
            bankAccountField.setBackground(darkModeColorRightPanel);
            issuerBankField.setBackground(darkModeColorRightPanel);
            balanceAmountField.setBackground(darkModeColorRightPanel);
            rightPanel1ClearButton.setBackground(darkModeColorRightPanel);

            rightPanel2.setBackground(darkModeColorRightPanel);

            pinNumberField.setBackground(darkModeColorRightPanel);
            withdrawalAmountField.setBackground(darkModeColorRightPanel);
            rightPanel2YearComboBox.setBackground(darkModeColorComboBox);
            rightPanel2MonthComboBox.setBackground(darkModeColorComboBox);
            rightPanel2DayComboBox.setBackground(darkModeColorComboBox);

            addDebit.setBackground(darkModeColorRightPanel);
            clearDebit.setBackground(darkModeColorRightPanel);
            withdraw.setBackground(darkModeColorRightPanel);
            displayDebit.setBackground(darkModeColorRightPanel);

            rightPanel3.setBackground(darkModeColorRightPanel);
            cvcNumberField.setBackground(darkModeColorRightPanel);
            interestRateField.setBackground(darkModeColorRightPanel);
            rightPanel3YearComboBox.setBackground(darkModeColorComboBox);
            rightPanel3MonthComboBox.setBackground(darkModeColorComboBox);
            rightPanel3DayComboBox.setBackground(darkModeColorComboBox);

            creditLimitField.setBackground(darkModeColorRightPanel);
            gracePeriodField.setBackground(darkModeColorRightPanel);

            addCredit.setBackground(darkModeColorRightPanel);
            clearCredit1.setBackground(darkModeColorRightPanel);
            clearCredit2.setBackground(darkModeColorRightPanel);
            cancelCredit.setBackground(darkModeColorRightPanel);
            displayCredit.setBackground(darkModeColorRightPanel);
            setCreditLimit.setBackground(darkModeColorRightPanel);

            rightPanel4.setBackground(darkModeColorRightPanel);
            darkMode.setBackground(darkModeColorRightPanel);
            lightMode.setBackground(darkModeColorRightPanel);
        }
        else if(e.getSource() == lightMode){
            /*
             * The color objects are passed to appropriate panels, buttons, fields and combo boxes to change their color to a lighter tone.
             * This sets the theme of the gui to a lighter one. 
             */

            Color lightModeColorLeftPanel = new Color(118, 160, 30);
            Color lightModeColorRightPanel = new Color(165, 85, 64);
            Color lightModeColorComboBox = new Color(255, 109, 70); 

            leftPanel.setBackground(lightModeColorLeftPanel);
            detailsButton.setBackground(lightModeColorLeftPanel);
            debitCardButton.setBackground(lightModeColorLeftPanel);
            creditCardButton.setBackground(lightModeColorLeftPanel);
            settingButton.setBackground(lightModeColorLeftPanel);

            rightPanel1.setBackground(lightModeColorRightPanel);
            clientNameField.setBackground(lightModeColorRightPanel);
            cardIdField.setBackground(lightModeColorRightPanel);
            bankAccountField.setBackground(lightModeColorRightPanel);
            issuerBankField.setBackground(lightModeColorRightPanel);
            balanceAmountField.setBackground(lightModeColorRightPanel);
            rightPanel1ClearButton.setBackground(lightModeColorRightPanel);

            rightPanel2.setBackground(lightModeColorRightPanel);
            pinNumberField.setBackground(lightModeColorRightPanel);
            withdrawalAmountField.setBackground(lightModeColorRightPanel);
            rightPanel2YearComboBox.setBackground(lightModeColorComboBox);
            rightPanel2MonthComboBox.setBackground(lightModeColorComboBox);
            rightPanel2DayComboBox.setBackground(lightModeColorComboBox);

            addDebit.setBackground(lightModeColorRightPanel);
            clearDebit.setBackground(lightModeColorRightPanel);
            withdraw.setBackground(lightModeColorRightPanel);
            displayDebit.setBackground(lightModeColorRightPanel);

            rightPanel3.setBackground(lightModeColorRightPanel);
            cvcNumberField.setBackground(lightModeColorRightPanel);
            interestRateField.setBackground(lightModeColorRightPanel);
            rightPanel3YearComboBox.setBackground(lightModeColorComboBox);
            rightPanel3MonthComboBox.setBackground(lightModeColorComboBox);
            rightPanel3DayComboBox.setBackground(lightModeColorComboBox);

            creditLimitField.setBackground(lightModeColorRightPanel);
            gracePeriodField.setBackground(lightModeColorRightPanel);

            addCredit.setBackground(lightModeColorRightPanel);
            clearCredit1.setBackground(lightModeColorRightPanel);
            clearCredit2.setBackground(lightModeColorRightPanel);
            cancelCredit.setBackground(lightModeColorRightPanel);
            displayCredit.setBackground(lightModeColorRightPanel);
            setCreditLimit.setBackground(lightModeColorRightPanel);

            rightPanel4.setBackground(lightModeColorRightPanel);
            darkMode.setBackground(lightModeColorRightPanel);
            lightMode.setBackground(lightModeColorRightPanel);
        }

    }

    @Override
    public void keyPressed(KeyEvent e){
    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyReleased(KeyEvent e){
        /*
         * Each time a key is released in the cardIdField, withdrawal amount field, or pin number field, an iteration takes place
         * to check if the value typed after the release of the key matches with the value of the object's attribute. 
         * For example, if a valid card id, pin number of that card and withdrawal amount which is less than or equal to balance 
         * amount of that card id has been entered, a dialog box displaying values all the valid values is displayed. Before, 
         * that individual dialog boxes are displayed for each text field after a user inputs values in them. 
         *
         * However, things are a bit different for withdrawal amount field. If the balance amount is 10000 and the user attempts
         * to withdraw, for example, 1000. A dialog box would be displayed after each keystroke. A total of four dialog boxes  
         * would be displayed because the vales '1', '10', '100' and '1000' are all valid values for withdrawal amount for this 
         * case. Therefore, for withdrawal amount, only validity is checked after each keystroke and the dialog box is not displyed.
         * The user may display both the individual dialog box and the final dialog box after hitting the enter key and after 
         * having typed the withdrawal amount. 
         */

        if (e.getSource() == cardIdField){
            try{
                rightPanel2Year = rightPanel2YearComboBox.getSelectedItem().toString(); 
                rightPanel2Month = rightPanel2MonthComboBox.getSelectedItem().toString();
                rightPanel2Day = rightPanel2DayComboBox.getSelectedItem().toString(); 
                String dateOfWithdrawal = rightPanel2Year + "-" + rightPanel2Month + "-" + rightPanel2Day;
                int cardId = Integer.parseInt(cardIdField.getText());

                Iterator itr = arrayList.iterator(); 
                Iterator itr1 = arrayList.iterator();
                
                /*
                 * Two iterators were used to iterate through the list. The same iterator cannot be used to run two while. 
                 */
                
                if(arrayList.isEmpty()){
                }
                else{
                    while(itr.hasNext()){
                        BankCard bankCard = (BankCard)itr.next();

                        /*
                         * Need to check for card id before checking instance and after checking instance so that two objects 
                         * of different type but same card id are displayed one after the other. 
                         */
                        
                        if(bankCard instanceof DebitCard){
                            if(bankCard.getCardId() == cardId){
                                validCardIdDebit = true;  
                                JOptionPane.showMessageDialog(mainFrame, "A valid card Id for debit card was entered.", "Information", JOptionPane.INFORMATION_MESSAGE);

                                break;
                            }
                            else{
                                validCardIdDebit = false; 
                            }
                        }
                    } 

                    if (validCardIdDebit && validPinNumber && validWithdrawalAmount){
                        JOptionPane.showMessageDialog(mainFrame,  "The values entered were correct." + "\n" + "\n" + "The card Id is " + cardIdField.getText() + "." + "\n" + "The withdrawal amount is " + withdrawalAmountField.getText() + "." + "\n" + "The date of withdrawal is " + dateOfWithdrawal + "." + "\n" + "The pin Number is " + pinNumberField.getText() + ".", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }

                    while(itr1.hasNext()){
                        BankCard bankCard = (BankCard)itr1.next(); 
                        if (bankCard instanceof CreditCard){
                            if(bankCard.getCardId() == cardId){ 
                                validCardIdCredit = true;
                                JOptionPane.showMessageDialog(mainFrame, "A valid card Id for credit card was entered.", "Information", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                            else{ 
                                validCardIdCredit = false; 
                            }
                        }
                    } 
                    
                    if(validCardIdCredit){
                        double creditLimit = Double.parseDouble(creditLimitField.getText()); 
                        int gracePeriod = Integer.parseInt(gracePeriodField.getText());

                        JOptionPane.showMessageDialog(mainFrame, "The values entered are -" + "\n" + "\n" + "The credit limit is " + creditLimit + "." + "\n" + "The grace period is " + gracePeriod + ".");
                    }

                }
            }
            catch(NumberFormatException exception){
            }
        }
        
        
        else if(e.getSource() == withdrawalAmountField){  
            try{
                                                        
                // To check if correct withdrawal amount was entered after each keystroke.
                
                rightPanel2Year = rightPanel2YearComboBox.getSelectedItem().toString(); 
                rightPanel2Month = rightPanel2MonthComboBox.getSelectedItem().toString();
                rightPanel2Day = rightPanel2DayComboBox.getSelectedItem().toString(); 
                String dateOfWithdrawal = rightPanel2Year + "-" + rightPanel2Month + "-" + rightPanel2Day;

                int withdrawalAmount = Integer.parseInt(withdrawalAmountField.getText());  
                int cardId = Integer.parseInt(cardIdField.getText());
                Iterator itr = arrayList.iterator(); 

                if(arrayList.isEmpty()){                            
                }
                else{
                    while(itr.hasNext()){
                        BankCard bankCard = (BankCard)itr.next(); 
                        if(bankCard instanceof DebitCard && bankCard.getCardId() == cardId){
                            if(bankCard.getBalanceAmount() >= withdrawalAmount){
                                validWithdrawalAmount = true;
                                break;
                            }
                            else{
                                validWithdrawalAmount = false; 
                            }
                        }
                    }
                }
            }
            catch(NumberFormatException exception){
            }
        }
        
        else if(e.getSource() == pinNumberField){
            try{
                
                // To check if correct pin number was entered after each keystroke.
                
                rightPanel2Year = rightPanel2YearComboBox.getSelectedItem().toString(); 
                rightPanel2Month = rightPanel2MonthComboBox.getSelectedItem().toString();
                rightPanel2Day = rightPanel2DayComboBox.getSelectedItem().toString(); 
                String dateOfWithdrawal = rightPanel2Year + "-" + rightPanel2Month + "-" + rightPanel2Day;

                int cardId = Integer.parseInt(cardIdField.getText());
                int pinNumber = Integer.parseInt(pinNumberField.getText());  
                Iterator itr = arrayList.iterator(); 
                
                if(arrayList.isEmpty()){
                }
                else{
                    while(itr.hasNext()){
                        BankCard bankCard = (BankCard)itr.next();

                        if(bankCard instanceof DebitCard){
                            if(bankCard.getCardId() == cardId){
                                DebitCard debitCard = (DebitCard)bankCard; 
                                if(debitCard.getPinNumber() == pinNumber){
                                    validPinNumber = true;
                                    JOptionPane.showMessageDialog(mainFrame, "A valid pin number was entered.", "Information", JOptionPane.INFORMATION_MESSAGE);
                                    break;
                                }
                                else{
                                    validPinNumber = false; 
                                }
                            }
                        }
                    }
                }
                if (validCardIdDebit && validPinNumber && validWithdrawalAmount){
                    JOptionPane.showMessageDialog(mainFrame, "The values entered were correct." + "\n" + "\n" + "The card Id is " + cardIdField.getText() + "." + "\n" + "The withdrawal amount is " + withdrawalAmountField.getText() + "." + "\n" + "The date of withdrawal is " + dateOfWithdrawal + "." + "\n" + "The pin Number is " + pinNumberField.getText() + ".", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch(NumberFormatException exception){
            }
        }
    }

    public static void main(String[] args) {
        
        /*
         * An anonymous object of main method is made and called. 
         */
        
        new BankGUI(); 
    }

}