package br.com.vinicius.signo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.vinicius.signo.dto.PessoaDTO;
import br.com.vinicius.signo.model.Pessoa;
import br.com.vinicius.signo.model.Signo;

public class PessoaDAO {

	private final Connection conn;

	public PessoaDAO(Connection con) {
		this.conn = con;
	}

	public boolean inserir(Pessoa pessoa) throws SQLException {
		String sql = "INSERT INTO PESSOA (CODIGO, NOME, DT_NASCIMENTO, CODIGO_SIGNO) VALUES (SEQ_PESSOA.nextval, ?, ?, ?)";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, pessoa.getNome());
		statement.setDate(2, new Date(pessoa.getDtNascimento().getTime()));
		statement.setInt(3, pessoa.getSigno().getCodigo());

		return statement.executeUpdate() > 0;
	}

	public boolean alterar(Pessoa pessoa) throws SQLException {
		String sql = "UPDATE PESSOA SET NOME = ?, DT_NASCIMENTO = ?, CODIGO_SIGNO = ? WHERE CODIGO = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, pessoa.getNome());
		statement.setDate(2, new Date(pessoa.getDtNascimento().getTime()));
		statement.setInt(3, pessoa.getSigno().getCodigo());
		statement.setInt(4, pessoa.getCodigo());

		return statement.executeUpdate() > 0;
	}

	public boolean excluir(Integer codigo) throws SQLException {
		String sql = "DELETE PESSOA WHERE CODIGO = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, codigo);

		return statement.executeUpdate() > 0;
	}

	public List<PessoaDTO> lista() throws SQLException {
		List<PessoaDTO> lPessoas = new ArrayList<>();

		String sql = "SELECT PES.CODIGO, PES.NOME, PES.DT_NASCIMENTO, ";
		sql += "SIG.CODIGO, SIG.NOME, SIG.DIA_INICIAL, SIG.DIA_FINAL, SIG.MES_INICIAL, SIG.MES_FINAL ";
		sql += " FROM PESSOA PES ";
		sql += " INNER JOIN SIGNO SIG ON (PES.CODIGO_SIGNO = SIG.CODIGO) ";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int codigo = rs.getInt(1);
					String nome = rs.getString(2);
					Date dtNascimento = rs.getDate(3);
					int codigoSigno = rs.getInt(4);
					String nomeSigno = rs.getString(5);
					int diaInicial = rs.getInt(6);
					int diaFinal = rs.getInt(7);
					int mesInicial = rs.getInt(8);
					int mesFinal = rs.getInt(9);
					lPessoas.add(new Pessoa(codigo, nome, dtNascimento, new Signo(codigoSigno, nomeSigno, diaInicial, diaFinal, mesInicial, mesFinal)).toDTO());
				}
			}
		}

		return lPessoas;

	}
	
	public Pessoa buscarPessoaPorCodigo(Integer codigo) throws SQLException {
		Pessoa pessoa = null;

		String sql = "SELECT PES.CODIGO, PES.NOME, PES.DT_NASCIMENTO, ";
		sql += "SIG.CODIGO, SIG.NOME, SIG.DIA_INICIAL, SIG.DIA_FINAL, SIG.MES_INICIAL, SIG.MES_FINAL ";
		sql += " FROM PESSOA PES ";
		sql += " INNER JOIN SIGNO SIG ON (PES.CODIGO_SIGNO = SIG.CODIGO) ";
		sql += " WHERE PES.CODIGO = ? ";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, codigo);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int codigoPessoa = rs.getInt(1);
					String nome = rs.getString(2);
					Date dtNascimento = rs.getDate(3);
					int codigoSigno = rs.getInt(4);
					String nomeSigno = rs.getString(5);
					int diaInicial = rs.getInt(6);
					int diaFinal = rs.getInt(7);
					int mesInicial = rs.getInt(8);
					int mesFinal = rs.getInt(9);
					pessoa = new Pessoa(codigoPessoa, nome, dtNascimento, new Signo(codigoSigno, nomeSigno, diaInicial, diaFinal, mesInicial, mesFinal));
				}
			}
		}

		return pessoa;

	}
}
