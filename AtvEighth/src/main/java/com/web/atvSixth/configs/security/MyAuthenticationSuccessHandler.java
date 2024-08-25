package com.web.atvSixth.configs.security;

import com.web.atvSixth.configs.Resolve;
import com.web.atvSixth.model.Entity.Pesssoa.Pessoa;
import com.web.atvSixth.model.Entity.Usuario;
import com.web.atvSixth.model.Repository.UsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private Resolve r;

    @Autowired
    private UsuarioRepository ryUsuario;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        Usuario usuario = ryUsuario.usuario(username);

        if (usuario != null) {
            Pessoa pessoa = r.pessoaUser(usuario.getId());
            request.getSession().setAttribute("pessoaLogada", pessoa);
        }

        response.sendRedirect("/produto/comprar");
    }
}