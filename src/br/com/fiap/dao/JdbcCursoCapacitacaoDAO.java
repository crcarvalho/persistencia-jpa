package br.com.fiap.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import br.com.fiap.entity.CursoCapacitacao;
import br.com.fiap.mapper.CursoCapacitacaoMapper;

public class JdbcCursoCapacitacaoDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	//propriedade: dataSource
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void incluir(CursoCapacitacao curso) throws Exception{
		try {
			String sql = "INSERT INTO curso_capacitacao "
					+ "(NOME, DURACAO, AREA, DESCRICAO, CUSTO, IDESCOLA) VALUES (?,?,?,?,?,?)";
			this.jdbcTemplate.update(
					sql,
					curso.getNome(),
					curso.getDuracao(),
					curso.getArea(),
					curso.getDescricao(),
					curso.getCusto(),
					curso.getEscola().getId());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public CursoCapacitacao buscar(int idCurso) throws Exception {
		CursoCapacitacao curso = null;
		try {
			String query = "SELECT * FROM ESCOLA WHERE ID= ? ";
			curso = this.jdbcTemplate.queryForObject(
					query, 
					new Integer[] {idCurso}, 
					new CursoCapacitacaoMapper());
		} catch (Exception e) {
			throw e;
		}
		return curso;
	}
	
	public List<CursoCapacitacao> listaCursos() throws Exception {
		List< CursoCapacitacao > cursos = new ArrayList<>();
		try {
			cursos = this.jdbcTemplate.query(
					"SELECT * FROM curso_capacitacao", new CursoCapacitacaoMapper());
		} catch (Exception e) {
			throw e;
		}
		return cursos;
	}
	
	public void excluir(int id) throws Exception{
		try {
			System.out.println("EXCLUSAO INICIADA");
			String sql = "DELETE FROM ALUNO WHERE ID = ?";
			Object[] params = {id};
			int rows = this.jdbcTemplate.update(
					sql,
					params);
			System.out.println(rows + " REGISTROS EXCLUÍDOS");
		}catch (Exception e) {
			throw e;
		}
	}
	
}
