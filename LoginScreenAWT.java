package Assignment8;


import java.awt.*;
import java.awt.event.*;

class LoginException extends Exception {
    public LoginException(String message) {
        super(message);
    }
}

public class LoginScreenAWT extends Frame implements ActionListener {
    Label userLabel, passLabel, messageLabel;
    TextField userText, passText;
    Button loginButton, clearButton;
    int attempts = 0;
    final int MAX_ATTEMPTS = 3;

    LoginScreenAWT() {
        
        setTitle("Login Screen");
        setSize(400, 300);
        setLayout(null);
        setVisible(true);

        
        userLabel = new Label("Username:");
        passLabel = new Label("Password:");
        messageLabel = new Label("");

        userText = new TextField();
        passText = new TextField();
        passText.setEchoChar('*');

        loginButton = new Button("Login");
        clearButton = new Button("Clear");

        
        userLabel.setBounds(50, 50, 80, 30);
        passLabel.setBounds(50, 100, 80, 30);
        userText.setBounds(150, 50, 150, 30);
        passText.setBounds(150, 100, 150, 30);
        loginButton.setBounds(80, 160, 80, 30);
        clearButton.setBounds(200, 160, 80, 30);
        messageLabel.setBounds(50, 210, 300, 30);

        
        add(userLabel);
        add(passLabel);
        add(userText);
        add(passText);
        add(loginButton);
        add(clearButton);
        add(messageLabel);

        
        loginButton.addActionListener(this);
        clearButton.addActionListener(this);

        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            try {
                validateLogin(userText.getText(), passText.getText());
                messageLabel.setText("Login Successful!");
            } catch (LoginException ex) {
                attempts++;
                messageLabel.setText(ex.getMessage() + " Attempt " + attempts + "/" + MAX_ATTEMPTS);

                if (attempts >= MAX_ATTEMPTS) {
                    messageLabel.setText("Maximum login attempts exceeded.");
                    loginButton.setEnabled(false);
                }
            }
        } else if (e.getSource() == clearButton) {
            userText.setText("");
            passText.setText("");
            messageLabel.setText("");
        }
    }

    void validateLogin(String username, String password) throws LoginException {
        if (!username.equals(password)) {
            throw new LoginException("Username and Password do not match!");
        }
    }

    public static void main(String[] args) {
        new LoginScreenAWT();
    }
}
