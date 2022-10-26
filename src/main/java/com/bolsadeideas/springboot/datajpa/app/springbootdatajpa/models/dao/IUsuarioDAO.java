package com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.models.dao;

import java.util.List;

import com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.models.entity.Usuario;

/* DAO -> Data Access Object : Clase que implementa y provee una interfaz para trabajar con datos */

public interface IUsuarioDAO {

    public List<Usuario> findAll();

    public void save(Usuario usuario);
    
    public Usuario findOne(Long id);

    public void delete(Long id);

}
