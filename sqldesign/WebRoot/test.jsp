<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Database Connection Test</title>
</head>
<body>
    <h1>Database Connection Test</h1>
    <%
        // Database connection details
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=sql;encrypt=true;trustServerCertificate=true";
        String dbUser = "zcw";
        String dbPassword = "123";
        String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        Connection connection = null;
        try {
            // Load the JDBC driver
            Class.forName(driverClass);

            // Connect to the database
            connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            
            // If connection is successful
            out.println("<p>Connection Successful!</p>");
            out.println("<p>Database: " + connection.getMetaData().getDatabaseProductName() + "</p>");
            out.println("<p>Driver: " + connection.getMetaData().getDriverName() + "</p>");
        } catch (ClassNotFoundException e) {
            out.println("<p>Driver not found: " + e.getMessage() + "</p>");
        } catch (SQLException e) {
            out.println("<p>Connection failed: " + e.getMessage() + "</p>");
        } finally {
            // Close connection if open
            if (connection != null) {
                try {
                    connection.close();
                    out.println("<p>Connection closed.</p>");
                } catch (SQLException e) {
                    out.println("<p>Error closing connection: " + e.getMessage() + "</p>");
                }
            }
        }
    %>
</body>
</html>