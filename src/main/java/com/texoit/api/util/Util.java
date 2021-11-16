package com.texoit.api.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.texoit.api.classes.Ganhador;
import com.texoit.api.classes.Intervalo;
import com.texoit.api.classes.Premiacao;

public class Util {

	public static List<String> getProdutoresSplited(String produtores) {
		List<String> produtoresArray = null;
		try {
			produtoresArray = Arrays.asList(produtores.split(" and "));

			if (produtoresArray.size() > 1) {
				produtores = produtoresArray.get(0).trim() + "," + produtoresArray.get(1).trim();
			} else {
				produtores = produtoresArray.get(0).trim();
			}
			produtoresArray = Arrays.asList(produtores.split(","));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return produtoresArray;
	}

	/**
	 * Retorna os produtores que foram premiados duas ou mais vezes
	 * 
	 * @param vencedores
	 * @return
	 */
	public static List<Premiacao> listProdutoresVencedores(List<Premiacao> vencedores) {

		Premiacao lastVencedor = new Premiacao();
		List<Premiacao> vencedoresDoisOuMaisAnos = new ArrayList<Premiacao>();
		try {
			for (Premiacao vencedor : vencedores) {
				if (vencedor.getProducers() == lastVencedor.getProducers()) {
					if (!vencedoresDoisOuMaisAnos.contains(lastVencedor)) {
						vencedoresDoisOuMaisAnos.add(lastVencedor);
					}
					vencedoresDoisOuMaisAnos.add(vencedor);
				}
				lastVencedor = vencedor;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vencedoresDoisOuMaisAnos;
	}

	/**
	 * Retorna os produtores com intervalo maior e menor
	 * @param vencedores
	 * @return
	 */
	public static Intervalo getProdutoresVencedores(List<Premiacao> vencedores) {

		List<Ganhador> possiveisGanhadores = new ArrayList<Ganhador>();
		Premiacao lastVencedor = new Premiacao();
		Intervalo intervalo = new Intervalo();

		int i = 0;
		int min;
		int max;
		List<Integer> qtdAnos = new ArrayList<Integer>();
		List<Ganhador> ganhadorListMin = new ArrayList<>();
		List<Ganhador> ganhadorListMax = new ArrayList<>();

		try {
			// verificar qtde 'min' e 'max' de anos entre as vitórias
			for (i = 0; i < vencedores.size(); i++) {
				if (vencedores.get(i).getProducers() == lastVencedor.getProducers()) {
					qtdAnos.add(vencedores.get(i).getYear() - lastVencedor.getYear());
				}
				lastVencedor = vencedores.get(i);
			}
			
			lastVencedor = new Premiacao();
			min = Collections.min(qtdAnos);
			max = Collections.max(qtdAnos);
			
			// adicionar ganhadores que tem o itervalo igual a 'min' ou 'max'
			for (i = 0; i < vencedores.size(); i++) {
				if (vencedores.get(i).getProducers() == lastVencedor.getProducers()) { // só entrar se os dois forem iguais
					if ((vencedores.get(i).getYear() - lastVencedor.getYear() == min) 
					||  (vencedores.get(i).getYear() - lastVencedor.getYear() == max)) { // só entrar se o intervalo for igual a 'min' ou 'max'
						if (!ganhadorJaExiste(possiveisGanhadores, vencedores.get(i).getProducers())) { // verificar se o ganhador já está adicionado (para não add nos casos que o intervalo for igual a algum anterior)
							Ganhador ganhador = new Ganhador();
							ganhador.setProducer(vencedores.get(i).getProducers());
							ganhador.setInterval(vencedores.get(i).getYear() - vencedores.get(i-1).getYear());
							ganhador.setPreviousWin(vencedores.get(i-1).getYear());
							ganhador.setFollowingWin(vencedores.get(i).getYear());

							possiveisGanhadores.add(ganhador);
						}
					}
				}
				lastVencedor = vencedores.get(i);
			}
			
			// setar os possiveis ganhadores no 'min' e 'max'
			for (Ganhador ganhador : possiveisGanhadores) {
				if (ganhador.getInterval() == min) {
					ganhadorListMin.add(ganhador);
					intervalo.setMin(ganhadorListMin);
				}
				if (ganhador.getInterval() == max) {
					ganhadorListMax.add(ganhador);
					intervalo.setMax(ganhadorListMax);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return intervalo;
	}
	
	public static boolean ganhadorJaExiste(List<Ganhador> lista, String nome) {
		boolean retorno = false;
		for (Ganhador ganhador : lista) {
			if (ganhador.getProducer() == nome) {
				retorno = true;
			}
		}
		return retorno;
	}
	
}
