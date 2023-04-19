package uz.pdp.onlinebanking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.onlinebanking.entity.enums.ActionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Currency currency;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    @Column(nullable = false)
    private BigDecimal rate;
    @Column(nullable = false)
    private Date date;
    private boolean active;
}
