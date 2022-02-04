package com.robinfood.models.feedback;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "FBCK_CustomerFeedback")
public class CustomerFeedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String description;

    @OneToMany(mappedBy = "customerFeedback", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<FeedbackQuestion> questions;
}
