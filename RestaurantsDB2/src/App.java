import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {
        new DB("jdbc:sqlite:./database.sqlite");
        PreparedStatement insertCafe = DB.conn.prepareStatement("INSERT INTO cafes (name) VALUES (?);");
        String[] cafes = {
                "Bear",
                "Stefan's Cafe",
                "Emilie's Cafe"
        };

        for (String cafe : cafes) {
            insertCafe.setString(1, cafe);
            insertCafe.executeUpdate();
        }

        Statement getAllCafes = DB.conn.createStatement();
        PreparedStatement getOneCafe = DB.conn.prepareStatement("SELECT * FROM cafes WHERE ID = ?:");

        try {
            ResultSet results = getAllCafes.executeQuery("SELECT * FROM cafes;");
            while (results.next()) {
                System.out.printf("ID: %d\n", results.getInt(1));
                System.out.printf("Name: %d\n", results.getString(2));
            }
            getOneCafe.setInt(1, 4);
            ResultSet starcafe = getOneCafe.executeQuery();
            System.out.printf("Winner is: %d\n", starcafe.getString(2));
        } catch (SQLException errr) {

        }

        insertCafe.setString(1, "Star Cafe");
        insertCafe.executeUpdate();
        DB.close();
    }
}
