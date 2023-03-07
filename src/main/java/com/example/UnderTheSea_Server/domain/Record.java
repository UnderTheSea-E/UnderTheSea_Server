package com.example.UnderTheSea_Server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long record_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(nullable = true)
    private String content;

    @Column(nullable = true)
    private Integer satisfaction;

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