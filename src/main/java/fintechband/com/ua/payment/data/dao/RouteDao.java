package fintechband.com.ua.payment.data.dao;

import fintechband.com.ua.payment.data.entity.Route;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nhaitanov
 */
@Repository
public interface RouteDao {

    List<Route> findAll();

    Route findById(Long id);

    Route save(Route status);

    void deleteById(Long id);

}
