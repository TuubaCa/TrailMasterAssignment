
package com.trailmasterassignment;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Denna klass representerar en löpsession (run session).
 * Den innehåller information som distans, tid, datum och session ID.
 */
public class TrailRun {

    private double distance; // Distans (km)
    private int durationInSeconds; // Tid (i sekunder)
    private Date date; // Datum
    private String runIdentifier; // TrailMaster ID

    /**
     * En konstruktör med parametrar. (Constructor with parameters)
     * Användarens data initieras.
     * @param runIdentifier runIdentifier
     * @param distance Distans (km)
     * @param durationInSeconds Tid (i sekunder)
     * @param date Datum (valfritt / optional)
     */
    public TrailRun(String runIdentifier, double distance, int durationInSeconds, Date date) {
        this.runIdentifier = runIdentifier;
        this.distance = distance;
        this.durationInSeconds = durationInSeconds;
        this.date = (date != null) ? date : new Date(); // Om inget datum anges, används dagens datum.
    }

   

    /**
     * Beräknar genomsnittlig hastighet (average speed) i km/h.
     * @return Genomsnittlig hastighet
     */
    public double getAverageSpeedPerHour() {
        double hours = durationInSeconds / 3600.0;
        return distance / hours;
    }

    /**
     * Beräknar tempot per kilometer (pace per kilometer) i formatet minuter:sekunder.
     * @return Tempo per kilometer
     */
    public double getMinutesPerKilometer() {
        double minutes = durationInSeconds / 60.0;
        return minutes / distance;
    }

    // Getter-metoder (Getters)

    public String getRunIdentifier() {
        return runIdentifier;
    }

    public double getDistance() {
        return distance;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public Date getDate() {
        return date;
    }

    /**
     * Returnerar en strängrepresentation av objektet (string representation of the object).
     * @return Formaterad sträng
     */
    @Override
    public String toString() {
        return String.format("Run ID: %s, Distans: %.2f km, Tid: %s, Datum: %s",
                runIdentifier, distance, formatDuration(), date);
    }

    /**
     * Formaterar tid till ett läsbart format (format time to readable format) som timmar:minuter:sekunder.
     * @return Formaterad tid
     */
    private String formatDuration() {
        long hours = TimeUnit.SECONDS.toHours(durationInSeconds);
        long minutes = TimeUnit.SECONDS.toMinutes(durationInSeconds) % 60;
        long seconds = durationInSeconds % 60;
        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    }
}
