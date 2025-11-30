package com.Strart.web.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Strart.model.Categoria;
import com.Strart.model.Produto;
import com.Strart.model.dto.ProdutoDTO;
import com.Strart.service.CategoriaService;
import com.Strart.service.ProdutoService;
@Controller
@RequestMapping({"/","/produtos"})
public class ProdutoController {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;
    private final ModelMapper mapper;

    public ProdutoController(ProdutoService produtoService,
                             CategoriaService categoriaService,
                             ModelMapper mapper) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
        this.mapper = mapper;
    }

    @GetMapping
    public String listar(Model model) {
        List<Produto> produtos = produtoService.listarTodos();
        model.addAttribute("produtos", produtos);
        return "produtos/list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new ProdutoDTO());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "produtos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("produto") ProdutoDTO dto) {
        Produto produto = mapper.map(dto, Produto.class);
        // Ajustar categoria manualmente
        Categoria categoria = categoriaService.buscarPorId(dto.getCategoriaId());
        produto.setCategoria(categoria);
        produtoService.salvar(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Produto produto = produtoService.buscarPorId(id);
        ProdutoDTO dto = mapper.map(produto, ProdutoDTO.class);
        dto.setCategoriaId(produto.getCategoria().getId());
        model.addAttribute("produto", dto);
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "produtos/form";
    }

    @GetMapping("/delete/{id}")
    public String deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return "redirect:/produtos";
    }

}