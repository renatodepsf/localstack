package com.seguro.demo.controller;

import com.seguro.demo.domain.Contrato;
import com.seguro.demo.dto.ContratoDto;
import com.seguro.demo.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/contrato")
public class ContratoController {

    @Autowired
    private ContratoRepository repository;

    @PostMapping("/salvar")
    public void salvar(@RequestBody ContratoDto dto) {
        repository.save(new Contrato(dto));
    }

    @GetMapping("/listar")
    public List<Contrato> retornaContrato() {
        return repository.findAll();
    }

}
