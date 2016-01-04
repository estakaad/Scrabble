package scrabblegame;
import java.sql.*;

public class DictionaryDatabase {

    Connection conn = null;

    //https://github.com/KristerV/javaHarjutused/blob/lahendused/src/teema3/SQL_Login/Andmebaas.java põhjal
    public DictionaryDatabase() {
        createConnection();
    }

    private void createConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:ekss.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public String makeQuery(String usersInput) {

        try {
            Statement stat = conn.createStatement();

            String sql = "SELECT COUNT(*) AS count FROM WORDS WHERE WORD1 = '" + usersInput.toLowerCase() + "' ";

            ResultSet rs = stat.executeQuery(sql);

            String dbCount = rs.getString("count");

            rs.close();
            stat.close();
            return dbCount;
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return null;

    }

    public int wordCount(String usersInput) {

        return Integer.parseInt(makeQuery(usersInput));
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection closed");
    }


}
