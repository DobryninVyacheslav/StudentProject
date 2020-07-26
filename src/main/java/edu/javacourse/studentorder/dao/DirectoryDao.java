package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.Street;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DirectoryDao {

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/jc_student",
                "postgres", "postgres");
        return connection;
    }

    public List<Street> findStreet(String pattern) throws Exception {
        List<Street> result = new LinkedList<>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT street_code, street_name " +
                "FROM jc_street WHERE UPPER(street_name) LIKE UPPER('%" + pattern + "%')";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Street street =
                    new Street(resultSet.getLong("street_code"),
                            resultSet.getString("street_name"));
            result.add(street);
        }
        return result;
    }
}
