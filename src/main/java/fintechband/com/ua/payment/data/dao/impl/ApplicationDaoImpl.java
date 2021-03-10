package fintechband.com.ua.payment.data.dao.impl;

import fintechband.com.ua.payment.data.dao.ApplicationDao;
import fintechband.com.ua.payment.data.entity.Application;
import fintechband.com.ua.payment.data.repository.ApplicationRepository;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nhaitanov
 */
@Component
public class ApplicationDaoImpl implements ApplicationDao {

    @Autowired
    private ApplicationRepository applicationRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Application> findAll() {
        ArrayList<Application> applicationList = (ArrayList<Application>) applicationRepository.findAll();
        return applicationList;
    }

    @SneakyThrows
    @Override
    public Application findById(Long id) {
        return applicationRepository.findById(id).orElseThrow(()
                -> new NotFoundException(String.format("Application with current id '%s' not found", id)));
    }

    @Override
    public List<Application> findAllByStatus(Long status) {
        /*variant of code implementation for a variety*/
        return (ArrayList<Application>) entityManager.createQuery("select a from Application as a where a.status = " + status, Application.class).getResultList();
    }

    @Override
    public Application findByStatus(Long status) {
        /*variant of code implementation for a variety*/
        return entityManager.createQuery("select a from Application as a where a.status = " + status , Application.class).setFirstResult(0).setMaxResults(1).getSingleResult();
    }

    @Override
    public Long save(Application application) {
        return applicationRepository.save(application).getId();
    }

    @Override
    public void deleteById(Long id) {
        applicationRepository.deleteById(id);
    }
}
