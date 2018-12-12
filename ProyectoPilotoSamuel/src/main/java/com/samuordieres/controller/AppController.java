package com.samuordieres.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.samuordieres.model.CentroTuristico;
import com.samuordieres.model.Cliente;
import com.samuordieres.model.Email;
import com.samuordieres.model.Empleado;
import com.samuordieres.model.Reserva;
import com.samuordieres.model.Usuario;
import com.samuordieres.service.CentroTuristicoService;
import com.samuordieres.service.ClienteService;
import com.samuordieres.service.EmailService;
import com.samuordieres.service.EmpleadoService;
import com.samuordieres.service.ReservaService;
import com.samuordieres.service.UsuarioService;

@Controller
@RequestMapping("/")
@ComponentScan("com.samuordieres")
public class AppController {

	@Autowired
	EmpleadoService service;

	@Autowired
	UsuarioService userService;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	CentroTuristicoService centroTuristicoService;
	
	@Autowired
	ReservaService reservaService;

	@Autowired
	MessageSource messageSource;

	
	
	/*
	 * INICIO
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String inicio() {
		return "home";
	}

	
	
	//  --- A PARTIR DE AQUÍ EMPIEZAN LOS METODOS DE CLIENTE ---

	
	/*
	 * This method will provide the MEDIUM TO ADD a NEW CLIENTE.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newCliente(ModelMap model) {
		
		
		Cliente cliente = new Cliente();
		
		model.addAttribute("cliente", cliente);
		model.addAttribute("edit", false);
		
		List<CentroTuristico> centrosTuristicos = centroTuristicoService.findAllCentrosTuristicos();
		model.addAttribute("centrosTuristicos", centrosTuristicos);
		
		return "registration";
	}

	
	
	/*
	 * This method will be called ON FORM SUBMISSION, handling POST request for
	 * SAVING CLIENTE IN DDBB. It also VALIDATES the CLIENTE INPUT.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveCliente(@Valid Cliente cliente, BindingResult result, ModelMap model) {

		Email email = new Email();
		
		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [dni] should be implementing
		 * custom @Unique annotation and applying it on field [dni] of Model class
		 * [Cliente].
		 * 
		 * Below [if block] is to demonstrate that you can fill
		 * custom errors outside the validation framework as well while still using
		 * internationalized messages.
		 * 
		 */
		if (!clienteService.isClienteDniUnique(cliente.getId(), cliente.getDni())) {
			FieldError dniError = new FieldError("cliente", "dni", messageSource.getMessage("non.unique.dni",
					new String[] { cliente.getDni() }, Locale.getDefault()));
			result.addError(dniError);
			return "registration";
		}

		
		email.setCliente(cliente);
		email.setEmail((cliente.getEmail().getEmail()));
		cliente.setEmail(email);
		
		emailService.saveEmail(email);

