package br.com.vinicius.signo.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import br.com.vinicius.signo.dao.PessoaDAO;
import br.com.vinicius.signo.dto.PessoaDTO;
import br.com.vinicius.signo.jdbc.oracle.ConnectionPoolOracle;
import br.com.vinicius.signo.model.Pessoa;
import br.com.vinicius.signo.model.Signo;

public class PessoaService {
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
	
	public void inserir(Pessoa pessoa) throws SQLException{
		try (Connection con = new ConnectionPoolOracle().getConnection()) {
			definirSignoPessoa(pessoa);
			new PessoaDAO(con).inserir(pessoa);
		}
	}

	private void definirSignoPessoa(Pessoa pessoa) throws SQLException {
		List<Signo> lSignos = new SignoService().listarSignos();
		for (Signo signo : lSignos) {
			LocalDate dtNascimento = pessoa.getDtNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if((dtNascimento.getMonthValue() == signo.getMesInicial() && dtNascimento.getDayOfMonth() >= signo.getDiaInicial())
					|| (dtNascimento.getMonthValue() == signo.getMesFinal() && dtNascimento.getDayOfMonth() <= signo.getDiaFinal())){
				pessoa.setSigno(signo);
				break;
			}
		}
	}
	
	public void alterar(Pessoa pessoa) throws SQLException{
		try (Connection con = new ConnectionPoolOracle().getConnection()) {
			definirSignoPessoa(pessoa);
			new PessoaDAO(con).alterar(pessoa);
		}
	}
	
	public void excluir(Integer codigo) throws SQLException{
		try (Connection con = new ConnectionPoolOracle().getConnection()) {
			new PessoaDAO(con).excluir(codigo);
		}
	}

	public List<PessoaDTO> listarPessoas() throws SQLException{
		try (Connection con = new ConnectionPoolOracle().getConnection()) {
            return new PessoaDAO(con).lista();
		}
	}
	
	public Pessoa buscarPessoaPorCodigo(Integer codigo) throws SQLException{
		try (Connection con = new ConnectionPoolOracle().getConnection()) {
            return new PessoaDAO(con).buscarPessoaPorCodigo(codigo);
		}
	}
	
}
