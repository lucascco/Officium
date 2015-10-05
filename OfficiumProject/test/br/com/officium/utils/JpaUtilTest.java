package br.com.officium.utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.officium.dao.GenericDao;
import br.com.officium.dao.impl.GenericDaoImpl;
import br.com.officium.dao.impl.StatusTarefaDaoImpl;
import br.com.officium.dominio.StatusTarefa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lucas Corrêa
 */
public class JpaUtilTest {

    public JpaUtilTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
//    @Test
    public void updateSchemaTableBancoDados() {
        EntityManager entityManager = JpaUtil.getEntityManager();
    }

//    @Test
    public void salvarObjetos() throws Exception {
        GenericDao dao;
        dao = new StatusTarefaDaoImpl();

        StatusTarefa statusTarefa = new StatusTarefa();
        statusTarefa.setDescricao("Teste Tarefa");

        dao.salvar(statusTarefa);
    }
    
//    @Test
    public void salvarVariosObjetos() throws Exception {
        
        StatusTarefaDaoImpl dao = new StatusTarefaDaoImpl();

        StatusTarefa statusTarefa1 = new StatusTarefa();
        statusTarefa1.setDescricao("Teste de Tarefa 1");
        StatusTarefa statusTarefa2 = new StatusTarefa();
        statusTarefa2.setDescricao("Teste de Tarefa 2");
        StatusTarefa statusTarefa3 = new StatusTarefa();
        statusTarefa3.setDescricao("Teste de Tarefa 3");
        StatusTarefa statusTarefa4 = new StatusTarefa();
        statusTarefa4.setDescricao("Teste de Tarefa 4");
        dao.salvarVarios(statusTarefa1, statusTarefa2, statusTarefa3, statusTarefa4);
    }
//    @Test

    public void ExcluirObjetos() throws Exception {
        GenericDao dao;
        dao = new StatusTarefaDaoImpl();

        StatusTarefa statusTarefa = new StatusTarefa(1l);

        dao.excluir(statusTarefa);
    }

    @Test
    public void retornarTodosObjetos() throws Exception {
            StatusTarefaDaoImpl dao = new StatusTarefaDaoImpl();

        List<StatusTarefa> result = dao.consultarTodos();
        Assert.assertEquals(4, result.size());

    }
    
//    @Test
    public void retornarTodosObjetosOrdenacao() throws Exception {
            StatusTarefaDaoImpl dao = new StatusTarefaDaoImpl();

        List<StatusTarefa> result = dao.consultarTodosOrdenados("descricao");
        Assert.assertEquals(4, result.size());

    }
    
    @Test
    public void consultarPorParametro() throws Exception {
        StatusTarefaDaoImpl dao = new StatusTarefaDaoImpl();
        StatusTarefa statusTarefa = new StatusTarefa();
        statusTarefa.setDescricao("Teste de Tarefa 2");
        List<StatusTarefa> result = dao.consultar(0, 0, statusTarefa);
        Assert.assertEquals(1, result.size());

    }

}
