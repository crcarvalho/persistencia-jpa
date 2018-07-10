package br.com.fiap.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcAlunoDAO {
	
	//private JdbcTemplate jdbcTemplate;
	
	private DataSource dataSource;
	
	//propriedade: dataSource
	public void setDataSource(DataSource dataSource) {
		//this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}
	
	public void excluir(Long id) throws Exception{
		try {
			System.out.println("EXCLUSAO INICIADA");
			String sql = "DELETE FROM ALUNO WHERE ID = ?";
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
