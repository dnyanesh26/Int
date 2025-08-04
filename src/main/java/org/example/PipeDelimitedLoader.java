package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class PipeDelimitedLoader {

    public static void main(String[] args) throws FileNotFoundException {


//        try (Connection connection = new DbConnection().getConnection();
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery("SELECT * from Users")) {
//
//            if (resultSet.next()) {
//                System.out.println(resultSet.getString("name") );
//            }
//
//        }
//        catch (ClassNotFoundException | SQLException e) {
//                throw new RuntimeException(e);
//        }

        String filePath = "C:\\Users\\dnyan\\Downloads\\interest_data_1000_rows.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String tableName = "FinData";
            Connection connection = new DbConnection().getConnection();
            connection.setAutoCommit(false); // Use transactions for better performance

            while ((line = reader.readLine()) != null) {

                String[] values = line.split("\\|"); // Split by pipe

                if(values[0].equals("Principal Amount"))
                    continue;
                // Assuming your table has columns: col1, col2, col3, ...
                String sql = "INSERT INTO " + tableName + " (PA ,Interest_Rate, Years, Compound_Period, Simple_Int, Compound_Int, ROI) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    // Set values based on the number of columns and data types
                    preparedStatement.setFloat(1, Float.parseFloat(values[0]));
                    preparedStatement.setFloat(2, Float.parseFloat(values[1]));
                    preparedStatement.setFloat(3, Float.parseFloat(values[2]));
                    preparedStatement.setFloat(4, Float.parseFloat(values[3]));
                    preparedStatement.setFloat(5, Float.parseFloat(values[4]));
                    preparedStatement.setFloat(6, Float.parseFloat(values[5]));
                    preparedStatement.setFloat(7, Float.parseFloat(values[6]));

                    // ... set other values accordingly

                    preparedStatement.executeUpdate();
                }
            }

            connection.commit(); // Commit the transaction
            connection.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately, potentially rolling back the transaction
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
