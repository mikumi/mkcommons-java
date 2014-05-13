/**
 * 
 */
package com.michael_kuck.commons.mysql;

import com.michael_kuck.commons.Log;

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
    public static Connection getNewConnection(final String host, final int port, final String user,
                                              final String password, final String database)
    {
        Connection connection = null;

        final String connectionUrl = getUrl(host, port, database);
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", user);
        connectionProperties.put("password", password);
        connectionProperties.put("autoReconnnect", "true");
        Log.debug("Trying to connect to " + connectionUrl + " with user: " + user + ", password: (hidden)");
        try
        {
            connection = DriverManager.getConnection(connectionUrl, connectionProperties);
        } catch (final SQLException e)
        {
            Log.error("Error connecting to MySQL database: " + e.getLocalizedMessage());
        }

        return connection;
    }

	/**
	 * @return
	 */
    private static String getUrl(final String host, final int port, final String database) {
        return "jdbc:mysql://" + host + ":" + port + "/" + database;
    }

}
