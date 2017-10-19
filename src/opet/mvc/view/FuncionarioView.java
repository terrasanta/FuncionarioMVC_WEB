package opet.mvc.view;

import java.util.List;

import opet.mvc.controller.FuncionarioController;
import opet.mvc.util.Leitor;
import opet.mvc.vo.Diretor;
import opet.mvc.vo.Funcionario;
import opet.mvc.vo.Professor;
import opet.mvc.vo.Secretario;

public class FuncionarioView {

	private final int MENU_INCLUIR = 1;
	//private final int MENU_ALTERAR = 2;
	//private final int MENU_EXCLUIR = 3;
	private final int MENU_LISTAR = 4;
	private final int MENU_SAIR = 0;

	private FuncionarioController fc;
	public FuncionarioView() {
		fc = new FuncionarioController();
	}

	public void menu() {
		int opc = -1;
		do {
			System.out.println("MENU DE OPÇÕES");
			System.out.println("=================");
			System.out.println("Selecione uma opção");
			System.out.println("Digite 1 para cadastrar");
			System.out.println("Digite 2 para alterar");
			System.out.println("Digite 3 para excluir");
			System.out.println("Digite 4 para listar");
			System.out.println("Digite 0 para sair");

			opc = Leitor.readInt("Digite sua opção: ");

			switch (opc) {
			case MENU_INCLUIR:
				incluir();
				break;
			case MENU_LISTAR:
				listar();
				break;
			case MENU_SAIR:
				System.out.println("Até logo!");
				break;
			default:
				System.out.println("Verifique sua opção");
			}
		} while (opc != 0);
	}

	public void incluir() {
		String nome = Leitor.readString("Entre com o nome: ");
		Double salario = Leitor.readDouble("Entre com o valor do salário: ");
		int tipo = Leitor.readInt("Entre com o tipo (1 - Diretor | 2 - Secretario | 3 - Professor): ");

		Funcionario novoFuncionario;
		if (tipo == 1) {
			novoFuncionario = new Diretor(nome, salario);
		} else if (tipo == 2) {
			novoFuncionario = new Secretario(nome, salario);
		} else {
			novoFuncionario = new Professor(nome, salario);
		}
		
		if(fc.incluir(novoFuncionario)) {
			System.out.println("Funcionário inserido com sucesso.");
		}else {
			System.out.println("Erro ao incluir funcionário");
		}

	}

	public void listar() {
		List<Funcionario> lf = fc.listarTodos();

		if (lf == null) {
			System.out.println("Erro");
		} else if (lf.isEmpty()) {
			System.out.println("Lista vazia");
		} else {
			for (Funcionario funcionario : lf) {
				int tipo;
				if (funcionario instanceof Professor)
					tipo = 3;
				else if (funcionario instanceof Secretario)
					tipo = 2;
				else
					tipo = 1;

				System.out.println("Nome: " + funcionario.getNome() + " - Salario: " + funcionario.getSalario() + " - Tipo: "+tipo);
				System.out.println("==========================================");
			}
		}
	}

}
