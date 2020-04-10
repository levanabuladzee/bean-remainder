package dao;

import model.Location;
import model.Remainder;
import util.DBConnection;

import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class RemainderDAO {
    public void showRemainder() {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "SELECT * FROM remainder;";

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int remainderId = resultSet.getInt("remainder_id");
                String remainderName = resultSet.getString("remainder_name");
                LocalTime remainderTime = resultSet.getTime("remainder_time").toLocalTime();
                String remainderCity = resultSet.getString("remainder_city");
                String remainderZip = resultSet.getString("remainder_zip");

                System.out.println(remainderId + "\t" + remainderName + "\t" + remainderTime + "\t" + remainderCity + "\t" + remainderZip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (statement != null) statement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void showRemainder(int id) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "SELECT * FROM remainder WHERE remainder_id = ?;";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int remainderId = resultSet.getInt("remainder_id");
                String remainderName = resultSet.getString("remainder_name");
                LocalTime remainderTime = resultSet.getTime("remainder_time").toLocalTime();
                String remainderCity = resultSet.getString("remainder_city");
                String remainderZip = resultSet.getString("remainder_zip");

                System.out.println(remainderId + "\t" + remainderName + "\t" + remainderTime + "\t" + remainderCity + "\t" + remainderZip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void addRemainder(Remainder remainder) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "INSERT INTO remainder(remainder_name, remainder_time, remainder_city, remainder_zip) VALUES(?, ?, ?, ?);";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, remainder.getName());
            preparedStatement.setTime(2, Time.valueOf(remainder.getTime()));
            preparedStatement.setString(3, remainder.getLocation().getCity());
            preparedStatement.setString(4, remainder.getLocation().getZipCode());

            int i = preparedStatement.executeUpdate();
            System.out.println(i + " records inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void updateRemainder(Remainder remainder, int remainderId) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "UPDATE remainder SET remainder_name = ?, remainder_time = ?, remainder_city = ?, remainder_zip = ? WHERE remainder_id = ?;";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, remainder.getName());
            preparedStatement.setTime(2, Time.valueOf(remainder.getTime()));
            preparedStatement.setString(3, remainder.getLocation().getCity());
            preparedStatement.setString(4, remainder.getLocation().getZipCode());
            preparedStatement.setInt(5, remainderId);

            int i = preparedStatement.executeUpdate();
            System.out.println(i + " records updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void removeRemainder(int remainderId) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "DELETE FROM remainder WHERE remainder_id = ?;";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, remainderId);

            int i = preparedStatement.executeUpdate();
            System.out.println(i + " records deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void getRemainders() {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "SELECT * FROM remainder ORDER BY remainder_time;";
        ArrayList<Remainder> remainders = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int remainderId = resultSet.getInt("remainder_id");
                String remainderName = resultSet.getString("remainder_name");
                LocalTime remainderTime = resultSet.getTime("remainder_time").toLocalTime();
                String remainderCity = resultSet.getString("remainder_city");
                String remainderZip = resultSet.getString("remainder_zip");
                remainders.add(new Remainder(remainderId, remainderName, remainderTime, new Location(remainderCity, remainderZip)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (statement != null) statement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        for (Remainder remainder : remainders) {
            LocalTime localTime = remainder.getTime();
            LocalTime localTimeNow = LocalTime.now();
            long startTime = Duration.between(localTimeNow.withSecond(0).withMinute(0).withHour(0), localTimeNow).getSeconds();
            long endTime = localTime.getSecond() + (localTime.getMinute() * 60) + (localTime.getHour() * 3600);
            if (startTime >= endTime) {
                showRemainder(remainder.getId());
            }
        }
    }
}
