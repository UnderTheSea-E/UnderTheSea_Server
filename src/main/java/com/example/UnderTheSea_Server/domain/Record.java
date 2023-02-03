package com.example.UnderTheSea_Server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Builder
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

    @Column(nullable = true)
    private Integer satisfaction;

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

    public Record(Long record_id, String content, int satisfaction, RecordStatus status, String img_url, Date created_at, Date updated_at) {
        this.record_id = record_id;
        this.content = content;
        this.satisfaction = satisfaction;
        this.status = status;
        this.img_url = img_url;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}