package com.trailmasterassignment;

import java.text.DecimalFormat;

/**
 * Denna klass representerar användarens information och fitness-data.
 * Den integrerar med RunManager för att hantera löpsessioner.
 */
public class User {

    private float height; // Längd i cm
    private float weight; // Vikt i kg
    private int age; // Ålder

    private final RunManager runManager; // Hanterar löpsessioner
    private double fitnessScore; // Fitnesspoäng

    /**
     * Skapar en ny användare med grundläggande information.
     * @param height Längd i cm
     * @param weight Vikt i kg
     * @param age Ålder
     */
    public User(float height, float weight, int age) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.fitnessScore = 0.0;
        this.runManager = new RunManager();
    }

    // Getter och Setter-metoder

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Beräknar och returnerar användarens fitnesspoäng.
     * @return Fitnesspoäng som en formaterad sträng.
     */
    public String calculateFitnessScore() {
        if (runManager.isEmpty()) {
            return "0.00";
        }

        TrailRun latestRun = getLatestRun();
        if (latestRun == null) {
            return "0.00";
        }

        fitnessScore = Math.max(
            fitnessScore + (latestRun.getDistance() + latestRun.getAverageSpeedPerHour() / latestRun.getMinutesPerKilometer()) - (daysSinceLastRun() / 2.0),
            0.0
        );

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(fitnessScore);
    }

    /**
     * Returnerar antal sparade löpsessioner.
     * @return Antal sessioner.
     */
    public int getRunCount() {
        return runManager.getNumberOfRuns();
    }

    /**
     * Beräknar antal dagar sedan senaste löpsession.
     * @return Antal dagar.
     */
    public int daysSinceLastRun() {
        return runManager.daysSinceLastRun();
    }

    /**
     * Hämtar den senaste löpsessionen.
     * @return Den senaste TrailRun eller null om inga finns.
     */
    private TrailRun getLatestRun() {
        return runManager.getLatestRunIdentifier();
    }

    /**
     * Lägger till en ny löpsession.
     * @param newRun Ny TrailRun att lägga till.
     */
    public void addRun(TrailRun newRun) {
        runManager.addRunIdentifier(newRun);
    }

    /**
     * Returnerar den totala distansen för alla löpsessioner.
     * @return Total distans i kilometer.
     */
    public double getTotalDistance() {
        return runManager.getTotalDistance();
    }

    /**
     * Returnerar medeldistansen för alla löpsessioner.
     * @return Medeldistans i kilometer.
     */
    public double getAverageDistance() {
        return runManager.getAverageDistance();
    }
}