/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.dominio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lucas Corrêa
 */
@Entity
@Table(name = "tarefas", schema = PojoBase.DB)
@XmlRootElement
public class Tarefa implements PojoBase {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Short importancia;
    @Temporal(TemporalType.TIMESTAMP)
    private Date duracao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_termino")
    private Date dataTermino;
    private String descricao;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "usuario_delegado_id")
    private Usuario usuarioDelegado;
    @ManyToOne
    @JoinColumn(name="status_tarefa_id")
    private StatusTarefa statusTarefa;
    
    @Transient
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 

    public Tarefa() {
    }

    public Tarefa(Long id) {
        this.id = id;
    }

    public long calcularProgresso(){
        long tempoTotal = getDuracao().getTime() - getInicio().getTime();
        long tempoDecorrido = new Date().getTime() - getInicio().getTime();
        return((tempoDecorrido*100l)/tempoTotal);
    }
    
    public String getDateTerminoFormat(){
        if(dataTermino == null){
            return "";
        }
        return(simpleDateFormat.format(this.dataTermino));
    }
    
    public String getDateCriacaoFormat(){
        return(simpleDateFormat.format(this.inicio));
    }
    
    public String getDateDuracaoFormat(){
        return(simpleDateFormat.format(this.duracao));
    }
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public Object getClone() {
        try {
            return this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Tarefa.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tarefa other = (Tarefa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Short getImportancia() {
        return importancia;
    }

    public void setImportancia(Short importancia) {
        this.importancia = importancia;
    }

    public Date getDuracao() {
        return duracao;
    }

    public void setDuracao(Date duracao) {
        this.duracao = duracao;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioDelegado() {
        return usuarioDelegado;
    }

    public void setUsuarioDelegado(Usuario usuarioDelegado) {
        this.usuarioDelegado = usuarioDelegado;
    }

    public StatusTarefa getStatusTarefa() {
        return statusTarefa;
    }

    public void setStatusTarefa(StatusTarefa statusTarefa) {
        this.statusTarefa = statusTarefa;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }
    
    

}
