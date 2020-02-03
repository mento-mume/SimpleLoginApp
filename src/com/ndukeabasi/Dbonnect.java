package com.ndukeabasi;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbonnect {

    Connection con = null;
    public Connection connection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ndukeabasi?serverTimezone=UTC#","root","");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;

    }
}
