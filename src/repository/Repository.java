package repository;

import domain.Question;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;

public class Repository {
    public ArrayList<Question> questions;
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    public Repository() {
        questions = new ArrayList<>();
        openConnection();
        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Question");

            while (rs.next()) {
                questions.add(new Question(
                        rs.getInt("id"),
                        rs.getString("text"),
                        rs.getString("correct_answer"),
                        rs.getInt("score"),
                        rs.getString("user_answer")
                ));
                ;
            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Question> getQuestions(){
        return this.questions;
    }

    public void update(Question question) throws SQLException {
        String updateSql="Update Question set user_answer = ? WHERE id = ? ";
        PreparedStatement preparedStatement=getConnection().prepareStatement(updateSql);
        preparedStatement.setString(1,question.getUserAnswer());
        preparedStatement.setInt(2,question.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
