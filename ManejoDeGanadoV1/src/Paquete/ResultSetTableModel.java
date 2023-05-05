/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paquete;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class ResultSetTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;

    public ResultSetTableModel(ResultSet rs) throws SQLException {

        super();

        // Get ResultSet metadata
        ResultSetMetaData rsmd = rs.getMetaData();

        // Get number of columns
        int columnCount = rsmd.getColumnCount();

        // Create Vector of column names
        Vector<String> columnNames = new Vector<String>(columnCount);

        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(rsmd.getColumnName(i));
        }

        // Set column names as column headers
        setColumnIdentifiers(columnNames);

        // Create Vector of rows
        Vector<Vector<Object>> rows = new Vector<Vector<Object>>();

        // Loop through ResultSet rows
        while (rs.next()) {

            // Create Vector of row values
            Vector<Object> row = new Vector<Object>(columnCount);

            // Loop through ResultSet columns
            for (int i = 1; i <= columnCount; i++) {
                row.add(rs.getObject(i));
            }

            // Add row to rows Vector
            rows.add(row);
        }

        // Set rows Vector as data
        setDataVector(rows, columnNames);

    }

}