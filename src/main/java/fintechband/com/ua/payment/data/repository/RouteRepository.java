package fintechband.com.ua.payment.data.repository;

import fintechband.com.ua.payment.data.entity.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nhaitanov
 */
@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {
}
