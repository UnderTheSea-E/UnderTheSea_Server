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
@Table(name = "Purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchase_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
//    @Column(nullable = false)
    private User user;

    @Column(nullable = false)
    private String product;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'UNDECIDED'")
    private PurchaseSatisfaction satisfaction;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'UNDECIDED'")
    private PurchaseStatus status;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updated_at;
}
