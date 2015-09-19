package com.xiaomi.jdbc.test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * @author 		zhanghu1
 * @date   		2015年8月10日
 * @fileName	JdbcMultiple.java
 * @packageName	com.xiaomi.jdbc.test
 * @projectName JDBCTest
 * @Company		Xiaomi
 */

public class JdbcMultiple {
	public static void firstTest() throws Exception{
		//注册数据库驱动
		//jar包中的
        Class.forName("com.mysql.jdbc.Driver");
        //建立数据库连接
        //参数一：jdbc：mysql//地址：端口/数据库,参数二：用户名，参数三：密码
        Connection conn = (Connection) DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/mysql","root","");
        //创建SQL语句
        Statement st = (Statement) conn.createStatement();
        //执行语句，返回结果
        ResultSet rt = st.executeQuery("show tables");
        //循环取出结果
        while(rt.next()) {
            //获取字段
            System.out.println(rt.getObject(1));
        }
        //关闭资源，最先打开的最后关
        rt.close();
        st.close();
        conn.close();
	}
	
	public static void secondTest() {
		String url = "jdbc:mysql://localhost:3306/mysql";
        String user = "root";
        String pwd = "";
        String sql = "select * from first_table";
        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url,user,pwd);
            st = (Statement) conn.createStatement();
            //执行查询语句，另外也可以用execute()，代表执行任何SQL语句
            rs = st.executeQuery(sql);
            while(rs.next()) {
                System.out.println(rs.getObject(1) + "  " + 
                        rs.getObject(2) + "  " + rs.getObject(3) + " " + rs.getObject(4));
            }
        //分别捕获异常
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //判断资源是否存在
                if(rs != null) {
                    rs.close();
                    //显示的设置为空，提示gc回收
                    rs = null;
                }
                if(st != null) {
                    st.close();
                    st = null;
                }
                if(conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }    
        }
	}
}

