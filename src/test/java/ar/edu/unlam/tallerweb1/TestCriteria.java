package ar.edu.unlam.tallerweb1;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Cbu;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Producto;

@SuppressWarnings("unchecked")
public class TestCriteria extends SpringTest {

	
	@Test
	@Transactional
	public void buscarClientePorNombre() {
		
		Session session = getSession();
		
		Cbu elcbu = new Cbu();
		Cliente rocio = new Cliente();
		elcbu.setCbu("123456789");
		rocio.setNombre("rocio");
		rocio.setCbu(elcbu);
		
		session.save(rocio);
		
		Cbu nuevoCbu = new Cbu();
		Cliente damian = new Cliente();
		nuevoCbu.setCbu("987654321");
		damian.setNombre("damian");
		damian.setCbu(nuevoCbu);
		
		session.save(damian);
		
		List<Cliente> lc = getSession().createCriteria(Cliente.class)
				.add(Restrictions.eq("nombre","rocio"))
				//.add(Restrictions.eq("nombre","damian"))
				//.add(Restrictions.eq("nombre",""))
				.list();
		
		assertThat(lc).hasSize(1);
	}
	
	@Test
	@Transactional
	public void buscarProductosDeUnCliente() {
		
		Session session = getSession();
		
		Cbu elcbu = new Cbu();
		Cliente rocio = new Cliente();
		elcbu.setCbu("123456789");
		rocio.setNombre("rocio");
		rocio.setCbu(elcbu);
		
		session.save(rocio);
		
		Producto producto1= new Producto();
		producto1.setNombre("Jamon");
		producto1.setDescripcion("Mmmmmm Jamoooon");
		producto1.setCliente(rocio);
		session.save(producto1);
		
		Producto producto2= new Producto();
		producto2.setNombre("Salame");
		producto2.setDescripcion("22 de oro");
		producto2.setCliente(rocio);
		session.save(producto2);
		
		Cbu nuevoCbu = new Cbu();
		Cliente damian = new Cliente();
		nuevoCbu.setCbu("987654321");
		damian.setNombre("damian");
		damian.setCbu(nuevoCbu);
		
		session.save(damian);
		
		Producto producto3= new Producto();
		producto3.setNombre("Queso");
		producto3.setDescripcion("Cremon");
		producto3.setCliente(damian);
		session.save(producto3);
		
		List<Producto> lc = getSession().createCriteria(Producto.class)
				.add(Restrictions.eq("cliente",damian))
				//.add(Restrictions.eq("nombre","damian"))
				//.add(Restrictions.eq("nombre",""))
				.list();
		
		assertThat(lc).hasSize(1);
		
	}
	
	@Test
	@Transactional
	public void buscarProductosDeClienteRocio() {
		
		Session session = getSession();
		
		Cbu elcbu = new Cbu();
		Cliente rocio = new Cliente();
		elcbu.setCbu("123456789");
		rocio.setNombre("rocio");
		rocio.setCbu(elcbu);
		
		session.save(rocio);
		
		Producto producto1= new Producto();
		producto1.setNombre("Jamon");
		producto1.setDescripcion("Mmmmmm Jamoooon");
		producto1.setCliente(rocio);
		session.save(producto1);
		
		Producto producto2= new Producto();
		producto2.setNombre("Salame");
		producto2.setDescripcion("22 de oro");
		producto2.setCliente(rocio);
		session.save(producto2);
		
		Cbu nuevoCbu = new Cbu();
		Cliente damian = new Cliente();
		nuevoCbu.setCbu("987654321");
		damian.setNombre("damian");
		damian.setCbu(nuevoCbu);
		
		session.save(damian);
		
		Producto producto3= new Producto();
		producto3.setNombre("Queso");
		producto3.setDescripcion("Cremon");
		producto3.setCliente(damian);
		session.save(producto3);
		
		List<Producto> lc = getSession().createCriteria(Producto.class)
				.createAlias("cliente", "ClienteBuscado")
				.add(Restrictions.eq("ClienteBuscado.nombre", "rocio"))
				.list();
		
		assertThat(lc).hasSize(2);
		
	}
	
