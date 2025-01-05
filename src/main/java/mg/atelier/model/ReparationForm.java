package mg.atelier.model;

import java.time.LocalDateTime;
import java.util.List;

public class ReparationForm {

    private List<ComputerComponent> components;
    private List<Integer> reparations;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double totalAmount;

    // Getters and Setters
    public List<ComputerComponent> getComponents() {
        return components;
    }

    public void setComponents(List<ComputerComponent> components) {
        this.components = components;
    }

    public List<Integer> getReparations() {
        return reparations;
    }

    public void setReparations(List<Integer> reparations) {
        this.reparations = reparations;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}