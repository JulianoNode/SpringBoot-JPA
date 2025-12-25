package com.Strart;

public class Organizacao {
	public void Run() {
		System.err.println("App Rondando...\n");
	}
	public void Lealt() {
		System.err.println(
				"🔹 1️ Relacionamento UNIDIRECIONAL\n📌 Nome correto: UNIDIRECIONAL\n➡️ Apenas uma entidade conhece a outra.\n");
		System.err.println(
				"🔹 2️ Relacionamento BIDIRECIONAL\n📌 Nome correto: BIDIRECIONAL\n➡️ As duas entidades se conhecem.");
		System.out.println("\ncom.Start\n"
				+ "│\r\n"
				+ "├── config\r\n"
				+ "│   └── MapperConfig.java\r\n"
				+ "│\r\n"
				+ "├── controller\r\n"
				+ "│   ├── ProdutoController.java\r\n"
				+ "│   ├── CategoriaController.java\r\n"
				+ "│   ├── FornecedorController.java\r\n"
				+ "│\r\n"
				+ "├── dto\r\n"
				+ "│   ├── ProdutoDTO.java\r\n"
				+ "│   ├── CategoriaDTO.java\r\n"
				+ "│   ├── FornecedorDTO.java\r\n"
				+ "│\r\n"
				+ "├── model\r\n"
				+ "│   ├── Produto.java\r\n"
				+ "│   ├── Categoria.java\r\n"
				+ "│   ├── Fornecedor.java\r\n"
				+ "│\r\n"
				+ "├── repository\r\n"
				+ "│   ├── ProdutoRepository.java\r\n"
				+ "│   ├── CategoriaRepository.java\r\n"
				+ "│   ├── FornecedorRepository.java\r\n"
				+ "│\r\n"
				+ "├── service\r\n"
				+ "│   ├── ProdutoService.java\r\n"
				+ "│   ├── CategoriaService.java\r\n"
				+ "│   ├── FornecedorService.java\r\n"
				+ "│\r\n"
				+ "└── resources\r\n"
				+ "    └── static\r\n"
				+ "        ├── produto.js\n"
				+ "        ├── categoria.js\n"
				+ "        └── fornecedor.js\n"
				+ "│\r\n"
				+ "└── resources\r\n"
				+ "    └── templates\r\n"
				+ "        ├── produto.html\n"
				+ "        ├── categoria.html\n"
				+ "        └── fornecedor.html\n");
	}
}
