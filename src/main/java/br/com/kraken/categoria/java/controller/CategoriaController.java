package br.com.kraken.categoria.java.controller;

import br.com.kraken.categoria.java.model.AcessoModel;
import br.com.kraken.categoria.java.model.CategoriaModel;
import br.com.kraken.categoria.java.modelDTO.AcessoDTO;
import br.com.kraken.categoria.java.modelDTO.CategoriaDTO;
import br.com.kraken.categoria.java.service.AcessoService;
import br.com.kraken.categoria.java.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping ("/categoria")
public class CategoriaController {
    private final CategoriaService categoriaService;
    private final AcessoService acessoService;
    public CategoriaController (CategoriaService categoriaService, AcessoService acessoService){
        this.categoriaService = categoriaService;
        this.acessoService = acessoService;
    }

    @PostMapping("/auth/registry")
    public ResponseEntity<Object> clienteCategoria(@RequestBody @Valid AcessoDTO acessoDTO) {
        var acessoModel = new AcessoModel();
        BeanUtils.copyProperties(acessoDTO, acessoModel);
        acessoModel.setDataCadastro(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.CREATED).body(acessoService.getRepository().save(acessoModel));
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
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public ResponseEntity<Object> cadastrarCategoria (@RequestParam(value = "titulo") String titulo, @RequestParam(value = "descricao") String descricao, @RequestPart(value = "imagem")MultipartFile imagem) {
        CategoriaModel categoriaModel = new CategoriaModel();
        try {
            if (imagem.isEmpty())
                return ResponseEntity.badRequest().body("Not Found Image");
            String imageBase = Base64.getEncoder().encodeToString(imagem.getBytes());
            categoriaModel.setTitulo(titulo);
            categoriaModel.setDescricao(descricao);
            categoriaModel.setImagem(imageBase);

            return ResponseEntity.status(HttpStatus.OK).body(categoriaService.getCategoriaRepository().save(categoriaModel));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error Register Image");
        }
    }

    @PutMapping ("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
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
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public ResponseEntity<Object> deletarCategoria (@PathVariable (value = "id") UUID id){
        Optional<CategoriaModel> categoriaModelOptional = categoriaService.getCategoriaRepository().findById(id);
        if (!categoriaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Categoria");
        }
        categoriaService.getCategoriaRepository().delete(categoriaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Delete success");
    }
}
