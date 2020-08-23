package edu.javacourse.city.dao;

import edu.javacourse.city.domian.PersonRequest;
import edu.javacourse.city.domian.PersonResponse;
import edu.javacourse.city.exception.PersonCheckException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonCheckDao {

    private static final String SQL_REQUEST = "";

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        PersonResponse response = new PersonResponse();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_REQUEST);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                response.setRegistered(true);
                response.setTemporal(resultSet.getBoolean("temporal"));
            }
        } catch (SQLException e) {
            throw new PersonCheckException(e);
        }
        return response;
    }

    private Connection getConnection() {
        return null;
    }
}
