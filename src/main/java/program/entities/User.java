package program.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name="tblUsers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date dateCreated;

    @Column(nullable = false)
    private boolean isDelete;


    @Column(length = 200, nullable = false)
    private String firstName;

    @Column(length = 200, nullable = false)
    private String lastName;

    @Column(length = 30, nullable = false)
    private String phone;

    @Column(length = 255, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String password;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @ManyToMany
    private List<Role> roles=new ArrayList<>();


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<Order> orders=new ArrayList<>();
}
