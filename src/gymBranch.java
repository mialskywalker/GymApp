
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class gymBranch {

    public void insertUpdateDeleteGymBranch(
            char operation, Integer id, String city,
            String street, String workingHours, String workingDays)
    {
        
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        
        if(operation == 'i') {
            try {
                ps = con.prepareStatement(
                        "INSERT INTO gym_branch(city, street, working_hours, working_days)"
                                + " VALUES (?,?,?,?)");
                ps.setString(1, city);
                ps.setString(2, street);
                ps.setString(3, workingHours);
                ps.setString(4, workingDays);
                
                if(ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "New Gym Branch Added");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(gymBranch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void fillGymBranchTable(JTable table, String valueToSearch) {
        
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `gym_branch` WHERE CONCAT("
                    + "'city', 'street', 'working_hours', 'working_days')LIKE ?");
            ps.setString(1, "%"+valueToSearch+"%");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next()) {
                row = new Object[5];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                
                model.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(gymBranch.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
