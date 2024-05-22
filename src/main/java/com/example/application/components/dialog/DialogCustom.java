package com.example.application.components.dialog;

import com.example.application.components.button.ButtonCustom;
import com.vaadin.flow.component.dialog.Dialog;

public class DialogCustom extends Dialog {
    public ButtonCustom btnCancel;
    public ButtonCustom btnConfirm;

    public DialogCustom(String title, String buttonCancel, String buttonConfirm) {
        super();

        setHeaderTitle(title);

        btnCancel = new ButtonCustom(buttonCancel);
        btnConfirm = new ButtonCustom(buttonConfirm);

        btnCancel.addClickListener(e -> this.close());

        getFooter().add(btnCancel, btnConfirm);
    }
}
