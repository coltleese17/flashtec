package com.leesec.flashtractproject.repository;

import com.leesec.flashtractproject.entity.contract.Contract;
import org.springframework.data.repository.CrudRepository;

public interface ContractRepository extends CrudRepository<Contract, Long> {
}
