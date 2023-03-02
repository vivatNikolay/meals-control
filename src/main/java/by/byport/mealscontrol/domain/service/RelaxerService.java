package by.byport.mealscontrol.domain.service;

import by.byport.mealscontrol.domain.dao.RelaxerDao;
import by.byport.mealscontrol.domain.entity.Individual;
import by.byport.mealscontrol.domain.entity.Relaxer;
import org.hibernate.Hibernate;

import java.util.*;

public class RelaxerService {
    private RelaxerDao relaxersDao;

    public RelaxerService() {

    }

    public List<Relaxer> getRelaxersForMeal() {
        List<Relaxer> relaxers = new ArrayList<>();
        for (Object row : relaxersDao.findForMeal(new Date())) {
            Object[] props = (Object[]) row;
            Relaxer relaxer = new Relaxer();
            relaxer.setRelaxerId((Long) props[0]);

            Individual individual = new Individual();
            individual.setSurname((String) props[1]);
            individual.setName((String) props[2]);
            individual.setPatronymic((String) props[3]);
            relaxer.setIndividual(individual);

            Hibernate.initialize(relaxer.getMealCheckSet());

            relaxers.add(relaxer);
        }

        return relaxers;
    }

    public RelaxerDao getRelaxerDao() {
        return relaxersDao;
    }

    public void setRelaxerDao(RelaxerDao relaxersDao) {
        this.relaxersDao = relaxersDao;
    }
}
