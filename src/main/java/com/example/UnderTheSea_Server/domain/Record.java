package com.example.UnderTheSea_Server.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long record_id;

    @Column(nullable = true)
    private String content;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'UNSATISFIED'")
    private RecordSatisfaction satisfaction;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVE'")
    private RecordStatus status;

    @Column(nullable = true)
    private String img_url;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updated_at;
}