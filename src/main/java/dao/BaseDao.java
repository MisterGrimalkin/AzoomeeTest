package dao;

import java.sql.*;

public class BaseDao {

    protected Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:hsqldb:file:azoomee", "SA", "");
        } catch (SQLException e) {
            // TODO error handling
            e.printStackTrace();
        }
        return null;
    }

    protected void executeUpdate(String sql) {
        Connection connection;
        try {
            connection = getConnection();
            if ( connection!=null ) {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.execute();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected ResultSet executeSelect(String sql) {
        Connection connection;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            if ( connection!=null ) {
                PreparedStatement statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    protected long getMaxValue(String table, String column) {
        ResultSet rs = executeSelect("SELECT MAX(" + column + ") FROM " + table + ";");
        try {
            while ( rs.next() ) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }



}
