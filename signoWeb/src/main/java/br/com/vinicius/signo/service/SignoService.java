package br.com.vinicius.signo.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.vinicius.signo.dao.SignoDAO;
import br.com.vinicius.signo.jdbc.oracle.ConnectionPoolOracle;
import br.com.vinicius.signo.model.Signo;

public class SignoService {
	
	public List<Signo> listarSignos() throws SQLException{
		try (Connection con = new ConnectionPoolOracle().getConnection()) {
            return new SignoDAO(con).lista();
		}
	}
	
}
