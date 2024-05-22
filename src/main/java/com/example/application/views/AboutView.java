package com.example.application.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Sobre")
@Route(value = "sobre", layout = MainLayout.class)
public class AboutView extends VerticalLayout {
    private static final String TITLE = "Bem-vindo ao Cine-X: Sua Experiência Cinematográfica Inigualável";
    private static final String TITLE2 = "Nossas Salas de Cinema: Uma Viagem ao Coração da Ação";
    private static final String TITLE3 = "Variedade de Experiências Cinematográficas";
    private static final String TITLE4 = "Programação Diversificada: De Blockbusters a Filmes Independentes";
    private static final String TITLE5 = "Cine-X VIP: Uma Experiência Premium";
    private static final String TITLE6 = "Aplicativo Cine-X: Seu Portal para o Mundo do Cinema";

    public AboutView() {
        setSpacing(false);

        add(new H2(TITLE));
        add(new Paragraph("No Cine-X, acreditamos que o cinema não é apenas uma forma de entretenimento; é uma jornada emocionante e imersiva que transporta você para diferentes mundos. Com tecnologia de ponta, uma variedade de opções de exibição e um compromisso inabalável com a qualidade, o Cine-X é o seu destino cinematográfico definitivo."));
        add(new H2(TITLE2));
        add(new Paragraph("No Cine-X, estamos empenhados em oferecer uma experiência visual e sonora excepcional. Nossas salas de cinema são equipadas com tecnologia de última geração, incluindo projeção de alta resolução, som surround imersivo e assentos confortáveis. Cada sala é projetada para transportar você para o centro da ação, proporcionando uma imersão total nos filmes que você ama."));
        add(new H2(TITLE3));
        add(new Paragraph("No Cine-X, entendemos que cada espectador é único, e é por isso que oferecemos uma variedade de experiências cinematográficas. Seja em 2D, 3D ou até mesmo em formato IMAX, temos opções para todos os gostos. Além disso, nosso sistema de projeção avançado garante uma qualidade de imagem excepcional, independentemente do formato escolhido."));
        add(new H2(TITLE4));
        add(new Paragraph("No Cine-X, celebramos a diversidade cinematográfica. Nossa programação inclui uma seleção abrangente de filmes, desde os mais recentes blockbusters de Hollywood até joias cinematográficas independentes. Quer você seja um fã de ação, drama, comédia ou suspense, temos algo especial reservado para você."));
        add(new H2(TITLE5));
        add(new Paragraph("O Cine-X não se limita às quatro paredes do cinema. Com nosso aplicativo dedicado, você pode explorar a programação, comprar ingressos, reservar assentos e até mesmo receber recomendações personalizadas com base em suas preferências cinematográficas. Leve a magia do cinema para onde quer que vá com o aplicativo Cine-X."));
        add(new H2(TITLE6));
        add(new Paragraph("Para os verdadeiros entusiastas do cinema, oferecemos o Cine-X VIP. Com assentos luxuosos, serviço de catering exclusivo e acesso prioritário, o Cine-X VIP eleva sua experiência cinematográfica a um patamar superior. Desfrute de um tratamento digno das estrelas enquanto mergulha nos filmes mais recentes em grande estilo."));
        add(new Paragraph("No Cine-X, nossa missão é proporcionar a você uma experiência cinematográfica inigualável. Junte-se a nós enquanto celebramos a magia do cinema e criamos memórias duradouras em cada projeção. Prepare-se para uma jornada cinematográfica emocionante no Cine-X, onde a magia do cinema ganha vida!"));

        setSizeFull();
//        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
