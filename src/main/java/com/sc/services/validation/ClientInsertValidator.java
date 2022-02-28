package com.sc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sc.domain.Client;
import com.sc.domain.DTO.ClientInsertDTO;
import com.sc.domain.enums.ClientType;
import com.sc.repositories.ClientRepository;
import com.sc.resources.exceptions.FieldMessage;
import com.sc.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientInsertDTO> {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientInsertDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (ClientType.toEnum(objDto.getClientType()).equals(ClientType.PHYSICAL) && !BR.isValidCPF(objDto.getCpf_cnpj())) {
			list.add(new FieldMessage("cpf_cnpj","invalid CPF."));
		}
		if (ClientType.toEnum(objDto.getClientType()).equals(ClientType.LEGAL) && !BR.isValidCNPJ(objDto.getCpf_cnpj())) {
			list.add(new FieldMessage("cpf_cnpj","invalid CNPJ."));
		}
		
		Client client = clientRepository.findByEmail(objDto.getEmail());
		if (client!=null) {
			list.add(new FieldMessage("email","E-mail already registered."));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMsg()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}