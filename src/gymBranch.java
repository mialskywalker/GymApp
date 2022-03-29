
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    
}
