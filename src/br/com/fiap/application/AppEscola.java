package br.com.fiap.application;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.fiap.dao.JdbcAlunoDAO;
import br.com.fiap.dao.JdbcCursoAlunoDAO;
import br.com.fiap.dao.JdbcCursoCapacitacaoDAO;
import br.com.fiap.dao.JdbcEscolaDAO;
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
		while (true) {
			if (JOptionPane.showConfirmDialog(null, "Deseja incluir escola?", "Incluir Escola",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				incluirEscola();
			} else if (JOptionPane.showConfirmDialog(null, "Deseja incluir aluno?", "Incluir aluno",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				incluirAluno();
			} else if (JOptionPane.showConfirmDialog(null, "Deseja incluir aluno para um curso?",
					"incluir aluno ao curso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				AlunoHelper helperAluno = new AlunoHelper(em);
				List<Aluno> lstAluno = helperAluno.listarAluno();
				Aluno aluno = (Aluno) JOptionPane.showInputDialog(null, "Selecione o aluno", "Alunos",
						JOptionPane.INFORMATION_MESSAGE, null, lstAluno.toArray(), null);

				CursoCapacitacaoHelper cursoHelper = new CursoCapacitacaoHelper(em);
				List<CursoCapacitacao> lstCurso = cursoHelper.listarCursoCapacitacao();
				CursoCapacitacao curso = (CursoCapacitacao) JOptionPane.showInputDialog(null, "Selecione o curso",
						"cursos", JOptionPane.INFORMATION_MESSAGE, null, lstCurso.toArray(), null);
				incluirCursoAluno(aluno, curso);
			} else if (JOptionPane.showConfirmDialog(null, "Deseja incluir nota ao aluno?", "Incluir nota ao aluno",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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

			ApplicationContext context = new ClassPathXmlApplicationContext("beanJdbc.xml");

			if (JOptionPane.showConfirmDialog(null, "Excluir curso aluno?", "Excluir curso aluno",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				CursoAlunoHelper caHelper = new CursoAlunoHelper(em);
				List<CursoAluno> lstCursoAluno = caHelper.listarCursoAluno();
				CursoAluno cursoAluno = (CursoAluno) JOptionPane.showInputDialog(null,
						"Selecione o curso do aluno para exclusão", "Curso aluno", JOptionPane.INFORMATION_MESSAGE,
						null, lstCursoAluno.toArray(), null);

				JdbcCursoAlunoDAO daoCursoAluno = (JdbcCursoAlunoDAO) context.getBean("jdbcCursoAlunoDAO");
				try {
					caHelper.excluir(cursoAluno);
					// daoCursoAluno.excluirCursoAluno(cursoAluno.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (JOptionPane.showConfirmDialog(null, "Excluir aluno?", "Excluir aluno",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				AlunoHelper alunoHelper = new AlunoHelper(em);
				List<Aluno> lstAluno = alunoHelper.listarAluno();
				Aluno aluno = (Aluno) JOptionPane.showInputDialog(null, "Selecione o aluno", "Aluno",
						JOptionPane.INFORMATION_MESSAGE, null, lstAluno.toArray(), null);
				JdbcAlunoDAO daoAluno = (JdbcAlunoDAO) context.getBean("jdbcAlunoDAO");
				try {
					alunoHelper.excluir(aluno);
					// daoAluno.excluir(aluno.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (JOptionPane.showConfirmDialog(null, "Excluir curso?", "Excluir curso",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				CursoCapacitacaoHelper cursoHelper = new CursoCapacitacaoHelper(em);
				List<CursoCapacitacao> lstCurso = cursoHelper.listarCursoCapacitacao();
				CursoCapacitacao curso = (CursoCapacitacao) JOptionPane.showInputDialog(null,
						"Selecione o curso para exclusão", "Escola", JOptionPane.INFORMATION_MESSAGE, null,
						lstCurso.toArray(), null);

				JdbcCursoCapacitacaoDAO daoCurso = (JdbcCursoCapacitacaoDAO) context.getBean("jdbcCursoCapacitacaoDAO");
				try {
					daoCurso.excluir(curso.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (JOptionPane.showConfirmDialog(null, "Excluir escola?", "excluir escola",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				EscolaHelper escolaHelper = new EscolaHelper(em);
				List<Escola> lstEscola = escolaHelper.listarEscolas();
				Escola escola = (Escola) JOptionPane.showInputDialog(null, "Selecione a escola para exclusão", "Escola",
						JOptionPane.INFORMATION_MESSAGE, null, lstEscola.toArray(), null);
				JdbcEscolaDAO daoEscola = (JdbcEscolaDAO) context.getBean("jdbcEscolaDAO");
				try {
					escolaHelper.excluir(escola);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (JOptionPane.showConfirmDialog(null, "Atualizar nota/conclusão do aluno?", "atualizar nota",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				CursoAlunoHelper caHelper = new CursoAlunoHelper(em);
				List<CursoAluno> lstCursoAluno = caHelper.listarCursoAluno();
				CursoAluno cursoAluno = (CursoAluno) JOptionPane.showInputDialog(null,
						"Selecione o curso do aluno para exclusão", "Curso aluno", JOptionPane.INFORMATION_MESSAGE,
						null, lstCursoAluno.toArray(), null);
				String nota = JOptionPane.showInputDialog("Informe a nota do aluno");
				if (nota == null)
					nota = String.valueOf(cursoAluno.getNotaFinal());
				cursoAluno.setNotaFinal(Float.parseFloat(nota));
				if (JOptionPane.showConfirmDialog(null, "Aluno concluiu curso?", "atualizar nota",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					cursoAluno.setDataConclusaoString(
							JOptionPane.showInputDialog("Informe a data de conclusão do curso (dd/MM/YYYY)"));
				}
				caHelper.salvar(cursoAluno);
			}
		}
	}

	private static void incluirEscola() {

		EscolaHelper helper = new EscolaHelper(em);
		Escola escola = new Escola();
		escola.setNomeFantasia(JOptionPane.showInputDialog("Informe o nome da escola"));
		escola.setCnpj(JOptionPane.showInputDialog("Informe o cnpj"));
		escola.setDataString(JOptionPane.showInputDialog("Informe a data de fundação (dd/MM/YYYY)"));
		escola.setEndereco(JOptionPane.showInputDialog("Informe o endereço"));
		escola.setRazaoSocial(escola.getNomeFantasia());

		if (JOptionPane.showConfirmDialog(null, "Deseja incluir curso?", "Incluir Curso",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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

			if (isInsertCurso) {
				System.out.println("chamou insert curso");
				System.out.println(helper.salvar(curso));
				System.out.println("curso inserido");
			} else {
				escola.getCursos().add(curso);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void incluirAluno() {
		try {
			AlunoHelper helper = new AlunoHelper(em);
			Aluno aluno = new Aluno();
			aluno.setNome(JOptionPane.showInputDialog("Informe o nome"));
			aluno.setCpf(JOptionPane.showInputDialog("Informe o CPF"));
			aluno.setCelular(JOptionPane.showInputDialog("Informe o celular"));
			aluno.setDataString(JOptionPane.showInputDialog("Informe a data de nasciemnto(dd/MM/YYYY)"));
			aluno.setEmail(JOptionPane.showInputDialog("Informe o e-mail"));
			aluno.setEndereco(JOptionPane.showInputDialog("Informe o endereço"));
			aluno.setRg(JOptionPane.showInputDialog("Informe o RG"));
			aluno.setSexo(JOptionPane.showInputDialog("Informe o sexo(M|F)"));
			aluno.setTelefone(JOptionPane.showInputDialog("Informe o telefone"));
			System.out.println(helper.salvar(aluno));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
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