package com.web.atvSixth.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    UsuarioDetailsConfig usuarioDetailsConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        customizer ->
                                customizer
                                        .requestMatchers("/**css/**", "/pages/home",
                                                "/pessoafisica/form", "/pessoajuridica/form",
                                                "produto/comprar").permitAll()
                                        .requestMatchers("/pessoafisica/list", "/pessoajuridica/list", "/produto/list", "/venda/list").hasAnyRole("ADMIN")
                                        .requestMatchers(HttpMethod.POST, "/pessoafisica/save", "/pessoajuridica/save").permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
                .formLogin(customizer ->
                        customizer
                                .loginPage("/pages/login")
                                .defaultSuccessUrl("/produto/comprar", true)
                                .permitAll()
                )
                .httpBasic(withDefaults())
                .logout(LogoutConfigurer::permitAll)
                .rememberMe(withDefaults());
        return http.build();
    }
/*

     @Bean
     public InMemoryUserDetailsManager userDetailsService() {
         UserDetails user1 = User.withUsername("user")
                 .password(passwordEncoder().encode("123"))
                 .roles("USER")
                 .build();
         UserDetails admin = User.withUsername("admin")
                 .password(passwordEncoder().encode("admin"))
                 .roles("ADMIN")
                 .build();
         return new InMemoryUserDetailsManager(user1, admin);
     }
*/

    @Autowired
    public void configureUserDetails(final AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(usuarioDetailsConfig).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * Com o método, instanciamos uma instância do encoder BCrypt e deixando o controle dessa instância como responsabilidade do Spring.
     * Agora, sempre que o Spring Security necessitar condificar um senha, ele já terá o que precisa configurado.
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}