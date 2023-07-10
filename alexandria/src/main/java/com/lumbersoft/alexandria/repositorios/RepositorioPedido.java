package com.lumbersoft.alexandria.repositorios;


import com.lumbersoft.alexandria.entidades.Menu.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioPedido extends JpaRepository<Purchase,Integer> {



    @Query("SELECT p FROM Purchase p JOIN p.coffees c WHERE c.id = :id")
    List<Purchase> purchaseByCoffee(@Param("id")Integer id);

    @Query("SELECT p FROM Purchase p JOIN p.sweets c WHERE c.id = :id")
    List<Purchase> purchaseBySweets(@Param("id")Integer id);
}
