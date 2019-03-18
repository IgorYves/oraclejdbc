package tp1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientControl {
	static final String DBOR = "jdbc:oracle:thin:@localhost:1521:xe";
	static final String USER = "livraison";
	static final String PWD = "livraison";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Connection connection;
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection(DBOR, USER, PWD);
		ClientDAO clientDAO = new ClientDAO(connection);
		
		Client client10 = clientDAO.findByKey(10);
		System.out.println(client10);
		System.out.println("---------");
		
		Client client200 = new Client(200, "Petrov-Vodkin", "06.22.00.00.22");
		try {
			clientDAO.insertClient(client200);
		} catch (ClientDaoException e) {
			System.out.println("Huston, we have a problem : " + e.getMessage());
		}
		clientDAO.affichAll();	
		
		clientDAO.deleteClient(client200);
		clientDAO.affichAll();
		
		try {
			clientDAO.insertClient(client200);
		} catch (ClientDaoException e) {
			System.out.println("Huston, we have a problem : " + e.getMessage());
		}
		clientDAO.affichAll();	
		
		try {
			clientDAO.updateClient(client200);
		} catch (ClientDaoException e) {
			System.out.println("Huston, we have a problem : " + e.getMessage());
		}
		clientDAO.affichAll();	
		
		clientDAO.deleteClient(200);
		clientDAO.affichAll();
		
		ArrayList<Client> allClientsList = clientDAO.findAll();
		System.out.println("---------");
		System.out.println("---------");
		allClientsList.stream().forEach(System.out::println);
		
		System.out.println("---------");
		Client client11 = clientDAO.findByName("Simon Lecoq");
		System.out.println(client11);
		System.out.println("---------");
		
		connection.close();
	}
}
