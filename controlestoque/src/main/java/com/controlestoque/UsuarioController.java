package com.controlestoque;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlestoque.Enums.StatusUsuario;
import com.controlestoque.Repository.GrupoUsers;
import com.controlestoque.Repository.Usuarios;
import com.controlestoque.model.Usuario;
import com.controlestoque.service.UsuarioService;
import com.controlestoque.service.exception.EmailUsuarioJaCadastradoException;
import com.controlestoque.service.exception.SenhaObrigatoriaUsuarioException;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private GrupoUsers gruUserRepository;

	@Autowired
	private UsuarioService usuService;
	
	@Autowired
	private Usuarios usuRepository;

//	@Autowired
//	PasswordEncoder passwordEnconder;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/novoUsuario");
		mv.addObject("grupoUser", gruUserRepository.findAll());
		return mv;
	}

	@PostMapping({"/novo","{\\+d}"})
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes atrrributes) {
		if (result.hasErrors()) {
			return novo(usuario);
		}
		try {
			usuService.salvar(usuario);

		} catch (EmailUsuarioJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(usuario);

		} catch (SenhaObrigatoriaUsuarioException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return novo(usuario);
		}

//		 if(usuario.isNovo()) {
//			 usuario.setSenha(this.passwordEnconder.encode( usuario.getSenha()));
//			 usuario.setConfirmacaoSenha(usuario.getSenha());
//			 
//		 }

		atrrributes.addFlashAttribute("mensagem", "Usuario Salvo com sucesso!");
		return new ModelAndView("redirect:/usuario/novo");

	}
	
	@PutMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public void atualizarStatus(@RequestParam("codigos[]") Long[] codigos, @RequestParam("status") StatusUsuario statusUsuario) {
		usuService.alterarStatus(codigos, statusUsuario);
	}

//	@GetMapping
//	public ModelAndView pesquisarUsuario(UsuarioFilter usuarioFilter, @PageableDefault(size = 3) Pageable pageable,
//			HttpServletRequest httpServletRequest) {
//		ModelAndView mv = new ModelAndView("usuario/pesquisaUsuario");
//		mv.addObject("grupo", gruRepository.findAll());
//
//		PageWrapper<Usuario> paginaWrapper = new PageWrapper<>(usuRepository.filtrar(usuarioFilter, pageable),
//				httpServletRequest);
//		
//		mv.addObject("pagina", paginaWrapper);
//		return mv;
//	}
//	
//	@GetMapping("/{codigo}")
//	public ModelAndView editar(@PathVariable ("codigo") Long codigo) {
//		Usuario usuario = usuRepository.buscarComGrupos(codigo);
//		ModelAndView mv = novo(usuario);
//		mv.addObject(usuario);
//		return mv;
//	}

}
