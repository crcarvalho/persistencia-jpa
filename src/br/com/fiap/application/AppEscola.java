package br.com.fiap.application;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.CursoAluno;
import br.com.fiap.entity.CursoCapacitacao;
import br.com.fiap.entity.Escola;
import br.com.fiap.helper.AlunoHelper;
import br.com.fiap.helper.CursoAlunoHelper;
import br.com.fiap.helper.CursoCapacitacaoHelper;
import br.com.fiap.helper.EscolaHelper;

public class AppEscola {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("JPA_PROJECT_PERSISTENCIA");
		em = emf.createEntityManager();
		if( JOptionPane.showConfirmDialog(null, "Deseja incluir escola?", "Incluir Escola", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			incluirEscola();
		}else if(JOptionPane.showConfirmDialog(null, "Deseja incluir aluno?", "Incluir aluno", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			incluirAluno();
		}else if( JOptionPane.showConfirmDialog(null, "Adicionar aluno para um curso?", "Adicionar aluno ao curso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			AlunoHelper helperAluno = new AlunoHelper(em);
			List<Aluno> lstAluno = helperAluno.listarAluno();
			Aluno aluno = (Aluno) JOptionPane.showInputDialog(null, "Selecione o aluno", "Alunos",
					JOptionPane.INFORMATION_MESSAGE, null, lstAluno.toArray(), null);
			
			CursoCapacitacaoHelper cursoHelper = new CursoCapacitacaoHelper(em);
			List<CursoCapacitacao> lstCurso = cursoHelper.listarCursoCapacitacao();
			CursoCapacitacao curso = (CursoCapacitacao) JOptionPane.showInputDialog(null, "Selecione o curso", "cursos",
					JOptionPane.INFORMATION_MESSAGE, null, lstCurso.toArray(), null);
			incluirCursoAluno( aluno, curso );
		}else if(JOptionPane.showConfirmDialog(null, "Adicionar nota ao aluno?", "Adicionar nota ao aluno", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			AlunoHelper helperAluno = new AlunoHelper(em);
			List<Aluno> lstAluno = helperAluno.listarAluno();
			Aluno aluno = (Aluno) JOptionPane.showInputDialog(null, "Selecione o aluno", "Alunos",
					JOptionPane.INFORMATION_MESSAGE, null, lstAluno.toArray(), null);
			
			Set<CursoAluno> setCursoAluno = aluno.getCursosAluno();
			CursoAluno cursoAluno = (CursoAluno) JOptionPane.showInputDialog(null, "Selecione o curso", "cursos",
					JOptionPane.INFORMATION_MESSAGE, null, setCursoAluno.toArray(), null);
			String nota = JOptionPane.showInputDialog("Informe a nota do aluno");
			CursoAlunoHelper caHelper = new CursoAlunoHelper(em);
			cursoAluno.setNotaFinal(Float.parseFloat(nota));
			caHelper.salvar(cursoAluno);
		}
		
		if(JOptionPane.showConfirmDialog(null, "Excluir curso aluno?", "Excluir curso aluno", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			CursoAlunoHelper caHelper = new CursoAlunoHelper(em);
			List< CursoAluno > lstCursoAluno = caHelper.listarCursoAluno();
			CursoAluno cursoAluno = (CursoAluno) JOptionPane.showInputDialog(null, "Selecione o curso do aluno para exclusão", "Curso aluno",
					JOptionPane.INFORMATION_MESSAGE, null, lstCursoAluno.toArray(), null);
			caHelper.excluir(cursoAluno);
		} else if (JOptionPane.showConfirmDialog(null, "Excluir aluno?", "Excluir aluno", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			AlunoHelper alunoHelper = new AlunoHelper(em);
			List< Aluno > lstAluno = alunoHelper.listarAluno();
			Aluno aluno = (Aluno) JOptionPane.showInputDialog(null, "Selecione o aluno", "Aluno",
					JOptionPane.INFORMATION_MESSAGE, null, lstAluno.toArray(), null);
			alunoHelper.excluir(aluno);
		} else if (JOptionPane.showConfirmDialog(null, "Excluir curso?", "Excluir curso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			CursoCapacitacaoHelper cursoHelper = new CursoCapacitacaoHelper(em);
			List< CursoCapacitacao > lstCurso = cursoHelper.listarCursoCapacitacao();
			CursoCapacitacao curso = (CursoCapacitacao) JOptionPane.showInputDialog(null, "Selecione o curso para exclusão", "Escola",
					JOptionPane.INFORMATION_MESSAGE, null, lstCurso.toArray(), null);
			cursoHelper.excluir(curso);
		} else if ( JOptionPane.showConfirmDialog(null, "Excluir escola?", "excluir escola", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION ) {
			EscolaHelper escolaHelper = new EscolaHelper(em);
			List< Escola > lstEscola = escolaHelper.listarEscolas();
			Escola escola = (Escola) JOptionPane.showInputDialog(null, "Selecione a escola para exclusão", "Escola",
					JOptionPane.INFORMATION_MESSAGE, null, lstEscola.toArray(), null);
			escolaHelper.excluir(escola);
		}
		
	}

	private static void incluirEscola() {
		
		EscolaHelper helper = new EscolaHelper(em);
		Escola escola = new Escola();
		/*escola.setNomeFantasia("Nome escola");
		escola.setCnpj("123654");
		escola.setDataString("12/12/2012");
		escola.setEndereco("ENDERECO");
		escola.setRazaoSocial("razao social");*/
		
		escola.setNomeFantasia(JOptionPane.showInputDialog("Informe o nome da escola"));
		escola.setCnpj(JOptionPane.showInputDialog("Informe o cnpj"));
		escola.setDataString(JOptionPane.showInputDialog("Informe a data de fundação (dd/MM/YYYY)"));
		escola.setEndereco(JOptionPane.showInputDialog("Informe o endereço"));
		escola.setRazaoSocial(escola.getNomeFantasia());
		
		if( JOptionPane.showConfirmDialog(null, "Deseja incluir curso?", "Incluir Curso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			incluirCurso(escola, false);
		}

		System.out.println(helper.salvar(escola));
	}
	
	private static void incluirCurso(Escola aEscola, boolean isInsertCurso) {
		try {
			System.out.println("Entrou incluirCurso");
			CursoCapacitacaoHelper helper = new CursoCapacitacaoHelper(em);
			
			CursoCapacitacao curso = new CursoCapacitacao();
			curso.setArea(JOptionPane.showInputDialog("Informe a area do curso"));
			String custo = JOptionPane.showInputDialog("Informe o custo");
			float valorCurso = Float.parseFloat(custo);
			curso.setCusto(valorCurso);
			curso.setDescricao(JOptionPane.showInputDialog("Informe a descrição"));
			
			String duracao = JOptionPane.showInputDialog("Informe a duração");
			int duracaoNum = Integer.parseInt(duracao);
			curso.setDuracao(duracaoNum);
			
			Escola escola = new Escola();
			escola = aEscola;
			
			curso.setEscola(escola);
			curso.setNome(curso.getDescricao());
			
			if( isInsertCurso ) {
				System.out.println("chamou insert curso");
				System.out.println(helper.salvar(curso));
				System.out.println("curso inserido");
			}else {
				escola.getCursos().add(curso);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void incluirAluno() {
		try {
			AlunoHelper helper = new AlunoHelper(em);
			Aluno aluno = new Aluno();
			aluno.setCelular("11123456789");
			aluno.setCpf(JOptionPane.showInputDialog("Informe o CPF"));
			aluno.setDataString("11/01/1999");
			aluno.setEmail("teste@teste.com.br");
			aluno.setEndereco("teste");
			aluno.setNome("carlos teste");
			aluno.setRg(JOptionPane.showInputDialog("Informe o RG"));
			aluno.setSexo("M");
			aluno.setTelefone("1123456789");
			
			/*
			aluno.setNome(JOptionPane.showInputDialog("Informe o nome"));
			aluno.setCelular(JOptionPane.showInputDialog("Informe o cleular"));
			aluno.setTelefone(aluno.getCelular());*/
			System.out.println(helper.salvar(aluno));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}
	
	private static void incluirCursoAluno(Aluno aluno, CursoCapacitacao curso) {
		try {
			CursoAlunoHelper caHelper = new CursoAlunoHelper(em);
			CursoAluno cursoAluno = new CursoAluno();
			cursoAluno.setAluno(aluno);
			cursoAluno.setCurso(curso);
			cursoAluno.setDataInicioString(JOptionPane.showInputDialog("Informe a data inicio (dd/MM/YYYY"));
			caHelper.salvar(cursoAluno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}