package br.com.zanona.tcc.server;

import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zanona.tcc.server.domain.AtrativoTuristico;
import br.com.zanona.tcc.server.domain.Perfil;
import br.com.zanona.tcc.server.persistence.AtrativoTuristicoDAO;

//@RunWith(DemoiselleRunner.class)
public class JacksonJsonTest {

	@Autowired
	private Logger logger;
	
	@Autowired
	private AtrativoTuristicoDAO atrativoDAO;
	
	@Test
	public void serializar() {

		try {

			AtrativoTuristico at = atrativoDAO.findOne(1); 
			ObjectMapper oMapper = new ObjectMapper();
			String outputChild1 = oMapper.writeValueAsString(at);
			logger.debug(outputChild1);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void deserializar() {

		try {

			//AtrativoTuristico at = atrativoDAO.load(1); 
			
			ObjectMapper oMapper = new ObjectMapper();
		
			//AtrativoTuristico inputChild1 = oMapper.readValue("{\"coordenada\":\"POINT (-27 -43)\"}", AtrativoTuristico.class);
			Perfil p = oMapper.readValue("{\"coordenada\":\"POINT (-27 -43)\" ,\"categoria\": { \"id\" : 108 } }", Perfil.class);
			logger.debug(p.toString());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
