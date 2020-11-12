/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.admin.kanban.ui.kanban;

import com.jhw.module.admin.kanban.core.domain.TareaDomain;
import com.jhw.module.admin.kanban.ui.tarea.TareaInputView;
import com.jhw.module.util.personalization.core.domain.Personalization;
import com.jhw.module.util.personalization.services.PersonalizationHandler;
import com.jhw.swing.bundles.dnd.DragHandler;
import com.jhw.swing.material.components.button.MaterialButtonIcon;
import com.jhw.swing.material.components.button._MaterialButtonIconTransparent;
import com.jhw.swing.material.components.container.panel._MaterialPanelComponent;
import com.jhw.swing.material.components.container.panel._PanelTransparent;
import com.jhw.swing.material.components.labels.MaterialLabel;
import com.jhw.swing.material.components.labels._MaterialLabel;
import com.jhw.swing.material.standards.MaterialFontRoboto;
import com.jhw.swing.models.input.dialogs.DialogModelInput;
import com.jhw.swing.util.interfaces.BindableComponent;
import com.jhw.utils.interfaces.Update;
import java.awt.BorderLayout;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class TareaSimplePanel extends _MaterialPanelComponent implements Update, BindableComponent<TareaDomain> {

    public static TareaSimplePanel from(TareaDomain tarea) {
        TareaSimplePanel tareaPanel = new TareaSimplePanel();
        tareaPanel.setObject(tarea);
        return tareaPanel;
    }

    private TareaDomain tarea;

    @Deprecated
    protected TareaSimplePanel() {
        initComponents();
        addListeners();

        DragSource.getDefaultDragSource()
                .createDefaultDragGestureRecognizer(
                        this, DnDConstants.ACTION_MOVE,
                        new DragHandler(this));
    }

    private void initComponents() {
        setGap(0);

        this.setLayout(new BorderLayout());

        //----------------UP-----------------------
        labelCodigo = new _MaterialLabel();
        labelCodigo.setFont(MaterialFontRoboto.MEDIUM.deriveFont(20f));
        labelCodigo.setHorizontalAlignment(SwingConstants.CENTER);

        labelPuntos = new _MaterialLabel();
        labelPuntos.setFont(MaterialFontRoboto.MEDIUM.deriveFont(16f));
        labelPuntos.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel up = new _PanelTransparent();
        up.setBorder(new EmptyBorder(5, 10, 0, 5));
        up.setLayout(new BorderLayout());
        up.add(labelCodigo, BorderLayout.WEST);
        up.add(labelPuntos, BorderLayout.EAST);
        this.add(up, BorderLayout.NORTH);

        //----------------MIDDLE-----------------------
        JPanel middle = new _PanelTransparent();
        middle.setLayout(new BorderLayout());
        labelNombre = new _MaterialLabel();
        labelNombre.setFont(MaterialFontRoboto.BOLD.deriveFont(18f));
        labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
        middle.add(labelNombre);
        this.add(middle);

        //----------------DOWN-----------------------
        buttonEdit = new _MaterialButtonIconTransparent();
        buttonEdit.setRippleColor(PersonalizationHandler.getColor(Personalization.KEY_COLOR_BUTTON_EDIT));
        buttonEdit.setIcon(PersonalizationHandler.getDerivableIcon(Personalization.KEY_ICON_BUTTON_EDIT));

        panelPrioridad = PrioridadSimplePanel.from();

        JPanel down = new _PanelTransparent();
        down.setLayout(new BorderLayout());

        down.add(buttonEdit, BorderLayout.WEST);
        down.add(panelPrioridad, BorderLayout.EAST);
        this.add(down, BorderLayout.SOUTH);
    }

    private MaterialLabel labelCodigo;
    private MaterialLabel labelPuntos;
    private MaterialLabel labelNombre;
    private MaterialButtonIcon buttonEdit;
    private PrioridadSimplePanel panelPrioridad;

    @Override
    public void update() {
        this.setToolTipText(tarea.getDescripcion());

        labelCodigo.setObject(tarea.getCodigoTarea());
        labelCodigo.setToolTipText("Código de la tarea: " + labelCodigo.getObject());

        labelNombre.setObject(tarea.getNombreTarea());
        labelNombre.setToolTipText("Nombre de la tarea: " + labelNombre.getObject());

        labelPuntos.setObject(String.valueOf(tarea.getPuntos()) + " pts.");
        labelPuntos.setToolTipText("Puntos de la tarea: " + labelPuntos.getObject());

        panelPrioridad.setObject(tarea.getPrioridadFk());//internamente actualiza
    }

    /**
     * Hack para tool tip multi line. Generalmente sale por proxy, pero como no
     * se puede usar... aqui esta a codigo
     *
     * @param text
     */
    @Override
    public void setToolTipText(String text) {
        if (text.trim().isEmpty()) {
            return;
        }
        text = "<html>" + text.replace("\n", "<br>") + "</html>";
        super.setToolTipText(text);
    }

    private void addListeners() {
        buttonEdit.addActionListener((ActionEvent e) -> {
            new DialogModelInput(TareaSimplePanel.this, TareaInputView.fromModel(tarea));
        });
    }

    @Override
    public TareaDomain getObject() {
        return tarea;
    }

    @Override
    public void setObject(TareaDomain t) {
        this.tarea = t;
        update();
    }
}
