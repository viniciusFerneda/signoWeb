package br.com.vinicius.signo.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.vinicius.signo.model.Pessoa;
import br.com.vinicius.signo.service.PessoaService;

@Path("pessoas")
public class PessoaController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Pessoa> listPessoas() {
		PessoaService pessoaService = new PessoaService();
		try {
			return pessoaService.listarPessoas();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{codigo}/")
	public Pessoa getPessoa(@PathParam("codigo") int codigo) {
		PessoaService pessoaService = new PessoaService();
		try {
			return pessoaService.buscarPessoaPorCodigo(codigo);
		} catch (SQLException e) {
			e.printStackTrace();
			return new Pessoa();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(Pessoa pessoa) {
		PessoaService pessoaService = new PessoaService();
		try {
			pessoaService.inserir(pessoa);
			return Response.status(Response.Status.OK).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(Pessoa pessoa) {
		PessoaService pessoaService = new PessoaService();
		try {
			pessoaService.alterar(pessoa.getCodigo(), pessoa.getNome());
			return Response.status(Response.Status.OK).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("{codigo}/")
	public Response delete(@PathParam("codigo") int codigo) {
		PessoaService pessoaService = new PessoaService();
		try {
			pessoaService.excluir(codigo);
			return Response.status(Response.Status.OK).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
