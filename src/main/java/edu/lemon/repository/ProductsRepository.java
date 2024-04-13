package edu.lemon.repository;

import edu.lemon.results.ProductsSummary;
import org.hibernate.Session;

import java.util.List;

public class ProductsRepository {

  public List<ProductsSummary> productsSummaries(Session session) {
    return session
        .createQuery("select new ProductsSummary(name) from Products", ProductsSummary.class)
        .getResultList();
  }


}
