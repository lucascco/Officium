/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.ws.service;

import br.com.officium.dominio.Usuario;
import br.com.officium.ws.service.utils.JsonGenerator;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.junit.Test;

/**
 *
 * @author Lucas Corrêa
 */
public class WebServiceTest {

    private final WebResource service;
    
    public WebServiceTest() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        service = client.resource(getBaseURI());
    }
    
//    @Test
    public void fazerLoginWebService(){
        Usuario usuario = new Usuario();
        usuario.setPassword("123456");
        usuario.setUsername("lucas_user");
        
        Form form = new Form();
        form.add("userString", JsonGenerator.generateJson(usuario));
        service.path("rest").path("UsuarioService").path("login")
        .post(form);
    }
    
    @Test
    public void cadastrarUsuarioWebService(){
        Usuario usuario = new Usuario();
        usuario.setPassword("123456");
        usuario.setUsername("teste_user");
        usuario.setNome("Teste Web Service");
        usuario.setEnable(true);
        
        Form form = new Form();
        form.add("jsonUsuario", JsonGenerator.generateJson(usuario));
        service.path("rest").path("UsuarioService").path("create")
        .post(form);
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/OfficiumProject").build();
    }

}
