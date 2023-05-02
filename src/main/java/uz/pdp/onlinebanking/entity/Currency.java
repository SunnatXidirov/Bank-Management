package uz.pdp.onlinebanking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.onlinebanking.entity.enums.CurrencyAbbr;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    private CurrencyAbbr abbr;
    private boolean active;
}
