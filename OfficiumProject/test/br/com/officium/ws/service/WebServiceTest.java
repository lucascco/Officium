/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.ws.service;

import br.com.officium.dominio.Usuario;
import br.com.officium.ws.service.utils.JsonGenerator;
import br.com.officium.ws.service.utils.SucessJson;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author Lucas Corrêa
 */
public class WebServiceTest {

    private final WebResource service;
    private final Gson gson;
    
    public WebServiceTest() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        service = client.resource(getBaseURI());
        gson = new Gson();
    }
    
    @Test
    public void fazerLoginWebService(){
        Usuario usuario = new Usuario();
        usuario.setPassword("123456");
        usuario.setUsername("lucas_user");
        
        Form form = new Form();
        form.add("userString", JsonGenerator.generateJson(usuario));
        String result = service.path("rest").path("UsuarioService").path("login")
        .post(String.class, form);
        
        SucessJson response = gson.fromJson(result, SucessJson.class);
        
        Assert.assertTrue(response.getCode() != - 1);
    }
    
//    @Test
    public void cadastrarUsuarioWebService(){
        Usuario usuario = new Usuario();
        usuario.setPassword("123456");
        usuario.setUsername("teste_user");
        usuario.setNome("Teste Web Service");
        usuario.setEnable(true);
        
        Form form = new Form();
        form.add("jsonUsuario", JsonGenerator.generateJson(usuario));
        String result = service.path("rest").path("UsuarioService").path("create")
        .post(String.class ,form);
        
        SucessJson response = gson.fromJson(result, SucessJson.class);
        
        Assert.assertTrue(response.getCode() != - 1);
        
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/OfficiumProject").build();
    }

}
