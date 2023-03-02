package by.byport.mealscontrol.ui.action;

import by.byport.mealscontrol.domain.entity.MealCheck;
import by.byport.mealscontrol.domain.entity.MealSeanceType;
import by.byport.mealscontrol.domain.entity.Relaxer;
import by.byport.mealscontrol.domain.service.RelaxerService;
import com.jgoodies.binding.list.SelectionInList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

public class BackAction extends AbstractAction {

    private static final long serialVersionUID = 2390307373069029736L;

    protected Component parent;
    protected SelectionInList<Relaxer> selectionInList;
    protected RelaxerService service;
    protected MealSeanceType mst;

    public BackAction(Component parent, SelectionInList<Relaxer> selectionInList, RelaxerService service, MealSeanceType mst) {
        super("Завершить сеанс");
        this.parent = parent;
        this.selectionInList = selectionInList;
        this.service = service;
        this.mst = mst;
    }

    public void actionPerformed(ActionEvent e) {
        Set<MealCheck> mealCheckList = new HashSet<>();
        for (Relaxer relaxer : selectionInList.getList()) {
            mealCheckList.addAll(relaxer.getMealCheckSet());
        }
        service.saveMealChecks(mealCheckList);
        //back action
    }
}
