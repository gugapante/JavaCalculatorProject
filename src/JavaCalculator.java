import javax.swing.*; // package provides lightweight components to build GUIs
import java.awt.*; // package contains classes for creating GUIs and handling graphics
import java.awt.event.*; // package contains classes and interfaces to handle events such as mouse clicks

public class JavaCalculator implements ActionListener{ // calculator class is implementing the ActionListener interface

    // before the constructor, let's declare a few things that we'll need
    JFrame frame;
    JTextField textField;
    // the JButton is an array of all the buttons for numbers 0-9, the argument is how many you want
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    // let's create all the function buttons:
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    // let's create a JPanel to hold all the separate buttons
    JPanel panel;

    // let's declare a font that we can use:
    Font myFont = new Font("Monospaced",Font.BOLD,25);

    double num1=0,num2=0,result=0;
    char operator;

    JavaCalculator(){ // class constructor
        frame = new JFrame("Calculator");
        // this line allows us to close out of the program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this line sets the size of the app window
        frame.setSize(420,550);
        // disables the layout manager for JFrame allowing you to manually position and size each component
        // using the setBounds line below
        frame.setLayout(null);

        // the below lines are to do with the output text field
        textField = new JTextField();
        textField.setBounds(50,25,300,50);
        textField.setFont(myFont);
        textField.setEditable(false);

        // let's work on adding our buttons next
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("x");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");

        // so we made an array for the buttons, and then we can set the position of each
        // button in this array as follows:
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        // let's say that if we want to add an ActionListener, change the font and do a few other things to each
        // of these buttons... that's a lot of work to go one by one. We can actually do this with a for loop:
        for(int i=0;i<9;i++){
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        // we'll do the same for the number buttons but in less steps as they are anonymous:
        for(int i=0;i<10;i++){
            // we also need to finish instantiating the numberButton, the line String.value(i) converts the integer
            // (which represents a number from 0 to 9) into it's string representation
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        // the delete and clear button won't be on our JPanel that has a grid layout so we'll make it separately
        negButton.setBounds(50,430,100,50);
        delButton.setBounds(150,430,100,50);
        clrButton.setBounds(250,430,100,50);

        // we need to finish instantiating the "panel" variable
        panel = new JPanel();
        panel.setBounds(50,100,300,300);
        // this allows us to make a gird layout and lets us specify how many rows and columns we can have,
        // and it can also let us decide how much space we want between the buttons
        panel.setLayout(new GridLayout(4,4,10,10));
        panel.setBackground(Color.GRAY);

        // now we add in the number buttons in order of how we want them to appear
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);

        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        // let's create an instance of this class in the main method
        JavaCalculator calc= new JavaCalculator();
    }
    // any class that implements action listener must provide an implementation of
    // the actionPerformed method...
    public void actionPerformed(ActionEvent e){
        // now we need to put some functionality when the user presses on the numbered buttons
        for(int i=0;i<10;i++){
            if(e.getSource() == numberButtons[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        // now let's add functionality for the decimal button
        if(e.getSource() == decButton){
            textField.setText(textField.getText().concat("."));
        }
        // let's add functionality to the add button
        if(e.getSource() == addButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        // let's add functionality to the subtract button
        if(e.getSource() == subButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        // let's add functionality to the multiply button
        if(e.getSource() == mulButton){
            num1 = Double.parseDouble(textField.getText());
            operator = 'x';
            textField.setText("");
        }
        // let's add functionality to the divide button
        if(e.getSource() == divButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        // let's add some functionality to the equals button
        if(e.getSource() == equButton){
            num2 = Double.parseDouble(textField.getText());
            // then we are going to use a switch statement to determine what mathematical operator to use
            switch(operator){
                case'+':
                    result = num1 + num2;
                    break;
                case'-':
                    result = num1 - num2;
                    break;
                case'x':
                    result = num1 * num2;
                    break;
                case'/':
                    result = num1 / num2;
                    break;
            }
            // then we need to update the text field
            textField.setText(String.valueOf(result));
            // then we set num1 as the value of our result to continue using the number we made if we want to
            num1 = result;
        }
        // the clear button is pretty simple to code but the delete button requires some extra work
        if(e.getSource() == clrButton){
            textField.setText("");
        }
        if(e.getSource() == delButton){
            String string = textField.getText();
            textField.setText("");
            for(int i=0;i<string.length()-1;i++){
                textField.setText(textField.getText()+string.charAt(i));
            }
        }
        if(e.getSource() == negButton){
            // so it's going to take whatever value is in textField and assign it to the temp variable
            double temp = Double.parseDouble(textField.getText());
            // we then need to flip the sign on our value and the easiest way to do this is by multiplying by -1
            temp*=-1;
            textField.setText(String.valueOf(temp));
        }
    }
}