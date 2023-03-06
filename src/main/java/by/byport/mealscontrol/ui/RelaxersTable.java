package by.byport.mealscontrol.ui;

import by.byport.mealscontrol.domain.entity.MealSeanceType;
import by.byport.mealscontrol.domain.entity.Relaxer;
import by.byport.mealscontrol.domain.service.RelaxerService;
import by.byport.mealscontrol.domain.utils.Localization;
import by.byport.mealscontrol.ui.action.BackAction;
import by.byport.mealscontrol.ui.action.QrAction;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.forms.builder.ButtonBarBuilder;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.FormLayout;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

public class RelaxersTable extends JPanel {
    private final JTextField searchField;
    private final TableRowSorter sorter;

    public RelaxersTable(MainForm parent, final RelaxerService service, MealSeanceType mst) {
        Localization localization = Localization.getInstance();

        SelectionInList<Relaxer> relaxers = new SelectionInList<>();
        relaxers.getList().addAll(service.getRelaxersForMeal());

        String[] columns = new String[] {
                localization.translate("relaxers.tab.surname"),
                localization.translate("relaxers.tab.name"),
                localization.translate("relaxers.tab.patronymic"),
                localization.translate("relaxers.tab.check"),
        };
        RelaxersTableModel tableModel = new RelaxersTableModel(relaxers, mst, columns);
        searchField = new JTextField();
        sorter = new TableRowSorter<>(tableModel);
        JXTable relaxersTable = new JXTable(tableModel);
        relaxersTable.setRowSorter(sorter);

        DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout("600dlu:g", "p, $lg, p,p, $lg, p, $lg, p, $lg, p"))
                .border(Borders.DIALOG);
        builder.append(new ButtonBarBuilder()
                .addGlue()
                .addButton(new QrAction(localization.translate("relaxers.tab.scan"), this, relaxers, mst))
                .build());
        builder.nextLine(2);
        builder.append(localization.translate("relaxers.tab.search"), searchField);
        builder.nextLine(2);
        builder.addSeparator(mst.getName());
        builder.nextLine(2);
        builder.append(new JScrollPane(relaxersTable));
        builder.nextLine(2);
        builder.append(new ButtonBarBuilder()
                .addGlue()
                .addButton(new BackAction(localization.translate("relaxers.tab.back"), parent, relaxers, service))
                .build());
        add(builder.getPanel());

        initListeners();
    }

    private void initListeners() {
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchField.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchField.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchField.getText());
            }
            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });
    }
}
