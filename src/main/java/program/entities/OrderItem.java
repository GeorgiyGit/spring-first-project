package program.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.sql.Date;

@Data
@Entity
@Table(name="tblOrderItems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 500, nullable = false)
    private String name;

    @Column(nullable = false)
    private Date dateCreated;

    @Column(nullable = false)
    private boolean isDelete;

    @Column(nullable = false)
    private int priceBuy;

    @Column(nullable = false)
    private int count;


    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
