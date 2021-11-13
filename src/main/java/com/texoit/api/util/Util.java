package com.texoit.api.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
		Intervalo intervalo = new Intervalo();

		int i = 0;
		int min;
		int max;
		List<Integer> qtdAnos = new ArrayList<Integer>();

		try {
			for (i = 0; i < vencedores.size(); i++) {
				if ((i % 2) == 0) {
					qtdAnos.add(vencedores.get(i + 1).getYear() - vencedores.get(i).getYear());

					Ganhador ganhador = new Ganhador();
					ganhador.setProducer(vencedores.get(i).getProducers());
					ganhador.setInterval(vencedores.get(i + 1).getYear() - vencedores.get(i).getYear());
					ganhador.setPreviousWin(vencedores.get(i).getYear());
					ganhador.setFollowingWin(vencedores.get(i + 1).getYear());

					possiveisGanhadores.add(ganhador);
				}
			}

			min = Collections.min(qtdAnos);
			max = Collections.max(qtdAnos);

			for (Ganhador ganhador : possiveisGanhadores) {
				if (ganhador.getInterval() == min) {
					intervalo.setMin(ganhador);
				}
				if (ganhador.getInterval() == max) {
					intervalo.setMax(ganhador);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return intervalo;
	}

}
