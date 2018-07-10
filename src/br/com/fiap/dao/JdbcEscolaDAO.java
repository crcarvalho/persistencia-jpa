package br.com.fiap.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.jdbc.exceptions.jdbc4.MySQLDataException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.com.fiap.entity.CursoCapacitacao;
import br.com.fiap.entity.Escola;
import br.com.fiap.mapper.CursoCapacitacaoMapper;
import br.com.fiap.mapper.EscolaMapper;

public class JdbcEscolaDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	//propriedade: dataSource
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void incluirEscola(Escola escola) throws Exception{
		try {
			String sql = "INSERT INTO ESCOLA"
					+ " (RAZAO_SOCIAL, DT_FUNDACAO, ENDERECO, CNPJ, NOME_FANTASIA) VALUES (?,?,?,?,?)";
			this.jdbcTemplate.update(
					sql,
					escola.getRazaoSocial(),
					escola.getDataFundacao(),
					escola.getEndereco(),
					escola.getCnpj(),
					escola.getNomeFantasia());
		}catch (Exception e) {
			throw e;
		}
	}
	
	public Escola buscar(int idEscola) throws Exception {
		Escola escola = null;
		try {
			String query = "SELECT * FROM ESCOLA WHERE ID= ? ";
			escola = this.jdbcTemplate.queryForObject(
					query, 
					new Integer[] {idEscola}, 
					new EscolaMapper());
		} catch (Exception e) {
			throw e;
		}
		return escola;
	}
	
	public List<Escola> listarEscolas() throws Exception {
		List<Escola> escolas = new ArrayList<>();
		try {
			escolas = this.jdbcTemplate.query(
					"SELECT * FROM ESCOLA", new EscolaMapper());
		} catch (Exception e) {
			throw e;
		}
		return escolas;
	}
	
	public List<CursoCapacitacao> listarCursos(int idEscola) throws Exception {
		List<CursoCapacitacao> cursos = new ArrayList<>();
		try {
			cursos = this.jdbcTemplate.query(
					"SELECT * FROM CURSO_CAPACITACAO", new CursoCapacitacaoMapper());
		} catch (Exception e) {
			throw e;
		}
		return cursos;
	}
	
	public void excluir(int id) throws Exception{
		try {
			System.out.println("EXCLUSAO INICIADA");
			String sql = "DELETE FROM ESCOLA WHERE ID = ?";
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
