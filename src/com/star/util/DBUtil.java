package com.star.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    public static Connection dbConnect() {
        Connection conn = null;

        String url, id, pass;

        try {
            Properties pro = new Properties();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            pro.load(new FileReader("src/com/star/util/dbinfo.properties"));
            url = pro.getProperty("url");
            id = pro.getProperty("userid");
            pass = pro.getProperty("password");
            conn = DriverManager.getConnection(url, id, pass);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void dbClose(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}