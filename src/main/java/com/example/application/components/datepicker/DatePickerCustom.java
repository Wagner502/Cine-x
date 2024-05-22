package com.example.application.components.datepicker;

import com.vaadin.flow.component.datepicker.DatePicker;

import java.util.List;
import java.util.Locale;

public class DatePickerCustom extends DatePicker {

    public DatePickerCustom() {
        super();

        setInternalization();
        Locale finnishLocale = new Locale("pt", "BR");
        setLocale(finnishLocale);
    }

    public DatePickerCustom(String title) {
        super();

        setLabel(title);

        setInternalization();
        Locale finnishLocale = new Locale("pt", "BR");
        setLocale(finnishLocale);
    }

    private void setInternalization() {
        DatePicker.DatePickerI18n brI18n = new DatePicker.DatePickerI18n();
        brI18n.setMonthNames(List.of("Janeiro", "Fevereiro", "Março", "Abril",
                "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro",
                "Novembro", "Dezembro"));
        brI18n.setWeekdays(List.of("Domingo", "Segunda", "Terça",
                "Quarta", "Quinta", "Sexta", "Sábado"));
        brI18n.setWeekdaysShort(
                List.of("DO", "SE", "TE", "QU", "QU", "SE", "SA"));
        brI18n.setWeek("Semana");
        brI18n.setToday("Hoje");
        brI18n.setCancel("Cancelar");

        setI18n(brI18n);
    }
}
