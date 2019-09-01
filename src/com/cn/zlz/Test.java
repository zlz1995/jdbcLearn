package com.cn.zlz;

import java.sql.*;

public class Test {
    @org.junit.Test
    public void test() {
        try {
            //加载MYSQL JDBC驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Success loading Mysql Driver!");
        }catch (Exception e)
        {
            System.out.print("Error loading Mysql Driver!");
            e.printStackTrace();
        }
        //定义为null，因为它们是数据流，之后要finally依次关闭
        Connection connection = null;//连接接口
        Statement stmt = null;//语句接口
        ResultSet rs = null;//结果集接口
        try{
            //连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码
            String url = "jdbc:mysql://localhost:3306/db_stu?characterEncoding=utf8&useSSL=true";
            String username = "root";
            String password = "123456";
            //通过驱动管理器获得连接
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Success connect Mysql server!!!\n"+connection);
            //得到执行SQL语句的对象statement
            stmt = connection.createStatement();
            /**
             *  执行SQL查询语句，并返回结果
             */
            rs = stmt.executeQuery("select * from tb_stu");
            while (rs.next()) {//下一行
                int id = rs.getInt(1);//或1，第一列值
                String name = rs.getString(2);
                String age = rs.getString(3);
                String sex = rs.getString(4);
                String address = rs.getString(5);
                System.out.println("编号=" + id + " 姓名=" + name + " 年龄=" + age+ " 性别=" + sex + " 地址=" + address);
            }
            /**
             * 执行更新executeUpdate（增、改、删）
             */
            //int result1=stmt.executeUpdate("insert into tb_stu(no,name,age,sex,address) values (2,'吴睿',29,'男','山西')");
            //int result2=stmt.executeUpdate("update tb_stu set age=11 where no=1");
            //int result3=stmt.executeUpdate("delete from tb_stu where no=2");
            //System.out.println("有"+result1+"行纪录被修改");
            /**
             * 建表
             */
            /*String tableSql = "create table tb_user (username varchar(50) not null primary key,password varchar(20) not null ); ";
            int result4 = stmt.executeUpdate(tableSql);////DDL语句返回值为0;
            if (result4 == 0) {
                System.out.println(tableSql + "表已经创建成功！");
            }*/
        }catch(Exception e){
            System.out.print("get data error!");
            e.printStackTrace();
        }finally {//注意流的关闭顺序
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
