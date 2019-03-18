package tp1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ClientEssais {
	static final String DBOR = "jdbc:oracle:thin:@localhost:1521:xe";
	static final String USER = "livraison";
	static final String PWD = "livraison";
	static JOptionPane jop = new JOptionPane();

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection ctn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
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
		
		Client client = new Client(200, "Petrov-Vodkin", "06.22.00.00.22");
		String sql = "insert into client "
				+ "(noclient, nomclient, notelephone) "
				+ "values (" + client.getNoClient() + ", "
				+ "'" + client.getNomClient() + "', "
				+ "'" + client.getNoTel() + "')";
		System.out.println(sql);
		stmt.executeQuery(sql);
		
		res = stmt.executeQuery("SELECT * from client");
		while (res.next()) {
			System.out.println(res.getInt("noclient") + " "
					+ res.getString("nomclient") + " "
					+ res.getString("notelephone"));
		}
		System.out.println("---------");
		
		ctn.rollback();

	}

}
