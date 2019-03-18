package tp1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ClientInsertJDBC {
	static final String DBOR = "jdbc:oracle:thin:@localhost:1521:xe";
	static final String USER = "livraison";
	static final String PWD = "livraison";
	static JOptionPane jop = new JOptionPane();

	public static void main(String[] args) {
		Connection ctn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			ctn = DriverManager.getConnection(DBOR, USER, PWD);
			ctn.setAutoCommit(false);
			stmt = ctn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * from client");
			while (res.next()) {
				System.out.println(res.getInt("noclient") + " "
						+ res.getString("nomclient") + " "
						+ res.getString("notelephone"));
			}
			System.out.println("---------");

			res = stmt.executeQuery(
					"insert into client values (90, 'Pierre Dupont', '000000')");

			res = stmt.executeQuery("SELECT * from client");
			while (res.next()) {
				System.out.println(res.getInt("noclient") + " "
						+ res.getString("nomclient") + " "
						+ res.getString("notelephone"));
			}
			System.out.println("---------");
			
			pstmt = ctn.prepareStatement("insert into client values (?, 'Tartempion', ?)");
			
			pstmt.setInt(1, 110);
			pstmt.setString(2, "0011001");
			pstmt.executeUpdate();
			System.out.println("---------");
			res = stmt.executeQuery("SELECT * from client");
			while (res.next()) {
				System.out.println(res.getInt("noclient") + " "
						+ res.getString("nomclient") + " "
						+ res.getString("notelephone"));
			}
			System.out.println("---------");
			System.out.println("---------");
			
			pstmt = ctn.prepareStatement("insert into client values (?, 'Vasechkin', ?)");
			
			pstmt.setInt(1, Integer.parseInt(JOptionPane.showInputDialog(jop, "noClient", "120")));
			pstmt.setString(2, JOptionPane.showInputDialog(jop, "noTelephone", "9857614"));
			pstmt.executeUpdate();
			System.out.println("---------");
			res = stmt.executeQuery("SELECT * from client");
			while (res.next()) {
				System.out.println(res.getInt("noclient") + " "
						+ res.getString("nomclient") + " "
						+ res.getString("notelephone"));
			}
			System.out.println("---------");
			
			pstmt = ctn.prepareStatement("SELECT * from client where noclient = ?");
			pstmt.setInt(1, 
				Integer.parseInt(JOptionPane.showInputDialog(jop, "select noClient", "60")));
			res = pstmt.executeQuery();
			while (res.next()) {
				System.out.println(res.getInt("noclient") + " "
						+ res.getString("nomclient") + " "
						+ res.getString("notelephone"));
			}
			System.out.println("---------");
			
			

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("-----------problem1... :-(");
		}
		finally {
			try {
				stmt.close();
				pstmt.close();
				ctn.rollback();
				ctn.close();
			} catch (SQLException e) {
				System.out.println("-----------problem2.... :-(");
			}
			
		}
		
	}

}
