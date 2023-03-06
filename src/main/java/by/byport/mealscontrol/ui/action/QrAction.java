package by.byport.mealscontrol.ui.action;

import by.byport.mealscontrol.domain.entity.MealSeanceType;
import by.byport.mealscontrol.domain.entity.Relaxer;
import by.byport.mealscontrol.ui.WaitingScanDialog;
import com.jgoodies.binding.list.SelectionInList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class QrAction extends AbstractAction {

    private static final long serialVersionUID = 2390307373069029736L;

    protected Component parent;
    protected SelectionInList<Relaxer> selectionInList;
    protected MealSeanceType mst;

    public QrAction(String name, Component parent, SelectionInList<Relaxer> selectionInList, MealSeanceType mst) {
        super(name);
        this.parent = parent;
        this.selectionInList = selectionInList;
        this.mst = mst;
    }

    public void actionPerformed(ActionEvent e) {
        JDialog dialog = new WaitingScanDialog(selectionInList.getList(), mst);
        dialog.pack();
        dialog.setResizable(false);
        dialog.setVisible(true);
    }
}
