package com.ndukeabasi;

import javax.swing.*;
import java.awt.*;

//driver code
public class Home {

}

class HomePage extends JFrame{

    private JLabel welcome;
    private JButton profile;
    private JButton registered_users;
    private JButton sign_out;

    public HomePage(){
        //frame styling
        setTitle("Sign Up");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new FlowLayout());
        setSize(400,400);
        //setResizable(false);

        welcome = new JLabel("Welcome");

        add(welcome);

        profile = new JButton("profile");
        add(profile);

        registered_users = new JButton("Registered Users");
        add(registered_users);

        sign_out = new JButton("Sign Out");
        add(sign_out);

    }
}
