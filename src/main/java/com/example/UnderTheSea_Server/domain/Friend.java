package com.example.UnderTheSea_Server.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Friend")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friend_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "my_id")
    private User user1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "your_id")
    private User user2;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVE'")
    private UserStatus status;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updated_at;
}
