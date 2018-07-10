package br.com.fiap.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.CursoAluno;
import br.com.fiap.entity.CursoCapacitacao;

public class CursoCapacitacaoHelper {
	
	
	private EntityManager em;
	
	public CursoCapacitacaoHelper(EntityManager em) {
		this.em = em;
	}
	
	//Salvar o registro de CursoCapacitacao na base de dados 
	public String salvar(CursoCapacitacao curso){
		String msgRetorno;
		try {
			em.getTransaction().begin();
			em.persist(curso);
			em.getTransaction().commit();
			msgRetorno = "Curso capaciação foi salvo com sucesso!";
		} catch (Exception e) {
			msgRetorno = e.getMessage();
		}
		return msgRetorno;
	}
	
	//Adiciona um registro de Curso aluno relcinado a m Curso e a um Aluno
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
	
	//Retorna uma lista de CursoCapacitacao
	public List<CursoCapacitacao> listarCursoCapacitacao(){
		TypedQuery<CursoCapacitacao> query = em.createQuery("SELECT c FROM CursoCapacitacao c", CursoCapacitacao.class);
		return query.getResultList();
	}
	
	//Retorno uma lista de cursos referenciados com aluno
	public List<CursoAluno> listarCursoAluno(int idCurso){
		TypedQuery<CursoAluno> query = em.createQuery("Select c From CursoAluno c where c.CursoCapacitacao.id = :idCurso", CursoAluno.class);
		query.setParameter("idCurso", idCurso);
		return query.getResultList();
	}
	
	public String excluir(CursoCapacitacao curso) {
		String msgRetorno;
		try {
			em.getTransaction().begin();
			em.remove(curso);
			em.getTransaction().commit();
			msgRetorno = "Curso capacitação excluído com sucesso!";
		} catch (Exception e) {
			msgRetorno = e.getMessage();
		}
		return msgRetorno;
	}
	
	public CursoCapacitacao buscarCursoCapacitacao( int idCurso ) {
		try {
			TypedQuery<CursoCapacitacao> query = em.createQuery("SELECT c FROM CursoCapacitacao c where c.Id =: idCurso", CursoCapacitacao.class);
			query.setParameter("idCurso", idCurso);
			return query.getResultList().get(0);
		} catch (Exception e) {
			System.out.println("Não foi possível localizar a escola com id: "+idCurso);
			return null;
		}
	}
}
