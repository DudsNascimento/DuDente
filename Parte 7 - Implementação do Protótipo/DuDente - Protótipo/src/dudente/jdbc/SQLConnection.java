/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dudente.jdbc;

import java.sql.*;

/**
 *
 * @author duds-ufjf
 */
public class SQLConnection {
    
    private Connection connection;
    private Statement statement;
    protected ResultSet resultSet;

    public SQLConnection(){
        
        connection = null;
        statement = null;
        resultSet = null;
    }
    
    public void IniciarConexao(String url, String user, String password) throws ClassNotFoundException, SQLException{
        
        Class.forName("com.mysql.jdbc.Driver");
        
        connection = DriverManager.getConnection(url, user, password);
        
        if(connection != null){

            statement = connection.createStatement();        
        }
    }
    
    public boolean ExecutarQuery(String comandoSQL) throws SQLException{

        if(resultSet != null){
            
            resultSet.close();
        }
        
        if(statement != null){
            
            resultSet = statement.executeQuery(comandoSQL);
            return (resultSet != null);
        }
        return false;
    }
    
    public boolean ExecutarUpdate(String comandoSQL) throws SQLException{

        if(resultSet != null){
            
            resultSet.close();
        }
        
        if(statement != null){
            
            return (statement.executeUpdate(comandoSQL) == 0);
        }
        return false;
    }
    
    public void EncerrarConexao() throws SQLException{
        
        if(resultSet != null){
            
            resultSet.close();
        }
        
        if(statement != null){
            
            statement.close();
        }
        
        if(connection != null){
            
            connection.close();
        }
    }
}
