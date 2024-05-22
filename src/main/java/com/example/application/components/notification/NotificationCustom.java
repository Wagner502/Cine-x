package com.example.application.components.notification;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class NotificationCustom extends Notification {
    public NotificationCustom() {
        super();
    }

    public static NotificationCustom showSuccess(String message) {
        return new NotificationCustom(
                message,
                NotificationVariant.LUMO_SUCCESS,
                Notification.Position.TOP_CENTER,
                5000);
    }

    public static NotificationCustom showError(String message) {
        return new NotificationCustom(
                message,
                NotificationVariant.LUMO_ERROR,
                Notification.Position.TOP_CENTER,
                5000);
    }

    public NotificationCustom(String message, NotificationVariant variant, Notification.Position position, int duration) {
        super();
        setText(message);
        addThemeVariants(variant);
        setPosition(position);
        setDuration(duration);
        addCloseIcon(message);
        open();
    }

    private void addCloseIcon(String message) {
        Div text = new Div(new Text(message));

        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getStyle().set("cursor", "pointer");
        closeButton.addClickListener(event -> {
            this.close();
        });

        HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setWidthFull();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        this.add(layout);
    }
}
