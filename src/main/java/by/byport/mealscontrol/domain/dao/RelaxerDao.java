package by.byport.mealscontrol.domain.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.Date;
import java.util.List;

public class RelaxerDao extends HibernateDaoSupport {

    public List<?> findForMeal(Date today) {
        return getHibernateTemplate().findByNamedParam("select"
                + " r.relaxerId,"
                + " ind.surname, ind.name, ind.patronymic"
                + " from Relaxer r"
                + " left join r.individual ind"
                + " left join r.relaxProduct product"
                + " where r.state = 5"
//                + " and product.mealType = 5"
                + " and r.arrivalDate <= :today"
                + " and r.departureDate >= :today"
                + " order by ind.surname",
                new String[] { "today" },
                new Date[] { today });
    }

}
