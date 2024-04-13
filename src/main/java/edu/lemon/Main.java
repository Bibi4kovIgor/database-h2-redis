package edu.lemon;

import edu.lemon.data.Data;
import edu.lemon.entities.Categories;
import edu.lemon.entities.Clients;
import edu.lemon.entities.Documents;
import edu.lemon.entities.Products;
import edu.lemon.results.ProductsSummary;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.math.BigDecimal;
import java.util.List;

public class Main {
  public static void main(String[] args) {

    Transaction transaction = null;

    try(StandardServiceRegistry standardServiceRegistry =
            new StandardServiceRegistryBuilder()
                .configure()
                .build();
        SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry)
            .buildMetadata()
            .buildSessionFactory();

        Session session = sessionFactory.openSession()
    ) {
      transaction = session.getTransaction();
      transaction.begin();
      
      Products productHamon = Products.builder()
          .price(new BigDecimal("1354.14"))
          .name("Hamon")
          .description("Delicious piece of pig's leg")
          .build();
      session.persist(productHamon);
      session.contains(productHamon);

      Data.categoriesData().forEach(session::persist);

      List<Categories> categories = session.createQuery("from Categories", Categories.class).list();
      Data.productsData(categories).forEach(session::persist);

      List<ProductsSummary> productsSummaries =
          session.createQuery("select new ProductsSummary(name, categories) from Products", ProductsSummary.class).list();
      System.out.println(productsSummaries);

      Data.documentsData().forEach(session::persist);
      List<Documents> documents = session.createQuery("from Documents", Documents.class).list();
      Data.clientsData(documents).forEach(session::persist);

      List<Clients> clients = session.createQuery("from Clients", Clients.class).list();
      List<Products> products = session.createQuery("from Products", Products.class).list();

      Data.ordersData(clients, products).forEach(session::persist);

      transaction.commit();

      session.contains(productHamon);

      transaction.begin();

      session.createQuery("from Products", Products.class)
          .list().forEach(System.out::println);

//
//      ProductsRepository productsRepository = new ProductsRepository();
//      System.out.println(productsRepository.productsSummaries(session));

      transaction.commit();


    } catch (Exception exception){
      if (transaction != null) {
        transaction.rollback();
      }

      System.err.println(exception.getMessage());
    }

  }
}