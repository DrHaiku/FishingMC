package fr.drhaiku.fish.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {
    private static Connection connection;
    private String urlBase;
    private String host;
    private String database;
    private String userName;
    private String password;

    public DataBaseManager(String urlBase, String host, String database, String userName, String password) {
        this.urlBase = urlBase;
        this.host = host;
        this.database = database;
        this.userName = userName;
        this.password = password;
    }

    public static Connection getConnexion() {
        return connection;
    }

    public Connection getCo() {
        return connection;
    }

    // * méthode effectuant la connexion:
    // */
    public void connexion() {
        if (!isOnline()) {
            try {
                connection = DriverManager.getConnection(this.urlBase + this.host + ":" + "3306" + "/" + this.database + "?useSSL=false", this.userName, this.password);
                System.out.println("[DataBaseManager] Succefully connected !");
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * méthode effectuant la deconnexion
     */
    public void deconnexion() {
        if (isOnline()) {
            try {
                connection.close();
                System.out.println("§a[DataBaseManager] Succefully disconnected !");
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isOnline() {
        try {
            if ((connection == null) || (connection.isClosed())) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
