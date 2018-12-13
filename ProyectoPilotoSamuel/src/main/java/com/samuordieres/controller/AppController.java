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
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.samuordieres.model.CentroTuristico;
import com.samuordieres.model.Cliente;
import com.samuordieres.model.Email;
import com.samuordieres.model.Empleado;
import com.samuordieres.model.Reserva;
import com.samuordieres.model.User;
import com.samuordieres.model.UserProfile;
import com.samuordieres.service.CentroTuristicoService;
import com.samuordieres.service.ClienteService;
import com.samuordieres.service.EmailService;
import com.samuordieres.service.EmpleadoService;
import com.samuordieres.service.ReservaService;
import com.samuordieres.service.UserProfileService;
import com.samuordieres.service.UserService;

@Controller
@RequestMapping("/")
@ComponentScan("com.samuordieres")
@SessionAttributes("roles")
public class AppController {

	@Autowired
    UserService userService;
     
    @Autowired
    UserProfileService userProfileService;
	
    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
     
    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;
	
	@Autowired
	EmpleadoService service;

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
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String inicio() {
//		return "home";
//	}

	
	
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
	public String updateCliente(@Valid Cliente cliente, BindingResult result, ModelMap model,
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

		email.setId(emailService.findEmailByClienteId(cliente.getId()).getId());
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
	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public String listClientes(ModelMap model) {

		List<Cliente> clientes = clienteService.findAllClientes();
		model.addAttribute("clientes", clientes);
		return "allclientes";
	}
	
	
	//  --- FIN DE LOS METODOS DE CLIENTE ---
	
	
	
	//  --- A PARTIR DE AQUÍ EMPIEZAN LOS METODOS DE USUARIO ---
	

	/**
     * This method will list all existing users.
     */
    @RequestMapping(value = { "/listusers" }, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
 
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getPrincipal());
        return "allusers";
    }
 
    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "userregistration";
    }
 
    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
            ModelMap model) {
 
        if (result.hasErrors()) {
            return "userregistration";
        }
 
        /*
         * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
         * and applying it on field [sso] of Model class [User].
         * 
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
         * framework as well while still using internationalized messages.
         * 
         */
        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "userregistration";
        }
         
        userService.saveUser(user);
 
        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "userregistrationsuccess";
    }
 
 
    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String ssoId, ModelMap model) {
        User user = userService.findBySSO(ssoId);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "userregistration";
    }
     
    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
            ModelMap model, @PathVariable String ssoId) {
 
        if (result.hasErrors()) {
            return "userregistration";
        }
 
        /*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }*/
 
 
        userService.updateUser(user);
 
        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "userregistrationsuccess";
    }
 
     
    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId) {
        userService.deleteUserBySSO(ssoId);
        return "redirect:/list";
    }
     
 
    /**
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }
     
    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/accesdenied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessdenied";
    }
 
    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/list";  
        }
    }
 
    /**
     * This method handles logout requests.
     * Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }
 
    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
     
    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
	
	
	
	
	
//	/*
//	 * This method will LIST ALL EXISTING USUARIOS.
//	 */
//	@RequestMapping(value = { "/usuarios", "/listUsuarios" }, method = RequestMethod.GET)
//	public String listUsuarios(ModelMap model) {
//
//		List<Usuario> usuarios = userService.findAllUsuarios();
//		model.addAttribute("usuarios", usuarios);
//		return "allusers";
//	}
//
//	
//
//	/*
//	 * This method will provide the MEDIUM TO LOGIN WITH a USUARIO.
//	 */
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView modelAndView = new ModelAndView("login");
//		modelAndView.addObject("usuario", new Usuario());
//		return modelAndView;
//	}
//
//	
//	
//	/*
//	 * This method CHECK IF THE USUARIOS EXISTS IN DDBB and show different
//	 * VIEWS based on the NIVEL(rol) property of USUARIO.
//	 */
//	
//	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
//	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("usuario") Usuario usuario) {
//		
//		ModelAndView modelAndView = null;
//		Usuario usuarioTemp = userService.validateUser(usuario);
//		
//		if (null != usuarioTemp) {
//			
//			List<Cliente> clientes = clienteService.findAllClientes();
//
//			switch (usuarioTemp.getNivel()) {
//
//			case (1):
//				modelAndView = new ModelAndView("onlyviewclientes");
//				modelAndView.addObject("clientes", clientes);
//				break;
//
//			case (2):
//				modelAndView = new ModelAndView("allclientes");
//				modelAndView.addObject("clientes", clientes);
//				break;
//
//			}
//
//		} else {
//			modelAndView = new ModelAndView("login");
//			modelAndView.addObject("message", "Username or Password is wrong!!");
//		}
//		return modelAndView;
//	}
//
//	
	
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
	
	/*
	 * This method will provide the MEDIUM TO ADD a NEW RESERVA.
	 */
	@RequestMapping(value = { "/newcentroturistico" }, method = RequestMethod.GET)
	public String newCentroTuristico(ModelMap model) {
		
		
		CentroTuristico centroTuristico = new CentroTuristico();
		
		model.addAttribute("centroTuristico", centroTuristico);
		model.addAttribute("edit", false);
		
			
		return "centroturisticoregistration";
	}

	
	
	/*
	 * This method will be called ON FORM SUBMISSION, handling POST request for
	 * SAVING RESERVA IN DDBB. It also VALIDATES the RESERVA INPUT.
	 */
	@RequestMapping(value = { "/newcentroturistico" }, method = RequestMethod.POST)
	public String saveCentroTuristico(@Valid CentroTuristico centroTuristico, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			return "centroturisticoregistration";
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
		if (!centroTuristicoService.isCentroTuristicoNombreUnique(centroTuristico.getId(), centroTuristico.getNombre())) {
			FieldError nombreError = new FieldError("centroTuristico", "nombre", messageSource.getMessage("non.unique.nombre",
					new String[] { centroTuristico.getNombre() }, Locale.getDefault()));
			result.addError(nombreError);
			return "centroturisticoregistration";
		}

		
		
		
		centroTuristicoService.saveCentroTuristico(centroTuristico);

		model.addAttribute("success", "El Centro Turístico: " + centroTuristico.getNombre() + ", se ha REGISTRADO SATISFACTORIAMENTE");
		return "success";
	}
	
	
	/*
	 * This method will provide the MEDIUM TO UPDATE an EXISTING RESERVA.
	 */
	@RequestMapping(value = { "/edit-{idCentroTuristico}-centroturistico" }, method = RequestMethod.GET)
	public String editCentroTuristico(@PathVariable int idCentroTuristico, ModelMap model) {
		
		
		CentroTuristico centroTuristico = centroTuristicoService.findById(idCentroTuristico);
		model.addAttribute("centroTuristico", centroTuristico);
		
		model.addAttribute("edit", true);
		
		return "centroturisticoregistration";
	}
	
	

	/*
	 * This method will be CALLED ON FORM SUBMISSION, handling POST request for
	 * UPDATING RESERVA in DDBB. It also VALIDATES the RESERVA input
	 */
	@RequestMapping(value = { "/edit-{idCentroTuristico}-centroturistico" }, method = RequestMethod.POST)
	public String updateCentroTuristico(@Valid CentroTuristico centroTuristico, BindingResult result, ModelMap model,
			@PathVariable int idCentroTuristico) {
		
		if (result.hasErrors()) {
			return "centroturisticoregistration";
		}

		
		centroTuristicoService.updateCentroTuristico(centroTuristico);
		
		

		model.addAttribute("success", "El Centro Turístico: " + centroTuristico.getNombre() + ", se ha ACTUALIZADO SATISFACTORIAMENTE");
		return "success";
	}

	
	
	/*
	 * This method will DELETE a CENTRO TURISTICO BY it's ID value.
	 */
	@RequestMapping(value = { "/delete-{idCentroTuristico}-centroturistico" }, method = RequestMethod.GET)
	public String deleteCentroTuristico(@PathVariable int idCentroTuristico) {
		centroTuristicoService.deleteCentroTuristicoById(idCentroTuristico);
		return "redirect:/allcentrosturisticos";
	}
	
	
	//  --- FIN DE LOS METODOS DE CENTRO TURISTICO ---
	
	
	
	//  --- A PARTIR DE AQUÍ EMPIEZAN LOS METODOS DE RESERVA ---

	
	/*
	 * This method will LIST ALL EXISTING RESERVAS.
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
	 * This method will DELETE a RESERVA BY it's ID value.
	 */
	@RequestMapping(value = { "/delete-{idReserva}-reserva" }, method = RequestMethod.GET)
	public String deleteReserva(@PathVariable int idReserva) {
		reservaService.deleteReserva(idReserva);
		return "redirect:/allreservas";
	}
	
	//  --- FIN DE LOS METODOS DE RESERVA ---


}
