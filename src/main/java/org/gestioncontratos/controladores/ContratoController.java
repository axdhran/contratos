package org.gestioncontratos.controladores;


import org.gestioncontratos.modelos.Cliente;
import org.gestioncontratos.modelos.Contrato;
import org.gestioncontratos.modelos.Servicio;
import org.gestioncontratos.servicios.interfaces.IClienteService;
import org.gestioncontratos.servicios.interfaces.IContratoService;
import org.gestioncontratos.servicios.interfaces.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/contratos")
public class ContratoController {
    @Autowired
    private IContratoService contratoService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IServicioService servicioService;

    @GetMapping()
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Contrato> contratos = contratoService.buscarTodosPaginados(pageable);
        model.addAttribute("contratos", contratos);

        int totalPages = contratos.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return ("contrato/index");
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("servicios", servicioService.obtenerTodos());
        model.addAttribute("clientes", clienteService.obtenerTodos());

        return "contrato/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam Integer clienteId, @RequestParam Integer servicioId,
                       @RequestParam String nombre, @RequestParam String tiempo,@RequestParam String fechaInicio,
                       @RequestParam String fechaFinal,
                       RedirectAttributes attributes){
        Servicio servicio = servicioService.buscarPorId(servicioId).get();
        Cliente cliente = clienteService.buscarPorId(clienteId).get();

        if(servicio != null && servicio != null){
            Contrato contrato = new Contrato();
            contrato.setNombre(nombre);
            contrato.setCliente(cliente);
            contrato.setServicio(servicio);
            contrato.setTiempo(tiempo);
            contrato.setFechaInicio(fechaInicio);
            contrato.setFechaFinal(fechaFinal);


            contratoService.crearOEditar(contrato);
            attributes.addFlashAttribute("msg", "Contrado creado correctamente");
        }

        return "redirect:/contratos";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        Contrato contrato = contratoService.buscarPorId(id);
        model.addAttribute("contrato", contrato);
        return "contrato/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Contrato contrato = contratoService.buscarPorId(id);
        model.addAttribute("servicios", servicioService.obtenerTodos());
        model.addAttribute("clientes", clienteService.obtenerTodos());
        model.addAttribute("contrato", contrato);
        return "contrato/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer id, @RequestParam Integer clienteId, @RequestParam Integer servicioId,
                         @RequestParam String nombre, @RequestParam String tiempo,@RequestParam String fechaInicio,
                         @RequestParam String fechaFinal, RedirectAttributes attributes){
        Servicio servicio = servicioService.buscarPorId(servicioId).get();
        Cliente cliente = clienteService.buscarPorId(clienteId).get();

        if(servicio != null && cliente != null){
            Contrato contrato = new Contrato();
            contrato.setId(id);
            contrato.setNombre(nombre);
            contrato.setCliente(cliente);
            contrato.setServicio(servicio);
            contrato.setTiempo(tiempo);
            contrato.setFechaInicio(fechaInicio);
            contrato.setFechaFinal(fechaFinal);

            contratoService.crearOEditar(contrato);
            attributes.addFlashAttribute("msg", "Contrato modificado correctamente");
        }

        return "redirect:/contratos";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Contrato contrato = contratoService.buscarPorId(id);
        model.addAttribute("contrato", contrato);
        return "contrato/delete";
    }

    @PostMapping("/delete")
    public String delete(Contrato contrato, RedirectAttributes attributes){
        contratoService.eliminarPorId(contrato.getId());
        attributes.addFlashAttribute("msg", "Contrato eliminado correctamente");
        return "redirect:/contratos";
    }
}
