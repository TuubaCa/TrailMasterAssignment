package com.trailmasterassignment;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RunManager {

    private final AtomicInteger idCounter = new AtomicInteger(1);
    private final HashMap<Integer, TrailRun> runIdentifier = new HashMap<>();

    /**
     * Lägger till en ny TrailRun och genererar ett unikt ID.
     * @param run TrailRun-objekt som ska läggas till.
     */
    public int addRunIdentifier(TrailRun run) {
        int uniqueId = idCounter.getAndIncrement();
        runIdentifier.put(uniqueId, run);
        return uniqueId;
    }

    /**
     * Hämtar en TrailRun baserat på dess ID.
     * @param id ID för TrailRun som ska hämtas.
     * @return TrailRun-objektet om det finns, annars null.
     */
    public TrailRun getRunIdentifier(int id) {
        return runIdentifier.get(id);
    }

    /**
     * Tar bort en TrailRun baserat på dess ID.
     * @param id ID för TrailRun som ska tas bort.
     */
    public void deleteRunIdentifier(int id) {
        runIdentifier.remove(id);
    }

    /**
     * Kontrollerar om det finns några sparade sessioner.
     * @return true om inga sessioner finns, annars false.
     */
    public boolean isEmpty() {
        return runIdentifier.isEmpty();
    }

    /**
     * Beräknar antal dagar sedan den senaste TrailRun.
     * @return Antal dagar sedan senaste sessionen.
     */
    public int daysSinceLastRun() {
        if (isEmpty()) {
            return 0;
        }

        Date today = new Date();
        return runIdentifier.values().stream()
                .mapToInt(run -> calculateDaysBetween(run.getDate(), today))
                .min()
                .orElse(0);
    }

    /**
     * Hämtar den senaste TrailRun baserat på datum.
     * @return Den senaste TrailRun.
     */
    public TrailRun getLatestRunIdentifier() {
        if (isEmpty()) {
            return null;
        }

        return runIdentifier.values().stream()
                .max((r1, r2) -> r1.getDate().compareTo(r2.getDate()))
                .orElse(null);
    }

    /**
     * Beräknar den totala distansen för alla sparade TrailRun.
     * @return Total distans i kilometer.
     */
    public double getTotalDistance() {
        return runIdentifier.values().stream()
                .mapToDouble(TrailRun::getDistance)
                .sum();
    }

    /**
     * Beräknar medeldistansen för alla sparade TrailRun.
     * @return Medeldistansen i kilometer.
     */
    public double getAverageDistance() {
        if (isEmpty()) {
            return 0.0;
        }
        return getTotalDistance() / runIdentifier.size();
    }

    /**
     * Returnerar antalet sparade TrailRun.
     * @return Antal sessioner.
     */
    public int getNumberOfRuns() {
        return runIdentifier.size();
    }

    /**
     * Returnerar en uppsättning av alla ID:n för sparade TrailRun.
     * @return En uppsättning av ID:n.
     */
    public Set<Integer> getSetOfIds() {
        return runIdentifier.keySet();
    }

    /**
     * Hjälpmetod för att beräkna antal dagar mellan två datum.
     * @param start Startdatum.
     * @param end Slutdatum.
     * @return Antal dagar mellan start och slut.
     */
    private int calculateDaysBetween(Date start, Date end) {
        long diffInMillies = Math.abs(end.getTime() - start.getTime());
        return (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
