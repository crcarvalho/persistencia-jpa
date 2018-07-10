package br.com.fiap.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.fiap.entity.Escola;

public class EscolaMapper implements RowMapper<Escola>{
	
	@Override
	public Escola mapRow(ResultSet result, int item) throws SQLException {
		Escola escola = new Escola();
		escola.setId(result.getInt("ID"));
		escola.setCnpj(result.getString("CNPJ"));
		escola.setDataString(result.getString("DT_FUNDACAO"));
		escola.setEndereco(result.getString("ENDERECO"));
		escola.setNomeFantasia(result.getString("NOME_FANTASIA"));
		escola.setRazaoSocial(result.getString("NOME_FANTASIA"));
		return escola;
	}

}
