package com.aprendendo.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.aprendendo.cursomc.domain.enums.TipoCliente;
import com.aprendendo.cursomc.dto.ClienteNewDTO;
import com.aprendendo.cursomc.resources.exception.FieldMessage;
import com.aprendendo.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

	if (objDto.getTipo().equals(TipoCliente.PESSSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
		list.add(new FieldMessage("cpfOuCnpj", "CPF invalido"));
	}
	
	if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
		list.add(new FieldMessage("cpfOuCnpj", "CNPJ invalido"));
	}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}