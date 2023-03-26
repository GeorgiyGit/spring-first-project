package program.entities;


import jakarta.persistence.*;
import lombok.Data;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="tblOrders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Date dateCreated;

    @Column(nullable = false)
    private boolean isDelete;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderStatus orderStatus;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
    private List<OrderItem> orderItems=new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
