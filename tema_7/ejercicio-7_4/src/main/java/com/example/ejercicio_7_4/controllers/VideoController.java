package com.example.ejercicio_7_4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ejercicio_7_4.domain.Video;
import com.example.ejercicio_7_4.services.VideoService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    private String txtMsg;

    // @GetMapping({ "/", "/list", "" })
    // public String showList(Model model) {
    //     model.addAttribute("listaVideos", videoService.obtenerTodos());

    //     if (txtMsg != null) {
    //         model.addAttribute("msg", txtMsg);
    //         txtMsg = null;
    //     }

    //     return "video/listView";
    // }

    @GetMapping("/curso/{idCurso}")
    public String showVideosCurso(@PathVariable Long idCurso, Model model) {
        if (txtMsg != null) {
            model.addAttribute("msg", txtMsg);
            txtMsg = null;
        }
        try {
            model.addAttribute("idCursoFijo", idCurso); //guardo el ID del curso para que no me falle en la creación del Video (ya que el curso estaría por defecto a null y salta error) y en la URL no sabe a qué curso me refiero
            model.addAttribute("listaVideos", videoService.buscarVideosPorIdCurso(idCurso));
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
        return "video/listView";
    }

    @GetMapping("/new/{idCurso}")
    public String showNew(@PathVariable Long idCurso, Model model) {
        model.addAttribute("videoForm", new Video()); //le pasamos la estructura de un vídeo para que pueda rellenar el contenido de este 
        model.addAttribute("idCursoFijo", idCurso); //guardo el ID del curso para que no me falle en la creación del Video (ya que el curso estaría por defecto a null y salta error) y en la URL no sabe a qué curso me refiero
        return "video/newFormView";
    }
 
    //IMPORTANTE: al crear un vídeo tengo asingado un Curso, por lo que hay dos formas de poder seleccionar el Curso al que le quiero añadir el vídeo: 1) mediante una lista de cursos inyectada en el controlador. 2) recibiendo ya el curso al que se le aplicará en el mapping (por ejemplo, a través de @PathVariable)
    @PostMapping("/new/submit/{idCurso}") //en este caso le digo a qué curso añado el vídeo a través de la URL
    public String showNewSubmit(@Valid @PathVariable Long idCurso, @ModelAttribute("videoForm") Video videoForm, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return "video/newFormView";
            }
            videoService.añadir(videoForm, idCurso);
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/video/curso/" + idCurso;
        }
        return "redirect:/video/curso/" + idCurso;
    }

    @GetMapping("/edit/{idVideo}") //como el vídeo ya está guardado en la BBDD, el objeto vídeo ya tiene dentro a su curso y no necesito pedirle el ID del curso a la URL porque puedo sacarlo del propio objeto vídeo
    public String showEdit(@PathVariable long idVideo, Video video, Model model) {
        try {
            video = videoService.obtenerPorId(idVideo); //obtenemos el contenido del vídeo para poder editarlo (y que las variables no estén vacías)
            model.addAttribute("idCursoFijo", video.getCurso().getId()); //le paso el id del curso para que sepa a qué curso me refiero en el formulario
            model.addAttribute("videoForm", videoService.editar(video, video.getCurso().getId()));
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/video/curso/" + video.getCurso().getId();
        }
        return "video/editFormView";
    }

    @PostMapping("/edit/submit/{idCurso}") //le tengo que pasar el id del curso a editar ya que si no, cuando envíe el Vídeo editado, el curso  estará a null (ya que no tengo ningún imput para ello)
    public String showEditSubmit(@Valid @PathVariable long idCurso, @ModelAttribute("videoForm") Video videoForm,BindingResult bindingResult ) {
        if (bindingResult.hasErrors()) {
            return "video/editFormView";
        }
        try {
            videoService.editar(videoForm, idCurso);
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/video/curso/" + idCurso;
        }
        return "redirect:/video/curso/" + idCurso;
    }

    @GetMapping("/delete/{idCurso}/{idVideo}") //técnicamente no hace falta el id del curso pero como hago una redirección a la lista de cursos... (en la vista ListView ahí ya le digo que coja el idCursoFijo para que sepa pintar esa variable, ya que solo hace eso, mostrar el ID del curso)
    public String showDelete(@PathVariable long idCurso, @PathVariable long idVideo) {
        try {
            videoService.borrar(idVideo);
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/video/curso/" + idCurso;
        }
        return "redirect:/video/curso/" + idCurso;
    }



}