
package com.example.UnderTheSea_Server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plan_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private Friend friend;

    @Column(name = "recommend_id", nullable = true)
    private Long recommend ;

    @Column(nullable = false)
    private String content;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVE'")
    private PlanStatus status;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updated_at;
}
