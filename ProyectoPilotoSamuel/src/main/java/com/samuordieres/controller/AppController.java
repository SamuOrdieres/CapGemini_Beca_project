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

import com.samuordieres.model.Cliente;
import com.samuordieres.model.Empleado;
import com.samuordieres.model.Usuario;
import com.samuordieres.service.ClienteService;
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
	MessageSource messageSource;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String inicio() {
		return "home";
	}

	/*
	 * This method will list all existing empleados.
	 */
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public String listEmpleados(ModelMap model) {
//
//		List<Empleado> empleados = service.findAllEmpleados();
//		model.addAttribute("empleados", empleados);
//		return "allemployees";
//	}

	/*
	 * This method will provide the medium to add a new empleado.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newEmpleado(ModelMap model) {
		Empleado empleado = new Empleado();
		model.addAttribute("empleado", empleado);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving empleado in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveEmpleado(@Valid Empleado empleado, BindingResult result, ModelMap model) {

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
		if (!service.isEmpleadoDniUnique(empleado.getId(), empleado.getDni())) {
			FieldError dniError = new FieldError("empleado", "dni", messageSource.getMessage("non.unique.dni",
					new String[] { empleado.getDni() }, Locale.getDefault()));
			result.addError(dniError);
			return "registration";
		}

		service.saveEmpleado(empleado);

		model.addAttribute("success", "El empleado " + empleado.getNombre() + " se ha REGISTRADO SATISFACTORIAMENTE");
		return "success";
	}

	/*
	 * This method will provide the medium to update an existing empleado.
	 */
	@RequestMapping(value = { "/edit-{dni}-empleado" }, method = RequestMethod.GET)
	public String editEmpleado(@PathVariable String dni, ModelMap model) {
		Empleado empleado = service.findEmpleadoByDni(dni);
		model.addAttribute("empleado", empleado);
		model.addAttribute("edit", true);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating empleado in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{dni}-empleado" }, method = RequestMethod.POST)
	public String updateEmpleado(@Valid Empleado empleado, BindingResult result, ModelMap model,
			@PathVariable String dni) {

		if (result.hasErrors()) {
			return "registration";
		}

		if (!service.isEmpleadoDniUnique(empleado.getId(), empleado.getDni())) {
			FieldError dniError = new FieldError("empleado", "dni", messageSource.getMessage("non.unique.dni",
					new String[] { empleado.getDni() }, Locale.getDefault()));
			result.addError(dniError);
			return "registration";
		}

		service.updateEmpleado(empleado);

		model.addAttribute("success", "Empleado " + empleado.getNombre() + " updated successfully");
		return "success";
	}

	/*
	 * This method will delete an empleado by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{dni}-empleado" }, method = RequestMethod.GET)
	public String deleteEmpleado(@PathVariable String dni) {
		service.deleteEmpleadoByDni(dni);
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
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("usuario") Usuario usuario) {
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
