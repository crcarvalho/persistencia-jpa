package br.com.fiap.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.fiap.entity.CursoCapacitacao;
import br.com.fiap.entity.Escola;

public class CursoCapacitacaoMapper implements RowMapper<CursoCapacitacao>{
	
	@Override
	public CursoCapacitacao mapRow(ResultSet rs, int arg1) throws SQLException {
		CursoCapacitacao curso = new CursoCapacitacao();
		curso.setId(rs.getInt("ID"));
		curso.setArea(rs.getString("AREA"));
		curso.setCusto(rs.getFloat("CUSTO"));
		curso.setDescricao(rs.getString("DESCRICAO"));
		curso.setDuracao(rs.getInt("DURACAO"));
		
		Escola escola = new Escola();
		escola.setId(rs.getInt("IDESCOLA"));
		curso.setEscola(escola);
		
		curso.setNome(rs.getString("NOME"));
		
		return curso;
	}
	
}
