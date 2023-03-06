package by.byport.mealscontrol.domain.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.Date;
import java.util.List;

public class RelaxerDao extends HibernateDaoSupport {

    public List<?> findForBreakfast(Date today) {
        return getHibernateTemplate().findByNamedParam("select"
                + " r"
                + " from Order ord"
                + " left join ord.relaxers r"
                + " left join r.visits v"
                + " left join v.place p"
                + " left join p.room room"
                + " left join r.individual ind"
                + " left join r.relaxProduct product"
                + " where r.state = 5"
                + " and product.mealType = 4"
                + " and r.arrivalDate < :today"
                + " and ADDDATE(r.departureDate, ord.daysAccountingType) >= :today"
                + " order by ind.surname",
                new String[] { "today" },
                new Date[] { today });
    }

    public List<?> findForOtherMeal(Date today) {
        return getHibernateTemplate().findByNamedParam("select"
                + " r"
                + " from Order ord"
                + " left join ord.relaxers r"
                + " left join r.visits v"
                + " left join v.place p"
                + " left join p.room room"
                + " left join r.individual ind"
                + " left join r.relaxProduct product"
                + " where r.state = 5"
                + " and product.mealType = 4"
                + " and r.arrivalDate <= :today"
                + " and ADDDATE(r.departureDate, ord.daysAccountingType) > :today"
                + " order by ind.surname",
                new String[] { "today" },
                new Date[] { today });
    }

}
