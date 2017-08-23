package br.com.vinicius.signo.dto;

import br.com.vinicius.signo.model.Pessoa;
import br.com.vinicius.signo.utils.DateUtils;

public class PessoaDTO {

	private Integer codigo;
	private String nome;
	private String dtNascimento;
	private String signo;

	public PessoaDTO() {
	}
	
	public PessoaDTO(Integer codigo, String nome, String dtNascimento, String signo) {
		this.codigo = codigo;
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.signo = signo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getSigno() {
		return signo;
	}

	public void setSigno(String signo) {
		this.signo = signo;
	}

	public Pessoa toPessoa() {
		return new Pessoa(this.codigo, this.nome, DateUtils.parseData(this.dtNascimento, DateUtils.PATTERN_DATA_PADRAO),
				null);
	}

}
