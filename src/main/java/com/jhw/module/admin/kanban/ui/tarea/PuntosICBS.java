package com.jhw.module.admin.kanban.ui.tarea;

import com.jhw.module.admin.kanban.core.utils.FibonacciNumberUtils;
import com.jhw.swing.models.input.icbs.InputComboBoxSelection;
import com.jhw.swing.models.input.panels.ModelPanel;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class PuntosICBS extends InputComboBoxSelection<Integer> {

    public PuntosICBS() {
        setLabel("Puntos");
        setButtonNuevoVisibility(false);
    }

    @Override
    public List<Integer> getList() throws Exception {
        return Arrays.asList(FibonacciNumberUtils.generateFibboFixed());
    }

    @Override
    public ModelPanel<Integer> inputPanel() {
        return null;
    }

    @Override
    protected void addPropertyChange() {
    }

}
