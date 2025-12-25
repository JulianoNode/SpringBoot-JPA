package com.Strart.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.Strart.DTOs.ProdutoDTO;
import com.Strart.model.Produto;
import com.Strart.repository.CategoriaRepository;
import com.Strart.repository.FornecedorRepository;
import com.Strart.service.ProdutoService;

@Controller
@RequestMapping({ "/", "/produtos" })
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	// Listar Produto e  Paginação da pagina
	
	@GetMapping
	public String listarProdutos(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "2") int size,
	        Model model) {

	    Page<Produto> paginaProdutos = produtoService.listarPaginado(page, size);

	    model.addAttribute("produto", new ProdutoDTO());
	    model.addAttribute("categorias", categoriaRepository.findAll());
	    model.addAttribute("fornecedores", fornecedorRepository.findAll());
	    model.addAttribute("produtos", produtoService.listar());
	    
	    model.addAttribute("produtos", paginaProdutos.getContent());
	    model.addAttribute("paginaAtual", page);
	    model.addAttribute("totalPaginas", paginaProdutos.getTotalPages());

	    return "produto/form";
	}

	// Salvar Produto
	@PostMapping
	public String salvar(@ModelAttribute ProdutoDTO produto, @RequestParam("arquivo") MultipartFile arquivo)
			throws IOException {
		salvarImagem(produto, arquivo);
		produtoService.salvar(produto);
		return "redirect:/produtos";
	}

	// Editar produto
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {

		Produto p = produtoService.buscarPorId(id);

		ProdutoDTO dto = new ProdutoDTO();
		dto.setId(p.getId());
		dto.setNome(p.getNome());
		dto.setPreco(p.getPreco());
		dto.setCategoriaId(p.getCategoria().getId());
		dto.setFornecedorId(p.getFornecedor().getId());
		dto.setImagem(p.getImagem());

		model.addAttribute("produto", dto);
		model.addAttribute("categorias", categoriaRepository.findAll());
		model.addAttribute("fornecedores", fornecedorRepository.findAll());
		model.addAttribute("produtos", produtoService.listar());

		model.addAttribute("editar", true); // ⭐ CONTROLA O MODAL

		return "produto/modal"; // ✅ AGORA EXISTE
	}

	@PostMapping("/editar")
	public String salvarEdicao(
	        @ModelAttribute ProdutoDTO produto,
	        @RequestParam(value = "arquivo", required = false) MultipartFile arquivo
	) throws IOException {

	    salvarImagem(produto, arquivo);
	    produtoService.atualizar(produto);
	    return "redirect:/produtos";
	}

	// Excluir produto (seguro)
	@PostMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		produtoService.excluir(id);
		return "redirect:/produtos";
	}

	// MÉTODO AUXILIAR DE IMAGEM
	private void salvarImagem(ProdutoDTO produto, MultipartFile arquivo) throws IOException {
		if (arquivo != null && !arquivo.isEmpty()) {
			String nomeArquivo = UUID.randomUUID() + "_" + 
			arquivo.getOriginalFilename().replaceAll("\\s+", "_");
			Path pasta = Paths.get("uploads/produtos");
			Files.createDirectories(pasta);
			Files.write(pasta.resolve(nomeArquivo), arquivo.getBytes());
			// 🔥 substitui pela nova
			produto.setImagem(nomeArquivo);
		}
		// ✅ se não enviou nova imagem, mantém a antiga
	}

	
}

//------------------------ Classe Imagens ----------------------
@Controller
@RequestMapping("/imagens")
class ImagemProdutoController {

	@GetMapping("/{nome}")
	@ResponseBody
	public ResponseEntity<byte[]> carregarImagem(@PathVariable String nome) throws IOException {
		Path caminho = Paths.get("uploads/produtos").resolve(nome);
		byte[] imagem = Files.readAllBytes(caminho);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,
				Files.probeContentType(caminho)).body(imagem);
	}
}
