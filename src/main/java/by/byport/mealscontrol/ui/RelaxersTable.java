package by.byport.mealscontrol.ui;

import by.byport.mealscontrol.domain.entity.MealSeanceType;
import by.byport.mealscontrol.domain.entity.Relaxer;
import by.byport.mealscontrol.domain.service.RelaxerService;
import by.byport.mealscontrol.ui.action.QrAction;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.forms.builder.ButtonBarBuilder;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.FormLayout;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RelaxersTable extends JPanel {

    private JXTable relaxersTable;
    private final SelectionInList<Relaxer> relaxers;
    private MealSeanceType mst = new MealSeanceType();
    private final RelaxerService service;
    public RelaxersTable(final RelaxerService service) {
        this.service = service;
        relaxers = new SelectionInList<>();
        mst.setName("Прием пищи");
        relaxers.getList().addAll(service.getRelaxersForMeal());

        RelaxersTableModel tableModel = new RelaxersTableModel(relaxers, mst);
        relaxersTable = new JXTable(tableModel);

        DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout("500dlu:g", "p, $lg, p, $lg, p"))
                .border(Borders.DIALOG);
        builder.append(new ButtonBarBuilder()
                .addButton(new QrAction(this, relaxers, service, mst))
                .build());
        builder.nextLine(2);
        builder.addSeparator(mst.getName());
        builder.nextLine(2);
        builder.append(new JScrollPane(relaxersTable));
        add(builder.getPanel());

        relaxers.addValueChangeListener(evt -> {
            tableModel.setListModel((ListModel) evt.getNewValue());
        });
    }

    public void setMst(MealSeanceType mst) {
        this.mst = mst;
    }
}
