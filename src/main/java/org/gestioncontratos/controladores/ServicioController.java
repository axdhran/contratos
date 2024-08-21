package org.gestioncontratos.controladores;

import org.gestioncontratos.modelos.Servicio;
import org.gestioncontratos.servicios.interfaces.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/servicio")
public class ServicioController {
    @Autowired
    private IServicioService servicioService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Servicio> servicios = servicioService.buscarTodosPaginados(pageable);
        model.addAttribute("clientes", servicios);

        int totalPages = servicios.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "servicio/index";
    }

    @GetMapping("/create")
    public String create(Servicio servicio){
        return "servicio/create";
    }

    @PostMapping("/save")
    public String save(Servicio servicio, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(servicio);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "servicio/create";
        }

        servicioService.createOrEdit(servicio);
        attributes.addFlashAttribute("msg", "Servicio creado correctamente");
        return "redirect:/servicios";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        Servicio servicio = servicioService.buscarPorId(id).get();
        model.addAttribute("servicio", servicio);
        return "servicio/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Servicio servicio = servicioService.buscarPorId(id).get();
        model.addAttribute("servicio", servicio);
        return "servicio/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Servicio servicio = servicioService.buscarPorId(id).get();
        model.addAttribute("servicio", servicio);
        return "servicio/delete";
    }

    @PostMapping("/delete")
    public String delete(Servicio servicio, RedirectAttributes attributes){
        servicioService.eliminarPorId(servicio.getId());
        attributes.addFlashAttribute("msg", "Servicio eliminado correctamente");
        return "redirect:/servicios";
    }
}
