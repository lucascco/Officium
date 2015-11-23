/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.dao;

import br.com.officium.dominio.Mensagem;
import java.util.List;

/**
 *
 * @author Lucas Corrêa
 */
public interface MensagemDao extends GenericDao<Mensagem>{
    public List<Mensagem> buscarPorUsuarioDestino(Long idUsuarioDestino) throws Exception;
}
