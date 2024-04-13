package edu.lemon.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Products {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "quantity")
  private int quantity;

  @OneToMany(fetch = FetchType.EAGER,
      cascade = CascadeType.ALL,
      mappedBy = "product")
  private Set<Orders> orders;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "products_categories",
             joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
             inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
  private Set<Categories> categories;
}
