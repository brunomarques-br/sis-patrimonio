package br.com.patrimonio.service;

import br.com.patrimonio.domain.Marca;
import br.com.patrimonio.dto.MarcaDTO;
import br.com.patrimonio.dto.MarcaPatrimonioDTO;
import br.com.patrimonio.exception.DataIntegrityException;
import br.com.patrimonio.exception.ObjectNotFoundException;
import br.com.patrimonio.repository.MarcaRepository;
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
public class MarcaService {


    private final PatrimonioService patrimonioService;
    private final MarcaRepository marcaRepository;

    @Autowired
    public MarcaService(
            @Lazy final PatrimonioService patrimonioService,
            final MarcaRepository marcaRepository) {

        this.patrimonioService = patrimonioService;
        this.marcaRepository = marcaRepository;
    }

    public MarcaDTO find(Integer idMarca) {
        Optional<Marca> optMarca = marcaRepository.findById(idMarca);
        return convertToDTO(optMarca.orElseThrow(() ->
                new ObjectNotFoundException("Marca não encontrada! id: " + idMarca + ", tipo: " + Marca.class.getName())));
    }

    public MarcaPatrimonioDTO findAllPatrimoniosByMarca(Integer idMarca) {
        MarcaDTO marca = find(idMarca);
        return new MarcaPatrimonioDTO(
                marca.getIdMarca(),
                marca.getNome(),
                patrimonioService.findByIdMarca(marca.getIdMarca())
        );
    }

    public List<MarcaDTO> findAll() {
        List<Marca> marcas = marcaRepository.findAll();
        return marcas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Page<Marca> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return marcaRepository.findAll(pageRequest);
    }

    public MarcaDTO insert(MarcaDTO marcaDTO) {
        Marca objMarca = convertToDomain(marcaDTO);
        objMarca.setIdMarca(null);
        return convertToDTO(marcaRepository.save(objMarca));
    }

    public MarcaDTO update(MarcaDTO marcaDTO) {
        Marca objMarca = convertToDomain(find(marcaDTO.getIdMarca()));
        objMarca.setNome(marcaDTO.getNome());
        return convertToDTO(marcaRepository.save(objMarca));
    }

    public void delete(Integer idMarca) {
        find(idMarca);
        try {
            marcaRepository.deleteById(idMarca);
        } catch (DataIntegrityException err) {
            throw new DataIntegrityException("Não é possível excluir uma marca que possua patrimônios vinculados.");
        }
    }

    public Marca convertToDomain(MarcaDTO marcaDTO) {
        return new Marca(marcaDTO.getIdMarca(), marcaDTO.getNome());
    }

    public MarcaDTO convertToDTO(Marca marca) {
        return new MarcaDTO(marca);
    }

}
