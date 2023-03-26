package program.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="tblBaskets")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "basket")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = true)
    private int count;
}
