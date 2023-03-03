package by.byport.mealscontrol;

import by.byport.mealscontrol.ui.MainForm;
import com.formdev.flatlaf.FlatLightLaf;
import com.jgoodies.common.base.SystemUtils;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.SkyBlue;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }
    private ClassPathXmlApplicationContext ctx;

    public App() {
        try {
            ctx = new ClassPathXmlApplicationContext("context.xml");
            ctx.registerShutdownHook();
        } catch (BeansException e) {
            throw e;
        }
        initLookAndFill();
    }

    private void initLookAndFill() {
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
    }

    private void start() {
        final JFrame mainFrame = ctx.getBean("mainForm", MainForm.class);
        SwingUtilities.invokeLater(new Runnable() {// Любые операции касающиеся swingoff должны
            // производится в EDT
            public void run() {
                try {
                    mainFrame.setVisible(true);
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                            "Произошла ошибка, обратитесь к разработчику", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
