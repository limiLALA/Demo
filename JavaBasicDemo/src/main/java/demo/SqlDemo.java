package demo;

import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlDemo {

    private final static String serverUrl = "jdbc:mysql://localhost:3306/test";
    private final static String serverTimeZone = "serverTimezone=Asia/Shanghai";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "123456";

    private String appendArgs(String s1, String... s2){
        StringBuilder sb = new StringBuilder();
        sb.append(s1);
        sb.append("?");
        for(String kv : s2){
            sb.append(kv);
            sb.append("&");
        }
        sb.append("0");
        return sb.toString();
    }

    void run() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(appendArgs(serverUrl,serverTimeZone), USERNAME, PASSWORD);
        String sql = "select * from user";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        boolean executed = preparedStatement.execute();
        if (executed)
            System.out.println("success");
        else
            System.out.println("fail");
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        new SqlDemo().run();


    }

}
