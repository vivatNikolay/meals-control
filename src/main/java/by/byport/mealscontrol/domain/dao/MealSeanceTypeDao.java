package by.byport.mealscontrol.domain.dao;

import by.byport.mealscontrol.domain.entity.MealSeanceType;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class MealSeanceTypeDao extends HibernateDaoSupport {

    public List<MealSeanceType> loadAll() {
        return getHibernateTemplate().loadAll(MealSeanceType.class);
    }

}
