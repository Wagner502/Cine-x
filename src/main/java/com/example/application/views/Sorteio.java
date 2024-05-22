package com.example.application.views;

//@PageTitle("Sorteio")
//@Route(value = "sorteio")
//@RouteAlias(value = "sorteio")
//public class Sorteio extends VerticalLayout {
//
//    Span spn = new Span();
//    Span spnGanhador = new Span("VENCEDOR(A)");
//    Span spnMarcou = new Span();
//    Anchor verPerfil = new Anchor();
//
//    public Sorteio() {
//        Span spnTitle = new Span("SORTEIO");
//        spnTitle.getStyle().set("font-weight", "bold");
//        spnTitle.getStyle().set("font-size", "150px");
//        getStyle().set("align-items", "center");
//
//        add(spnTitle);
//
//        HorizontalLayout hltLink = new HorizontalLayout();
//        hltLink.setWidthFull();
//        TextField tfdLink = new TextField("Link instagram");
//        tfdLink.setWidth("var(--vaadin-field-default-width, 30em)");
//        Span spnComment = new Span();
//
//        ButtonCustom btnCarregar = new ButtonCustom("CARREGAR");
//        btnCarregar.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
//        btnCarregar.addClickListener(e -> {
//            spnComment.setText("274 comentários carregados.");
//        });
//
//        hltLink.add(tfdLink, btnCarregar);
//        hltLink.setJustifyContentMode(JustifyContentMode.CENTER);
//        hltLink.setAlignItems(Alignment.BASELINE);
//
//        add(hltLink, spnComment);
//
//        ButtonCustom btnConfig = new ButtonCustom("SORTEAR");
//        btnConfig.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
//        btnConfig.setWidth("14em");
//        btnConfig.setHeight("3em");
//        add(btnConfig);
//        add(spnGanhador, spn, spnMarcou, verPerfil);
//        spnGanhador.setVisible(false);
//        spn.setVisible(false);
//        spnMarcou.setVisible(false);
//        verPerfil.setVisible(false);
//        btnConfig.addClickListener(e -> {
//            int vencedor = 0;
//            for (int i = 0; i < 10; i++) {
//                Random r = new Random();
//                int low = 1;
//                int high = 266;
//                int result = r.nextInt(high - low) + low;
//
//                if (i == 9) {
//                    vencedor = result;
//                }
//            }
//
//            createHtml(vencedor);
//
//        });
//
//    }
//
//    private void createHtml(int number) {
//        try {
//            ClassLoader classLoader = getClass().getClassLoader();
//            URL url = classLoader.getResource("listaNomes.txt");
//            Path path = Paths.get(url.toURI());
//            List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
//
//
//            spnGanhador.getStyle().set("font-weight", "bold");
//            spnGanhador.getStyle().set("font-size", "30px");
//
////            for (String line : allLines) {
//            var line = allLines.get(number);
//            var broke = line.split("--");
//            spn.setText("Comentário " + allLines.indexOf(line) + " - " + broke[0]);
//            verPerfil.setHref("https://instagram.com/" + broke[0]);
//            verPerfil.setText("Ver perfil @" + broke[0]);
//            verPerfil.setTarget("_blank");
//
//            String marcou = "";
//            for (int i = 0; i < broke.length; i++) {
//                if (i > 0 && i < broke.length - 1) {
//                    marcou = marcou + " " + broke[i];
//                }
//
//            }
//            spnMarcou.setText("Marcou: " + marcou);
//
//            var invalid = marcou.split(" ");
//            if(invalid.length < 4) {
//                spnGanhador.setText("VENCEDOR(A) (INVÁLIDO) - NÃO MARCOU 3 PESSOAS");
//            } else {
//                spnGanhador.setText("VENCEDOR(A)");
//            }
//
//            spnGanhador.setVisible(true);
//            spn.setVisible(true);
//            spnMarcou.setVisible(true);
//            verPerfil.setVisible(true);
////            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
