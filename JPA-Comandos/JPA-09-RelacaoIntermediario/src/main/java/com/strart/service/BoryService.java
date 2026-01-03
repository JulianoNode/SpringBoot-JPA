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
	public Bory salvar(Bory bory, MultipartFile file) throws IOException {
		// 🔒 valida o limite ANTES de salvar
		validarLimiteCards(bory);
		prepararImagem(bory, file);
		
		return repository.save(bory);
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

	// VALIDAR LIMITE (ESSENCIAL)
	public void validarLimiteCards(Bory bory) {

        long quantidadeAtual = repository.countByTipo(bory.getTipo());
        int limite = limitePorTipo(bory.getTipo());

        while (quantidadeAtual >= limite) {
            throw new IllegalStateException(
                "Limite de cards para o tipo " + bory.getTipo() +
                " já foi excedido. Máximo permitido: " + limite
            );
        }
	}

	public boolean limiteAtingido(TipoBory tipo) {
	    return repository.countByTipo(tipo) >= limitePorTipo(tipo);
	}
	
	// VALIDAR IMAGEM (ESSENCIAL)
	private void prepararImagem(Bory bory, MultipartFile file) throws IOException {
		// Exemplo: validação de limite por tipo
		long quantidade = repository.countByTipo(bory.getTipo());

		int limite = limitePorTipo(bory.getTipo());

		if (quantidade >= limite) {
			throw new IllegalStateException("Limite de registros atingido para o tipo: " + bory.getTipo());
		}

		// Salvar imagem (se existir)
		if (file != null && !file.isEmpty()) {
			String pasta = pastaPorTipo(bory.getTipo());
			String imagem = imagemService.salvarImagem(file, pasta);

			bory.setImagem(imagem);
			bory.setPastaImagem(pasta);
		}
	}

	private int limitePorTipo(TipoBory tipo) {
		return switch (tipo) {
		case START -> 6;
		case MIDDLE -> 9;
		case END -> 9;
		case CERTIFICADO -> 3;
		};
	}

}
