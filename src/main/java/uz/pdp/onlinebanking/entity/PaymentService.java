package uz.pdp.onlinebanking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class PaymentService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String logo;
    @ManyToOne
    private PaymentCategory paymentCategory;
    @ManyToOne
    private Users userId;
    @Column(nullable = false)
    private Long accountNumber;
    private boolean active;
}
