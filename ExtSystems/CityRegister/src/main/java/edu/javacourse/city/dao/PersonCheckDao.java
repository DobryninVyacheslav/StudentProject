package edu.javacourse.city.dao;

import edu.javacourse.city.domian.PersonRequest;
import edu.javacourse.city.domian.PersonResponse;
import edu.javacourse.city.exception.PersonCheckException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonCheckDao {

    private static final String SQL_REQUEST =
            "select temporal, upper(p.sur_name), upper('Васильев') from cr_address_person ap " +
            "inner join cr_person p on p.person_id = ap.person_id " +
            "inner join cr_address a on a.address_id = ap.address_id " +
            "where " +
            "upper(p.sur_name) = upper(?) " +
            "and upper(p.given_name) = upper(?) " +
            "and upper(p.patronymic) = upper(?) " +
            "and p.date_of_birth = ? " +
            "and a.street_code = ? " +
            "and upper(a.building) = upper(?) " +
            "and upper(extension) = upper(?) " +
            "and upper(a.apartment) = upper(?)";

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
