package br.com.kraken.categoria.java.service;

import br.com.kraken.categoria.java.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    public CategoriaService (CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    public CategoriaRepository getCategoriaRepository() {
        return categoriaRepository;
    }
}
