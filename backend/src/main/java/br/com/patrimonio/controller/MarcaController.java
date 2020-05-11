package br.com.patrimonio.controller;

import br.com.patrimonio.domain.Marca;
import br.com.patrimonio.dto.MarcaDTO;
import br.com.patrimonio.dto.MarcaPatrimonioDTO;
import br.com.patrimonio.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/marcas")
public class MarcaController {

    private final MarcaService service;

    @Autowired
    public MarcaController(final MarcaService service) {
        this.service = service;
    }

    @GetMapping(value = "/{idMarca}")
    public ResponseEntity<MarcaDTO> find(
            @PathVariable Integer idMarca) {
        return ResponseEntity.ok().body(service.find(idMarca));
    }

    @GetMapping(value = "/{idMarca}/patrimonios")
    public ResponseEntity<MarcaPatrimonioDTO> findPatrimoniosByMarca(
            @PathVariable Integer idMarca) {
        return ResponseEntity.ok().body(service.findAllPatrimoniosByMarca(idMarca));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<MarcaDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<MarcaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Marca> marcas = service.findPage(page, linesPerPage, orderBy, direction);
        Page<MarcaDTO> marcasDTO = marcas.map(MarcaDTO::new);
        return ResponseEntity.ok().body(marcasDTO);
    }

    @PostMapping
    public ResponseEntity<MarcaDTO> insert(
            @Valid @RequestBody MarcaDTO marcaDTO) {
        MarcaDTO newMarca = service.insert(marcaDTO);
        return ResponseEntity.ok().body(newMarca);
    }

    @PutMapping(value = "/{idMarca}")
    public ResponseEntity<MarcaDTO> update(
            @Valid @RequestBody MarcaDTO marcaDTO,
            @PathVariable Integer idMarca) {
        marcaDTO.setIdMarca(idMarca);
        marcaDTO = service.update(marcaDTO);
        return ResponseEntity.ok().body(marcaDTO);
    }

    @DeleteMapping(value = "/{idMarca}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer idMarca) {
        service.delete(idMarca);
        return ResponseEntity.noContent().build();
    }
}
