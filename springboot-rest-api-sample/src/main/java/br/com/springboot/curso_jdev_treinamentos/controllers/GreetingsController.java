package br.com.springboot.curso_jdev_treinamentos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamentos.repository.UsuarioRepository;
import br.com.springboot.curso_jdev_treinamentos.usuario.Usuario;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController //intercepta todas as requisições de dados mapeada de qualquer dispositivo
public class GreetingsController {
	
	@Autowired /* CDI - Injeção de dependencia */
	private UsuarioRepository usuarioRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    
    @RequestMapping (value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus (HttpStatus.OK)
    public String retornaOlaMundo (@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	
    	usuarioRepository.save(usuario); //grava no banco de dados   	
    	
    	return "Ola mundo " + nome;
    	
    }
    
	@GetMapping(value = "listatodos") /* Primeiro método de API - buscar todos */
    @ResponseBody //retorna os dados para o corpo da resposta
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	//executa a consulta no banco
    	
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    	//retorna a lista em JSON
    }
	
	@PostMapping(value= "salvar") //mapeia a url
	@ResponseBody //descrição da resposta
	public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario) { //recebe os dados para salvar
		
		Usuario user = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}
	
	@PutMapping(value= "atualizar") //O GetMapping intercepta os dados de um formulário
	@ResponseBody //descrição da resposta
	public ResponseEntity<?> atualizar (@RequestBody Usuario usuario) { 
		//@RequestParam - requisição de parâmetro. Só precisa passar um id pra realizar o delete.
		
		if (usuario.getId() == null) {
			return new ResponseEntity<String>("Id não foi informado para atualização", HttpStatus.OK);			
		}
		
		Usuario user = usuarioRepository.saveAndFlush(usuario);
		//recebe os dados para consultar
		
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}
	
	@DeleteMapping(value= "delete") //mapeia a url
	@ResponseBody //descrição da resposta
	public ResponseEntity<String> delete (@RequestParam Long iduser) { 
		//@RequestParam - requisição de parâmetro. Só precisa passar um id pra realizar o delete.
		
		usuarioRepository.deleteById(iduser);
		//deleteById parametro do spring boot
		
		return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
	}
	
	@GetMapping(value= "buscaruserid") //O GetMapping intercepta os dados de um formulário
	@ResponseBody //descrição da resposta
	public ResponseEntity<Usuario> buscaruserid (@RequestParam("iduser") Long iduser) { 
		//@RequestParam - requisição de parâmetro. Só precisa passar um id pra realizar o delete.
		
		Usuario usuario = usuarioRepository.findById(iduser).get();
		//recebe os dados para consultar
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	
	
    
    
}
