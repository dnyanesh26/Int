package org.example;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    private String url;
    private String user;
    private String password;
    private String d_password;
    private String keyFromConfig;

    public DbConnection() throws FileNotFoundException {
        SetConnectionProperties();
    }

    private void SetConnectionProperties() throws FileNotFoundException {
        String filePath = "C:\\Proj\\Int\\src\\main\\resources\\app.properties";
        File configFile = new File(filePath);
        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;

        if(configFile.exists()){
            try{
                if(configFile.canRead()) {

                    try (FileReader reader = new FileReader(configFile)){
                        Properties props = new Properties();
                        props.load(reader);

                        url = props.getProperty("url");
                        user = props.getProperty("user");
                        password = props.getProperty("password");
                        keyFromConfig = props.getProperty("key");

                        Decryption d = new Decryption();
                        SecretKey sk = new SecretKey();
                        SecretKeySpec key;

                        try{
                            key = sk.createSecretKey(keyFromConfig.toCharArray(), salt, iterationCount, keyLength);
                            d_password = d.decrypt(password, key);
                        } catch (InvalidKeySpecException e) {
                            throw new RuntimeException(e);
                        } catch (NoSuchAlgorithmException e) {
                            throw new RuntimeException(e);
                        } catch (InvalidAlgorithmParameterException e) {
                            throw new RuntimeException(e);
                        } catch (NoSuchPaddingException e) {
                            throw new RuntimeException(e);
                        } catch (IllegalBlockSizeException e) {
                            throw new RuntimeException(e);
                        } catch (BadPaddingException e) {
                            throw new RuntimeException(e);
                        } catch (InvalidKeyException e) {
                            throw new RuntimeException(e);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            throw new FileNotFoundException();
        }
    }

    public Connection getConnection() throws ClassNotFoundException {
        Connection con = null;
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(url,user,d_password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public boolean closeConnection(Connection con) {
    if (con == null)
        return true;

    try{
        con.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return true;
    }
}
