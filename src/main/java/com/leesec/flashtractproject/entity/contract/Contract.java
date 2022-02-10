package com.leesec.flashtractproject.entity.contract;

import com.leesec.flashtractproject.dto.ContractDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contract")
public class Contract {

        @Id
        @GeneratedValue
        private Long id;

        @Column
        private Long clientId;

        @Column
        private Long vendorId;

        @Column
        private LocalDateTime startDate;

        @Column
        private LocalDateTime endDate;

        @Column
        private String description;

        @Column
        private double totalCost;

        @Column
        private double remainingCost;

        @Column
        private LocalDateTime createdDate;

        @Column
        private LocalDateTime updatedDate;

//        @Column
//        private LocalDateTime deadline;

        @Enumerated(EnumType.STRING)
        @Column
        private ContractStatus status;

//        @OneToMany(mappedBy = "contractId")
//        private List<Invoice> invoices;

        //        @ManyToOne
//        @JoinColumn(name = "contractorUserId", referencedColumnName = "id", insertable = false, updatable = false)
//        private User contractorUser;

        public static Contract mapContractDTOtoContract(ContractDTO contractDTO) {
                Contract contract = Contract.buildNewContract();
                BeanUtils.copyProperties(contractDTO, contract);

                if (contract.status == null) {
                        //automatically set to approved for demo purposes
                        contract.setStatus(ContractStatus.APPROVED);
                }

                return contract;
        }

        public static Contract buildNewContract() {
                LocalDateTime now = LocalDateTime.now();
                return Contract.builder()
                        .createdDate(now)
                        .updatedDate(now)
                        .build();
        }




}
