package ar.edu.unlam.tallerweb1;

import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Cbu;
import ar.edu.unlam.tallerweb1.modelo.Cliente;

public class TestCliente extends SpringTest {

	@Test
	@Transactional @Rollback(true)
	public void testCrearClienteConCbuCascade() {
		
		Cbu elcbu = new Cbu();
		Cliente rocio = new Cliente();
		
		elcbu.setCbu("123456789");
		rocio.setNombre("rocio");
		rocio.setCbu(elcbu);
		
		Session session = getSession();
		session.save(rocio);
		
		Cliente buscado = session.get(Cliente.class, rocio.getId());
		
		//System.out.println(buscado.getId() + " " + buscado.getNombre());
		
		assertThat(buscado).isNotNull();
		
	}	
}
