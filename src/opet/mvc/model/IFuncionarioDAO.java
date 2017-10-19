package opet.mvc.model;

import java.util.List;

import opet.mvc.vo.Funcionario;

public interface IFuncionarioDAO
{
    public boolean incluir(Funcionario funcionario);

    public List<Funcionario> listarTodos();
    
    public List<Funcionario> listar(String nome);
}
