package com.mettre.usually.pojoVM;

import com.mettre.usually.enum_.StateEnum;
import com.mettre.usually.pojo.BasePage;
import lombok.Data;

@Data
public class FeedbackSearchVM extends BasePage {

    private String userId;

    private StateEnum state;

    private Long feedbackId;
}
