package by.byport.mealscontrol.ui.action;

import by.byport.mealscontrol.domain.entity.MealCheck;
import by.byport.mealscontrol.domain.entity.MealSeanceType;
import by.byport.mealscontrol.domain.entity.Relaxer;
import by.byport.mealscontrol.ui.WaitingScanDialog;
import com.jgoodies.binding.list.SelectionInList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class QrButton extends JButton {

    public QrButton(String name, SelectionInList<Relaxer> selectionInList, MealSeanceType mst) {
        setAction(new AbstractAction(name) {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new WaitingScanDialog(selectionInList.getList(), mst);
                dialog.pack();
                dialog.setResizable(false);
                dialog.setVisible(true);
            }
        });
        setPreferredSize(new Dimension(170, 45));
    }
}
