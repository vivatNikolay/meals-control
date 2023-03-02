package by.byport.mealscontrol.ui;
import by.byport.mealscontrol.domain.entity.MealSeanceType;
import by.byport.mealscontrol.domain.service.MealSeanceTypeService;
import by.byport.mealscontrol.domain.service.RelaxerService;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainForm extends JFrame {

    final RelaxerService relaxerService;
    RelaxersTable relaxerTab;

    public MainForm(final MealSeanceTypeService mealService, final RelaxerService relaxerService) {
        super("Контроль питания");
        this.relaxerService = relaxerService;
        FlatLightLaf.install(new FlatLightLaf());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = this.getToolkit().getScreenSize();

        JPanel contentPane = new JPanel();

        final JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(0, 1));
        for (final MealSeanceType mst : mealService.loadMealST()) {
            JPanel btnPanel = new JPanel();
            JButton b = new JButton(new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    buttons.setVisible(false);
                    openRelaxersTab(mst);
                    relaxerTab.setVisible(true);
                }
            });
            b.setText(mst.getName());
            b.setPreferredSize(new Dimension(screenSize.width/3, screenSize.height/mealService.loadMealST().size()/2));
            btnPanel.add(b);
            btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 15, 5));
            buttons.add(btnPanel);
        }
        contentPane.add(buttons);

        relaxerTab = new RelaxersTable(relaxerService);
        relaxerTab.setVisible(false);
        contentPane.add(relaxerTab);

        setContentPane(contentPane);
    }

    private void openRelaxersTab(MealSeanceType mst) {
        relaxerTab.setMst(mst);
    }
}
