package ru.tsystems.project.domain.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import ru.tsystems.project.domain.DAO.interfaces.TrainDAO;
import ru.tsystems.project.domain.entities.Train;
import ru.tsystems.project.exceptions.CustomDAOException;

public class TrainDAOImpl implements TrainDAO {

    private final EntityManager manager;

    public TrainDAOImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Train save(Train entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not save train: " + ex);
        }
        return entity;
    }

    @Override
    public List<Train> findAll() {
        // TypedQuery<Train> query;
        Query query = manager.createNamedQuery(Train.FINAALLTRAINS);
        List<Train> result = query.getResultList();
        return result;
    }

    @Override
    public Train delete(Train entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not remove train: " + ex);
        }
        return entity;
    }

    @Override
    public Train update(Train entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Can not update train: " + ex);
        }
        return entity;
    }

    @Override
    public Train findById(int id) {
        Train result = manager.find(Train.class, id);
        return result;
    }

}
