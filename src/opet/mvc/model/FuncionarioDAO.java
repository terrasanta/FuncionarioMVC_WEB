package opet.mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import opet.mvc.vo.Diretor;
import opet.mvc.vo.Funcionario;
import opet.mvc.vo.Professor;
import opet.mvc.vo.Secretario;

public class FuncionarioDAO implements IFuncionarioDAO
{

    public static final String DB_USUARIO = "Israel";
    public static final String DB_PASS    = "aWp524Cc";
    public static final String DB_URL     = "jdbc:oracle:thin:@localhost:1521:XE";

    public FuncionarioDAO()
    {

    }

    @Override
    public boolean incluir(Funcionario pFuncionario)
    {
        Connection connection = null;
        try
        {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.print("Conectando ao banco...");
            connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
            System.out.println("Conectado!");

            PreparedStatement pstmt = connection
                            .prepareStatement("INSERT INTO FUNCIONARIO (NOME, SALARIO, TIPO) values (?,?,?)");
            pstmt.setString(1, pFuncionario.getNome());
            pstmt.setDouble(2, pFuncionario.getSalario());
            if (pFuncionario instanceof Professor)
                pstmt.setInt(3, 3);
            else
                if (pFuncionario instanceof Secretario)
                    pstmt.setInt(3, 2);
                else
                    pstmt.setInt(3, 1);
            pstmt.executeUpdate();
            return true;
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
            	if(connection != null)
            		connection.close();
            }
            catch (SQLException Except)
            {
                Except.printStackTrace();
            }
        }
    }

    @Override
    public List<Funcionario> listarTodos()
    {
        Connection connection = null;
        
        List <Funcionario> listaFuncionario = new ArrayList<Funcionario>();
        
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.print("Conectando ao banco...");
            connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
            System.out.println("Conectado!");

            PreparedStatement pstmt = connection
                            .prepareStatement("select nome, salario, tipo from funcionario");
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
            	Funcionario f;
            	
            	int tipo = rs.getInt("tipo");
            	String nome = rs.getString("nome");
            	Double salario = rs.getDouble("salario");
            	
            	if (tipo == 1)
            		f = new Diretor(nome, salario);
                else if (tipo == 2) {
                	f = new Secretario(nome, salario);
                }else {
                	f = new Professor(nome, salario);
                }
            	
            	listaFuncionario.add(f);
            	
            }
            return listaFuncionario;
            
        }catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
            	if(connection != null)
            		connection.close();
            }
            catch (SQLException Except)
            {
                Except.printStackTrace();
            }
        }
    }
    
    @Override
    public List<Funcionario> listar(String nomeFuncionario)
    {
        Connection connection = null;
        
        List <Funcionario> listaFuncionario = new ArrayList<Funcionario>();
        
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.print("Conectando ao banco...");
            connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
            System.out.println("Conectado!");

            PreparedStatement pstmt = connection
                            .prepareStatement("select nome, salario, tipo from funcionario where lower(nome) like lower( ? ) || '%'");
            pstmt.setString(1, nomeFuncionario);
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
            	Funcionario f;
            	
            	int tipo = rs.getInt("tipo");
            	String nome = rs.getString("nome");
            	Double salario = rs.getDouble("salario");
            	
            	if (tipo == 1)
            		f = new Diretor(nome, salario);
                else if (tipo == 2) {
                	f = new Secretario(nome, salario);
                }else {
                	f = new Professor(nome, salario);
                }
            	
            	listaFuncionario.add(f);
            	
            }
            return listaFuncionario;
            
        }catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
            	if(connection != null)
            		connection.close();
            }
            catch (SQLException Except)
            {
                Except.printStackTrace();
            }
        }
    }

}
