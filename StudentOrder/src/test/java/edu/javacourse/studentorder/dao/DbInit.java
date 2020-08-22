package edu.javacourse.studentorder.dao;

import org.junit.BeforeClass;

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

public class DbInit {

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
}
