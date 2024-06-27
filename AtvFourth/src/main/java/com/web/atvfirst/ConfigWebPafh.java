package com.web.atvfirst;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author fagno
 * @Configuration para indicar ao Spring que essa é uma classe de configuração.
 * Em seguida, é preciso implementar a interface WebMvcConfigurer.
 */

@Configuration
public class ConfigWebPafh implements WebMvcConfigurer {

    /**
     * Com a chamada a registry.addViewController(), estamos registrando um controller
     * definido pelo próprio Spring, para atender a requisições direcionadas à URL / e /home. E com a chamada
     * a setViewName(), sempre que a aplicação receber uma requisição para um desses endereços, a view home,
     * criada na última aula, será exibida.
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/pessoa/list");
        registry.addRedirectViewController("/", "/produto/list");
        registry.addRedirectViewController("/venda", "/venda/list");
        registry.addRedirectViewController("/pessoa/fisica", "/pessoa/fisica/list");
        registry.addRedirectViewController("/pessoa/juridica", "/pessoa/juridica/list");
    }


}
