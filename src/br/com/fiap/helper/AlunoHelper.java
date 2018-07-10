package br.com.fiap.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.CursoAluno;
import br.com.fiap.entity.CursoCapacitacao;
import br.com.fiap.entity.Escola;

public class AlunoHelper {
	
	private EntityManager em;
	
	public AlunoHelper(EntityManager em) {
		this.em = em;
	}
	
	//Salvar o registro de Aluno na base de dados 
	public String salvar(Aluno aluno){
		String msgRetorno;
		try {
			System.out.println("INICIOU O SAVE");
			em.getTransaction().begin();
			System.out.println("SALVANDO ALUNO");
			em.persist(aluno);
			System.out.println("COMMIT ALUNO");
			em.getTransaction().commit();
			msgRetorno = "Aluno foi salvo com sucesso!";
		} catch (Exception e) {
			msgRetorno = e.getMessage();
		}
		return msgRetorno;
	}
	
	//Adiciona um registro de Curso aluno relacionado a m Curso e a um Aluno
	public String adicionarCursoAluno(int idCurso, int idAluno, CursoAluno cursoAluno) {
		String msgRetorno;
		try {
			CursoCapacitacao curso = em.find(CursoCapacitacao.class, idCurso);
			Aluno aluno = em.find(Aluno.class, idAluno);
			cursoAluno.setAluno(aluno);
			cursoAluno.setCurso(curso);
			curso.getCursosAluno().add(cursoAluno);
			aluno.getCursosAluno().add(cursoAluno);
			em.getTransaction().begin();
			em.persist(cursoAluno);
			em.getTransaction().commit();
			msgRetorno = "Curso aluno salvo com sucesso!";
		} catch (Exception e) {
			msgRetorno = e.getMessage();
		}
		return msgRetorno;
	}
	
	//Retorna uma lista de Aluno
	public List<Aluno> listarAluno(){
		TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a", Aluno.class);
		return query.getResultList();
	}
	
	//Retorno uma lista de cursos referenciados com aluno
	public List<CursoAluno> listarCursoAluno(int idAluno){
		TypedQuery<CursoAluno> query = em.createQuery("Select c From CursoAluno c where c.Aluno.id = :idAluno", CursoAluno.class);
		query.setParameter("idAluno", idAluno);
		return query.getResultList();
	}
	
	//Excluir registro de aluno
	public String excluir(Aluno aluno) {
		String msgRetorno;
		try {
			em.getTransaction().begin();
			em.remove(aluno);
			em.getTransaction().commit();
			msgRetorno = "O registro de aluno foi excluído!";
		} catch (Exception e) {
			msgRetorno = e.getMessage();
		}
		return msgRetorno;
	}
	
	
}
