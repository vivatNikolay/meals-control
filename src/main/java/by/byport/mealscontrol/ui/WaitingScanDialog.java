package by.byport.mealscontrol.ui;

import by.byport.mealscontrol.domain.entity.MealCheck;
import by.byport.mealscontrol.domain.entity.MealSeanceType;
import by.byport.mealscontrol.domain.entity.Relaxer;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class WaitingScanDialog extends JDialog implements KeyListener {

    private final static String YUNOST_SANKEY = "198";
    private final ImageIcon checkIcon;
    private final ImageIcon crossIcon = new ImageIcon("images/cross.png");
    private final StringBuilder stringBuilder;
    private final MealSeanceType meal;
    private JLabel labelText;
    private JLabel labelImage;
    private List<Relaxer> relaxers;
    public WaitingScanDialog(List<Relaxer> relaxers, MealSeanceType meal) {
        checkIcon = new ImageIcon("images/check.png");
        stringBuilder = new StringBuilder();
        this.relaxers = relaxers;
        this.meal = meal;

        setTitle("Сканирование");
        Dimension screenSize = this.getToolkit().getScreenSize();
        setPreferredSize(new Dimension(screenSize.width/3, screenSize.height/3));
        setLocation(screenSize.width/3, screenSize.height/3);

        labelText = new JLabel("Пусто  ");
        labelImage = new JLabel(checkIcon);

        DefaultFormBuilder builder =
                new DefaultFormBuilder(new FormLayout("$lcg, 100dlu", "5dlu, p, $lg, p, $lg"))
                .border(Borders.DIALOG);

        builder.nextColumn();
        builder.nextRow();
        builder.append(labelText);
        builder.nextLine(2);
        builder.append(labelImage);
        builder.nextLine();
        add(builder.getPanel());

        addKeyListener(this);
    }

    private void writeToString(char keyChar) {
        if ('\n' == keyChar) {
            checkRelaxer(stringBuilder.toString());
            int length = stringBuilder.length();
            stringBuilder.delete(0, length);
            return;
        }
        stringBuilder.append(keyChar);
    }

    private void checkRelaxer(String str) {
        try {
            final String[] values = str.split(" ");
            final Long relaxerId = new Long(values[0]);
            final String sanKey = values[1];
            Optional<Relaxer> oRelaxer = relaxers.stream()
                    .filter(r -> r.getRelaxerId().equals(relaxerId))
                    .findFirst();
            if (oRelaxer.isPresent() && YUNOST_SANKEY.equals(sanKey)) {
                labelText.setText(oRelaxer.get().getIndividual().toString());
                MealCheck mealCheck = new MealCheck();
                mealCheck.setRelaxer(oRelaxer.get());
                mealCheck.setCheckDate(new Date());
                mealCheck.setMealSeanceType(meal);
                oRelaxer.get().getMealCheckSet().add(mealCheck);
                return;
            }
        } catch (Exception e) {
            labelText.setText("Неверный qr");
            return;
        }
        labelText.setText("Отдыхающий не найден");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        writeToString(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
