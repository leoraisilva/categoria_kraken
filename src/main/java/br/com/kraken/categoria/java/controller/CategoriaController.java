package br.com.kraken.categoria.java.controller;

import br.com.kraken.categoria.java.model.CategoriaModel;
import br.com.kraken.categoria.java.modelDTO.CategoriaDTO;
import br.com.kraken.categoria.java.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping ("/categoria")
public class CategoriaController {
    private final CategoriaService categoriaService;
    public CategoriaController (CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<Object> listarCategoria () {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.getCategoriaRepository().findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarCategoria (@PathVariable (value = "id") UUID id) {
        Optional<CategoriaModel> categoriaModelOptional = categoriaService.getCategoriaRepository().findById(id);
        return categoriaModelOptional.<ResponseEntity<Object>>map(
            categoriaModel -> ResponseEntity.status(HttpStatus.OK).body(categoriaModelOptional.get())).
            orElseGet(
                    () -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Categoria")
            );
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarCategoria (@RequestBody @Valid CategoriaDTO categoriaDTO) {
        CategoriaModel categoriaModel = new CategoriaModel();
        BeanUtils.copyProperties(categoriaDTO, categoriaModel);
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.getCategoriaRepository().save(categoriaModel));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Object> alterarCategoria (@PathVariable (value = "id") UUID id, @RequestBody @Valid CategoriaDTO categoriaDTO){
        Optional<CategoriaModel> categoriaModelOptional = categoriaService.getCategoriaRepository().findById(id);
        if (!categoriaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Categoria");
        }
        categoriaModelOptional.get().setTitulo(categoriaDTO.titulo());
        categoriaModelOptional.get().setImagem(categoriaDTO.imagem());
        categoriaModelOptional.get().setDescricao(categoriaDTO.descricao());
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.getCategoriaRepository().save(categoriaModelOptional.get()));
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<Object> deletarCategoria (@PathVariable (value = "id") UUID id){
        Optional<CategoriaModel> categoriaModelOptional = categoriaService.getCategoriaRepository().findById(id);
        if (!categoriaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Categoria");
        }
        categoriaService.getCategoriaRepository().delete(categoriaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Delete success");
    }
}
