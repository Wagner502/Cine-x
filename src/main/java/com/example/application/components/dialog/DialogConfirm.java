package com.example.application.components.dialog;

import com.example.application.components.button.ButtonCustom;
import com.vaadin.flow.component.dialog.Dialog;

public class DialogConfirm extends Dialog {
    private ButtonCustom btnConfirm = new ButtonCustom("CONFIRMAR");
    private ButtonCustom btnCancel = new ButtonCustom("CANCELAR");

    public DialogConfirm(String title) {
        setHeaderTitle(title);
        buildDefaultConfigs(true);
    }

    public DialogConfirm(String title, String message) {
        setHeaderTitle(title);
        add(message);
        buildDefaultConfigs(true);
    }

    public DialogConfirm(String title, String message, boolean buttonCloseEvent) {
        setHeaderTitle(title);
        add(message);
        buildDefaultConfigs(buttonCloseEvent);
    }

    private void buildDefaultConfigs(boolean buttonCloseEvent) {
        if (buttonCloseEvent)
            btnCancel.addClickListener(evt -> close());

        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);
        setDraggable(true);
        setModal(true);

        getFooter().add(btnCancel);
        getFooter().add(btnConfirm);
    }


    public ButtonCustom getButtonConfirm() {
        return btnConfirm;
    }

    public ButtonCustom getButtonCancel() {
        return btnCancel;
    }

    public void setLabelBtnCancel(String label) {
        btnCancel.setText(label);
    }

    public void setLabelBtnConfirm(String label) {
        btnConfirm.setText(label);
    }
}
