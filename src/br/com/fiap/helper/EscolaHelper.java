package br.com.fiap.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.entity.CursoCapacitacao;
import br.com.fiap.entity.Escola;

public class EscolaHelper {
	
	private EntityManager em;
	
	public EscolaHelper(EntityManager em) {
		this.em = em;
	}
	
	public String salvar(Escola escola) {
		String msgRetorno;
		try {
			em.getTransaction().begin();
			em.persist(escola);
			em.getTransaction().commit();
			msgRetorno = "Escola incluída com sucesso!";
		}catch(Exception e) {
			msgRetorno = e.getMessage();
		}
		return msgRetorno;
	}
	
	public String adicionarCursoCapacitacao(int idEscola, CursoCapacitacao curso) {
		String msgRetorno;
		try {
			Escola escola = em.find(Escola.class, idEscola);
			curso.setEscola(escola);
			escola.getCursos().add(curso);
			em.getTransaction().begin();
			em.persist(escola);
			em.getTransaction().commit();
			msgRetorno = "Curso adicionado à escola com sucesso!";
		} catch (Exception e) {
			msgRetorno = e.getMessage();
		}
		
		return msgRetorno;
	}
	
	public List<Escola> listarEscolas(){
		TypedQuery<Escola> query = em.createQuery("SELECT e FROM Escola e", Escola.class);
		return query.getResultList();
	}
	
	public List<CursoCapacitacao> listarCursos(int idEscola){
		TypedQuery<CursoCapacitacao> query = em.createQuery("Select c From Cursos p where p.escola.id = :idEscola", CursoCapacitacao.class);
		query.setParameter("idEvento", idEscola);
		return query.getResultList();
	}
	
	public String excluir(Escola escola) {
		String msgRetorno;
		try {
			em.getTransaction().begin();
			em.remove(escola);
			em.getTransaction().commit();
			msgRetorno = "O registro da escola foi excluído!";
		} catch (Exception e) {
			msgRetorno = e.getMessage();
		}
		return msgRetorno;
	}
	
	public Escola buscarEscola( int idEscola ) {
		try {
			TypedQuery<Escola> query = em.createQuery("SELECT e FROM Escola e where e.Id =: idEscola", Escola.class);
			query.setParameter("idEscola", idEscola);
			return query.getResultList().get(0);
		} catch (Exception e) {
			System.out.println("Não foi possível localizar a escola com id: "+idEscola);
			return null;
		}
	}
	
}
