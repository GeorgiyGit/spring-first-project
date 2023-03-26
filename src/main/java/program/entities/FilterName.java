package program.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="tblFilterNames")
public class FilterName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 500, nullable = false)
    private String name;

    @Column(nullable = false)
    private Date dateCreated;

    @Column(nullable = false)
    private boolean isDelete;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "filterName")
    private List<FilterNameGroup> filterNameGroups=new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "filterName")
    private List<Filter> filters=new ArrayList<>();
}
