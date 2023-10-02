package dxc.interview;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginPage {

    private JFrame frame;
    private JPanel panel;
    private JTextField userText;
    private JTextField pwText;
    private JLabel label;

    public static void main(String[] args) {
        new LoginPage();
    }

    public LoginPage() {
        //frame
        frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        //userid textfield
        JPanel userPanel = new JPanel();
        JLabel userLabel = new JLabel("User ID: ");
        userText = new JTextField(15);
        userPanel.add(userLabel);
        userPanel.add(userText);

        //pw textfield
        JPanel pwPanel = new JPanel();
        JLabel pwLabel = new JLabel("Password:");
        pwText = new JTextField(15);
        pwPanel.add(pwLabel);
        pwPanel.add(pwText);

        //Login button
        JButton loginB = new JButton("Login");
        loginB.addActionListener(new LoginButtonAction());

        //GUI layout
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(BorderLayout.NORTH, userPanel);
        panel.add(BorderLayout.CENTER, pwPanel);
        panel.add(BorderLayout.SOUTH, loginB);
        label = new JLabel("           ");
        panel.add(BorderLayout.SOUTH, label);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.getContentPane().add(BorderLayout.CENTER, panel);

        frame.setVisible(true);
    }

    public class LoginButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton logout = new JButton("Log Out");
            logout.addActionListener(new LogoutButton());
            String user = userText.getText();
            String pw = pwText.getText();
            String[] result=null; 
            try {
                result = checkUser(user, pw);
            } catch (FileNotFoundException ex) {
                label.setText("Error");
            }
            
            if (result != null) {
                panel.removeAll();
                frame.setSize(300, 300);
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(new JLabel("Welcome!  "+result[3]));
                panel.add(new JLabel(result[2]));
                panel.add(logout);
                if (result[2].equals("manager")){
                    panel.add(new JLabel("*Insert restricted webpage here*"));
                }
            } else if (result == null) {
                label.setText("Login Unsuccessful");

            }
        }
    }

    public class LogoutButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new LoginPage();
        }
    }

    private static String[] checkUser(String user, String pw) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("DXCTest.csv"));
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] userData = s.split(",");
            if (user.equalsIgnoreCase(userData[0]) && pw.equals(userData[1])) {
                return userData;
            }
        }
        return null;
    }

}
