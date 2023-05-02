package uz.pdp.onlinebanking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private long number;
    @OneToOne
    private CardType cardTypeId;
    @ManyToOne
    private Users usersId;
    private Date expireDate;
    private Integer cvv;
    @ManyToOne
    private Tariff tariffId;
    private BigDecimal balance;
    @ManyToOne
    private Currency currency;
}
