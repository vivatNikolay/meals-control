package by.byport.mealscontrol.ui;
import by.byport.mealscontrol.domain.entity.MealSeanceType;
import by.byport.mealscontrol.domain.service.MealSeanceTypeService;
import by.byport.mealscontrol.domain.service.RelaxerService;
import by.byport.mealscontrol.domain.utils.Localization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainForm extends JFrame {

    final RelaxerService relaxerService;
    JPanel buttons;
    JPanel relaxerTab;

    public MainForm(final MealSeanceTypeService mealService, final RelaxerService relaxerService) {
        super();
        Localization localization = Localization.getInstance();
        setTitle(localization.translate("main.form.title"));
        this.relaxerService = relaxerService;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = this.getToolkit().getScreenSize();

        UIManager.put("OptionPane.noButtonText", localization.translate("main.no"));
        UIManager.put("OptionPane.yesButtonText", localization.translate("main.yes"));

        JPanel contentPane = new JPanel();

        buttons = new JPanel();
        buttons.setLayout(new GridLayout(0, 1));
        for (final MealSeanceType mst : mealService.loadMealST()) {
            JPanel btnPanel = new JPanel();
            JButton b = new JButton(new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    openRelaxersTab(mst);
                }
            });
            b.setText(mst.getName());
            b.setPreferredSize(new Dimension(screenSize.width/3, screenSize.height/mealService.loadMealST().size()/2));
            btnPanel.add(b);
            btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 15, 5));
            buttons.add(btnPanel);
        }
        contentPane.add(buttons);

        relaxerTab = new JPanel();
        relaxerTab.setVisible(false);
        contentPane.add(relaxerTab);

        setContentPane(contentPane);

        initEventHandling();
    }

    private void openRelaxersTab(MealSeanceType mst) {
        Component[] componentList = relaxerTab.getComponents();
        for (Component c : componentList) {
            relaxerTab.remove(c);
        }
        relaxerTab.revalidate();
        relaxerTab.repaint();
        relaxerTab.add(new RelaxersTable(this, relaxerService, mst), 0);
        switchPanels();
    }

    public void switchPanels() {
        boolean value = buttons.isVisible();
        buttons.setVisible(!value);
        relaxerTab.setVisible(value);
    }

    private void initEventHandling() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
    }

    private void exit() {
        if (relaxerTab.isVisible()) {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            if (JOptionPane.showConfirmDialog(MainForm.this, "Вы действительно хотите закрыть программу без завершения сеанса?",
                    "Подтверждение", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.OK_OPTION) {
                this.dispose();
            }
        } else {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
