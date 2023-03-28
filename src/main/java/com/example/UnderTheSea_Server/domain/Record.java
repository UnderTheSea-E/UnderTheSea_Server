package com.example.UnderTheSea_Server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Builder
@Setter
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
    private int satisfaction;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVE'")
    private RecordStatus status;

    @Column(nullable = true)
    private String img_url;

    @Column(nullable = false)
    private LocalDate date;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updated_at;

    public Record(Long record_id, User user, String content, int satisfaction, RecordStatus status, String img_url, LocalDate date, Date created_at, Date updated_at) {
        this.record_id = record_id;
        this.user = user;
        this.content = content;
        this.satisfaction = satisfaction;
        this.status = status;
        this.img_url = img_url;
        this.date = date;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}