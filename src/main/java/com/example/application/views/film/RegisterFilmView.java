package com.example.application.views.film;

import com.example.application.backend.document.FilmDocument;
import com.example.application.backend.service.FilmService;
import com.example.application.components.button.ButtonCustom;
import com.example.application.components.datepicker.DatePickerCustom;
import com.example.application.components.dialog.DialogCustom;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

import static com.example.application.components.notification.NotificationCustom.showSuccess;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class RegisterFilmView extends FormLayout {
    private FilmDocument filmDocument;
    private DialogCustom dlgRegister;
    private FilmService filmService;

    // fields
    private final TextField tfdName = new TextField("Nome");
    private final TextField tfdDescription = new TextField("Descrição");
    private final IntegerField tfdPrice = new IntegerField("Valor");
    private final TextField tfdSession = new TextField("Sessões");
    private final TextField tfdRoom = new TextField("Sala");
    private final TextField tfdUrlImages = new TextField("URL imagens");
    private final IntegerField tfdDuration = new IntegerField("Duração");
    private final DatePickerCustom dtpInit = new DatePickerCustom("Inicio cartaz");
    private final DatePickerCustom dtpFinal = new DatePickerCustom("Fim cartaz");
    private final Checkbox chkActive = new Checkbox("Ativo", true);

    public final ButtonCustom btnFakeClick = new ButtonCustom();

    public RegisterFilmView(
            FilmDocument filmDocument,
            DialogCustom dlgRegister,
            FilmService filmService) {
        this.filmDocument = filmDocument;
        this.dlgRegister = dlgRegister;
        this.filmService = filmService;

        setResponsiveSteps(
                new ResponsiveStep("0", 1),
                new ResponsiveStep("500px", 2),
                new ResponsiveStep("800px", 3),
                new ResponsiveStep("1200px", 4),
                new ResponsiveStep("1600px", 5)
        );

        createFields();
        createFooterEvents();
        if (nonNull(filmDocument) && isNotBlank(filmDocument.getId())) {
            populateFields();
        }
    }

    private void populateFields() {
        StringBuilder images = new StringBuilder();
        filmDocument.getUrlImage().forEach(e -> {
            images.append(e).append(";");
        });

        tfdName.setValue(filmDocument.getName());
        tfdDescription.setValue(filmDocument.getDescription());
        tfdPrice.setValue(filmDocument.getPrice());
        tfdRoom.setValue(filmDocument.getRoom());
        tfdSession.setValue(filmDocument.getSession());
        tfdUrlImages.setValue(images.toString());
        tfdDuration.setValue(filmDocument.getDuration());
        dtpInit.setValue(filmDocument.getInitDate());
        dtpFinal.setValue(filmDocument.getFinalDate());
        chkActive.setValue(filmDocument.isActive());
    }

    private void createFields() {
        add(
                tfdName,
                tfdDescription,
                tfdPrice,
                tfdSession,
                tfdRoom,
                tfdUrlImages,
                tfdDuration,
                dtpInit,
                dtpFinal,
                chkActive
        );

        tfdName.focus();
    }

    private void createFooterEvents() {
        dlgRegister.btnConfirm.addClickListener(e -> {
            var document = buildFilmDocument();
            filmService.registerFilm(document);

            showSuccess("Filme cadastrado com sucesso!");
            clearFields();
            dlgRegister.close();
            btnFakeClick.click();
        });
    }

    private FilmDocument buildFilmDocument() {
//        var images = Arrays.stream(tfdUrlImages.getValue().split(";")).toList();

        // criar validação de campos

        var filmDocument =  FilmDocument.builder()
                .name(tfdName.getValue())
                .description(tfdDescription.getValue())
                .price(tfdPrice.getValue())
                .urlImage(List.of(tfdUrlImages.getValue()))
                .duration(tfdDuration.getValue())
                .initDate(dtpInit.getValue())
                .finalDate(dtpFinal.getValue())
                .active(chkActive.getValue())
                .session(tfdSession.getValue())
                .room(tfdRoom.getValue())
                .build();

        if(isNotBlank(this.filmDocument.getId()))
            filmDocument.setId(this.filmDocument.getId());
        return filmDocument;
    }

    private void clearFields() {
        tfdName.clear();
        tfdDescription.clear();
        tfdPrice.clear();
        tfdSession.clear();
        tfdUrlImages.clear();
        tfdRoom.clear();
        tfdDuration.clear();
        dtpInit.clear();
        dtpFinal.clear();
        chkActive.setValue(true);

        tfdName.focus();
    }
}
