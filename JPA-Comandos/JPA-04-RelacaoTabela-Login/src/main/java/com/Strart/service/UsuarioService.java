package com.Strart.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Strart.datatables.Datatables;
import com.Strart.datatables.DatatablesColunas;
import com.Strart.domain.Perfil;
import com.Strart.domain.Usuario;
import com.Strart.repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private Datatables datatables;

    // Buscar por email
    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    // LOGIN - Spring Security 4.X
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = repository.findByEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + email);
        }

        return new User(
                usuario.getEmail(),
                usuario.getSenha(),
                AuthorityUtils.createAuthorityList(getAuthorities(usuario.getPerfis()))
        );
    }

    private String[] getAuthorities(List<Perfil> perfis) {
        String[] authorities = new String[perfis.size()];
        for (int i = 0; i < perfis.size(); i++) {
            authorities[i] = perfis.get(i).getDesc();
        }
        return authorities;
    }

    // Listagem datatables
    @Transactional(readOnly = true)
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.USUARIOS);

        Page<Usuario> page = datatables.getSearch().isEmpty()
                ? repository.findAll(datatables.getPageable())
                : repository.findByEmailOrPerfil(datatables.getSearch(), datatables.getPageable());

        return datatables.getResponse(page);
    }

    // Salvar novo usuário
    @Transactional
    public void salvarUsuario(Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        repository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorIdEPerfis(Long usuarioId, Long[] perfisId) {
        return repository.findByIdAndPerfis(usuarioId, perfisId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
    }

    public static boolean isSenhaCorreta(String senhaDigitada, String senhaArmazenada) {
        return new BCryptPasswordEncoder().matches(senhaDigitada, senhaArmazenada);
    }

    @Transactional
    public void alterarSenha(Usuario usuario, String senha) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
        repository.save(usuario);
    }
}