		model.addAttribute("success", "El cliente " + cliente.getNombre() + " " + cliente.getPrimerApellido()+ " " + cliente.getSegundoApellido() + " se ha REGISTRADO SATISFACTORIAMENTE");
		return "success";
	}
	
	

	/*
	 * This method will provide the MEDIUM TO UPDATE an EXISTING CLIENTE.
	 */
	@RequestMapping(value = { "/edit-{dniCliente}-cliente" }, method = RequestMethod.GET)
	public String editCliente(@PathVariable String dniCliente, ModelMap model) {
		Cliente cliente = clienteService.findClienteByDni(dniCliente);
//		CentroTuristico centroTuristico = centroTuristicoService.findById(cliente.getCentroTuristicoId());
		
		model.addAttribute("cliente", cliente);
//		model.addAttribute("centroTuristico", centroTuristico);
		
		List<CentroTuristico> centrosTuristicos = centroTuristicoService.findAllCentrosTuristicos();
		model.addAttribute("centrosTuristicos", centrosTuristicos);
		
		model.addAttribute("edit", true);
		return "registration";
	}
	
	

	/*
	 * This method will be CALLED ON FORM SUBMISSION, handling POST request for
	 * UPDATING CLIENTE in DDBB. It also VALIDATES the CLIENTE input
	 */
	@RequestMapping(value = { "/edit-{dniCliente}-cliente" }, method = RequestMethod.POST)
	public String updateEmpleado(@Valid Cliente cliente, BindingResult result, ModelMap model,
			@PathVariable String dniCliente) {
		
		Email email = new Email();

		if (result.hasErrors()) {
			return "registration";
		}

		if (!clienteService.isClienteDniUnique(cliente.getId(), cliente.getDni())) {
			FieldError dniError = new FieldError("cliente", "dniCliente", messageSource.getMessage("non.unique.dni",
					new String[] { cliente.getDni() }, Locale.getDefault()));
			result.addError(dniError);
			return "registration";
		}

		email.setId(cliente.getEmail().getId());
		email.setEmail((cliente.getEmail().getEmail()));		
		email.setCliente(cliente);
		cliente.setEmail(email);

		emailService.updateEmail(cliente);
		clienteService.updateCliente(cliente);

		model.addAttribute("success", "El cliente " + cliente.getNombre() + " se ha actualizado satisfactoriamente.");
		return "success";
	}

	
	
	/*
	 * This method will DELETE an CLIENTE BY it's DNI value.
	 */
	@RequestMapping(value = { "/delete-{dni}-cliente" }, method = RequestMethod.GET)
	public String deleteCliente(@PathVariable String dni) {
		clienteService.deleteClienteByDni(dni);
		return "redirect:/list";
	}

	
	/*
	 * This method will LIST ALL EXISTING CLIENTES.
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listClientes(ModelMap model) {

		List<Cliente> clientes = clienteService.findAllClientes();
		model.addAttribute("clientes", clientes);
		return "allclientes";
	}
	
	
	//  --- FIN DE LOS METODOS DE CLIENTE ---
	
	
	
	//  --- A PARTIR DE AQUÍ EMPIEZAN LOS METODOS DE USUARIO ---
	

	/*
	 * This method will LIST ALL EXISTING USUARIOS.
	 */
	@RequestMapping(value = { "/usuarios", "/listUsuarios" }, method = RequestMethod.GET)
	public String listUsuarios(ModelMap model) {

		List<Usuario> usuarios = userService.findAllUsuarios();
		model.addAttribute("usuarios", usuarios);
		return "allusers";
	}

	

	/*
	 * This method will provide the MEDIUM TO LOGIN WITH a USUARIO.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("usuario", new Usuario());
		return modelAndView;
	}

	
	
	/*
	 * This method CHECK IF THE USUARIOS EXISTS IN DDBB and show different
	 * VIEWS based on the NIVEL(rol) property of USUARIO.
	 */
	
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("usuario") Usuario usuario) {
		
		ModelAndView modelAndView = null;
		Usuario usuarioTemp = userService.validateUser(usuario);
		
		if (null != usuarioTemp) {
			
			List<Cliente> clientes = clienteService.findAllClientes();

			switch (usuarioTemp.getNivel()) {

			case (1):
				modelAndView = new ModelAndView("onlyviewclientes");
				modelAndView.addObject("clientes", clientes);
				break;

			case (2):
				modelAndView = new ModelAndView("allclientes");
				modelAndView.addObject("clientes", clientes);
				break;

			}

		} else {
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("message", "Username or Password is wrong!!");
		}
		return modelAndView;
	}

	
	
	//  --- FIN DE LOS METODOS DE USUARIO ---
	
	
	
	
	//  --- A PARTIR DE AQUÍ EMPIEZAN LOS METODOS DE EMPLEADO ---
	
	
	//  ** ACTUALMENTE SIN USO **
	
	/*
	 * This method will provide the medium to add a new empleado.
	 */
	
	@RequestMapping(value = { "/userregistration" }, method = RequestMethod.GET)
	public String newUsuario(ModelMap model) {
		Empleado empleado = new Empleado();
		model.addAttribute("empleado", empleado);
		model.addAttribute("edit", false);
		return "registration";
	}
	
	
	//  --- FIN DE LOS METODOS DE EMPLEADO ---
	
	
	
	
	//  --- A PARTIR DE AQUÍ EMPIEZAN LOS METODOS DE CENTRO TURISTICO ---

	
	/*
	 * This method will LIST ALL EXISTING CENTROS TURISTICOS.
	 */
	@RequestMapping(value = "/allcentrosturisticos", method = RequestMethod.GET)
	public String listCentrosTuristicos(ModelMap model) {

		List<CentroTuristico> centrosTuristicos = centroTuristicoService.findAllCentrosTuristicos();
		model.addAttribute("centrosTuristicos", centrosTuristicos);
		return "allcentrosturisticos";
	}
	
	//  --- FIN DE LOS METODOS DE CENTRO TURISTICO ---
	
	
	
	//  --- A PARTIR DE AQUÍ EMPIEZAN LOS METODOS DE RESERVA ---

	
	/*
	 * This method will LIST ALL EXISTING CENTROS TURISTICOS.
	 */
	@RequestMapping(value = "/allreservas", method = RequestMethod.GET)
	public String listReservas(ModelMap model) {

		List<Reserva> reservas = new ArrayList<>();
		reservas = reservaService.findAllReservas();
		model.addAttribute("reservas", reservas);
		return "allreservas";
	}
	
	
	/*
	 * This method will provide the MEDIUM TO ADD a NEW RESERVA.
	 */
	@RequestMapping(value = { "/newreserva" }, method = RequestMethod.GET)
	public String newReserva(ModelMap model) {
		
		
		Reserva reserva = new Reserva();
		
		model.addAttribute("reserva", reserva);
		model.addAttribute("edit", false);
		
		List<Cliente> clientes = clienteService.findAllClientes();
		List<CentroTuristico> centrosTuristicos = centroTuristicoService.findAllCentrosTuristicos();
		
		model.addAttribute("clientes", clientes);
		model.addAttribute("centrosTuristicos", centrosTuristicos);
		
		return "reservaregistration";
	}

	
	
	/*
	 * This method will be called ON FORM SUBMISSION, handling POST request for
	 * SAVING RESERVA IN DDBB. It also VALIDATES the RESERVA INPUT.
	 */
	@RequestMapping(value = { "/newreserva" }, method = RequestMethod.POST)
	public String saveReserva(@Valid Reserva reserva, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			return "reservaregistration";
		}

		Cliente cliente = clienteService.findById(reserva.getCliente().getId());
		CentroTuristico centroTuristico = centroTuristicoService.findById(reserva.getCentroTuristico().getId());
		
		reserva.setCliente(cliente);
		reserva.setCentroTuristico(centroTuristico);
		
		reservaService.saveReserva(reserva);

		model.addAttribute("success", "La reserva ** " + reserva.getId() + " ** del Cliente: -- " 
		+ reserva.getCliente().getNombre() + " " + reserva.getCliente().getPrimerApellido() 
		+ " -- , en el Centro Turistico ** " + reserva.getCentroTuristico().getNombre() 
		+" **, se ha REGISTRADO SATISFACTORIAMENTE");
		
		return "success";
	}
	
	
	/*
	 * This method will provide the MEDIUM TO UPDATE an EXISTING RESERVA.
	 */
	@RequestMapping(value = { "/edit-{idReserva}-reserva" }, method = RequestMethod.GET)
	public String editReserva(@PathVariable int idReserva, ModelMap model) {
		
		Reserva reserva = reservaService.findById(idReserva);
		Cliente cliente = reserva.getCliente();
		CentroTuristico centroTuristico = reserva.getCentroTuristico();
		
		model.addAttribute("reserva", reserva);
		model.addAttribute("cliente", cliente);
		model.addAttribute("centroTuristico", centroTuristico);
		
		List<Cliente> clientes = clienteService.findAllClientes();
		List<CentroTuristico> centrosTuristicos = centroTuristicoService.findAllCentrosTuristicos();
		
		model.addAttribute("clientes", clientes);
		model.addAttribute("centrosTuristicos", centrosTuristicos);
		
		model.addAttribute("edit", true);
		
		return "reservaregistration";
	}
	
	

	/*
	 * This method will be CALLED ON FORM SUBMISSION, handling POST request for
	 * UPDATING RESERVA in DDBB. It also VALIDATES the RESERVA input
	 */
	@RequestMapping(value = { "/edit-{idReserva}-reserva" }, method = RequestMethod.POST)
	public String updateReserva(@Valid Reserva reserva, BindingResult result, ModelMap model,
			@PathVariable int idReserva) {
		
		if (result.hasErrors()) {
			return "reservaregistration";
		}

		
		Cliente cliente = clienteService.findById(reserva.getCliente().getId());
		CentroTuristico centroTuristico = centroTuristicoService.findById(reserva.getCentroTuristico().getId());
		
		reserva.setCliente(cliente);
		reserva.setCentroTuristico(centroTuristico);
		
		reservaService.updateReserva(reserva);
		
		

		model.addAttribute("success", "La reserva ** " + reserva.getId() + " ** del Cliente: -- "
						+ reserva.getCliente().getNombre() + " " + reserva.getCliente().getPrimerApellido()
						+ " -- , en el Centro Turistico ** " + reserva.getCentroTuristico().getNombre()
						+ " **, se ha ACTUALIZADO SATISFACTORIAMENTE");
		
		return "success";
	}

	
	
	/*
	 * This method will DELETE an CLIENTE BY it's DNI value.
	 */
	@RequestMapping(value = { "/delete-{idReserva}-reserva" }, method = RequestMethod.GET)
	public String deleteReserva(@PathVariable int idReserva) {
		reservaService.deleteReserva(idReserva);
		return "redirect:/allreservas";
	}
	
	//  --- FIN DE LOS METODOS DE RESERVA ---


}
