package by.byport.mealscontrol.ui;

import by.byport.mealscontrol.domain.entity.MealCheck;
import by.byport.mealscontrol.domain.entity.MealSeanceType;
import by.byport.mealscontrol.domain.entity.Relaxer;
import by.byport.mealscontrol.domain.utils.Localization;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.FormLayout;
import org.apache.commons.lang.time.DateUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.List;

public class WaitingScanDialog extends JDialog implements KeyListener {

    private final static String YUNOST_SANKEY = "198";
    private final ImageIcon checkIcon;
    private final ImageIcon crossIcon;
    private final StringBuilder stringBuilder;
    private final MealSeanceType meal;
    private final JLabel labelText;
    private final JLabel labelImage;
    private final List<Relaxer> relaxers;
    private final Localization localization;

    public WaitingScanDialog(List<Relaxer> relaxers, MealSeanceType meal) {
        localization = Localization.getInstance();

        checkIcon = new ImageIcon(getClass().getClassLoader().getResource("images/check.png"));
        crossIcon = new ImageIcon(getClass().getClassLoader().getResource("images/cross.png"));
        stringBuilder = new StringBuilder();
        this.relaxers = relaxers;
        this.meal = meal;

        setTitle(localization.translate("waiting.dialog.title"));
        Dimension screenSize = this.getToolkit().getScreenSize();
        setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
        setLocation(screenSize.width / 4, screenSize.height / 4);

        JPanel panel = new JPanel();
        labelText = new JLabel(localization.translate("waiting.dialog.waiting"));
        labelText.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        labelImage = new JLabel();

        DefaultFormBuilder builder =
                new DefaultFormBuilder(new FormLayout("center:350dlu", "5dlu, p, $lg, p, $lg"))
                        .border(Borders.DIALOG);

        builder.nextRow();
        builder.append(labelText);
        builder.nextLine(4);
        builder.append(labelImage);
        builder.nextLine();
        panel.add(builder.getPanel());
        add(panel);

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
            for (Relaxer relaxer : relaxers) {
                if (relaxer.getRelaxerId().equals(relaxerId) && YUNOST_SANKEY.equals(sanKey)) {
                    if (!relaxer.getMealCheckSet().isEmpty()) {
                        for (MealCheck mealCheck : relaxer.getMealCheckSet()) {
                            if (mealCheck.getMealSeanceType().equals(meal) && DateUtils.isSameDay(new Date(), mealCheck.getCheckDate())) {
                                labelText.setText(relaxer.getIndividual().toString() + localization.translate("waiting.dialog.double.visit"));
                                labelImage.setIcon(crossIcon);
                                return;
                            }
                        }
                    }
                    labelText.setText(relaxer.getIndividual().toString());
                    labelImage.setIcon(checkIcon);
                    MealCheck mealCheck = new MealCheck();
                    mealCheck.setRelaxer(relaxer);
                    mealCheck.setCheckDate(new Date());
                    mealCheck.setMealSeanceType(meal);
                    relaxer.getMealCheckSet().add(mealCheck);

                    return;
                }
            }
        } catch (Exception e) {
            labelText.setText(localization.translate("waiting.dialog.wrong.qr"));
            labelImage.setIcon(crossIcon);
            return;
        }
        labelText.setText(localization.translate("waiting.dialog.not.found"));
        labelImage.setIcon(crossIcon);
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
