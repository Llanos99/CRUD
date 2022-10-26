package com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import org.hibernate.validator.constraints.NotEmpty;

/* POJO Class, acá reside la clase de usuario. */
/* @Entity, para indicar que la clase Usuario está mapeada a una tabla. */

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    /*
     * Serializable -> El proceso de convertir un objeto en una secuencia de bytes
     * para luego almacenarlo o transmitirlo en una base de datos, en .JSON o para
     * trabajar con sesiones HTTP.
     */

    /*
     * Asignamos el ID (Notación @ID) del usuario como la llave en la base de datos,
     * en este ejemplo simple asumimos que el ID es único por usuario. Además,
     * pedimos quE se incremente de 1 en 1 en la base de datos con
     * strategy=GenerationType.IDENTITY
     */

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Ingrese un numero entre 0 y 1")
    @Min(value = 0, message = "El valor ingresado debe ser mayor o igual a 0")
    @Max(value = 1, message = "El valor ingresado debe ser menor o igual a 1")
    @Column(name = "current_state")
    private Integer active;

    @NotEmpty(message = "El campo no puede ser vacio")
    @Size(min = 2, max = 10, message = "El usuario debe tener un tamaño entre 2 y 10")
    @Column(name = "current_username")
    private String username;

    @NotEmpty(message = "El campo no puede ser vacio")
    @Size(min = 2, max = 10, message = "La contraseña debe tener un tamaño entre 2 y 10")
    @Column(name = "current_password")
    private String password;

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario [active=" + active + ", username=" + username + ", password=" + password + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
