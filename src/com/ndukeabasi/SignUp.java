package com.ndukeabasi;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

//driver code
public class SignUp {
    newUser nU = new newUser();
}
class newUser extends JFrame{
    private Container main;
    private JLabel title;
    private JLabel name;
    private JLabel signUp_userName;
    private JLabel phoneNumber;
    private JLabel sex;
    private JLabel address;
    private JLabel dateOfBirth;
    private JLabel signUp_password;
    private JLabel security;
    private JLabel pic_path;
    private JLabel answer;
    private JTextField name_text;
    private JTextField username_text;
    private JTextField phone_text;
    private JRadioButton male;
    private JRadioButton female;
    private ButtonGroup sexgrp;
    private JTextArea address_text;
    private JTextField dob;
    private JPasswordField password_text;
    private JComboBox security_text;
    private JTextField answer_text;
    private JButton pic;
    private JButton submit;
    private JButton back;

    //open connection to databse
    Dbonnect connect = new Dbonnect();
    Connection con = new Dbonnect().connection();
    ResultSet resultSet;

    //variable to hold path
    String image_path = null;


    private String[] secq = {"what is your maiden name?", "place of birth?", "name of favorite uncle?"};

    public newUser(){

        //frame styling
        setTitle("Sign Up");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600,700);
        setLocationRelativeTo(null);
        setResizable(false);


        main = getContentPane();
        main.setLayout(null);

        //creating objects of the components
        //styling components
        //adding components to frame

        title = new JLabel("Sign Up form");
        title.setFont(new Font("Arial",Font.PLAIN,30));
        title.setSize(300,40);
        title.setLocation(100,40);
        main.add(title);

        name = new JLabel("Name");
        name.setFont(new Font("Arial",Font.PLAIN,20));
        name.setSize(100,20);
        name.setLocation(100,100);
        main.add(name);

        name_text = new JTextField(25);
        name_text.setFont(new Font("Arial",Font.PLAIN,15));
        name_text.setSize(100,20);
        name_text.setLocation(250,100);
        main.add(name_text);

        signUp_userName = new JLabel("Username");
        signUp_userName.setFont(new Font("Arial",Font.PLAIN,20));
        signUp_userName.setSize(100,20);
        signUp_userName.setLocation(100,150);
        main.add(signUp_userName);

        username_text = new JTextField(25);
        username_text.setFont(new Font("Arial",Font.PLAIN,15));
        username_text.setSize(100,20);
        username_text.setLocation(250,150);
        main.add(username_text);

        phoneNumber = new JLabel("Phone Number");
        phoneNumber.setFont(new Font("Arial",Font.PLAIN,20));
        phoneNumber.setSize(200,20);
        phoneNumber.setLocation(100,200);
        main.add(phoneNumber);

        phone_text = new JTextField(25);
        phone_text.setFont(new Font("Arial",Font.PLAIN,15));
        phone_text.setSize(100,20);
        phone_text.setLocation(250,200);
        main.add(phone_text);


        sex = new JLabel("Sex");
        sex.setFont(new Font("Arial",Font.PLAIN,20));
        sex.setSize(100,20);
        sex.setLocation(100,250);
        main.add(sex);

        male = new JRadioButton("Male");
        male.setFont(new Font("Arial",Font.PLAIN,20));
        male.setSize(75,20);
        male.setLocation(250,250);
        main.add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Arial",Font.PLAIN,20));
        female.setSize(150,20);
        female.setLocation(325,250);
        main.add(female);

        sexgrp = new ButtonGroup();
        sexgrp.add(male);
        sexgrp.add(female);

        address = new JLabel("address");
        address.setFont(new Font("Arial",Font.PLAIN,20));
        address.setSize(100,20);
        address.setLocation(100,300);
        main.add(address);

        address_text = new JTextArea();
        address_text.setSize(200,80);
        address_text.setLineWrap(true);
        address_text.setFont(new Font("Arial",Font.PLAIN,15));
        address_text.setLocation(250,300);
        main.add(address_text);

        signUp_password= new JLabel("Password");
        signUp_password.setFont(new Font("Arial",Font.PLAIN,20));
        signUp_password.setSize(100,20);
        signUp_password.setLocation(100,400);
        main.add(signUp_password);

        password_text = new JPasswordField(25);
        password_text.setFont(new Font("Arial",Font.PLAIN,15));
        password_text.setSize(100,20);
        password_text.setLocation(250,400);
        main.add(password_text);

        security = new JLabel("Security Questions");
        security.setLocation(80,450);
        security.setSize(200,20);
        security.setFont(new Font("Arial",Font.PLAIN,20));
        main.add(security);

        security_text = new JComboBox(secq);
        security_text.setFont(new Font("Arial",Font.PLAIN,15));
        security_text.setSize(200,20);
        security_text.setLocation(250,450);
        main.add(security_text);

        answer = new JLabel("Answer");
        answer.setLocation(100,500);
        answer.setSize(200,20);
        answer.setFont(new Font("Arial",Font.PLAIN,20));
        main.add(answer);

