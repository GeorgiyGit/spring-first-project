package program.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="tblFilters")
public class Filter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private FilterName filterName;

    @ManyToOne(fetch = FetchType.LAZY)
    private FilterValue filterValue;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
