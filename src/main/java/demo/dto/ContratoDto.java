package demo.dto;

import java.util.List;

public class ContratoDto {

    private String nome;
    private Long cpfCnpj;
    private List<String> seguros;
    private String cep;

    public ContratoDto (String nome, Long cpfCnpj, List<String> seguros, String cep) {
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.seguros = seguros;
        this.cep = cep;
    }

    public ContratoDto() {
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
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
