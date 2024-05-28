package org.teamproject.teamproject.Service;


import org.springframework.web.bind.annotation.RestController;
import org.teamproject.teamproject.Vo.UserVo;

import java.sql.*;
import java.util.function.DoubleToIntFunction;

@RestController
public class DBService {


//    public static String url = "jdbc:mysql://localhost:3306/javadb1";
//    public static String user = "root";
//    public static String password = "root";

    public static String url = "jdbc:mysql://192.168.0.197:3306/javadb1";
    public static String user = "team1";
    public static String password = "123456";


    //제어 객체생성
    static Connection conn = null;
    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;


    //mysql연결
    ////5/8 수정했습니다.
    static Connection getConnect() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("DB Conn Fail");
            }
        }
        return conn;
    }

    //DB Close
    protected void DBClose(){
        if(conn != null){
            try {
                conn.close(); conn=null;
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt != null){
            try{
                stmt.close(); stmt=null;
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(pstmt != null){
            try{
                pstmt.close(); pstmt=null;
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs != null){
            try{
                rs.close(); rs=null;
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }

    }


}

