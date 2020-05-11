package br.com.patrimonio.validator;

import br.com.patrimonio.domain.Marca;
import br.com.patrimonio.dto.MarcaDTO;
import br.com.patrimonio.repository.MarcaRepository;
import br.com.patrimonio.validator.util.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class MarcaInsertValidator implements ConstraintValidator<MarcaInsert, MarcaDTO> {

    private final MarcaRepository marcaRepository;

    @Autowired
    public MarcaInsertValidator(final MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @Override
    public void initialize(MarcaInsert constraintAnnotation) {
    }

    @Override
    public boolean isValid(MarcaDTO marcaDTO, ConstraintValidatorContext context) {
        List<FieldMessage> listMessages = new ArrayList<>();

        Marca objMarca = marcaRepository.findByNome(marcaDTO.getNome());
        if (objMarca != null)
            listMessages.add(new FieldMessage("Nome", "O nome informado da marca, já consta cadastrado no sistema."));

        for (FieldMessage e : listMessages) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return listMessages.isEmpty();
    }


}
