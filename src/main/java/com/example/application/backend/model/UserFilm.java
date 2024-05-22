package com.example.application.backend.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserFilm {
    private String document;
    private String name;
    private String seat;
    private String session;
}
