package LoanAssistant;
/************ :: SUVAN  INTERNSHIP  BY JAVA Project  1  submitted By JAYANT NISHAD :: ************/
/*** $~+_**::**_+$ Internship Project First  1nd Loan Assistant  $~+_**::**_+$ ***/
import javax.swing.*;
import java.awt.*;
import  java.awt.event.*;
import java.text.*;

public class Loan_Assistant  extends JFrame {
    /**************** JLabels ***************************/

    JLabel balanceLabel = new JLabel();
    JTextField balanceTextField = new JTextField();

    JLabel interestLabel = new JLabel();
    JTextField interestTextField = new JTextField();

    JLabel monthsLabel = new JLabel();
    JTextField monthsTextField = new JTextField();

    JLabel paymentLabel = new JLabel();
    JTextField paymentTextField = new JTextField();

    /**** Add the Button to Frame using ********/
    JButton computeButton = new JButton();
    JButton newLoanButton = new JButton();
    JButton monthButton = new JButton();
    JButton paymentButton = new JButton();

    /************** Analysis selection create ************************/
    JLabel analysisLabel = new JLabel();
    JTextArea analysisTextArea = new JTextArea();
    JButton exitButton = new JButton();

    /*************** Global variable *****************/
    double balance, interest, monthlyInterest, multiplier, payment;/*Code  by Design in _ computing Monthly Payment */
    int month, paymentNumber/*loan Anaylsis*/;/*               |_- >*/
    boolean computePayment;//empty line recover in if at else
    //code Design Loan Analysis
    double loanBalance, finalPayment;

    /*************** Font and Colour Size Define *************************************/
    Font myFont = new Font("Arial", Font.PLAIN, 16);

    Color lightYellow = new Color(255, 255, 128);

    /******************** main Driver  ***************************/
    public static void main(String[] args) {
        //create Frame
        new Loan_Assistant().show();/****** calling method **********/
        //Loan_Assistant s=new Loan_Assistant();
        // s.getResponse();
        //Code Confirm Dialog

    }

