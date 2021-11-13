package com.texoit.api;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.texoit.api.classes.Premiacao;
import com.texoit.api.repository.PremiacaoRepository;
import com.texoit.api.util.Util;

@SpringBootApplication
public class TexoitApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TexoitApiApplication.class, args);
	}

}

@Component
class LerArquivoEInserirRegistros implements CommandLineRunner {

	@Autowired
	private PremiacaoRepository repository;

	@Override
	public void run(String... args) throws Exception {

		List<String> produtores;
		boolean isFirstLine = true;
		Premiacao premiacao;
		CsvReader csvReader = new CsvReader();
		List<List<String>> csvRecords = csvReader.readCsvFile2("./src/main/resources/files/movielist.csv");
		for (Iterator<List<String>> iterator = csvRecords.iterator(); iterator.hasNext();) {
			List<String> list = (List<String>) iterator.next();
			if (!isFirstLine) {
				
				produtores = Util.getProdutoresSplited(list.get(3));
				for (String produtor : produtores) {
					
					if (!produtor.trim().equals("")) {
						premiacao = new Premiacao();
						premiacao.setYear(Integer.parseInt(list.get(0)));
						premiacao.setTitle(list.get(1));
						premiacao.setStudios(list.get(2));
						premiacao.setProducers(produtor.trim());
						if (list.size() == 5) {
							premiacao.setWinner(list.get(4));

						} else {
							premiacao.setWinner("no");
						}
						repository.criar(premiacao);
					}
				}
				
			}
			isFirstLine = false;
		}
	}
}