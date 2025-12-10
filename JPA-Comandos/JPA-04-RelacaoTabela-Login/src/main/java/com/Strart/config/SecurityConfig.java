package com.Strart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// Removidas importações desnecessárias do AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.Strart.domain.PerfilTipo;
// A importação de UsuarioService é necessária apenas se fosse usar o AuthenticationManager
// import com.Strart.service.UsuarioService;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

	private static final String ADMIN = PerfilTipo.ADMIN.getDesc();
	private static final String AFILIADO = PerfilTipo.AFILIADO.getDesc();

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		// 1. CONFIGURAÇÃO DO FLUXO DE REQUISIÇÕES (Authorization)
		http.authorizeHttpRequests(authorize -> {
			configurarAcessosPublicos(authorize);
			configurarAcessosAfiliado(authorize);
			configurarAcessosAdmin(authorize);
			configurarAcessosEspecialidades(authorize);

			// Qualquer outra requisição precisa estar autenticada
			authorize.anyRequest().authenticated();
		})
		
		// 2. CONFIGURAÇÃO DE LOGIN (FormLogin com sintaxe de lambda)
		.formLogin(form -> form
			.loginPage("/login")
			.defaultSuccessUrl("/", true)
			.failureUrl("/login-error")
			.permitAll()
		)
		
		// 3. CONFIGURAÇÃO DE LOGOUT (Logout com sintaxe de lambda)
		.logout(logout -> logout
			.logoutSuccessUrl("/")
			.permitAll()
		)
		
		// 4. CONFIGURAÇÃO DE TRATAMENTO DE EXCEÇÕES (Acesso Negado)
		.exceptionHandling(exception -> exception
			.accessDeniedPage("/acesso-negado")
		);

		return http.build();
	}

	private void configurarAcessosPublicos(
			AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
		// ATENÇÃO: Adicionei "/error" para quebrar o loop de redirecionamento.
		authorize.requestMatchers("/webjars/**", "/css/**", "/image/**", "/js/**", "/error").permitAll()
				.requestMatchers("/vendas/**").permitAll().requestMatchers("/", "/home").permitAll()
				.requestMatchers("/fragments/links-header/visitante.html").permitAll()
				.requestMatchers("/imagemIndicado/{idprod}").permitAll().requestMatchers("/IndicadoScrollTopUser/ajax")
				.permitAll().requestMatchers("/Indicados").permitAll().requestMatchers("/pesquisarIndicados")
				.permitAll().requestMatchers("/buscarIndicados").permitAll();
	}

	private void configurarAcessosAfiliado(
			AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
		authorize.requestMatchers("/afiliados/dados", "/afiliados/salvar", "/afiliados/editar", "/salvarCategoria")
				.hasAnyAuthority(AFILIADO, ADMIN).requestMatchers("/afiliados/**").hasAuthority(AFILIADO);
	}

	private void configurarAcessosAdmin(
			AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
		authorize.requestMatchers("/u/editar/senha", "/u/confirmar/senha").hasAnyAuthority(AFILIADO)
				.requestMatchers("/u/**").hasAuthority(ADMIN);
	}

	private void configurarAcessosEspecialidades(
			AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
		authorize.requestMatchers("/especialidades/datatables/server/afiliado/*").hasAnyAuthority(AFILIADO, ADMIN)
				.requestMatchers("/especialidades/titulo").hasAnyAuthority(AFILIADO, ADMIN)
				.requestMatchers("/especialidades/**").hasAuthority(ADMIN);
	}

	/**
	 * Bean para o PasswordEncoder. Essencial para autenticação.
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * O MÉTODO authenticationManager FOI REMOVIDO. 
	 * No Spring Boot 3.x (Spring Security 6+), ele é configurado automaticamente
	 * usando o PasswordEncoder e o UserDetailsService (seu UsuarioService).
	 */
}