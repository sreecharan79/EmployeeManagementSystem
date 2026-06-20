package com.example.ems.dto;

import java.util.List;
import java.util.Map;

public class DashboardSummary {
    private long totalEmployees;
    private long presentToday;
    private long onLeave;
    private List<Map<String, Object>> recentActivities;

    public DashboardSummary() {
    }

    public DashboardSummary(long totalEmployees, long presentToday, long onLeave, List<Map<String, Object>> recentActivities) {
        this.totalEmployees = totalEmployees;
        this.presentToday = presentToday;
        this.onLeave = onLeave;
        this.recentActivities = recentActivities;
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public long getPresentToday() {
        return presentToday;
    }

    public void setPresentToday(long presentToday) {
        this.presentToday = presentToday;
    }

    public long getOnLeave() {
        return onLeave;
    }

    public void setOnLeave(long onLeave) {
        this.onLeave = onLeave;
    }

    public List<Map<String, Object>> getRecentActivities() {
        return recentActivities;
    }

    public void setRecentActivities(List<Map<String, Object>> recentActivities) {
        this.recentActivities = recentActivities;
    }
}
