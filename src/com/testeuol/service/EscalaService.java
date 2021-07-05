package com.testeuol.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.testeuol.entity.Pessoa;
import com.testeuol.repository.EscalaRepository;
import com.testeuol.vo.PessoaVO;

public class EscalaService implements IEscalaService {

	@Autowired
	public EscalaRepository escalaRepository;

	public List<PessoaVO> montarEscala() {
		List<PessoaVO> listaEscala = new ArrayList<>();

		List<String> resolvedores = new LinkedList<>(
				Arrays.asList("João", "Maria", "Zeca", "Mario", "Gustavo", "Camila", "Pedro", "Juliana", "Gisele"));

		int contador = 0;

		do {
			Calendar calendar = Calendar.getInstance();

			if (contador == 0) {
				calendar.add(Calendar.DATE, -1);
			} else {
				calendar.add(Calendar.DATE, contador - 1);
			}

			if (isDiaUtil(calendar)) {

				for (String nome : resolvedores) {
					inserirPessoa(nome, calendar, listaEscala);
					resolvedores.remove(nome);

					break;
				}

			} else {
				inserirPessoa("--", calendar, listaEscala);
			}

			contador++;

		} while (!resolvedores.isEmpty());

		return listaEscala;
	}

	private void inserirPessoa(String nome, Calendar calendarPost, List<PessoaVO> listaEscala) {
		PessoaVO pessoa = new PessoaVO();
		pessoa.setData(calendarPost.getTime());
		pessoa.setNome(nome);

		listaEscala.add(pessoa);
	}

	private boolean isDiaUtil(Calendar calendar) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(calendar.getTime());

		if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return false;
		} else {
			return true;
		}
	}
}
