package com.agendamento.backend.dto;

import com.agendamento.backend.model.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EnderecoDTO {
    private String logradouro;
    private String bairro;
    private String cidade;

    @NotBlank(message = "Número é obrigatório")
    private String numero;

    @Pattern(regexp = "^[A-Z]{2}$", message = "UF deve ter 2 letras")
    private String uf;

    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "CEP deve estar no formato 00000-000")
    private String cep;

    public EnderecoDTO() {
    }

    public EnderecoDTO(Endereco endereco) {
        this.logradouro = endereco.getLogradouro();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.uf = endereco.getUf();
        this.cep = endereco.getCep();
        this.numero = endereco.getNumero();
    }
}