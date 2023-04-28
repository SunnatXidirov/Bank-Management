package uz.pdp.onlinebanking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.onlinebanking.entity.enums.ActionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Conversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Card fromCardId;
    @ManyToOne
    private Card targetCardId;
    private BigDecimal amount;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ActionType actionType;
    @Column(nullable = false)
    private Date date;
}

