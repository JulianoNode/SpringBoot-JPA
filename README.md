# SpringBoot-JPA
Comandos da JPA relacionado ao spring boot

JPA – Comandos, Anotações e Conceitos Principais
📚 1. Entidades

@Entity – Define a classe como entidade. marca uma classe Java como entidade do JPA. Cada instância dessa classe representa uma linha da tabela no banco de dados.

@Table(name="") – Nome da tabela.

@Id – Chave primária.

@GeneratedValue(strategy=...) – Estratégias: AUTO, IDENTITY, SEQUENCE, TABLE.

@Column – Configura propriedades de coluna.


2. Relacionamentos

@OneToOne – Um para um.

@OneToMany – Um para muitos.

@ManyToOne – Muitos para um.

@ManyToMany – Muitos para muitos.

@JoinColumn – Define coluna de chave estrangeira.

mappedBy, cascade, fetch – Configurações de relacionamento.


3. Tipos Incorporados

@Embeddable – Classe incorporável.

@Embedded – Campo incorporado.


4. Enumerações

@Enumerated(EnumType.STRING) – Armazena enums como texto.


5. Datas

@Temporal – Para tipos Date.


6. EntityManager

persist, merge, remove, find, getReference, flush, clear, detach.


7. JPQL

================ Consultas com createQuery, parâmetros, named queries.

================ Spring Boot – Spring Data JPA =============================
1. Configuração

Dependência: spring-boot-starter-data-jpa. 
============== Configurar:=========== 
datasource_ spring.datasource.url=jdbc:mysql://localhost:3306/meubanco
		  _	spring.datasource.username=root
		  _ spring.datasource.password=1234
		  _ spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

ddl-auto_ spring.jpa.hibernate.ddl-auto = update
		_ Valor		  ->	O que faz
		_ none		  ->	Não mexe no banco (opção mais segura em produção)
		_ update	  ->	Atualiza tabelas sem apagar dados
		_ create	  ->	Cria tabelas toda vez (apaga tudo)
		_ create-drop ->	Cria tabelas e apaga ao encerrar a aplicação
		_ validate	  ->	Apenas valida se o modelo Java combina com o banco

show-sql_ spring.jpa.show-sql = true
		_ spring.jpa.properties.hibernate.format_sql=true

✅ 2. Repositórios

=============== JpaRepository – Métodos: =============== 
📚 Resumo

| Método       | O que faz                     | Onde usar           |
| ------------ | ----------------------------- | ------------------- |
| save()       | Cria ou atualiza uma entidade | Controller, Service |
| findAll()    | Busca todos registros         | Listagens           |
| findById()   | Busca por ID                  | Consultas           |
| delete()     | Remove um objeto              | Service/Controller  |
| deleteById() | Remove pelo ID                | Service/Controller  |

		✅ 1. save()
		Salva um objeto no banco de dados.

		Se o objeto não tem ID, ele é criado.

		Se o objeto já tem ID, ele é atualizado.

		Exemplo:
		User user = new User("Maria", "maria@mail.com");
		userRepository.save(user);

		✅ 2. findAll()
		O que faz:

		Busca todos os registros de uma tabela.

		Exemplo:
		List<User> users = userRepository.findAll();

		✅ 3. findById()
		O que faz:

		Busca um registro pelo ID.
		Retorna um Optional<T> (pode existir ou não).

		Exemplo:
		Optional<User> user = userRepository.findById(1L);

		✅ 4. delete()
		O que faz:

		Deleta um objeto passado como argumento.

		Exemplo:
		User user = userRepository.findById(1L).get();
		userRepository.delete(user);

		✅ 5. deleteById()
		O que faz:

		Deleta um registro diretamente pelo ID, sem precisar buscá-lo antes.

		Exemplo:
		userRepository.deleteById(1L);