    /******************* method *****************************/
    public Loan_Assistant()//method constructor
    {
        //frame constructor
        setTitle("Loan Assistant");
        setSize(300, 300);

        setLayout(null);
        setVisible(true);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            // @Override
            public void windowClosing(WindowEvent e) {
                exitForm(e);
            }
        });

        /**************  construct   setLayout     ****************/
        getContentPane().setLayout(new GridBagLayout());//pane ,Layout , bag
        ///above to two button add so at that time
        GridBagConstraints gridConstraints;//GridBagConstraints gridConstraints;//
        /*************  construct  setText       ***************/
        balanceLabel.setText("Loan Balance");
        balanceLabel.setFont(myFont);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.WEST;
        gridConstraints.insets = new Insets(10, 10, 0, 0);
        getContentPane().add(balanceLabel, gridConstraints);

        balanceTextField.setPreferredSize(new Dimension(100, 25));

        balanceTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        balanceTextField.setFont(myFont);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        getContentPane().add(balanceTextField, gridConstraints);

        balanceTextField.addActionListener(new ActionListener() {/*Design in Focus Transfer*/
            @Override
            public void actionPerformed(ActionEvent e) {
                balanceTextFieldActionPerformed(e);
            }
        });


        interestLabel.setText("Interest Rate");
        interestLabel.setFont(myFont);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.anchor = GridBagConstraints.WEST;
        gridConstraints.insets = new Insets(10, 10, 0, 0);
        getContentPane().add(interestLabel, gridConstraints);

        interestTextField.setPreferredSize(new Dimension(100, 25));
        interestTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        interestTextField.setFont(myFont);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        getContentPane().add(interestTextField, gridConstraints);
        interestTextField.addActionListener(new ActionListener() {/*Design in Focus Transfer*/
            @Override
            public void actionPerformed(ActionEvent e) {
                interestTextFieldActionPerformed(e);
            }
        });
        monthsLabel.setText("Number of Payments");
        monthsLabel.setFont(myFont);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        gridConstraints.anchor = GridBagConstraints.WEST;
        gridConstraints.insets = new Insets(10, 10, 0, 0);
        getContentPane().add(monthsLabel, gridConstraints);

        monthsTextField.setPreferredSize(new Dimension(100, 25));
        monthsTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        monthsTextField.setFont(myFont);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 2;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        getContentPane().add(monthsTextField, gridConstraints);

        monthsTextField.addActionListener(new ActionListener() {/*Design in Focus Transfer*/
            @Override
            public void actionPerformed(ActionEvent e) {
                monthsTextFieldActionPerformed(e);
            }

        });


        paymentLabel.setText("Monthly Payment");
        paymentLabel.setFont(myFont);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        gridConstraints.anchor = GridBagConstraints.WEST;
        gridConstraints.insets = new Insets(10, 10, 0, 0);
        getContentPane().add(paymentLabel, gridConstraints);

        paymentTextField.setPreferredSize(new Dimension(100, 25));
        paymentTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        paymentTextField.setFont(myFont);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 3;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        getContentPane().add(paymentTextField, gridConstraints);
        paymentTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentTextFieldActionPerformed(e);
            }
        });

        //Details:- 1 frame
        computeButton.setText("Compute Monthly Payment");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;/*first computeted at the montly payment*/
        gridConstraints.gridy = 4;
        gridConstraints.gridwidth = 2;
        gridConstraints.insets = new Insets(10, 0, 0, 0);
        getContentPane().add(computeButton, gridConstraints);
        computeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computeButtonActionPerformed(e);
            }
        });
        newLoanButton.setText("New Loan Analysis");
        newLoanButton.setEnabled(false);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 5;
        gridConstraints.gridwidth = 2;
        gridConstraints.insets = new Insets(10, 0, 10, 0);
        getContentPane().add(newLoanButton, gridConstraints);
        newLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newLoanButtonActionPerformed(e);
            }
        });

        /**     Butttons   **/

        monthButton.setText("X");
        monthButton.setFocusable(false);//foucs traversal
        monthButton.setFocusable(false);//monthsButton.setFocusable(false);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 2;
        gridConstraints.insets = new Insets(10, 0, 0, 0);
        getContentPane().add(monthButton, gridConstraints);
        monthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monthButtonActionPerformed(e);
            }
        });

        //second button 2 Jframe
        paymentButton.setText("X");
        paymentButton.setFocusable(false);//focus traversal
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 3;
        gridConstraints.insets = new Insets(10, 0, 0, 0);
        getContentPane().add(paymentButton, gridConstraints);
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentButtonActionPerformed(e);
            }
        });

        //Both X button appear now .we write code , only one of the buttons will display at a time's
        //so finish the Frame by adding the three remaining control ( Label ,text field and Button).
        //properties are :
        /*Analysis label form of list poining*/
        //add control frame
        //by Analysis
        analysisLabel.setText("Loan Analysis:");
        analysisLabel.setFont(myFont);//<---Font on
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 3;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.WEST;//---->
        gridConstraints.insets = new Insets(0, 10, 0, 0);
        getContentPane().add(analysisLabel, gridConstraints);

        //set Dimension of analysis
        analysisTextArea.setPreferredSize(new Dimension(250, 150));
        analysisTextArea.setFocusable(false);//fource by traverssal

        analysisTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        analysisTextArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        analysisTextArea.setEditable(false);
        analysisTextArea.setBackground(Color.WHITE);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 3;
        gridConstraints.gridy = 1;
        gridConstraints.gridheight = 4;
        gridConstraints.insets = new Insets(0, 10, 0, 10);
        getContentPane().add(analysisTextArea, gridConstraints);

        //exit access fixed
        exitButton.setText("Exit");
        exitButton.setFocusable(false);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 3;
        gridConstraints.gridy = 5;
        getContentPane().add(exitButton, gridConstraints);
        //Focus  Transfer Code Design
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButtonActionPerformed(e);
            }
        });

        pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
        paymentButton.doClick();
    }

    /************ ActionEvent ... in Focus control ****************/

    private void balanceTextFieldActionPerformed(ActionEvent e) {
        balanceTextField.transferFocus();
    }

    private void interestTextFieldActionPerformed(ActionEvent e) {
        interestTextField.transferFocus();
    }

    private void monthsTextFieldActionPerformed(ActionEvent e) {
        monthsTextField.transferFocus();
    }

    private void paymentTextFieldActionPerformed(ActionEvent e) {
        paymentTextField.transferFocus();
    }
    /**************  Input  validateDecimalNumber      ************/
    private boolean validateDecimalNumber(JTextField textField) {/* Input validation*/
        String s = textField.getText().trim(); //Recovering point at Input Validation
        boolean hasDecimal = false;
        boolean valid = true;
        if (s.length() == 0) {
            valid = false;
        } else {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c >= '0' && c <= '9') {
                    continue;
                } else if (c == '.' && !hasDecimal) {
                    hasDecimal = true;
                } else {
                    //invalid character
                    valid = false;
                }
            }
        }
        textField.setText(s);
        if (!valid) {
            textField.requestFocus();
        }
        return (valid);
    }
    /************** code Design Computing Monthly  Payment ***********/
    private void computeButtonActionPerformed(ActionEvent e) {   /* code Design by Computing Monthly Payment  */
        if (validateDecimalNumber(balanceTextField)) {
            balance = Double.valueOf(balanceTextField.getText()).doubleValue();
        } else {
            /************message option *************/
            JOptionPane.showConfirmDialog(null, "Invalid or empty Loan Balance entry.\nPlease Correct.", "Balance Input Error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (validateDecimalNumber((interestTextField))) {
            interest = Double.valueOf(interestTextField.getText()).doubleValue();
        } else {
            JOptionPane.showConfirmDialog(null, "Invalid or empty  Interest Rate entry.\nPlease Correct.", "Interest Input Error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        monthlyInterest = interest / 1200;//by dividing 12 *100=1200

        //compute loan Payment
        if (computePayment) {
            if (validateDecimalNumber(monthsTextField)) {
                month = Integer.valueOf(monthsTextField.getText()).intValue();
            } else {
                JOptionPane.showConfirmDialog(null, "Invalid or empty  Number of Payments entry.\nPlease Correct.", "Number of Payments Input Error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (interest == 0)//zero Interest
            {
                payment = balance / month;
            } else {
                multiplier = Math.pow(1 + monthlyInterest, month);

                payment = balance * monthlyInterest * multiplier / (multiplier - 1);
            }
            paymentTextField.setText(new DecimalFormat("0.00").format(payment));
        } else {
            //compute number of payments
            if (validateDecimalNumber(paymentTextField)) {
                payment = Double.valueOf(paymentTextField.getText()).doubleValue();
                if (payment <= (balance * monthlyInterest + 1.0)) {
                    if (JOptionPane.showConfirmDialog(null, "Minimum payment must be $ " + new DecimalFormat("0.00").format((int) (balance * monthlyInterest + 1.0)) + "\n" + "Do want to use the minimum payment ?", "input Error", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_NO_OPTION) {
                        paymentTextField.setText(new DecimalFormat("0.00").format((int) balance * monthlyInterest + 1.0));
                        payment = Double.valueOf(paymentTextField.getText()).doubleValue();
                    } else {
                        paymentTextField.requestFocus();
                        return;
                    }
                }
            } else {
                JOptionPane.showConfirmDialog(null, "Invalid or empty  Number of Monthly Payment entry.\nPlease Correct.", "Payment Input Error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (interest == 0) {
                month = (int) (balance / payment);
            } else {
                month = (int) ((Math.log(payment) - Math.log(payment - balance * monthlyInterest)) / Math.log(1 + monthlyInterest));
            }
            monthsTextField.setText(String.valueOf(month));
        }

        //loan Analysis structure
        //reset payment prior to analysis to fix aat two decimals
        payment = Double.valueOf(paymentTextField.getText()).doubleValue();
        //show Analysis
        analysisTextArea.setText("Loan Balance $ " + new DecimalFormat("0.00").format(balance));
        analysisTextArea.append("\n" + "Interest Rate: " + new DecimalFormat("0.00").format(interest) + "%");

        //process all but last payment
        loanBalance = balance;
        for (paymentNumber = 1; paymentNumber <= month - 1; paymentNumber++) {
            loanBalance += loanBalance * monthlyInterest - payment;
        }
        //find final payment
        finalPayment = loanBalance;
        if (finalPayment > payment) {
            //apply one or more payment
            loanBalance += loanBalance * monthlyInterest - payment;
            finalPayment = loanBalance;
            month++;
            monthsTextField.setText(String.valueOf(month));
        }
        /***************** last Analysis at that time **************************/
        analysisTextArea.append("\n\n" + String.valueOf(month - 1) + " Payments of $ " + new DecimalFormat("0.00").format(payment));
        analysisTextArea.append("\n" + "Final Payment of: $" + new DecimalFormat("0.00").format(finalPayment));
        analysisTextArea.append("\n" + "Total Payment  :  $" + new DecimalFormat("0.00").format((month - 1) * payment + finalPayment));
        analysisTextArea.append("\n" + "Interest Paid  :  $" + new DecimalFormat("0.00").format((month - 1) * payment + finalPayment - balance));

        computeButton.setEnabled(false);
        newLoanButton.setEnabled(true);
        newLoanButton.requestFocus();


    }
    /** Analysis Button    **/
    private void newLoanButtonActionPerformed(ActionEvent e) { //clear computed value and analysis
        /*now New Loan Button Action */
        if (computePayment) {
            paymentTextField.setText("");
        } else {
            monthsTextField.setText("");
        }
        analysisTextArea.setText("");
        computeButton.setEnabled(true);
        newLoanButton.setEnabled(false);
        balanceTextField.requestFocus();
    }

    private void monthButtonActionPerformed(ActionEvent e) {/** Action in  button of month  so as soon as possible */

        computePayment = false;
        paymentButton.setVisible(true);

        monthButton.setVisible(false);
        monthsTextField.setText(" ");
        monthsTextField.setEditable(false);//foucs
        //monthsTextField.setEditable(false);//ch
        monthsTextField.setBackground(lightYellow);
        monthsTextField.setFocusable(false);

        paymentTextField.setEditable(true);//foucs
        paymentTextField.setBackground(Color.WHITE);
        paymentTextField.setFocusable(true);

        computeButton.setText("Compute Number of Payments");
        balanceTextField.requestFocus();
    }

    private void paymentButtonActionPerformed(ActionEvent e) { /* that is switch mode that is effest switching X to X*/
        computePayment = true;//edit on fix.................***********
        paymentButton.setVisible(false);

        monthButton.setVisible(true);//foucs
        monthsTextField.setEditable(true);
        monthsTextField.setBackground(Color.WHITE);
        monthsTextField.setFocusable(true);

        paymentTextField.setText(" ");
        paymentTextField.setEditable(false);//foucs
        paymentTextField.setBackground(lightYellow);
        paymentTextField.setFocusable(false);

        computeButton.setText("Compute Monthly Payment");
        balanceTextField.requestFocus();//foucs traversal
    }

    private void exitButtonActionPerformed(ActionEvent e) {
        /* Exit button that is super*/
        int response;
        response=JOptionPane.showConfirmDialog(null, "This is an Loan_Assistant Program Cancel say Yes Or No Button\nAnd Please choice Option any One.","Loan_Assistant",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
        if (response==JOptionPane.YES_NO_OPTION)
        {
            //pressed yes
            System.exit(0);

        }
        else if(response==JOptionPane.NO_OPTION)
        {
            //pressed No
            // System.exit(1);
            response=JOptionPane.showConfirmDialog(null, "let's go to Program","~Hello sir~!!",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
        }
    }
    private void exitForm(WindowEvent e) {
        System.exit(0);
    }

}