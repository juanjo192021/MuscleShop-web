package com.muscleshop.web.models;

public class PreferenceResponse {
    private String preferenceId;

    public PreferenceResponse(String preferenceId) {
        this.preferenceId = preferenceId;
    }

    public String getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(String preferenceId) {
        this.preferenceId = preferenceId;
    }
}
