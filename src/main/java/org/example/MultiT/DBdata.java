package org.example.MultiT;

import org.example.DbConnection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBdata {

    public List<FinData> getFinData() throws SQLException {
        List <FinData> finData= new ArrayList<>();
        Connection connection = null;
        try {

            connection = new DbConnection().getConnection();
            connection.setAutoCommit(false); // Use transactions for better performance

            String sql = "SELECT * FROM findata";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();


                while (resultSet.next()) {

                    FinData f = new FinData();
                    f.setCompound_Int(resultSet.getFloat("Compound_Int"));
                    f.setPA(resultSet.getFloat("PA"));
                    f.setCompound_Period(resultSet.getFloat("Compound_Period"));
                    f.setInterest_Rate(resultSet.getFloat("Interest_Rate"));
                    f.setYears(resultSet.getFloat("Years"));
                    f.setSimple_Int(resultSet.getFloat("Simple_Int"));
                    f.setROI(resultSet.getFloat("ROI"));

                    finData.add(f);
                }
            }


        } catch (SQLException | IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately, potentially rolling back the transaction
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }

        return finData;
    }
}
