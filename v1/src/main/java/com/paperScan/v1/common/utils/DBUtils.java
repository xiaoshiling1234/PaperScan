package com.paperScan.v1.common.utils;

/**
 * Description:
 * Author：xb
 * DATE：2019/5/8 10:05
 */


import com.paperScan.v1.common.conf.EnvConfReader;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

@Component
public class DBUtils {
    static {

    }
    /**
     *表示定义数据库的用户名
     */
    private static String USERNAME;
    /**
     * 定义数据库的密码
     */

    private  static String PASSWORD;
    /**
     * 定义数据库的驱动信息
     */
    private static  String DRIVER;
    /**
     * 定义访问数据库的地址
     */
    private static  String URL ;

    private static DBUtils per = null;
    /**
     * 定义数据库的链接
     */
    private static Connection con = null;
    /**
     * 定义sql语句的执行对象
     */
    private PreparedStatement pstmt = null;
    /**
     * 定义查询返回的结果集合
     */
    private ResultSet resultSet = null;

    private DBUtils() {
    }

    /**
     * 单例模式，获得工具类的一个对象
     *
     * @return
     */
    public static DBUtils getInstance() throws IOException {
        if (per == null) {
            Properties properties = new EnvConfReader().readEnvProperties();
            USERNAME=properties.getProperty("datasource.msyql.USERNAME");
            PASSWORD=properties.getProperty("datasource.msyql.PASSWORD");
            DRIVER=properties.getProperty("datasource.msyql.DRIVER");
            URL=properties.getProperty("datasource.msyql.URL");
            per = new DBUtils();
            per.registeredDriver();
        }
        return per;
    }

    private void registeredDriver() {
        try {
            Class.forName(DRIVER);
            System.out.println("注册驱动成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得数据库的连接
     *
     * @return
     */
    public Connection getConnection() throws SQLException {
        if (con == null || con.isClosed()) {
            try {
                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("连接数据库成功!!");
        } else {
            System.out.println("获取已有数据库连接成功!!");
        }
        return con;
    }

    /**
     * 完成对数据库的表的添加删除和修改的操作
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public boolean executeUpdate(String sql, List<Object> params)
            throws SQLException {
        boolean flag;
        // 表示当用户执行添加删除和修改的时候所影响数据库的行数
        int result;
        pstmt = con.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            int index = 1;
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index + i, params.get(i));
            }
        }

        result = pstmt.executeUpdate();
        flag = result > 0 ? true : false;
        return flag;
    }

    /**
     * 从数据库中查询数据
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> executeQuery(String sql,
                                                  List<Object> params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        int index = 1;
        pstmt = con.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }
        return list;
    }

    /**
     * jdbc的封装可以用反射机制来封装,把从数据库中获取的数据封装到一个类的对象里
     *
     * @param sql
     * @param params
     * @param cls
     * @return
     * @throws Exception
     */
    public <T> List<T> executeQueryByRef(String sql, List<Object> params,
                                         Class<T> cls) throws Exception {
        List<T> list = new ArrayList<T>();
        int index = 1;
        pstmt = con.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            // 通过反射机制创建实例
            T resultObject = cls.newInstance();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                Field field = cls.getDeclaredField(cols_name);
                // 打开javabean的访问private权限
                field.setAccessible(true);
                field.set(resultObject, cols_value);
            }
            list.add(resultObject);
        }
        return list;

    }

    public void closeDB() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}