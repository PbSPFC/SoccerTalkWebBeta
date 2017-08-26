package com.iteractionapps.soccertalk.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.iteractionapps.soccertalk.model.Clube;
import com.iteractionapps.soccertalk.model.Usuario;
import com.iteractionapps.soccertalk.repository.Clubes;
import com.iteractionapps.soccertalk.service.CadastroUsuarioService;
import com.iteractionapps.soccertalk.service.NegocioException;
import com.iteractionapps.soccertalk.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Clubes clubes;

    @Inject
    private CadastroUsuarioService cadastroUsuarioService;

    private Usuario usuario;
    private Clube clube;

    private List<Clube> listaClubes;

    private String confirmaSenha;
    private Long idTime;

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public CadastroUsuarioBean() {
        limpar();
    }

    private void limpar() {
        usuario = new Usuario();
    }

    public void inicializar() {
        if (FacesUtil.isNotPostback()) {
            listaClubes = clubes.todosClubes();
        }
    }

    public void salvar() {

        if (!this.usuario.getSenha().equals(this.confirmaSenha)) {
            throw new NegocioException("Campo de confirmação de senha está inválido.");
        }
        this.usuario = cadastroUsuarioService.salvar(this.usuario);
        
        FacesUtil.addInfoMessage(this.usuario.getNome() + ", seu cadastro efetuado com sucesso!");
        
        limpar();

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isEditando() {
        return this.usuario.getId() != null;
    }

    public List<Clube> getListaClubes() {
        return listaClubes;
    }

    public void setListaClubes(List<Clube> listaClubes) {
        this.listaClubes = listaClubes;
    }

    public Clubes getClubes() {
        return clubes;
    }

    public void setClubes(Clubes clubes) {
        this.clubes = clubes;
    }

    public CadastroUsuarioService getCadastroUsuarioService() {
        return cadastroUsuarioService;
    }

    public void setCadastroUsuarioService(CadastroUsuarioService cadastroUsuarioService) {
        this.cadastroUsuarioService = cadastroUsuarioService;
    }

    public Clube getClube() {
        return clube;
    }

    public void setClube(Clube clube) {
        this.clube = clube;
    }
    
    
    
}
