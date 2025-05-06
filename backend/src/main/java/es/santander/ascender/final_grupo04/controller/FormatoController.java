package es.santander.ascender.final_grupo04.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.santander.ascender.final_grupo04.model.Formato;
import es.santander.ascender.final_grupo04.repository.FormatoRepository;

@RestController
@RequestMapping("/api/formatos")
public class FormatoController {

    @Autowired
    private FormatoRepository formatoRepository;

    @GetMapping("/listar")
    public List<Formato> listarFormatos() {
        return formatoRepository.findAll();
    }
}