3. Query Methods

	# Query Methods do Spring Data JPA
	## Igualdade
	
	- findByNome
	- findByEmail
	
	## Comparações Numéricas
	
	- findByIdadeGreaterThan
	- findByIdadeLessThan
	- findByIdadeBetween
	
	## Consultas com LIKE
	
	- findByNomeContaining
	- findByNomeStartsWith
	- findByNomeEndsWith
	
	## Operadores Lógicos
	
	- findByNomeAndEmail
	- findByNomeOrEmail
	
	## Ordenação
	
	- findByNomeContainingOrderByIdadeDesc
	
	## Paginação
	
	- findByIdadeGreaterThan(pageable)
	
	## Valores Nulos
	
	- findByEmailIsNull
	- findByEmailIsNotNull
	
	## Booleanos
	- findByAtivoTrue
	- findByAtivoFalse.

4. @Query

JPQL e nativeQuery para SQL.

	# Exemplos completos de @Query no Spring Data JPA
	## 1. Consulta simples com JPQL
	
	@Query("SELECT u FROM Usuario u WHERE u.nome = :nome")
	Usuario buscarPorNome(@Param("nome") String nome);
	
	## 2. Consulta com LIKE
	
	@Query("SELECT u FROM Usuario u WHERE u.nome LIKE %:nome%")
	List buscarPorNomeContendo(@Param("nome") String nome);
	
	## 3. Consulta com parâmetros posicionais
	
	@Query("SELECT u FROM Usuario u WHERE u.idade > ?1")
	List buscarMaioresQue(Integer idade);
	
	## 4. Consulta com JOIN
	
	@Query("SELECT u FROM Usuario u JOIN u.endereco e WHERE e.cidade = :cidade")
	List buscarPorCidade(@Param("cidade") String cidade);
	
	## 5. Consulta com múltiplas condições
	
	@Query("SELECT u FROM Usuario u WHERE u.nome = :nome AND u.email = :email")
	Usuario buscarPorNomeEEmail(String nome, String email);
	
	## 6. Consulta nativa (SQL)
	
	@Query(value = "SELECT * FROM usuarios WHERE ativo = true", nativeQuery = true)
	List buscarAtivos();
	
	## 7. Atualização com @Modifying
	
	@Modifying
	@Query("UPDATE Usuario u SET u.ativo = false WHERE u.id = :id")
	void desativarUsuario(@Param("id") Long id);
	
	## 8. Delete com @Modifying
	
	@Modifying
	@Query("DELETE FROM Usuario u WHERE u.email = :email")
	void deletarPorEmail(@Param("email") String email);
	
	## 9. Contagem personalizada
	
	@Query("SELECT COUNT(u) FROM Usuario u WHERE u.ativo = true")
	Long contarAtivos();
	
	## 10. Busca limitada
	
	@Query("SELECT u FROM Usuario u ORDER BY u.id DESC")
	List buscarRecentes(Pageable pageable);

5. Paginação e Ordenação

✅ 1. Como ler os dados retornados
Page<Usuario> page = usuarioRepository.findAll(pageable);

page.getContent();        // lista de usuários
page.getTotalPages();     // total de páginas
page.getTotalElements();  // total de registros
page.getNumber();         // página atual
page.getSize();           // tamanho da página
page.hasNext();           // tem próxima página?



findAll(Pageable), PageRequest.of.

6. Transações

@Transactional com rollbackFor, readOnly.

✔ Quando usar cada um?

Situação									Anotação recomendada
Busca simples								@Transactional(readOnly = true)
Salvamento/edição/remoção					@Transactional
Você quer rollback mesmo para Exception		@Transactional(rollbackFor = Exception.class)
Serviço com múltiplas operações de banco	@Transactional

7. DTOs e Projections

Interfaces com getters usados como projections.

8. Auditoria

@CreatedDate, @LastModifiedDate, @EnableJpaAuditing.

🎯 Resumo rápido
Anotação	Função

@CreatedDate										Preenche automaticamente quando o registro é criado
@LastModifiedDate									Atualiza sempre que o registro é alterado
@EnableJpaAuditing									Ativa o sistema de auditoria no Spring
@EntityListeners(AuditingEntityListener.class)		Liga a entidade ao sistema de auditoria

✔ Exemplo em JAVA SPRING BOOT:

@SpringBootApplication
@EnableJpaAuditing
public class MinhaAplicacao {

    public static void main(String[] args) {
        SpringApplication.run(MinhaAplicacao.class, args);
    }
}


Codigo de paginação no HTML:

