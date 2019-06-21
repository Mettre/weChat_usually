package com.mettre.usually.pojoVM;

import com.mettre.usually.enum_.StateEnum;
import com.mettre.usually.pojo.BasePage;
import lombok.Data;

@Data
public class FeedbackSearchVM extends BasePage {

    private StateEnum state;

    public FeedbackSearchVM() {
    }

    public FeedbackSearchVM(StateEnum state) {
        this.state = state;
    }
}
