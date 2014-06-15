package tomcatsample;

import java.io.FileInputStream;

import java.io.IOException;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.util.Properties;

public class SimpleDBSource implements DBSource {

    private Properties props;

    private String url;

    private String user;

    private String passwd;
    
    //classpath的文件路径 
    private final static String JDBC_PATH = "/tomcatsample/jdbc.properties";  

    public SimpleDBSource() throws IOException,

                                         ClassNotFoundException {

        this(SimpleDBSource.class.getResource(JDBC_PATH).getFile());

    }

     

    public SimpleDBSource(String configFile) throws IOException,

                                                    ClassNotFoundException {

        props = new Properties();
        
        System.out.println(configFile); 

        props.load(new FileInputStream(configFile));

              

        url = props.getProperty("mylocal.caterpillar.url");

        user = props.getProperty("mylocal.caterpillar.user");

        passwd = props.getProperty("mylocal.caterpillar.password");

              

        Class.forName(

                    props.getProperty("mylocal.caterpillar.driver"));

    }

    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url, user, passwd);

    }

    public void closeConnection(Connection conn) throws SQLException {

        conn.close();

    }

}