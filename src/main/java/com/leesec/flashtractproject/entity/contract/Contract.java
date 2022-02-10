package com.leesec.flashtractproject.entity.contract;

import com.leesec.flashtractproject.dto.ContractDTO;
import com.leesec.flashtractproject.entity.invoice.Invoice;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
        @CreationTimestamp
        private LocalDateTime createdDate;

        @Column
        @UpdateTimestamp
        private LocalDateTime updatedDate;

        @Enumerated(EnumType.STRING)
        @Column
        private ContractStatus status;

        public static Contract mapContractDTOtoContract(ContractDTO contractDTO) {
                Contract contract = new Contract();
                BeanUtils.copyProperties(contractDTO, contract,"createdDate", "updatedDate");

                if (contract.status == null) {
                        //Automatically set to approved for demo purposes
                        contract.setStatus(ContractStatus.APPROVED);
                }

                return contract;
        }
}
