/**
 * 
 */
package com.michael_kuck.commons.mysql;

import com.michael_kuck.commons.log.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author michaelkuck
 * 
 */
public class MySqlHelper {

	/**
	 * 
	 */
	private MySqlHelper()
	{
	}

	/**
	 * @return
	 */
    public static Connection getNewConnection(final String host, final int port, final String user, final String password, final String database) throws
                                                                                                                                                  SQLException
    {
        Connection connection = null;

        final String connectionUrl = getUrl(host, port, database);
        Properties connectionProperties = new Properties();
        connectionProperties.put("autoReconnnect", "true");
        connectionProperties.put("user", user);
        connectionProperties.put("password", password);
        Log.debug("Trying to connect to " + connectionUrl + " with user: " + user + ", password: (hidden)");
        connection = DriverManager.getConnection(connectionUrl, connectionProperties);

        return connection;
    }

    /**
     * @param host
     * @param port
     * @param database
     * @return
     */
    public static String getUrl(final String host, final int port, final String database) {
        return "jdbc:mysql://" + host + ":" + port + "/" + database;
    }

    /**
     * Safely closes a connection if applicable.
     * <p>
     * Checks for null. Catches exceptions.
     *
     * @param connection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                Log.error("Could not close connection: " + e.getLocalizedMessage());
            }
        }
    }

}
