package com.example.application.components.button;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;

public class ButtonCustom extends Button {
    public ButtonCustom(String label) {
        super(label);

        setLabelVariant(label);
    }

    public ButtonCustom() {
        super();
    }

    public void setLabelVariant(String label) {
        switch (label.toLowerCase()) {
            case "confirmar", "cadastrar", "aceitar", "salvar", "comprar ingresso", "procurar" ->
                    addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);
            case "cancelar", "sair", "excluir" ->
                    addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);
            case "editar" ->
                    addThemeVariants(ButtonVariant.LUMO_CONTRAST, ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);
        }
    }
}
