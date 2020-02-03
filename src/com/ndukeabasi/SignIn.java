package com.ndukeabasi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//driver code
public class SignIn{
    user nU= new user();
}
class user extends JFrame{
    // components on form
    private Container signIn_main;
    private JLabel title;
    private JLabel user_name;
    private JLabel user_pass;
    private JTextField username_text;
    private JPasswordField username_pass;
    private JCheckBox logged_in;
    private JButton sign_in;
    private JButton sign_inReset;
    private JButton forgot_pass;

    //open connection to databse
    Dbonnect connect = new Dbonnect();
    Connection con = new Dbonnect().connection();
    ResultSet resultSet;



    //constructor to initialize components with default values
    public user() {

        //frame styling
        setTitle("Sign In");
        setBounds(300,90,900,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400,500);
        setLocationRelativeTo(null);
        setResizable(false);

        signIn_main = getContentPane();
        signIn_main.setLayout(null);

        title = new JLabel("Sign In form");
        title.setFont(new Font("Arial",Font.PLAIN,30));
        title.setSize(300,40);
        title.setLocation(100,40);
        signIn_main.add(title);

        user_name = new JLabel("Username");
        user_name.setFont(new Font("Arial",Font.PLAIN,20));
        user_name.setSize(100,20);
        user_name.setLocation(100,100);
        signIn_main.add(user_name);

        username_text = new JTextField(25);
        username_text.setFont(new Font("Arial",Font.PLAIN,15));
        username_text.setSize(100,20);
        username_text.setLocation(200,100);
        signIn_main.add(username_text);

        user_pass = new JLabel("Password");
        user_pass.setFont(new Font("Arial",Font.PLAIN,20));
        user_pass.setSize(100,20);
        user_pass.setLocation(100,150);
        signIn_main.add(user_pass);

        username_pass = new JPasswordField(25);
        username_pass.setFont(new Font("Arial",Font.PLAIN,15));
        username_pass.setSize(100,20);
        username_pass.setLocation(200,150);
        signIn_main.add(username_pass);

        logged_in = new JCheckBox("Keep me logged in");
        logged_in.setFont(new Font("Arial",Font.PLAIN,10));
        logged_in .setSize(150,20);
        logged_in .setLocation(100,200);
        signIn_main.add(logged_in);

        sign_in = new JButton("Sign In");
        sign_in.setFont(new Font("Arial",Font.PLAIN,20));
        sign_in.setSize(100,20);
        sign_in.setLocation(100,250);
        sign_in.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get text from username&password
                String username =  username_text.getText();
                String password = String.valueOf(username_pass.getPassword());

                //check if login details are in db
                String query = "SELECT * FROM `users` WHERE `uname` =? AND`password` =?";

                try {
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1,username);
                    pst.setString(2,password);
                    resultSet = pst.executeQuery();

                    if(resultSet.next()){
                        HomePage hm = new HomePage();
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "login details incorrect");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        signIn_main.add(sign_in);

        sign_inReset = new JButton("Sign Up");
        sign_inReset.setFont(new Font("Arial",Font.PLAIN,20));
        sign_inReset.setSize(100,20);
        sign_inReset.setLocation(220,250);
        sign_inReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp su = new SignUp();
                dispose();
            }
        });
        signIn_main.add(sign_inReset);

    }


}
