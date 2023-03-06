package by.byport.mealscontrol.ui.action;

import by.byport.mealscontrol.domain.entity.MealCheck;
import by.byport.mealscontrol.domain.entity.Relaxer;
import by.byport.mealscontrol.domain.service.RelaxerService;
import by.byport.mealscontrol.ui.MainForm;
import com.jgoodies.binding.list.SelectionInList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

public class BackButton extends JButton {

    public BackButton(String name, MainForm mainForm, SelectionInList<Relaxer> selectionInList, RelaxerService service) {
        setAction(new AbstractAction(name) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set<MealCheck> mealCheckList = new HashSet<>();
                for (Relaxer relaxer : selectionInList.getList()) {
                    mealCheckList.addAll(relaxer.getMealCheckSet());
                }
                service.saveMealChecks(mealCheckList);
                mainForm.switchPanels();
            }
        });
        setPreferredSize(new Dimension(170, 55));
        setBackground(Color.RED);
        setForeground(Color.WHITE);
    }
}
