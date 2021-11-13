package com.texoit.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.texoit.api.classes.Premiacao;

@Component
public class PremiacaoRepository {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Premiacao> listarTodos(){
		return manager.createQuery("from premiacao", Premiacao.class).getResultList();
	}
	
	@Transactional
	public Premiacao criar(Premiacao premiacao) {
		return manager.merge(premiacao);
	}
	
	public List<Premiacao> listarVencedores(){
		return manager.createQuery("from premiacao where winner = 'yes' order by producers, year", Premiacao.class).getResultList();
	}
	
}
