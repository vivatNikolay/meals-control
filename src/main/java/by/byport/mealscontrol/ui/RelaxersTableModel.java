package by.byport.mealscontrol.ui;

import by.byport.mealscontrol.domain.entity.MealCheck;
import by.byport.mealscontrol.domain.entity.MealSeanceType;
import by.byport.mealscontrol.domain.entity.Relaxer;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import org.apache.commons.lang.time.DateUtils;

import javax.swing.*;
import java.util.Date;

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

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 3:
                return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }
}
