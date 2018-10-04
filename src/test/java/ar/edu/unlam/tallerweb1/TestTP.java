package ar.edu.unlam.tallerweb1;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Cbu;
import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Continente;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

public class TestTP extends SpringTest {

	@Test
	@Transactional
	public void buscarPaisesDeHablaInglesa() {
		
		Session session = getSession();
		
		Pais inglaterra = new Pais();
		inglaterra.setIdioma("ingles");
		
		session.save(inglaterra);
		
		Pais australia = new Pais();
		australia.setIdioma("ingles");
		
		session.save(australia);
		Pais suecia = new Pais();
		suecia.setIdioma("sueco");
		
		session.save(suecia);	
		
		List<Pais> Resultado = getSession().createCriteria(Pais.class)
				.add(Restrictions.eq("idioma","ingles"))
				.list();
				assertThat(Resultado).hasSize(2);
	}
	
	@Test
	@Transactional
	public void buscarPaisesDelContinenteEuropeo() {
		
		Session session = getSession();
		
		Continente europa = new Continente("europa");
		Continente america = new Continente("america");
		session.save(europa);
		session.save(america);
		
		Pais inglaterra = new Pais();
		inglaterra.setContinente(europa);
		
		session.save(inglaterra);
		
		Pais argentina = new Pais();
		argentina.setContinente(america);
		
		session.save(argentina);
		
		Pais suecia = new Pais();
		suecia.setContinente(europa);
		
		session.save(suecia);
		
		List<Pais> Resultado = getSession().createCriteria(Pais.class)
				.createAlias("continente", "continenteBuscado")
				.add(Restrictions.eq("continenteBuscado.nombre", "europa"))
				.list();
				assertThat(Resultado).hasSize(2);
	}
	// No funciona
	@Test
	@Transactional
	public void buscarPaisesAlNorteDelTropicoDeCancer() {
		
		Session session = getSession();
		
		Ubicacion ubicacionLima = new Ubicacion(-77.0282400, -12.0431800);
		Ubicacion ubicacionOttawa = new Ubicacion(-75.69, 45.4208);

		session.save(ubicacionLima);
		session.save(ubicacionOttawa);
		
		Pais peru = new Pais();
		Pais canada = new Pais();
				
		Ciudad lima = new Ciudad("lima", ubicacionLima, peru);
		Ciudad ottawa = new Ciudad("ottawa", ubicacionOttawa, canada);
		
		peru.setCapital(lima);
		canada.setCapital(ottawa);
		
		session.save(lima);
		session.save(ottawa);
		
		session.save(peru);
		session.save(canada);
		
		List<Pais> Resultado = getSession().createCriteria(Pais.class)
				.createAlias("capital", "capital")
				.createAlias("capital.ubicacionGeografica", "ubicacion")
				.add(Restrictions.gt("ubicacion.latitud", 23.437222))
				.list();
				assertThat(Resultado).hasSize(1);
	}
	
}
