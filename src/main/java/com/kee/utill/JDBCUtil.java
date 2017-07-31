package utill;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by kee on 2017/7/6.
 */
public class JDBCUtil {
    private static String mDriver;
    private static String mUrl;
    private static String mUser;
    private static String mPassword;

    /**
     * 1.注册对应数据库驱动
     */
    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
        mDriver = resourceBundle.getString("driver");
        mUrl = resourceBundle.getString("url");
        mUser = resourceBundle.getString("user");
        mPassword = resourceBundle.getString("password");
        try {
            Class.forName(mDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2.获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(mUrl, mUser, mPassword);
//            connection = DriverManager.getConnection(mUrl);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("create DataBase Connection fail k");
        }

        return connection;
    }

    /**
     * 3.创建Statement
     * 4.准备Sql语句
     * 5.执行Sql
     * ----------------
     * 6.关闭全部资源资源
     *
     * @param conn
     * @param st
     * @param rs
     */
    public static void closeJDBC(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null)
                        conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("All close");
                }

            }
        }
    }


    public static void main(String[] arg) {
//        ResourceBundle wechatmessage = ResourceBundle.getBundle("weixinconfig");

        testConnectAndShowData();

//        testConnectAndShowData();
//        testprop();
//        testresource();
    }

    private static void testresource() {
        ResourceBundle wechatmessage = ResourceBundle.getBundle("wechatmessage");
        String myIntegraCount = wechatmessage.getString("MyIntegraCount");
        System.out.println(myIntegraCount);
    }

    private static void testprop() {
        Properties prop = new Properties();
        try {
            prop.load(new InputStreamReader(JDBCUtil.class.getClassLoader().getResourceAsStream("wechatmessage.properties"), "GBK"));
            String myIntegraCount = prop.getProperty("MyIntegraCount");
            System.out.println(myIntegraCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试数据库连接，并打印所有数据
     */
    private static void testConnectAndShowData() {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from t_devices;";
            ResultSet resultSet = statement.executeQuery(sql);
            showAllDatas(resultSet);
            closeJDBC(connection, statement, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showAllDatas(ResultSet resultSet) {
        ResultSetMetaData metaData = null;
        try {
            metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t\t");
//                System.out.print(metaData.getColumnName(i) + "\t\t");
            }
            System.out.println("");
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t\t");
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