	@Test
	@Transactional
	public void buscarProductosDeUnSexo() {
		
		Session session = getSession();
		
		Cbu elcbu = new Cbu();
		Cliente rocio = new Cliente();
		elcbu.setCbu("123456789");
		rocio.setNombre("rocio");
		rocio.setSexo("Femenino");
		rocio.setCbu(elcbu);
		
		session.save(rocio);
		
		Producto producto1= new Producto();
		producto1.setNombre("Jamon");
		producto1.setDescripcion("Mmmmmm Jamoooon");
		producto1.setCliente(rocio);
		session.save(producto1);
		
		Producto producto2= new Producto();
		producto2.setNombre("Salame");
		producto2.setDescripcion("22 de oro");
		producto2.setCliente(rocio);
		session.save(producto2);
		
		Cbu esteCbu = new Cbu();
		Cliente juan = new Cliente();
		esteCbu.setCbu("987654321");
		juan.setNombre("juan");
		juan.setSexo("Masculino");
		juan.setCbu(esteCbu);
		
		session.save(juan);
		
		Cbu nuevoCbu = new Cbu();
		Cliente damian = new Cliente();
		nuevoCbu.setCbu("987654321");
		damian.setNombre("damian");
		damian.setSexo("Masculino");
		damian.setCbu(nuevoCbu);
		
		session.save(damian);
		
		Producto producto3= new Producto();
		producto3.setNombre("Queso");
		producto3.setDescripcion("Cremon");
		producto3.setCliente(damian);
		session.save(producto3);
		
		List<Producto> lc = getSession().createCriteria(Producto.class)
				.createAlias("cliente", "ClienteBuscado")
				.add(Restrictions.eq("ClienteBuscado.sexo", "Femenino"))
				.list();
		
		assertThat(lc).hasSize(2);
		
	}
	
	@Test
	@Transactional @Rollback(true)
	public void buscarProductosDeClienteConDni() {
		
		Session session = getSession();
		
		Cbu elcbu = new Cbu();
		Cliente rocio = new Cliente();
		elcbu.setCbu("123456789");
		rocio.setNombre("rocio");
		rocio.setSexo("Femenino");
		rocio.setDni(40);
		rocio.setCbu(elcbu);
		
		session.save(rocio);
		
		Producto producto1= new Producto();
		producto1.setNombre("Jamon");
		producto1.setDescripcion("Mmmmmm Jamoooon");
		producto1.setCliente(rocio);
		session.save(producto1);
		
		Producto producto2= new Producto();
		producto2.setNombre("Salame");
		producto2.setDescripcion("22 de oro");
		producto2.setCliente(rocio);
		session.save(producto2);
		
		Cbu esteCbu = new Cbu();
		Cliente juan = new Cliente();
		esteCbu.setCbu("987654321");
		juan.setNombre("juan");
		juan.setSexo("Masculino");
		juan.setDni(3);
		juan.setCbu(esteCbu);
		
		session.save(juan);
		
		Producto producto4= new Producto();
		producto4.setNombre("Pan");
		producto4.setDescripcion("Lactal");
		producto4.setCliente(juan);
		session.save(producto4);
		
		Cbu nuevoCbu = new Cbu();
		Cliente damian = new Cliente();
		nuevoCbu.setCbu("987654321");
		damian.setNombre("damian");
		damian.setSexo("Masculino");
		damian.setDni(15);
		damian.setCbu(nuevoCbu);
		
		session.save(damian);
		
		Producto producto3= new Producto();
		producto3.setNombre("Queso");
		producto3.setDescripcion("Cremon");
		producto3.setCliente(damian);
		session.save(producto3);
		
		List<Producto> lc = getSession().createCriteria(Producto.class)
				.createAlias("cliente", "ClienteBuscado")
				.add(Restrictions.disjunction()
						.add(Restrictions.lt("ClienteBuscado.dni", 10))
						.add(Restrictions.gt("ClienteBuscado.dni", 30))
				)
				.list();
		
		assertThat(lc).hasSize(3);
		
	}

}
