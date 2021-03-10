package fintechband.com.ua.payment.data.repository;

import fintechband.com.ua.payment.data.entity.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nhaitanov
 */
@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {
}
