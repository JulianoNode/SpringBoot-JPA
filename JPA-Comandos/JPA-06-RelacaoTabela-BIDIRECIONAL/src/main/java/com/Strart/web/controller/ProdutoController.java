package com.Strart.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Strart.DTOs.ProdutoDTO;
import com.Strart.repository.CategoriaRepository;
import com.Strart.repository.FornecedorRepository;
import com.Strart.service.ProdutoService;

@Controller
@RequestMapping({"/","/produtos"})
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @GetMapping
    public String tela(Model model) {
        model.addAttribute("produto", new ProdutoDTO());
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("fornecedores", fornecedorRepository.findAll());
        model.addAttribute("produtos", produtoService.listar());
        return "produto";
    }

    @PostMapping
    public String salvar(
            @ModelAttribute ProdutoDTO produto,
            @RequestParam("arquivo") MultipartFile arquivo
    ) throws IOException {

        if (!arquivo.isEmpty()) {
            String nomeArquivo = UUID.randomUUID() + "_" +
                    arquivo.getOriginalFilename().replaceAll("\\s+", "_");

            Path pastaUploads = Paths.get("uploads");
            Files.createDirectories(pastaUploads);

            Path caminhoArquivo = pastaUploads.resolve(nomeArquivo);
            Files.write(caminhoArquivo, arquivo.getBytes());

            produto.setImagem(nomeArquivo);
        }

        produtoService.salvar(produto);
        return "redirect:/produtos";
    }
}
