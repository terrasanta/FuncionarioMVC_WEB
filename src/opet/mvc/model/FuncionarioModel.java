package opet.mvc.model;

import java.util.List;

import opet.mvc.vo.Funcionario;

public class FuncionarioModel
{
	private FuncionarioDAO funcionarioDAO;
    public FuncionarioModel()
    {
    	funcionarioDAO = new FuncionarioDAO();
    }
    public boolean incluir(Funcionario pFuncionario)
    {
        return funcionarioDAO.incluir(pFuncionario);
    }
    public List<Funcionario> listarTodos()
    {
        return funcionarioDAO.listarTodos();
    }
    public List<Funcionario> listar(String nome)
    {
        return funcionarioDAO.listar(nome);
    }

}
