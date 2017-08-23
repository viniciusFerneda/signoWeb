package br.com.vinicius.signo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.vinicius.signo.model.Signo;

public class SignoDAO {

	private final Connection conn;
	
	public SignoDAO(Connection con) {
		this.conn = con;
	}
	
	public List<Signo> lista() throws SQLException {
		List<Signo> lSignos = new ArrayList<>();

		String sql = "SELECT * FROM SIGNO";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int codigo = rs.getInt("CODIGO");
					String nome = rs.getString("NOME");
					int diaInicial = rs.getInt("DIA_INICIAL");
					int diaFinal = rs.getInt("DIA_FINAL");
					int mesInicial = rs.getInt("MES_INICIAL");
					int mesFinal = rs.getInt("MES_FINAL");
					Signo signo = new Signo(codigo, nome, diaInicial, diaFinal, mesInicial, mesFinal);
					lSignos.add(signo);
				}
			}
		}

		return lSignos;

	}
}
