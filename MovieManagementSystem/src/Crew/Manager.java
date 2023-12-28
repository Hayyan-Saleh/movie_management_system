package Crew;

import Domain.Day;

import java.io.Serializable;

public class Manager implements Serializable {
    private String managerName,managerPassword;
    private double managerIncome,discountAmount;
    private Day managerDiscountDay;

    public Manager(String managerName, String managerPassword) {
        this.discountAmount=0;
        this.managerIncome=0;
        this.managerDiscountDay=null;
        this.managerName = managerName;
        this.managerPassword = managerPassword;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public double getManagerIncome() {
        return managerIncome;
    }

    public void setManagerIncome(double managerIncome) {
        this.managerIncome = managerIncome;
    }

    public Day getManagerDiscountDay() {
        return managerDiscountDay;
    }

    public void setManagerDiscountDay(Day managerDiscountDay) {
        this.managerDiscountDay = managerDiscountDay;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }
    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }
}
