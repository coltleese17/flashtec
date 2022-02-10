package com.leesec.flashtractproject.service;

import com.leesec.flashtractproject.dto.ContractDTO;
import com.leesec.flashtractproject.entity.contract.Contract;
import com.leesec.flashtractproject.exception.ContractNotFoundException;
import com.leesec.flashtractproject.exception.DatabaseSaveFailedException;
import com.leesec.flashtractproject.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Log4j
@Service
public class ContractControllerService {

    private final ContractRepository contractRepository;

    public ContractControllerService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Contract saveContract(ContractDTO contractDTO) {
        Contract contract = Contract.mapContractDTOtoContract(contractDTO);

        //TODO: abstract the try catch to a DAO class
        try{
            contractRepository.save(contract);
        } catch(RuntimeException e){
            //log.info("Save to DB failed for contractID:" + contract.getId());
            throw new DatabaseSaveFailedException(e);
        }

        return contract;
    }

    public Contract getContractByID(Long contractId) {
        Optional<Contract> contract = Optional.empty();
        contract = contractRepository.findById(contractId);

        //!isContractLinkedToUser(contract, user)
        if (contract.isEmpty()) {
            throw new ContractNotFoundException();
        }

        return contract.get();
    }
}
