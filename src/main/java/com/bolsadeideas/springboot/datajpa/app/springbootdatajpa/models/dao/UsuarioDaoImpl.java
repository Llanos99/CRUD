package com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.models.entity.Usuario;

/* @Repository: Marcamos la clase como un componente de persistencia, de acceso a datos. */

@Repository("usuarioDaoJPA")
public class UsuarioDaoImpl implements IUsuarioDAO {

    /*
     * EntityManager : Se encarga de manejar las clases de entidades. Actualiza,
     * elimina, realiza consultas, etc. pero desde la clase objeto, no en la base de
     * datos.
     */
    @PersistenceContext
    private EntityManager em;

    /*
     * Transactional: Una anotación de transacción en la base de datos, solo vamos a
     * leer, por lo cual hacemos readOnly = true
     */

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Usuario> findAll() {
        return em.createQuery("from Usuario").getResultList();
    }

    @Transactional
    @Override
    public void save(Usuario usuario) {
        if (usuario.getId() != null && usuario.getId() > 0) {
            /*
             * Si el usuario ya existe, solo actualizamos en la base de datos utilizando el
             * metodo merge()
             */
            em.merge(usuario);
        } else {
            /*
             * persist() toma el objeto usuario y lo guarda dentro del contexto de
             * persistencia/JPA. De esta manera, al hacer el commit se sincroniza con la
             * base de datos y hace el INSERT por debajo. Asi, el objeto usuario queda atado
             * al contexto de persistencia de JPA
             */
            em.persist(usuario);
        }
    }

    /*
     * Con find() JPA va automaticamente a la base de datos y nos retorna el usuario
     * con el id parametrizado. readOnly = true ya que esto es solo una consulta.
     */
    @Transactional(readOnly = true)
    @Override
    public Usuario findOne(Long id) {
        return em.find(Usuario.class, id);
    }

    /*
     * Obtenemos el usuario por su ID y, con el metodo remove() del EntityManager lo
     * eliminamos de la base de datos. No ponemos restricciones en la transaccion ya
     * que estamos haciendo una modificacion a la base de datos.
     */
    @Transactional
    @Override
    public void delete(Long id) {
        Usuario usuario = findOne(id);
        em.remove(usuario);
    }

}
