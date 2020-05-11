package br.com.patrimonio.controller;

import br.com.patrimonio.domain.Patrimonio;
import br.com.patrimonio.dto.PatrimonioDTO;
import br.com.patrimonio.dto.PatrimonioSaveDTO;
import br.com.patrimonio.service.PatrimonioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/patrimonios")
public class PatrimonioController {

    private final PatrimonioService service;

    @Autowired
    public PatrimonioController(final PatrimonioService service) {
        this.service = service;
    }

    @GetMapping(value = "/{numTombo}")
    public ResponseEntity<PatrimonioDTO> find(
            @PathVariable Integer numTombo) {
        return ResponseEntity.ok().body(service.find(numTombo));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<PatrimonioDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<PatrimonioDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Patrimonio> patrimonios = service.findPage(page, linesPerPage, orderBy, direction);
        Page<PatrimonioDTO> patrimoniosDTO = patrimonios.map(PatrimonioDTO::new);
        return ResponseEntity.ok().body(patrimoniosDTO);
    }

    @PostMapping
    public ResponseEntity<PatrimonioDTO> insert(
            @Valid @RequestBody PatrimonioSaveDTO patrimonioDTO) {
        PatrimonioDTO newPatrimonio = service.insert(patrimonioDTO);
        return ResponseEntity.ok().body(newPatrimonio);
    }

    @PutMapping(value = "/{numTombo}")
    public ResponseEntity<PatrimonioDTO> update(
            @Valid @RequestBody PatrimonioSaveDTO patrimonioSaveDTO,
            @PathVariable Integer numTombo) {
        patrimonioSaveDTO.setNumTombo(numTombo);
        PatrimonioDTO patrimonioDTO = service.update(patrimonioSaveDTO);
        return ResponseEntity.ok().body(patrimonioDTO);
    }

    @DeleteMapping(value = "/{numTombo}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer numTombo) {
        service.delete(numTombo);
        return ResponseEntity.noContent().build();
    }
}
