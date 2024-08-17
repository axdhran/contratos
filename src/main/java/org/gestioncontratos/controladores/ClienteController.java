package org.gestioncontratos.controladores;

import org.gestioncontratos.modelos.Cliente;
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
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private IClienteService clienteService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Cliente> clientes = clienteService.buscarTodosPaginados(pageable);
        model.addAttribute("clientes", clientes);

        int totalPages = clientes.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "cliente/index";
    }
    @GetMapping("/create")
    public String create(Cliente cliente){
        return "cliente/create";
    }

    @PostMapping("/save")
    public String save(Cliente cliente, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(cliente);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "cliente/create";
        }

        clienteService.crearOEditar(cliente);
        attributes.addFlashAttribute("msg", "Cliente creado correctamente");
        return "redirect:/clientes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        Cliente cliente = clienteService.buscarPorId(id).get();
        model.addAttribute("cliente", cliente);
        return "cliente/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Cliente cliente = clienteService.buscarPorId(id).get();
        model.addAttribute("cliente", cliente);
        return "cliente/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Cliente cliente = clienteService.buscarPorId(id).get();
        model.addAttribute("cliente", cliente);
        return "cliente/delete";
    }

    @PostMapping("/delete")
    public String delete(Cliente cliente, RedirectAttributes attributes){
        clienteService.eliminarPorId(cliente.getId());
        attributes.addFlashAttribute("msg", "Cliente eliminado correctamente");
        return "redirect:/clientes";
    }
}
