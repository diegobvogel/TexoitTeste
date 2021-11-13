package com.texoit.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.texoit.api.classes.Intervalo;
import com.texoit.api.classes.Premiacao;
import com.texoit.api.repository.PremiacaoRepository;
import com.texoit.api.util.Util;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class TexoitController {
	
	@Autowired
	private PremiacaoRepository repository;
	
	@GetMapping("/list")
	@ResponseBody
	public List<Premiacao> list() {
		return repository.listarTodos();
	}
	
	@GetMapping("/listWinners")
	@ResponseBody
	public Intervalo listWinners() {
		List<Premiacao> listProdutoresVencedores = repository.listarVencedores();
		List<Premiacao> vencedoresDoisOuMaisAnos = Util.listProdutoresVencedores(listProdutoresVencedores);
		return Util.getProdutoresVencedores(vencedoresDoisOuMaisAnos);
	}
	
}
