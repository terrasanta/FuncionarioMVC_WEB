package opet.mvc.controller;

import java.util.List;

import opet.mvc.model.FuncionarioModel;
import opet.mvc.vo.Funcionario;

public class FuncionarioController
{

	private FuncionarioModel funcionarioModel;
    public FuncionarioController()
    {
    	funcionarioModel = new FuncionarioModel();
    }
    
    public boolean incluir(Funcionario pFuncionario)
    {
        return funcionarioModel.incluir(pFuncionario);
    }
    public List<Funcionario> listarTodos()
    {
        return funcionarioModel.listarTodos();
    }
    public List<Funcionario> listar(String nome){
    	return funcionarioModel.listar(nome);
    }

}
