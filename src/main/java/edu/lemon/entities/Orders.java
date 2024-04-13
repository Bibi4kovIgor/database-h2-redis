package edu.lemon.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Orders {
  @Id
  @ManyToOne(fetch = FetchType.EAGER,
              cascade = CascadeType.ALL)
  @JoinColumn(name = "client_id", referencedColumnName = "id")
  private Clients client;

  @Column(name = "quantity")
  private int quantity;

  @Id
  @ManyToOne(fetch = FetchType.EAGER,
             cascade = CascadeType.ALL)
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Products product;

  @Column(name = "order_date")
  private Instant orderDate;


}
