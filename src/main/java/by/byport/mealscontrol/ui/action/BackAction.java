package by.byport.mealscontrol.ui.action;

import by.byport.mealscontrol.domain.entity.MealCheck;
import by.byport.mealscontrol.domain.entity.Relaxer;
import by.byport.mealscontrol.domain.service.RelaxerService;
import by.byport.mealscontrol.ui.MainForm;
import com.jgoodies.binding.list.SelectionInList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

public class BackAction extends AbstractAction {

    private final MainForm mainForm;
    private final SelectionInList<Relaxer> selectionInList;
    private final RelaxerService service;

    public BackAction(MainForm mainForm, SelectionInList<Relaxer> selectionInList, RelaxerService service) {
        super("Завершить сеанс");
        this.mainForm = mainForm;
        this.selectionInList = selectionInList;
        this.service = service;
    }

    public void actionPerformed(ActionEvent e) {
        Set<MealCheck> mealCheckList = new HashSet<>();
        for (Relaxer relaxer : selectionInList.getList()) {
            mealCheckList.addAll(relaxer.getMealCheckSet());
        }
        service.saveMealChecks(mealCheckList);
        mainForm.switchPanels();
    }
}
