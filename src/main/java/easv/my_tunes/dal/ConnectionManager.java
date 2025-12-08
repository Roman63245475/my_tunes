package easv.my_tunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class ConnectionManager {

    public static Connection getConnection() throws SQLServerException {
        SQLServerDataSource ds;
        ds = new SQLServerDataSource();
        ds.setDatabaseName("xxx"); // make this unique as names are shared on server
        ds.setUser("xxx"); // Use your own username
        ds.setPassword("xxx"); // Use your own password
        ds.setServerName("xxx");
        ds.setPortNumber('xxx');
        ds.setTrustServerCertificate(true);
        return ds.getConnection();
    }
}