        answer_text = new JTextField(25);
        answer_text.setFont(new Font("Arial",Font.PLAIN,15));
        answer_text.setSize(200,20);
        answer_text.setLocation(250,500);
        main.add(answer_text);

        dateOfBirth = new JLabel("Date of Birth");
        dateOfBirth.setFont(new Font("Arial",Font.PLAIN,20));
        dateOfBirth.setSize(150,20);
        dateOfBirth.setLocation(100,550);
        main.add(dateOfBirth);

        dob = new JTextField(25);
        dob.setFont(new Font("Arial",Font.PLAIN,15));
        dob.setSize(100,20);
        dob.setLocation(250,550);
        main.add(dob);

        submit = new JButton("Submit");
        submit.setFont(new Font("Arial",Font.PLAIN,15));
        submit.setSize(100,30);
        submit.setLocation(100,600);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = name_text.getText();
                String username = username_text.getText();
                String phone = phone_text.getText();
                String sex = "Male";
                String address = address_text.getText();
                String date_ob = dob.getText();
                String pass = String.valueOf(password_text.getPassword()) ;
                String security = security_text.getSelectedItem().toString();
                String answer = answer_text.getText();
                if(female.isSelected()){
                    sex = "Female";
                }

              if( verifyFields()){
                  if(!checkUsername(username)){
                      String query = "INSERT INTO `users`(`name`, `uname`, `phone_number`, `sex`, `address`, `password`, `security`, `answer`, `date_of_birth`,`pic`) VALUES (?,?,?,?,?,?,?,?,?,?)";
                      try {
                          PreparedStatement preparedStatement = con.prepareStatement(query);
                          preparedStatement.setString(1,name);
                          preparedStatement.setString(2,username);
                          preparedStatement.setString(3,phone);
                          preparedStatement.setString(4,sex);
                          preparedStatement.setString(5,address);
                          preparedStatement.setString(6,pass);
                          preparedStatement.setString(7,security);
                          preparedStatement.setString(8,answer);
                          preparedStatement.setString(9,date_ob);

                          try {
                              if(image_path != null){
                                  InputStream image = new FileInputStream(new File(image_path));
                                  preparedStatement.setBlob(10,image);
                              }else{
                                  preparedStatement.setNull(10, Types.NULL);
                              }
                          } catch (FileNotFoundException ex) {
                              ex.printStackTrace();
                          }

                          if(preparedStatement.executeUpdate()>0){
                              JOptionPane.showMessageDialog(null,"user added succesfully");
                          }
                      } catch (SQLException ex) {
                          ex.printStackTrace();
                      }
                  }
              }
            }
        });
        main.add(submit);

        back = new JButton("Back");
        back.setFont(new Font("Arial",Font.PLAIN,15));
        back.setSize(100,30);
        back.setLocation(250,600);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignIn sn = new SignIn();
                dispose();
            }
        });
        main.add(back);

        pic_path = new JLabel();
        pic_path.setFont(new Font("Arial",Font.PLAIN,10));
        pic_path.setSize(100,20);
        pic_path.setLocation(400,150);
        main.add(pic_path);


        pic = new JButton("add pic");
        pic.setFont(new Font("Arial",Font.PLAIN,15));
        pic.setSize(100,30);
        pic.setLocation(400,100);
        pic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //select an image path and set it to jlabel
                String path = null;
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File(System.getProperty("user.home")));

                FileNameExtensionFilter extension = new FileNameExtensionFilter("*images","png","jpeg","jpg");
                chooser.addChoosableFileFilter(extension);

                int filestate = chooser.showSaveDialog(null);

                //check if user selected file
                if(filestate == JFileChooser.APPROVE_OPTION){

                    File selectedImage = chooser.getSelectedFile();
                    path = selectedImage.getAbsolutePath();
                    pic_path.setText(path);
                    image_path = path;

                }


            }
        });
        main.add(pic);
    }


    public boolean verifyFields(){

       String name = name_text.getText();
       String username = username_text.getText();
       String phone = phone_text.getText();
       String sex = "Male";
       String address = address_text.getText();
       String date_ob = dob.getText();
       String pass = String.valueOf(password_text.getPassword()) ;
       String security = security_text.getSelectedItem().toString();
       String answer = answer_text.getText();

       if(female.isSelected()){
           sex = "Female";
       }

       //check empty fields
        if(name.trim().equals("") || username.trim().equals("") || phone.trim().equals("") || sex.trim().equals("") || address.trim().equals("") ||date_ob.trim().equals("")
        || pass.trim().equals("") || security.trim().equals("") || answer.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"one or more fields are empty", "empty fields",2);
            return false;
        }
        //if everything is ok
        else{
            return true;
        }
    }
    //check if username exist
    public boolean checkUsername(String uname){

        PreparedStatement statement;
        ResultSet resultSet;
        boolean userExist = false;

        String query = "SELECT * FROM `users` WHERE `uname` = ?";
        try {
            statement = con.prepareStatement(query);
            statement.setString(1,uname);
            resultSet= statement.executeQuery();

            if(resultSet.next()){
                userExist = true;
                JOptionPane.showMessageDialog(null,"this username already exist","username failed",2);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userExist;


    }






}
