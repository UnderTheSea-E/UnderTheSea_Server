package com.example.UnderTheSea_Server.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@Table(name = "Place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "place_id")
    private Long placeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String position;

    public Place(String name, String position) {
    }
}
