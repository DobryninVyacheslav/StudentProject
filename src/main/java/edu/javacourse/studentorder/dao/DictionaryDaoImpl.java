package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.CountryArea;
import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.RegisterOffice;
import edu.javacourse.studentorder.domain.Street;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDao {

    private static final String GET_STREET = "SELECT street_code, street_name " +
            "FROM jc_street WHERE UPPER(street_name) LIKE UPPER(?)";
    private static final String GET_PASSPORT = "SELECT * " +
            "FROM jc_passport_office WHERE p_office_area_id = ?";
    private static final String GET_REGISTER = "SELECT * " +
            "FROM jc_register_office WHERE r_office_area_id = ?";
    private static final String GET_AREA = "SELECT * " +
            "FROM jc_country_struct WHERE area_id LIKE ? AND area_id <> ?";

    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    public List<Street> findStreet(String pattern) throws DaoException {
        List<Street> result = new LinkedList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_STREET)) {
            statement.setString(1, "%" + pattern + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(new Street(resultSet.getLong("street_code"),
                            resultSet.getString("street_name")));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<PassportOffice> findPassportOffices(String areaId) throws DaoException {
        List<PassportOffice> result = new LinkedList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PASSPORT)) {
            statement.setString(1, areaId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(new PassportOffice(
                            resultSet.getLong("p_office_id"),
                            resultSet.getString("p_office_area_id"),
                            resultSet.getString("p_office_name")));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<RegisterOffice> findRegisterOffices(String areaId) throws DaoException {
        List<RegisterOffice> result = new LinkedList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_REGISTER)) {
            statement.setString(1, areaId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(new RegisterOffice(
                            resultSet.getLong("r_office_id"),
                            resultSet.getString("r_office_area_id"),
                            resultSet.getString("r_office_name")));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<CountryArea> findAreas(String areaId) throws DaoException {
        List<CountryArea> result = new LinkedList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_AREA)) {
            String param1 = buildParam(areaId);
            String param2 = areaId;

            statement.setString(1, param1);
            statement.setString(2, param2);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(new CountryArea(
                            resultSet.getString("area_id"),
                            resultSet.getString("area_name")));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private String buildParam(String areaId) throws SQLException {
        if (areaId == null || areaId.trim().isEmpty()) {
            return "__0000000000";
        } else if (areaId.endsWith("0000000000")) {
            return areaId.substring(0,2) + "___0000000";
        } else if (areaId.endsWith("0000000")) {
            return areaId.substring(0,5) + "___0000";
        } else if (areaId.endsWith("0000")) {
            return areaId.substring(0,8) + "____";
        }
        throw new SQLException("Invalid parameter 'areaId':" + areaId);
    }
}