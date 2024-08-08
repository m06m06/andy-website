package com.AndyChu.DevJourney.util;

import java.util.List;
import java.util.Map;

public class ValidationResult {
    private Map<String, String> errorMsgs;
    private List<String> validatorMsg;

    public ValidationResult(Map<String, String> errorMsgs, List<String> validatorMsg) {
        this.errorMsgs = errorMsgs;
        this.validatorMsg = validatorMsg;
    }

    public Map<String, String> getErrorMsgs() {
        return errorMsgs;
    }

    public void setErrorMsgs(Map<String, String> errorMsgs) {
        this.errorMsgs = errorMsgs;
    }

    public List<String> getValidatorMsg() {
        return validatorMsg;
    }

    public void setValidatorMsg(List<String> validatorMsg) {
        this.validatorMsg = validatorMsg;
    }

    public boolean hasErrors() {
        return !errorMsgs.isEmpty();
    }
}

