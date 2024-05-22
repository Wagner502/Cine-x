package com.example.application.backend.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Film")
public class FilmDocument {
    @Id
    private String id;
    private String name;
    private String description;
    private List<String> urlImage;
    private int duration;
    private LocalDate initDate;
    private LocalDate finalDate;
    private boolean active;
    private Integer price;
    private String session;
    private String room;
}
