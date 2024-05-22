package com.example.application.views;

import com.example.application.backend.document.MovieSeatDocument;
import com.example.application.backend.service.FilmService;
import com.example.application.components.button.ButtonCustom;
import com.example.application.components.notification.NotificationCustom;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import org.vaadin.barcodes.Barcode;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

@PageTitle("Meu(s) Ingresso(s)")
@Route(value = "meu-ingresso/:document?", layout = MainLayout.class)
@RouteAlias(value = "meu-ingresso/:document?", layout = MainLayout.class)
public class ValidateTicketView extends VerticalLayout implements BeforeEnterObserver {

    private final FilmService filmService;
    private final TextField tfdDocument = new TextField("Documento");
    private final ButtonCustom btnSearch = new ButtonCustom("Procurar");

    public ValidateTicketView(FilmService filmService) {
        this.filmService = filmService;

        btnSearch.addClickListener(e -> {
            if(!isBlank(tfdDocument.getValue())) {
                var documents = filmService.findAllMovieSeatsByDocument(tfdDocument.getValue());

                if(!documents.isEmpty()) {
                    createCards(documents);
                } else {
                    removeAll();
                    add(tfdDocument, btnSearch);
                    NotificationCustom.showError("Nenhum ingresso para esse documento!");
                }

            } else {
                NotificationCustom.showError("Digite o documento do comprador!");
            }

        });

        add(tfdDocument, btnSearch);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        var document = event.getRouteParameters().get("document");
        document.ifPresent(e -> {
            tfdDocument.setValue(e);
            var documents = filmService.findAllMovieSeatsByDocument(e);
            createCards(documents);
        });
    }

    private void createCards(List<MovieSeatDocument> movieSeatDocuments) {
        removeAll();
        add(tfdDocument, btnSearch);

        movieSeatDocuments.forEach(e -> {
            e.getSeats().removeIf(f -> !f.getDocument().equalsIgnoreCase(tfdDocument.getValue()));
            e.getSeats().forEach(f -> {
                Barcode qrcode = new Barcode(e.getFilmId(),
                        Barcode.Type.qrcode,
                        "200px",
                        "200px");

//                var film = filmService.findFilmById(e.getFilmId());

                Span spnFilm = new Span("Filme: " + e.getFilmName());
                Span spnSeat = new Span("Assento: " + f.getSeat());
                Span spnSession = new Span("Sessão: " + f.getSession() + "hrs");

                VerticalLayout vlt = new VerticalLayout();
                vlt.setSpacing(false);
                vlt.add(spnFilm, spnSeat, spnSession, qrcode);
                vlt.setJustifyContentMode(JustifyContentMode.CENTER);
                vlt.setAlignItems(Alignment.CENTER);

                vlt.getStyle().set("border", "2px solid #9e9e9e");
                vlt.getStyle().set("border-radius", "10px");

                add(vlt);
            });


        });


    }
}
