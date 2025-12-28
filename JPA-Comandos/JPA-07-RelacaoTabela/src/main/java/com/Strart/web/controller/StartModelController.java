package com.Strart.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.Strart.DTOs.StartModelDTO;
import com.Strart.model.StartModel;
import com.Strart.service.ProdutoService;
import com.Strart.service.StartModelService;

@Controller
@RequestMapping("/start-model")
public class StartModelController {

	@Autowired
	private StartModelService startModelService;
	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/produto/{produtoId}/novo")
	public String novo(@PathVariable Long produtoId, Model model) {

		StartModelDTO dto = new StartModelDTO();
		dto.setProdutoId(produtoId);

		model.addAttribute("startModelDTO", dto);

		return "startmodel/form";
	}

	@PostMapping("/salvar")
	public String salvar(@ModelAttribute StartModelDTO startModelDTO,
						@RequestParam(value = "arquivo", required = false)
						MultipartFile arquivo) throws IOException {

		salvarImagem(startModelDTO, arquivo);
		startModelService.salvar(startModelDTO);

		return "redirect:/start-model/produto/" + startModelDTO.getProdutoId();
	}

	@GetMapping("/produto/{produtoId}")
	public String listarPorProduto(@PathVariable Long produtoId, Model model) {

		model.addAttribute("startModels", startModelService.listarPorProduto(produtoId));

		model.addAttribute("produto", produtoService.buscarPorId(produtoId));

		return "startmodel/lista";
	}

	// MÉTODO AUXILIAR DE IMAGEM
	private void salvarImagem(StartModelDTO startModelDTO, MultipartFile arquivo) throws IOException {

		if (arquivo != null && !arquivo.isEmpty()) {
			if (!arquivo.getContentType().startsWith("image/")) {
				throw new RuntimeException("Arquivo inválido");
			}
			String nomeArquivo = UUID.randomUUID() + "_" + arquivo.getOriginalFilename().replaceAll("\\s+", "_");
			
			Path pasta = Paths.get("uploads/startmodels");
			Files.createDirectories(pasta);
			Files.write(pasta.resolve(nomeArquivo), arquivo.getBytes());
			// 🔥 substitui pela nova
			startModelDTO.setImagem(nomeArquivo);
		}
		// ✅ se não enviou nova imagem, mantém a antiga
	}
	
	 // 🔹 FORM EDITAR
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {

        StartModel sm = startModelService.buscarPorId(id);

        StartModelDTO dto = new StartModelDTO();
        dto.setId(sm.getId());
        dto.setTitulo(sm.getTitulo());
        dto.setDescricao(sm.getDescricao());
        dto.setImagem(sm.getImagem());
        dto.setProdutoId(sm.getProduto().getId());

        model.addAttribute("startModelDTO", dto);
        return "startmodel/editar";
    }

 // 🔹 SALVAR EDIÇÃO (COM IMAGEM)
    @PostMapping("/editar")
    public String editar(@ModelAttribute StartModelDTO dto,
                         @RequestParam(value = "arquivo", required = false)
                         MultipartFile arquivo) throws IOException {

        // 🔥 se enviou nova imagem, substitui
        salvarImagem(dto, arquivo);
        startModelService.editar(dto);
        return "redirect:/start-model/produto/" + dto.getProdutoId();
    }
    // 🔹 EXCLUIR
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {

        StartModel sm = startModelService.buscarPorId(id);
        Long produtoId = sm.getProduto().getId();

        startModelService.excluir(id);

        return "redirect:/start-model/produto/" + produtoId;
    }

}

//------------------------ Classe Imagens ----------------------
@Controller
@RequestMapping("/imagens")
class ImagemProdutoController {

	@GetMapping("/{nome}")
	@ResponseBody
	public ResponseEntity<byte[]> carregarImagem(@PathVariable String nome) throws IOException {
		Path caminho = Paths.get("uploads/startmodels").resolve(nome);
		byte[] imagem = Files.readAllBytes(caminho);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(caminho)).body(imagem);
	}
}
