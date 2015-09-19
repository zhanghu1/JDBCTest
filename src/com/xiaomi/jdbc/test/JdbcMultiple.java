package com.xiaomi.jdbc.test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * @author 		zhanghu1
 * @date   		2015��8��10��
 * @fileName	JdbcMultiple.java
 * @packageName	com.xiaomi.jdbc.test
 * @projectName JDBCTest
 * @Company		Xiaomi
 */

public class JdbcMultiple {
	public static void firstTest() throws Exception{
		//ע�����ݿ�����
		//jar���е�
        Class.forName("com.mysql.jdbc.Driver");
        //�������ݿ�����
        //����һ��jdbc��mysql//��ַ���˿�/���ݿ�,���������û�����������������
        Connection conn = (Connection) DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/mysql","root","");
        //����SQL���
        Statement st = (Statement) conn.createStatement();
        //ִ����䣬���ؽ��
        ResultSet rt = st.executeQuery("show tables");
        //ѭ��ȡ�����
        while(rt.next()) {
            //��ȡ�ֶ�
            System.out.println(rt.getObject(1));
        }
        //�ر���Դ�����ȴ򿪵�����
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
            //ִ�в�ѯ��䣬����Ҳ������execute()������ִ���κ�SQL���
            rs = st.executeQuery(sql);
            while(rs.next()) {
                System.out.println(rs.getObject(1) + "  " + 
                        rs.getObject(2) + "  " + rs.getObject(3) + " " + rs.getObject(4));
            }
        //�ֱ𲶻��쳣
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //�ж���Դ�Ƿ����
                if(rs != null) {
                    rs.close();
                    //��ʾ������Ϊ�գ���ʾgc����
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

