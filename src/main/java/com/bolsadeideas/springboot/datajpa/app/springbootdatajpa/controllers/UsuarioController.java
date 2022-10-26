package com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.models.dao.IUsuarioDAO;
import com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.models.entity.Usuario;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {

    /*
     * TO/DO : Implementar la capa de servicios (@Service). Basicamente, esta capa
     * es una fachada para los metodos que tenemos en el DAO. Es una buena practica
     * en Ingenieria de Software y sigue el modelo llamado
     * "Business Service Facade". En otras palabras, es como si tuvieramos una unica
     * puerta a todos los servicios disponibles de la aplicacion.
     */

    @Autowired
    @Qualifier("usuarioDaoJPA")
    private IUsuarioDAO usuarioDao;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de usuarios");
        model.addAttribute("usuarios", usuarioDao.findAll());
        return "lista_usuarios";
    }

    @GetMapping("/form")
    public String create(Map<String, Object> model) {
        Usuario usuario = new Usuario();
        model.put("usuario", usuario);
        model.put("titulo", "Crear usuario");
        return "formulario_crear";
    }

    /*
     * Controlador para obtener los datos que se necesitan a la hora de crear un
     * nuevo usuario
     */

    /*
     * Necesitamos validar el formulario para la creacion de un nuevo usuario, por
     * esta razon usamos la notacion @Valid. El BIndingResult siempre debe ir al
     * lado del objeto mapeado en los parametros.
     */

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String save(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
        /* Si la se produjo algun error en la validacion lo manejamos en este If */
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Crear usuario");
            return "formulario_crear";
        }
        usuarioDao.save(usuario);
        status.setComplete();
        return "redirect:/listar";
    }

    /*
     * @PathVariable lo necesitamos para inyectar el valor del parametro (id) de la
     * ruta.
     */
    @GetMapping("/form/{id}")
    public String edit(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        Usuario usuario = null;
        if (id > 0) {
            usuario = usuarioDao.findOne(id);
        } else {
            return "redirect:/listar";
        }
        /*
         * Habiendo llegado a este punto solo significa que se encontro el usuario con
         * el id especifico, de modo que mandamos la info. del usuario a la vista.
         */
        model.put("usuario", usuario);
        model.put("titulo", "Editar cliente");
        return "formulario_crear";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            usuarioDao.delete(id);
        }
        return "redirect:/listar";
    }

}
