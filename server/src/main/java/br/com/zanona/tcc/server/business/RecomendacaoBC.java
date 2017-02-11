package br.com.zanona.tcc.server.business;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.zanona.tcc.server.domain.Perfil;
import br.com.zanona.tcc.server.domain.Recomendacao;
import br.com.zanona.tcc.server.rbc.RecomendacaoCore;

@Component
public class RecomendacaoBC implements Serializable {

	private static final long serialVersionUID = 3965362815931539387L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private RecomendacaoCore cbrCore;

	/**
	 * Este método executa o ciclo de RBC para efetuar a sugestão dos atrativos
	 * turísticos
	 * 
	 * @param perfil
	 * @return
	 * @throws RuntimeException
	 */
	public Recomendacao executar(Perfil perfil) throws Exception {
		logger.debug("iniciando processo de recomendacao");
		Recomendacao recomendacao = null;

		try {

			// carregando base de casos
			cbrCore.preCycle();

			// executando a consulta
			recomendacao = cbrCore.execCycle(perfil);

			// finalizando ciclo
			cbrCore.postCycle();

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}

		logger.debug("finalizado processo de recomendacao");

		return recomendacao;
	}

	/**
	 * Método que efetua o aprendizado de um caso qualquer na base de casos.
	 * Esta recomendação pode ter vindo do ciclo do rbc ou não.
	 * 
	 * @param recomendacao
	 */
	public void aprender(Recomendacao recomendacao) {
		cbrCore.learn(recomendacao);
	}

}
