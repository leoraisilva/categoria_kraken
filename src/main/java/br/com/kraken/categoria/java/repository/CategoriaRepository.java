package br.com.kraken.categoria.java.repository;


import br.com.kraken.categoria.java.model.CategoriaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoriaRepository extends CrudRepository <CategoriaModel, UUID> {

}
