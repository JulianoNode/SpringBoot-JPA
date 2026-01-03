document.addEventListener("DOMContentLoaded", function() {
	const input = document.getElementById('fileInput');
	const preview = document.getElementById('preview');

	if (input) {
		input.addEventListener('change', () => {
			const file = input.files[0];
			if (file && preview) {
				preview.src = URL.createObjectURL(file);
				preview.style.display = 'block';
			}
		});
	}
});

function mostrarNomeArquivo(input) {
	const fileName = document.getElementById("file-name");
	if (!fileName) return;

	if (input.files && input.files.length > 0) {
		fileName.textContent = input.files[0].name;
	} else {
		fileName.textContent = "Nenhuma imagem selecionada";
	}
}
