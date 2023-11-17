package com.seguro.demo.domain;

import com.seguro.demo.dto.ContratoDto;
import com.seguro.demo.dto.Seguros;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long cpfCnpj;
    private String cep;
    @ElementCollection
    private List<String> seguros = new ArrayList<>();

    public Contrato(Long id, String nome, Long cpfCnpj, List<String> seguros, String cep) {
        this.id = id;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.seguros = seguros;
        this.cep = cep;
    }

    public Contrato() {
    }

    public Contrato(ContratoDto dto) {
        this.cpfCnpj = dto.getCpfCnpj();
        this.nome = dto.getNome();
        this.seguros = dto.getSeguros();
        this.cep = dto.getCep();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(Long cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public List<String> getSeguros() {
        return seguros;
    }

    public void setSeguros(List<String> seguros) {
        this.seguros = seguros;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
