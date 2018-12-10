package com.samuordieres.controller;

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
import com.samuordieres.model.Usuario;
import com.samuordieres.service.CentroTuristicoService;
import com.samuordieres.service.ClienteService;
import com.samuordieres.service.EmailService;
import com.samuordieres.service.EmpleadoService;
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
	CentroTuristicoService centroTuristicoService;
	
	@Autowired
	EmailService emailService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String inicio() {
		return "home";
	}

	/*
	 * This method will provide the medium to add a new cliente.
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
	 * This method will be called on form submission, handling POST request for
	 * saving empleado in database. It also validates the user input
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
		 * [Empleado].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill
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
	 * This method will provide the medium to update an existing cliente.
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
	 * This method will be called on form submission, handling POST request for
	 * updating empleado in database. It also validates the user input
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
	 * This method will delete an cliente by it's DNI value.
	 */
	@RequestMapping(value = { "/delete-{dni}-cliente" }, method = RequestMethod.GET)
	public String deleteCliente(@PathVariable String dni) {
		clienteService.deleteClienteByDni(dni);
		return "redirect:/list";
	}

	/*
	 * A PARTIR DE AQUÍ EMPIEZAN LOS METODOS DE USUARIO
	 */

	/*
	 * This method will list all existing empleados.
	 */
	@RequestMapping(value = { "/usuarios", "/listUsuarios" }, method = RequestMethod.GET)
	public String listUsuarios(ModelMap model) {

		List<Usuario> usuarios = userService.findAllUsuarios();
		model.addAttribute("usuarios", usuarios);
		return "allusers";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("usuario", new Usuario());
		return modelAndView;
	}

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("usuario") Usuario usuario) {
		
		ModelAndView modelAndView = null;
		Usuario usuarioTemp = userService.validateUser(usuario);
		
		if (null != usuarioTemp) {
			
			List<Cliente> clientes = clienteService.findAllClientes();

			switch (usuarioTemp.getNivel()) {

			case (1):
				modelAndView = new ModelAndView("viewclients");
				modelAndView.addObject("clientes", clientes);
				break;

			case (2):
				modelAndView = new ModelAndView("allclients");
				modelAndView.addObject("clientes", clientes);
				break;

			}

		} else {
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("message", "Username or Password is wrong!!");
		}
		return modelAndView;
	}

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
	
	/*
	 * This method will list all existing empleados.
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listClientes(ModelMap model) {

		List<Cliente> clientes = clienteService.findAllClientes();
		model.addAttribute("clientes", clientes);
		return "allclients";
	}

}
