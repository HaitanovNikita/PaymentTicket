package fintechband.com.ua.payment.data.repository;

import fintechband.com.ua.payment.data.entity.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nhaitanov
 */
@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {

    Application findAllByStatus(Long status);
}
