package com.strart;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoService {

	private final VideoRepository repository;

	// pasta na raiz do projeto
	private static final String BASE_DIR = "upload/";

	public VideoService(VideoRepository repository) {
		this.repository = repository;
	}

	public void salvar(MultipartFile file, String pasta) throws IOException {

		if (file == null || file.isEmpty()) {
			throw new IllegalArgumentException("Arquivo vazio");
		}

		String nomeOriginal = file.getOriginalFilename();
		if (nomeOriginal == null) {
			throw new IllegalArgumentException("Nome do arquivo inválido");
		}

		nomeOriginal = nomeOriginal.toLowerCase();

		// valida extensão + define MIME correto
		String tipo;
		if (nomeOriginal.endsWith(".mp4")) {
			tipo = "video/mp4";
		} else if (nomeOriginal.endsWith(".webm")) {
			tipo = "video/webm";
		} else {
			throw new IllegalArgumentException("Formato não suportado");
		}

		// sanitiza nome (remove espaço, acento, etc.)
		String nomeSeguro = nomeOriginal.replaceAll("[^a-zA-Z0-9.]", "_");

		// nome final único
		String nomeArquivo = System.currentTimeMillis() + "_" + nomeSeguro;

		// cria pasta upload/videos/aulas
		Path diretorio = Paths.get(BASE_DIR + pasta);
		Files.createDirectories(diretorio);

		// salva arquivo físico
		Path destino = diretorio.resolve(nomeArquivo);
		Files.copy(file.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

		// salva metadados no banco
		Video video = new Video();
		video.setNomeArquivo(nomeArquivo);
		video.setPasta(pasta);
		video.setTipo(tipo);

		repository.save(video);
	}
}
