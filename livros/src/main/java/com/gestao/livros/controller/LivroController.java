package com.gestao.livros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.gestao.livros.model.Livro;
import com.gestao.livros.repository.LivrosRepository;

import jakarta.validation.Valid;

@Controller
public class LivroController {

	@Autowired
	private LivrosRepository lr;
	
	@RequestMapping(value="/cadastrarLivro", method=RequestMethod.GET)
	public String form() {
		return "cadastroLivro/formLivro";
	}
	
    @RequestMapping(value="/cadastrarLivro", method=RequestMethod.POST)
    public String cadastrar(@Valid Livro livros, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
        	attributes.addFlashAttribute("mensagem", "Aviso! Verifique os campos!");
            return "redirect:/cadastrarLivro";
        }
		
		lr.save(livros);
		attributes.addFlashAttribute("mensagem", "Livro cadastrado com sucesso!");
		return "redirect:/cadastrarLivro";
	}
	
    @RequestMapping("/deletar")
    public String deletarLivro(@RequestParam("codigo") Long codigo) {
        Livro livros=lr.findByCodigo(codigo);
        lr.delete(livros);
        return "redirect:/livros";
    }
    
	@RequestMapping("/livros")
	public ModelAndView ListaLivros() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Livro> livros = lr.findAll();
		mv.addObject("livros", livros);
		return mv;
	}
	
	@RequestMapping(value = "/editarLivro", method = RequestMethod.GET)
	public ModelAndView editarLivro(@RequestParam("codigo") Long codigo) {
	    ModelAndView mv = new ModelAndView("cadastroLivro/editarLivro"); // Página de edição de livro
	    Livro livro = lr.findByCodigo(codigo);
	    mv.addObject("livro", livro);
	    return mv;
	}

	@RequestMapping(value = "/atualizarLivro", method = RequestMethod.POST)
	public String atualizar(@Valid Livro livro, BindingResult result, RedirectAttributes attributes) {
	    if (result.hasErrors()) {
	        attributes.addFlashAttribute("mensagem", "Aviso! Verifique os campos!");
	        return "redirect:/editarLivro?codigo=" + livro.getCodigo();
	    }

	    Livro livroExistente = lr.findByCodigo(livro.getCodigo());
	    livroExistente.setTitulo(livro.getTitulo());
	    livroExistente.setAutor(livro.getAutor());
	    livroExistente.setEditora(livro.getEditora());
	    livroExistente.setAno(livro.getAno());
	    livroExistente.setIsbn(livro.getIsbn());

	    lr.save(livroExistente);
	    attributes.addFlashAttribute("mensagem", "Livro atualizado com sucesso!");
	    return "redirect:/livros";
	}
	
}
