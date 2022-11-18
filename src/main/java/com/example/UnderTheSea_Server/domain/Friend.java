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
@Table(name = "Friend")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friend_id;

    /*
    User Entity 생성 후 주석 풀기
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "my_id")
    @Column(nullable = false)
    private User user;
     */

    /*
    User Entity 생성 후 주석 풀기
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "your_id")
    @Column(nullable = false)
    private User user;
     */

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updated_at;
}
