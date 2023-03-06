package by.byport.mealscontrol.domain.service;

import by.byport.mealscontrol.domain.dao.RelaxerDao;
import by.byport.mealscontrol.domain.entity.MealCheck;
import by.byport.mealscontrol.domain.entity.Relaxer;
import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public class RelaxerService {
    private RelaxerDao relaxersDao;

    public RelaxerService() {

    }

    @Transactional
    public List<Relaxer> getRelaxersForMeal() {
        List<Relaxer> relaxers = new ArrayList<>();
        for (Object row : relaxersDao.findForMeal(new Date())) {
            Relaxer relaxer = (Relaxer) row;
            Hibernate.initialize(relaxer.getMealCheckSet());
            Hibernate.initialize(relaxer.getVisits());

            relaxers.add(relaxer);
        }

        return relaxers;
    }

    public void saveMealChecks(Set<MealCheck> mealCheckSet) {
        relaxersDao.getHibernateTemplate().saveOrUpdateAll(mealCheckSet);
    }

    public RelaxerDao getRelaxerDao() {
        return relaxersDao;
    }

    public void setRelaxerDao(RelaxerDao relaxersDao) {
        this.relaxersDao = relaxersDao;
    }
}
