import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Frame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private List<User> userList;

    public Frame(List<User> userList) {
        this.userList = userList;
        setTitle("Login/Register");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        usernameField = new JTextField();
        usernameField.setBounds(50, 30, 300, 30);
        add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(50, 70, 300, 30);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(50, 110, 100, 30);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                User user = authenticateUser(username, password);
                if (user != null) {
                    if (user instanceof Admin) {
                        // Admin login
                        showAdminDashboard();
                    } else {
                        // User login
                        showUserDashboard((User) user);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                }
            }
        });
        add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(200, 110, 100, 30);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both username and password.");
                } else if (isUsernameTaken(username)) {
                    JOptionPane.showMessageDialog(null, "Username already exists.");
                } else {
                    // Register new user
                    User newUser = new User(username, password);
                    userList.add(newUser);
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                }
            }
        });
        add(registerButton);

        setVisible(true);
    }

    private User authenticateUser(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private boolean isUsernameTaken(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private void showAdminDashboard() {
        // Display admin dashboard
        // Add functionality for admin operations on General Database
    }

    private void showUserDashboard(User user) {
        // Display user dashboard
        // Add functionality for user operations on Personal Database
    }
}
