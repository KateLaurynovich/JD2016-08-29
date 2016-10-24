package by.it.tsydzik.jd03_01;

import by.it.akhmelev.jd03_01.CN;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ex_01_Connection {


    public static void main(String[ ] args) {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {e.printStackTrace();}
        try (Connection connection=
                     DriverManager.getConnection
                             (by.it.tsydzik.jd03_01.CN.URL_DB, by.it.tsydzik.jd03_01.CN.USER_DB, by.it.tsydzik.jd03_01.CN.PASSWORD_DB);){
                if (!connection.isClosed())
                    System.out.println("Соединение установлено");
                connection.close();
                if (connection.isClosed())
                    System.out.println("Соединение прервано");
            }
            catch (Exception e){
                e.printStackTrace();
            }

    }
}
