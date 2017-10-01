package com.jchen.javaStu.moocNet.project.dbUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jchen on 17-9-1.
 */
public class  dbUtil{

    private static final String driverClassName = "com.mysql.jdbc.Driver";

    private static final String duurl = "jdbc:mysql://127.0.0.1:3306/socketIo";

    private static final String username = "root";

    private static final String password = "2014080102";


    private static Connection getConnection(){
        Connection connection=null;

        try {
            Class.forName(driverClassName);
            try {
                connection= DriverManager.getConnection(duurl,username,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            closeAll();
        }

        return connection;
    }

    //关闭资源
    private static void closeAll(Closeable...closeables){
        for (Closeable closeable:closeables){
            if(closeable!=null){
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
