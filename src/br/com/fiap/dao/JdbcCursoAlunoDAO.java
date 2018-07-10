package br.com.fiap.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import br.com.fiap.entity.CursoAluno;
import br.com.fiap.entity.CursoCapacitacao;
import br.com.fiap.entity.Escola;
import br.com.fiap.mapper.CursoCapacitacaoMapper;
import br.com.fiap.mapper.EscolaMapper;

public class JdbcCursoAlunoDAO {
	
	//private JdbcTemplate jdbcTemplate;
	
	private DataSource dataSource;
	
	//propriedade: dataSource
	public void setDataSource(DataSource dataSource) {
		//this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}
	
	public void excluirCursoAluno(int id) throws Exception{
		try {
			System.out.println("EXCLUSAO INICIADA");
			String sql = "DELETE FROM curso_aluno WHERE id = ?";
			JdbcTemplate template = new JdbcTemplate(this.dataSource);
			Object[] params = {id};
			int rows = template.update(
					sql,
					params);
			System.out.println(rows + " REGISTROS EXCLUÍDOS");
		}catch (Exception e) {
			throw e;
		}
	}
	
}
