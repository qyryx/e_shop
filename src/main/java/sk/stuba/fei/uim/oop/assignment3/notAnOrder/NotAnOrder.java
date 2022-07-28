package sk.stuba.fei.uim.oop.assignment3.notAnOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class NotAnOrder {

    // cause Order caused two day project delay -_-
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productId;

    private int amount;

    public NotAnOrder(Long productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }
}
