package tp_jdbc_oracle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PropertyResourceBundle;
import java.util.Set;

public class PingJdbc {
	static HashMap<String, String> connectionData = new HashMap<String, String>();
	public static void main(String[] args) 
			throws IOException, ClassNotFoundException, SQLException {
		String fileName = "jdbc.properties";
		String currKey;
		
		FileInputStream fileInStream = new FileInputStream(new File(fileName));
		PropertyResourceBundle propBundle =  new PropertyResourceBundle(fileInStream);
		Set<?> bundleKeys = propBundle.keySet();
		Iterator<?> itertr =  bundleKeys.iterator();
		while (itertr.hasNext()) {
			currKey = (String)itertr.next();
			connectionData.put(currKey, propBundle.getString(currKey));
		}

		Connection ctn = null;
		Statement stmt = null;
		Class.forName(connectionData.get(new String("driver")));
		ctn = DriverManager.getConnection(connectionData.get(new String("url")), 
										connectionData.get(new String("user")), 
										connectionData.get(new String("pwd")));
		ctn.setAutoCommit(false);
		stmt = ctn.createStatement();
		ResultSet res = stmt.executeQuery("SELECT * from client");
		while (res.next()) {
		System.out.println(res.getInt("noclient") + " "
				+ res.getString("nomclient") + " "
				+ res.getString("notelephone"));
		}
		System.out.println("---------");
		stmt.executeQuery("insert into client values (90, 'igor igor', '000000')");
		
	}

}
