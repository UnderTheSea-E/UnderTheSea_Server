package com.example.UnderTheSea_Server.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quiz_id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer;

    @Column(nullable = false)
    private String option;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updated_at;
}
