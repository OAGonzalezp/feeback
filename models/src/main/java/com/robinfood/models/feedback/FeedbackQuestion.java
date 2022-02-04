package com.robinfood.models.feedback;

import com.robinfood.models.enums.FeedbackQuestionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "FBCK_FeedbackQuestion")
public class FeedbackQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String questionStr;

    @Enumerated(EnumType.STRING)
    private FeedbackQuestionType type;

    @ManyToOne
    private CustomerFeedback customerFeedback;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<FeedbackOptionAnswer> optionAnswers;

}
