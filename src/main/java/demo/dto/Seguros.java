package demo.dto;

import java.io.Serializable;

public enum Seguros implements Serializable {
    RESIDENCIAL("residencial"),
    RURAL("rural"),
    ITENS_PESSOAIS("itens-pessoais");

    private String descricao;

    Seguros(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
