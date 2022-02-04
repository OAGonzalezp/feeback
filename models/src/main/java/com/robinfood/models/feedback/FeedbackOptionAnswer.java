package com.robinfood.models.feedback;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "FBCK_FeedbackOptionAnswer")
public class FeedbackOptionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private FeedbackQuestion question;

    @Column(name = "answerDescription", nullable = false)
    private String answerDescription;

}
