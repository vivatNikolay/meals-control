package by.byport.mealscontrol.domain.service;

import by.byport.mealscontrol.domain.entity.MealSeanceType;
import by.byport.mealscontrol.domain.dao.MealSeanceTypeDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MealSeanceTypeService {
    private MealSeanceTypeDao mealSTDao;

    public MealSeanceTypeService() {

    }

    @Transactional(readOnly = true)
    public List<MealSeanceType> loadMealST() {
        return mealSTDao.loadAll();
    }

    public MealSeanceTypeDao getMealSTDao() {
        return mealSTDao;
    }

    public void setMealSTDao(MealSeanceTypeDao mealSTDao) {
        this.mealSTDao = mealSTDao;
    }
}
