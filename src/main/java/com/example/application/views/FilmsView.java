package com.example.application.views;

import com.example.application.backend.model.FilmTmdb;
import com.example.application.backend.repository.OMDBFeign;
import com.example.application.backend.service.FilmService;
import com.example.application.components.button.ButtonCustom;
import com.example.application.components.dialog.DialogCustom;
import com.example.application.views.film.SelectSeatView;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.util.List;

import static com.example.application.backend.util.GlobalUtil.buildFormattedDateString;
import static java.util.Objects.nonNull;

@PageTitle("Cartaz")
@Route(value = "cartaz", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class FilmsView extends VerticalLayout {
    private final FilmService filmService;
    private DialogCustom dlgBuyTicket;

    private HorizontalLayout hlt = new HorizontalLayout();

    @Autowired
    private OMDBFeign omdbFeign;

    public FilmsView(FilmService filmService) {
        this.filmService = filmService;

        getStyle().set("flex-wrap", "wrap");
        getStyle().set("justify-content", "center");

        createCards();
    }

    private void createCards() {
//        var films = filmService.findAllFilmsByActiveTrue();

        var films = filmService.findTmdbNowPlaying();

        if (nonNull(films.getResults()) && !films.getResults().isEmpty()) {
            createLayout(films.getResults());
        } else {
            Span spnError = new Span("Nenhum filme encontrado");
            add(spnError);
        }

    }

    private void createLayout(List<FilmTmdb> films) {
        films.forEach(film -> {
            VerticalLayout div = new VerticalLayout();
            div.setPadding(false);
            div.setSpacing(false);
            div.setAlignItems(Alignment.CENTER);
            div.getStyle().set("border", "1px solid #9e9e9e");
            div.getStyle().set("border-radius", "5px");
            div.getStyle().set("margin", "5px");
            div.getStyle().set("padding", "10px");
            div.getStyle().set("width", "");

            Image img = new Image("https://image.tmdb.org/t/p/w342" + film.getPoster_path(), "filme");
//            img.setWidth("350px");
//            img.setMaxHeight("300px");
            div.add(img);

            H2 spnName = new H2(film.getTitle());
            spnName.getStyle().set("font-weight", "bold");
            spnName.getStyle().set("margin", "0");
            spnName.getStyle().set("width", "100%");
            spnName.getStyle().set("text-align", "center");

            H4 spnDescription = new H4(film.getOverview());
            spnDescription.getStyle().set("font-weight", "400");
            spnDescription.getStyle().set("margin", "0");
            spnDescription.getStyle().set("width", "100%");
            spnDescription.getStyle().set("text-align", "center");


            DecimalFormat valueFormat = new DecimalFormat("###,###,###,##0.00");
            var formattedValue = valueFormat.format(film.getPrice());
            Span spnDate = new Span("Data de lançamento: " + buildFormattedDateString(film.getRelease_date()));
            Span spnPrice = new Span("R$ " + formattedValue);
            spnPrice.getStyle().set("font-size", "20px");

            if (films.indexOf(film) % 2 == 0) {
                film.setSession("20H");
            } else {
                film.setSession("22H");
            }

            Span spnSession = new Span("Horários: " + film.getSession());

            ButtonCustom btnSee = new ButtonCustom("Comprar ingresso");

            createButtonSee(btnSee, film);

            div.add(
                    spnName,
                    spnDescription,
                    spnPrice,
                    spnSession,
                    spnDate,
                    btnSee
            );

            add(div);
        });
    }

    private void createButtonSee(ButtonCustom btnSee, FilmTmdb film) {
        btnSee.addClickListener(e -> {
            dlgBuyTicket = new DialogCustom("Selecionar assento", "Cancelar", "Confirmar");
            dlgBuyTicket.setSizeFull();
            dlgBuyTicket.open();

            SelectSeatView selectSeatView = new SelectSeatView(filmService, film, dlgBuyTicket);

            dlgBuyTicket.add(selectSeatView);
        });
    }

}
