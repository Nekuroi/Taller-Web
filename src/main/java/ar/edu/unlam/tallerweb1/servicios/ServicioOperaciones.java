package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.stereotype.Service;

@Service("servicioOperaciones")
public interface ServicioOperaciones {
	String operar(String operacion, String cadena);
}