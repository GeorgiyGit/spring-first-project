package program.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="tblProducts")
public class Product {
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
    private double price;

    @Column(length = 3000, nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
    private List<Basket> baskets = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> categories=new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<OrderItem> orderItems=new ArrayList<>();


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ProductImage> images=new ArrayList<>();
}
