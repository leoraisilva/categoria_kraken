package br.com.kraken.categoria.java.modelDTO;

import br.com.kraken.categoria.java.model.Rules;

import java.time.LocalDateTime;

public record AcessoDTO(String usuarioId, String usuario, LocalDateTime dataCadastro, Rules rules) {
}
