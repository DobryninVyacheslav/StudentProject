package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.CountryArea;
import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.RegisterOffice;
import edu.javacourse.studentorder.domain.Street;
import edu.javacourse.studentorder.exception.DaoException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class DictionaryDaoImplTest {

    @BeforeClass
    public static void startUp() throws URISyntaxException, IOException, SQLException {
        URL url1 = DictionaryDaoImplTest.class.getClassLoader()
                .getResource("StudentProject.sql");
        URL url2 = DictionaryDaoImplTest.class.getClassLoader()
                .getResource("StudentData.sql");
        List<String> lines1 = Files.readAllLines(Paths.get(url1.toURI()));
        String sql1 = lines1.stream().collect(Collectors.joining());

        List<String> lines2 = Files.readAllLines(Paths.get(url2.toURI()));
        String sql2 = lines2.stream().collect(Collectors.joining());
        try (Connection connection = ConnectionBuilder.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
        }
    }

    @Test
    public void testStreet() throws DaoException {
        List<Street> d = new DictionaryDaoImpl().findStreet("про");
        assertTrue(d.size() == 2);
    }
    @Test
    public void testPassportOffice() throws DaoException {
        List<PassportOffice> po = new DictionaryDaoImpl().findPassportOffices("010020000000");
        assertTrue(po.size() == 2);
    }
    @Test
    public void testRegisterOfficee() throws DaoException {
        List<RegisterOffice> ro = new DictionaryDaoImpl().findRegisterOffices("010010000000");
        assertTrue(ro.size() == 2);
    }
    @Test
    public void testArea() throws DaoException {
        List<CountryArea> ca1 = new DictionaryDaoImpl().findAreas("");
        assertTrue(ca1.size() == 2);
        List<CountryArea> ca2 = new DictionaryDaoImpl().findAreas("020000000000");
        assertTrue(ca2.size() == 2);
        List<CountryArea> ca3 = new DictionaryDaoImpl().findAreas("020010000000");
        assertTrue(ca3.size() == 2);
        List<CountryArea> ca4 = new DictionaryDaoImpl().findAreas("020010010000");
        assertTrue(ca4.size() == 2);
    }
}