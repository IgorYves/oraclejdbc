package tp1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientDAO {
	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	String sql;
	ArrayList<Client> allClientsList = new ArrayList<>();;
	
	public ClientDAO(Connection connection) {
		this.connection = connection;
	}
	

	public Client findByKey(int id) throws SQLException {
		preparedStatement = connection
				.prepareStatement("SELECT * from client where noclient = ?");
		preparedStatement.setInt(1, id);	
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return new Client(id, resultSet.getString("nomclient"), 
									resultSet.getString("notelephone"));
		}
		preparedStatement.close();
		return null;
	}
	
	public Client findByName(String name) throws SQLException {
		preparedStatement = connection
				.prepareStatement("SELECT * from client where nomclient = ?");
		preparedStatement.setString(1, name);	
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return new Client(resultSet.getInt("noclient"), 
							resultSet.getString("nomclient"), 
							resultSet.getString("notelephone"));
		}
		preparedStatement.close();
		
		return null;
	}
	
	public ArrayList<Client> findAll() throws SQLException {
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * from client");
		while (resultSet.next()) {
			allClientsList.add(new Client(resultSet.getInt("noclient"),
										resultSet.getString("nomclient"),
										resultSet.getString("notelephone")));
		}
		return allClientsList;
	}

	public int insertClient(Client client) throws ClientDaoException, SQLException {
		if (client == null) return -1;
		if (findByKey(client.getNoClient()) == null) {
			statement = connection.createStatement();
			sql = "insert into client "
					+ "(noclient, nomclient, notelephone) "
					+ "values (" + client.getNoClient() + ", "
					+ "'" + client.getNomClient() + "', "
					+ "'" + client.getNoTel() + "')";
			statement.executeQuery(sql);
			connection.commit();
			statement.close();
		}
		else {
			throw new ClientDaoException("client exist deja");
		}
		
		return 0;
	}
	
	public void updateClient(Client client) throws ClientDaoException, SQLException {
		if (findByKey(client.getNoClient()) != null) {
			statement = connection.createStatement();
			sql = "update client "
					+ "set nomclient = '" + client.getNomClient() + "'"
					+ ", notelephone = '" + client.getNoTel() + "'"
					+ " where noclient = " + client.getNoClient();
			statement.executeQuery(sql);
			connection.commit();
			statement.close();
		}
		else {
			insertClient(client);
		}
	}
	
	public void deleteClient(Client client) throws SQLException {
		if (findByKey(client.getNoClient()) != null) {
			statement = connection.createStatement();
			statement.executeQuery("delete from client where noclient=" 
												+ client.getNoClient());
			connection.commit();
			statement.close();
		}
	}
	
	public void deleteClient(int id) throws SQLException {
		preparedStatement = connection
						.prepareStatement("delete from client where noclient= ?");
		preparedStatement.setInt(1, id);
		preparedStatement.executeQuery();
		connection.commit();
		preparedStatement.close();
	}

	public void affichAll() throws SQLException {
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * from client");
		System.out.println("---------");
		while (resultSet.next()) {
			System.out.println(resultSet.getInt("noclient") + " "
					+ resultSet.getString("nomclient") + " "
					+ resultSet.getString("notelephone"));
		}
		System.out.println("---------");
	}
	
}
