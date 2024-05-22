package com.example.application.backend.document;

import com.example.application.backend.model.UserFilm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "MovieSeat")
public class MovieSeatDocument {

    @Id
    private String id;
    private List<UserFilm> seats;
    @Indexed(unique = true, background = true, sparse = true)
    private String filmId;
    private String filmName;
}
