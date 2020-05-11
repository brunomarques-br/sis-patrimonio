package br.com.patrimonio.service;

import br.com.patrimonio.domain.Patrimonio;
import br.com.patrimonio.dto.PatrimonioDTO;
import br.com.patrimonio.dto.PatrimonioSaveDTO;
import br.com.patrimonio.exception.ObjectNotFoundException;
import br.com.patrimonio.repository.PatrimonioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatrimonioService {

    private final PatrimonioRepository patrimonioRepository;
    private final MarcaService marcaService;

    @Autowired
    public PatrimonioService(final PatrimonioRepository patrimonioRepository,
                             @Lazy final MarcaService marcaService) {
        this.patrimonioRepository = patrimonioRepository;
        this.marcaService = marcaService;
    }

    public PatrimonioDTO find(Integer numTombo) {
        Optional<Patrimonio> optionalPatrimonio = patrimonioRepository.findById(numTombo);
        return convertToDTO(optionalPatrimonio.orElseThrow(() ->
                new ObjectNotFoundException("Patrimônio não encontrado! N. Tombo: " + numTombo + ", tipo: " + Patrimonio.class.getName())));
    }

    public PatrimonioSaveDTO findSave(Integer numTombo) {
        Optional<Patrimonio> optionalPatrimonio = patrimonioRepository.findById(numTombo);
        return convertSaveToDTO(optionalPatrimonio.orElseThrow(() ->
                new ObjectNotFoundException("Patrimônio não encontrado! N. Tombo: " + numTombo + ", tipo: " + Patrimonio.class.getName())));
    }

    public List<PatrimonioDTO> findByIdMarca(Integer idMarca) {
        List<Patrimonio> patrimonios = patrimonioRepository.findAllByMarca_IdMarca(idMarca);
        return patrimonios.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<PatrimonioDTO> findAll() {
        List<Patrimonio> patrimonios = patrimonioRepository.findAll();
        return patrimonios.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Page<Patrimonio> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return patrimonioRepository.findAll(pageRequest);
    }

    public PatrimonioDTO insert(PatrimonioSaveDTO patrimonioDTO) {
        Patrimonio objPatrimonio = convertToDomain(patrimonioDTO);
        objPatrimonio.setNumTombo(null);
        objPatrimonio = patrimonioRepository.save(objPatrimonio);
        objPatrimonio.setMarca(marcaService.convertToDomain(marcaService.find(patrimonioDTO.getIdMarca())));
        return convertToDTO(objPatrimonio);
    }

    public PatrimonioDTO update(PatrimonioSaveDTO patrimonioDTO) {
        Patrimonio objPatrimonio = convertToDomain(findSave(patrimonioDTO.getNumTombo()));
        objPatrimonio.setNome(patrimonioDTO.getNome());
        objPatrimonio.setDescricao(patrimonioDTO.getDescricao());
        objPatrimonio.setMarca(marcaService.convertToDomain(marcaService.find(patrimonioDTO.getIdMarca())));
        return convertToDTO(patrimonioRepository.save(objPatrimonio));
    }

    public void delete(Integer numTombo) {
        find(numTombo);
        patrimonioRepository.deleteById(numTombo);
    }

    public Patrimonio convertToDomain(PatrimonioSaveDTO patrimonioDTO) {
        return new Patrimonio(
                patrimonioDTO.getNumTombo(),
                patrimonioDTO.getNome(),
                patrimonioDTO.getDescricao(),
                marcaService.convertToDomain(marcaService.find(patrimonioDTO.getIdMarca()))
        );
    }

    public PatrimonioDTO convertToDTO(Patrimonio patrimonio) {
        return new PatrimonioDTO(patrimonio);
    }

    public PatrimonioSaveDTO convertSaveToDTO(Patrimonio patrimonio) {
        return new PatrimonioSaveDTO(patrimonio);
    }

}
