package com.example.application.views;

import com.example.application.backend.document.FilmDocument;
import com.example.application.backend.service.FilmService;
import com.example.application.components.button.ButtonCustom;
import com.example.application.components.dialog.DialogConfirm;
import com.example.application.components.dialog.DialogCustom;
import com.example.application.components.notification.NotificationCustom;
import com.example.application.views.film.RegisterFilmView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import static com.example.application.backend.util.GlobalUtil.buildFormattedDateString;

@PageTitle("Admin")
@Route(value = "admin/:code?", layout = MainLayout.class)
@RouteAlias(value = "admin/:code?", layout = MainLayout.class)
public class AdminView extends VerticalLayout implements BeforeEnterObserver {
    private FilmService filmService;

    private ButtonCustom btnNew = new ButtonCustom("Cadastrar");
    private DialogCustom dialogCustom;

    public AdminView(
            FilmService filmService
    ) {
        this.filmService = filmService;

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void createButtonMenu() {
        btnNew.addClickListener(e -> {
            createDialogNew();
        });
        add(btnNew);
    }

    private void createDialogNew() {
        dialogCustom = new DialogCustom("Novo filme", "Cancelar", "Salvar");
        RegisterFilmView registerFilmView = new RegisterFilmView(
                new FilmDocument(),
                dialogCustom,
                filmService
        );

        registerFilmView.btnFakeClick.addClickListener(e -> {
            resetView();
        });

        dialogCustom.add(registerFilmView);
        dialogCustom.open();
    }

    private void createCards() {
        var films = filmService.findAllFilmsByActiveTrue();
        films.forEach(film -> {
            VerticalLayout div = new VerticalLayout();
            div.setSpacing(false);
            div.getStyle().set("border", "2px solid #9e9e9e");
            div.getStyle().set("border-radius", "5px");
            div.getStyle().set("margin-bottom", "10px");
            div.getStyle().set("margin-bottom", "10px");

            Span spnName = new Span("Nome: " + film.getName());
            Span spnDescription = new Span("Descrição: " + film.getDescription());
            Span spnDuration = new Span("Duração: " + film.getDuration());
            Span spnSession = new Span("Sessão: " + film.getSession());
            Span spnRoom = new Span("Salas: " + film.getRoom());
            Span spnInitDate = new Span("Inicio cartaz: " + buildFormattedDateString(film.getInitDate()));
            Span spnFinalDate = new Span("Fim cartaz: " + buildFormattedDateString(film.getFinalDate()));
            Span spnActive = new Span("Status: " + (film.isActive() ? "ATIVO" : "INATIVO"));

            ButtonCustom btnEdit = new ButtonCustom("Editar");
            ButtonCustom btnDelete = new ButtonCustom("Excluir");

            createBtnEdit(btnEdit, film);
            createBtnDelete(btnDelete, film);

            HorizontalLayout hltBtn = new HorizontalLayout();
            hltBtn.setWidthFull();
            hltBtn.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
            hltBtn.setPadding(false);
            hltBtn.getStyle().set("border-top", "2px solid #9e9e9e");
            hltBtn.getStyle().set("margin-top", "15px");
            hltBtn.getStyle().set("padding-top", "15px");
            hltBtn.add(btnEdit, btnDelete);

            div.add(
                    spnName,
                    spnDescription,
                    spnDuration,
                    spnSession,
                    spnRoom,
                    spnInitDate,
                    spnFinalDate,
                    spnActive,
                    hltBtn
            );

            add(div);
        });
    }

    private void createBtnEdit(ButtonCustom btnEdit, FilmDocument filmDocument) {
        btnEdit.addClickListener(e -> {
            createDialogEdit(filmDocument);
        });
    }

    private void createDialogEdit(FilmDocument filmDocument) {
        dialogCustom = new DialogCustom("Novo filme", "Cancelar", "Salvar");
        dialogCustom.btnCancel.addClickListener(e -> dialogCustom.close());
        RegisterFilmView registerFilmView = new RegisterFilmView(
                filmDocument,
                dialogCustom,
                filmService
        );

        registerFilmView.btnFakeClick.addClickListener(e -> {
            resetView();
        });

        dialogCustom.add(registerFilmView);
        dialogCustom.open();
    }

    private void createBtnDelete(ButtonCustom btnDelete, FilmDocument filmDocument) {
        btnDelete.addClickListener(e -> {
            DialogConfirm dlgConfirm = new DialogConfirm("Excluir registro", "Deseja excluir o filme " + filmDocument.getName() + "?", true);
            dlgConfirm.open();
            dlgConfirm.getButtonConfirm().addClickListener(f -> {
                filmService.deleteFilm(filmDocument.getId());
                resetView();
                dlgConfirm.close();
                NotificationCustom.showSuccess("Filme excluido com sucesso");
            });
        });

    }

    private void resetView() {
        removeAll();
        add(btnNew);
        createCards();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        var code = event.getRouteParameters().get("code");

        if(code.isEmpty()) {
            UI.getCurrent().navigate(FilmsView.class);
        } else {
            var pwd = code.get();
            if(pwd.equals("Abc123@@")) {
                createButtonMenu();
                createCards();
            }
        }

    }
}
