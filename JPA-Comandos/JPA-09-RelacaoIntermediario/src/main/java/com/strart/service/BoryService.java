package com.strart.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.strart.model.Bory;
import com.strart.model.TipoBory;
import com.strart.repository.BoryRepository;

@Service
public class BoryService {

	private final BoryRepository repository;
	private final ImagemService imagemService;

	public BoryService(BoryRepository repository, ImagemService imagemService) {
		this.repository = repository;
		this.imagemService = imagemService;
	}

	public List<Bory> listarTodos() {
		return repository.findAll();
	}

	public List<Bory> listarPorTipo(TipoBory tipo) {
		return repository.findByTipo(tipo);
	}

	// MÉTODO CORRETO
	public void salvar(Bory bory, MultipartFile file) throws IOException {
		if (file != null && !file.isEmpty()) {
			String pasta = pastaPorTipo(bory.getTipo());
			String imagem = imagemService.salvarImagem(file, pasta);

			bory.setImagem(imagem);
			bory.setPastaImagem(pasta);
		}

		repository.save(bory);
	}

	// Define pasta conforme o tipo
	private String pastaPorTipo(TipoBory tipo) {
		return switch (tipo) {
		case START -> "starts";
		case MIDDLE -> "middles";
		case END -> "ends";
		case CERTIFICADO -> "certificados";
		};
	}
}
