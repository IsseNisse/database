import java.sql.*;

public class database {
    public static void main(String[] args) {
        try {
            // Set up connection to database
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://"+DatabaseLoginData.DBURL + ":" + DatabaseLoginData.port + "/" + DatabaseLoginData.DBname +
                            "? allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    DatabaseLoginData.user, DatabaseLoginData.password);

            // Setup statement
            int x = 1;
            Statement stmt = conn.createStatement();
            // Create query and execute
            String strSelect = "select body from story where id = " + x;
            String linksSelect = "select target_id, description from links where id = " + x;
            System.out.println("The SQL statement is: " + strSelect + "\n");

            ResultSet rset = stmt.executeQuery(strSelect);

            // Loop through the result set and print

            System.out.println("The records selected are:");
            while(rset.next()) {
                String title = rset.getString("body");
                System.out.println(title);
            }
            System.out.println("Total number of records = ");

            ResultSet linkset = stmt.executeQuery(linksSelect);

            System.out.println("The records selected are:");
            int rowCount = 0;
            while(linkset.next()) {
                int target_id = linkset.getInt("target_id");
                String description = linkset.getString("description");
                System.out.println(target_id + ", " + description);
                ++rowCount;
            }
            System.out.println("Total number of records = " + rowCount);

            // Close conn and stmt
            conn.close();
            stmt.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}
