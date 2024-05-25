package Server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class DBQuery {
    public static boolean functionName() {
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database
                        .prepareStatement("INSERT INTO Activity VALUES (?, ?, ?, ?, ?)");

                preparedStatement.setLong(1, 1);

                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public static boolean deleteApartment(ArrayList<Integer> selections) {
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database
                        .prepareStatement("UPDATE Apartment SET ownerId = null WHERE apartmentId = ?");
                PreparedStatement preparedStatement2 = DBConnection.database
                        .prepareStatement("DELETE FROM Vehicle WHERE apartmentId = ?");

                for (Integer i : selections) {
                    preparedStatement.setInt(1, i);
                    preparedStatement.addBatch();
                    preparedStatement2.setInt(1, i);
                    preparedStatement2.addBatch();
                }
                preparedStatement.executeBatch();
                preparedStatement2.executeBatch();

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static int login(String username, String password) {
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database
                        .prepareStatement("SELECT ID FROM [LOGIN] WHERE USERNAME = ? AND [PASSWORD] = ?");

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return -1;
    }
}
