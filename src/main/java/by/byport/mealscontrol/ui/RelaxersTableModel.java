package by.byport.mealscontrol.ui;

import by.byport.mealscontrol.domain.entity.MealCheck;
import by.byport.mealscontrol.domain.entity.MealSeanceType;
import by.byport.mealscontrol.domain.entity.Relaxer;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import org.apache.commons.lang.time.DateUtils;

import javax.swing.*;
import java.util.Date;
import java.util.Set;

public class RelaxersTableModel extends AbstractTableAdapter<Relaxer> {

    private final MealSeanceType meal;
    public RelaxersTableModel(ListModel listModel, MealSeanceType meal) {
        super(listModel, "Фамилия", "Имя", "Отчество", "отметка");
        this.meal = meal;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Relaxer relaxer = getRow(rowIndex);

        switch (columnIndex) {
            case 0:
                return relaxer.getIndividual().getSurname();
            case 1:
                return relaxer.getIndividual().getName();
            case 2:
                return relaxer.getIndividual().getPatronymic();
            case 3:
                return checkMeal(relaxer);
        }

        return null;
    }

    private boolean checkMeal(Relaxer relaxer) {
        if (relaxer.getMealCheckSet().isEmpty())
            return false;
        for (MealCheck mealCheck : relaxer.getMealCheckSet()) {
            if (mealCheck.getMealSeanceType().equals(meal) && DateUtils.isSameDay(new Date(), mealCheck.getCheckDate())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Relaxer relaxer = getRow(rowIndex);
        if (value == null) {
            return;
        }
        if (columnIndex == 3) {
            getChangeMealCheckValue(relaxer);
        }
    }

    private void getChangeMealCheckValue(Relaxer relaxer) {
        Set<MealCheck> mealCheckSet = relaxer.getMealCheckSet();
        if (!mealCheckSet.isEmpty()) {
            for (MealCheck mealCheck : mealCheckSet) {
                if (mealCheck.getMealSeanceType().equals(meal) && DateUtils.isSameDay(new Date(), mealCheck.getCheckDate())) {
                    relaxer.getMealCheckSet().remove(mealCheck);
                    return;
                }
            }
        }
        MealCheck mealCheck = new MealCheck();
        mealCheck.setRelaxer(relaxer);
        mealCheck.setCheckDate(new Date());
        mealCheck.setMealSeanceType(meal);
        relaxer.getMealCheckSet().add(mealCheck);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 3) {
            return true;
        }
        return super.isCellEditable(rowIndex, columnIndex);
    }

    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 3) {
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }
}
