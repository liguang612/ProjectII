package Server;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import Model.Account;
import Model.Choice;
import Model.Exam;
import Resources.Tools;

public class AuthQuery {
    public static boolean createChoices(ArrayList<Choice> choices, int questionId) {
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database
                        .prepareStatement("INSERT INTO CHOICE VALUES (?, ?, ?, ?)");

                preparedStatement.setInt(4, questionId);

                for (Choice choice : choices) {
                    preparedStatement.setString(1, choice.getText());
                    preparedStatement.setBytes(2, Tools.imageToBytes(choice.getMedia()));
                    preparedStatement.setBoolean(3, choice.getIsCorrect());

                    preparedStatement.addBatch();
                }

                preparedStatement.executeBatch();

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public static boolean createExam(Exam exam) {
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database
                        .prepareStatement("INSERT INTO EXAM VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                preparedStatement.setString(1, exam.getName());
                preparedStatement.setString(2, exam.getDescription());
                preparedStatement.setDate(3, new Date(exam.getOpenTime().getTime()));
                preparedStatement.setDate(4, new Date(exam.getCloseTime().getTime()));
                preparedStatement.setString(5, exam.getSubject());
                preparedStatement.setInt(6, exam.getDuration());
                preparedStatement.setBoolean(7, exam.getCanReviewed());
                preparedStatement.setFloat(8, exam.getTotal());
                preparedStatement.setInt(9, exam.getEasies());
                preparedStatement.setInt(10, exam.getMediums());
                preparedStatement.setInt(11, exam.getHards());
                preparedStatement.setInt(12, exam.getTeacherId());
                preparedStatement.setBoolean(13, exam.getCanRepeat());

                preparedStatement.executeUpdate();

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

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

    public static Account login(String username, String password) {
        if (DBConnection.database != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.database
                        .prepareStatement("SELECT ID FROM [LOGIN] WHERE USERNAME = ? AND [PASSWORD] = ?");

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    preparedStatement = DBConnection.database.prepareStatement("SELECT * FROM ACCOUNT WHERE ID = ?");

                    preparedStatement.setInt(1, resultSet.getInt(1));

                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        return new Account(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
                                resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6),
                                resultSet.getString(7), resultSet.getString(8),
                                Tools.BytesToImage(resultSet.getBytes(9)));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static int signUp(Account account, String username, String password) {
        if (DBConnection.database != null) {
            try {
                // Register username and password to get ID
                PreparedStatement preparedStatement;
                try {
                    preparedStatement = DBConnection.database
                            .prepareStatement("INSERT INTO [LOGIN] VALUES(?, ?)");

                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    return e.getErrorCode();
                }

                // Get ID
                preparedStatement = DBConnection.database
                        .prepareStatement("SELECT ID FROM [LOGIN] WHERE USERNAME = ? AND [PASSWORD] = ?");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    try {
                        int id = resultSet.getInt(1);

                        preparedStatement = DBConnection.database
                                .prepareStatement("INSERT INTO ACCOUNT VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");

                        preparedStatement.setInt(1, id);
                        preparedStatement.setInt(2, account.getRole());
                        preparedStatement.setString(3, account.getName());
                        preparedStatement.setDate(4, account.getDob());
                        preparedStatement.setString(5, account.getPhoneNumber());
                        preparedStatement.setString(6, account.getEmail());
                        preparedStatement.setString(7, account.getSchool());
                        preparedStatement.setString(8, account.getclass());
                        preparedStatement.setBytes(9, Tools.imageToBytes(account.getImage()));

                        preparedStatement.executeUpdate();
                    } catch (Exception e) {
                        int id = resultSet.getInt(1);

                        preparedStatement = DBConnection.database.prepareStatement("DELETE FROM [LOGIN] WHERE ID = ?");
                        preparedStatement.setInt(1, id);

                        preparedStatement.executeUpdate();

                        e.printStackTrace();
                    }
                }

                return 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return -1;
    }
}
