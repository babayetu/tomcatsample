package tomcatsample;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BasicDBSource implements DBSource {

	private Properties props;

	private String url;

	private String user;

	private String passwd;

	private int max; // 连接池中最大Connection数目

	private List<Connection> connections;

	private final static String JDBC_PATH = "/tomcatsample/jdbc.properties";

	public BasicDBSource() throws IOException, ClassNotFoundException {

		this(SimpleDBSource.class.getResource(JDBC_PATH).getFile());

	}

	public BasicDBSource(String configFile) throws IOException,

	ClassNotFoundException {

		props = new Properties();

		props.load(new FileInputStream(configFile));

		url = props.getProperty("mylocal.caterpillar.url");

		user = props.getProperty("mylocal.caterpillar.user");

		passwd = props.getProperty("mylocal.caterpillar.password");

		max = Integer.parseInt(

		props.getProperty("mylocal.caterpillar.poolmax"));

		Class.forName(

		props.getProperty("mylocal.caterpillar.driver"));

		connections = new ArrayList<Connection>();

	}

	public synchronized Connection getConnection()

	throws SQLException {

		if (connections.size() == 0) {

			return DriverManager.getConnection(url, user, passwd);

		}

		else {

			int lastIndex = connections.size() - 1;

			return connections.remove(lastIndex);

		}

	}

	public synchronized void closeConnection(Connection conn)

	throws SQLException {

		if (connections.size() == max) {

			conn.close();

		}

		else {

			connections.add(conn);

		}

	}

}