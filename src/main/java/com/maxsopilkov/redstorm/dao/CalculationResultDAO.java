package com.maxsopilkov.redstorm.dao;

import com.maxsopilkov.redstorm.entities.CalculationResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CalculationResultDAO {
    @Autowired
    SessionFactory sessionFactory;

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public void save(CalculationResult calculationResult)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(calculationResult);
    }
}
