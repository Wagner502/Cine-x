package com.example.application.views.film;

import com.example.application.backend.document.MovieSeatDocument;
import com.example.application.backend.model.FilmTmdb;
import com.example.application.backend.model.UserFilm;
import com.example.application.backend.service.FilmService;
import com.example.application.components.button.ButtonCustom;
import com.example.application.components.dialog.DialogCustom;
import com.example.application.components.notification.NotificationCustom;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.barcodes.Barcode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class SelectSeatView extends VerticalLayout {
    private final FilmService filmService;
    private final FilmTmdb film;
    private final List<String> selectedList = new ArrayList<>();
    private List<UserFilm> allSeats = new ArrayList<>();
    private final VerticalLayout vltFields = new VerticalLayout();
    private final VerticalLayout vltPayment = new VerticalLayout();

    private final TextField tfdDocument = new TextField("Documento");
    private final TextField tfdName = new TextField("Nome");
    private final ComboBox<String> cbbSession = new ComboBox<>("Horário");
    private final Span spnValue = new Span("R$ 0");
    private final ButtonCustom btnPix = new ButtonCustom("Copiar QRCode (pix)");
    private final DialogCustom dlgBuyTicket;

    public SelectSeatView(
            FilmService filmService,
            FilmTmdb film,
            DialogCustom dlgBuyTicket
    ) {
        this.filmService = filmService;
        this.film = film;
        this.dlgBuyTicket = dlgBuyTicket;

        this.setPadding(false);

        createSit();
        createFields();
        createPayment();

        dlgBuyTicket.btnConfirm.addClickListener(f -> {
            if (validateConfirm()) {
                var document = buildMovieSeat();
                filmService.saveMovieSeat(document);

                dlgBuyTicket.close();

                UI.getCurrent().navigate("meu-ingresso/" + tfdDocument.getValue());
            }
        });
    }

    private MovieSeatDocument buildMovieSeat() {
        var users = buildUserFilm();
        if (nonNull(allSeats) && !allSeats.isEmpty()) {
            users.addAll(allSeats);
        }

        return MovieSeatDocument.builder()
                .filmId(film.getId())
                .seats(users)
                .filmName(film.getTitle())
                .build();
    }

    private List<UserFilm> buildUserFilm() {
        List<UserFilm> list = new ArrayList<>();
        selectedList.forEach(e -> {
            var userFilm = UserFilm.builder()
                    .document(tfdDocument.getValue())
                    .name(tfdName.getValue())
                    .seat(e)
                    .session(cbbSession.getValue())
                    .build();

            list.add(userFilm);
        });

        return list;
    }

    private void createSit() {
        var movieSeatDocument = filmService.findAllMovieSeats(film.getId());
        VerticalLayout vltMain = new VerticalLayout();
        vltMain.setPadding(false);
        vltMain.setAlignItems(Alignment.CENTER);
        for (int i = 0; i < 6; i++) {
            HorizontalLayout hlt1 = new HorizontalLayout();
            for (int y = 0; y < 6; y++) {
                HorizontalLayout hlt2 = new HorizontalLayout();
                var id = i + "" + y;
                hlt2.setId(i + "" + y);
                hlt2.getStyle().set("width", "32px");
                hlt2.getStyle().set("height", "32px");
                hlt2.getStyle().set("border-radius", "50%");
                hlt2.getStyle().set("background", "green");
                hlt2.getStyle().set("cursor", "pointer");
                hlt2.setJustifyContentMode(JustifyContentMode.CENTER);
                hlt2.setAlignItems(Alignment.CENTER);
                hlt2.add(id);
                hlt2.addClickListener(e -> {
                    var exists = selectedList.stream()
                            .filter(f -> f.equalsIgnoreCase(id))
                            .findAny();

                    if (exists.isPresent()) {
                        var g = exists.get();
                        hlt2.getStyle().set("background", "green");
                        selectedList.remove(g);
                    } else {
                        selectedList.add(id);
                        hlt2.getStyle().set("background", "red");
                    }

                    calculateValue();
                });

                allSeats = movieSeatDocument.getSeats();

                if (nonNull(allSeats)) {
                    allSeats.stream()
                            .filter(seat -> seat.getSeat().equalsIgnoreCase(id))
                            .findAny()
                            .ifPresent(e -> {
                                hlt2.getStyle().set("background", "red");
                                hlt2.setEnabled(false);
                            });
                }

                hlt1.add(hlt2);
            }
            vltMain.add(hlt1);
        }

        add(vltMain);
    }

    private void createFields() {
        vltFields.getStyle().set("border-top", "2px solid #9e9e9e");
        vltFields.setSpacing(false);
        vltFields.setJustifyContentMode(JustifyContentMode.CENTER);
        vltFields.setAlignItems(Alignment.CENTER);

        tfdName.setWidth("16em");
        tfdDocument.setWidth("16em");

        spnValue.getStyle().set("font-size", "22px");
        spnValue.getStyle().set("font-weight", "bold");

        List<String> items = Arrays.stream(film.getSession().split(";")).toList();
        cbbSession.setItems(items);
        cbbSession.setItemLabelGenerator(e -> e);

        vltFields.add(spnValue, tfdName, tfdDocument, cbbSession);
        add(vltFields);
    }

    private void calculateValue() {
        var size = selectedList.size();
        var price = film.getPrice();

        var value = (price * size);
        DecimalFormat valueFormat = new DecimalFormat("###,###,###,##0.00");
        var formattedValue = valueFormat.format(value);

        spnValue.setText("R$ " + formattedValue);
    }

    private void createPayment() {
        vltPayment.getStyle().set("border-top", "2px solid #9e9e9e");
        vltPayment.setSpacing(false);
        vltPayment.setJustifyContentMode(JustifyContentMode.CENTER);
        vltPayment.setAlignItems(Alignment.CENTER);

        btnPix.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);
        btnPix.addClickListener(e -> {
            NotificationCustom.showSuccess("Código pix copiado!");
        });

        Barcode qrcode = new Barcode(film.getId() + "|" + film.getPrice() + "|" + film.getSession(),
                Barcode.Type.qrcode,
                "200px",
                "200px");

        vltPayment.add(qrcode, btnPix);

        add(vltPayment);
    }

    public boolean validateConfirm() {
        var isValid = true;

        if (selectedList.isEmpty()) {
            NotificationCustom.showError("Selecione pelo menos 1 assento!");
            isValid = false;
        }

        if (isBlank(tfdName.getValue())) {
            NotificationCustom.showError("Digite o nome do comprador!");
            isValid = false;
        }

        if (isBlank(tfdDocument.getValue())) {
            NotificationCustom.showError("Digite documento do comprador!");
            isValid = false;
        }

        if(isBlank(cbbSession.getValue())) {
            NotificationCustom.showError("Selecione um horário!");
            isValid = false;
        }

        return isValid;
    }
}
