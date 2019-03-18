package tp1;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class TestSum {
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
			String sql = "select sum(article.PRIXUNITAIRE * lignecommande.QUANTITE) s"
			+ " from detaillivraison, lignecommande, article \r\n"
			+ " where detaillivraison.nocommande = lignecommande.NOCOMMANDE"
			+ " and lignecommande.NOARTICLE = article.NOARTICLE\r\n"
			+ " and detaillivraison.nolivraison = 100";
			BigDecimal bd = null;
			ResultSet res = stmt.executeQuery(sql);
			if (res.next()) {
				bd = res.getBigDecimal("s");
				System.out.println("---------");
				System.out.println(bd);
				System.out.println("---------");
			}
			

			
			

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("-----------problem1... :-(");
		}
		finally {
			try {
				stmt.close();
				ctn.rollback();
				ctn.close();
			} catch (SQLException e) {
				System.out.println("-----------problem2.... :-(");
			}
			
		}
		
	}

}
