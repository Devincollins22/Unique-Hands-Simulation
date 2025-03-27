package edu.canisius.csc213.project1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UniqueHands {

    public static void main(String[] args) {
        int[] deckSizes = {24, 28};
        int[] handSizes = {6, 7};
        int trials = 5;
        String fileName = "unique_hands_results.csv";

        try (FileWriter writer = new FileWriter(fileName)) {
            // Write CSV header
            writer.write("Deck Size,Hand Size,Trial,Attempts,Time (sec)\n");

            for (int deckSize : deckSizes) {
                for (int handSize : handSizes) {
                    for (int i = 1; i <= trials; i++) {
                        long startTime = System.nanoTime();
                        int attempts = countAttemptsToSeeAllHands(deckSize, handSize);
                        long endTime = System.nanoTime();

                        double elapsedTime = (endTime - startTime) / 1e9;

                        // Write trial results to CSV
                        writer.write(String.format("%d,%d,%d,%d,%.3f\n", deckSize, handSize, i, attempts, elapsedTime));

                        // Print to console as well
                        System.out.printf("Deck Size: %d | Hand Size: %d | Trial %d | Attempts: %,d | Time: %.3f sec\n",
                                deckSize, handSize, i, attempts, elapsedTime);
                    }
                }
            }

            System.out.println("Results saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static int countAttemptsToSeeAllHands(int deckSize, int handSize) {
        Set<String> uniqueHands = new HashSet<>();
        int attempts = 0;
        int totalUniqueHands = calculateTotalUniqueHands(deckSize, handSize);

        System.out.println("Deck Simulation: How long to see every possible hand?");
        System.out.println("------------------------------------------------------");

        while (uniqueHands.size() < totalUniqueHands) {
            Deck deck = new Deck(deckSize);
            deck.shuffle();

            List<Card> hand = new ArrayList<>();
            for (int i = 0; i < handSize; i++) {
                hand.add(deck.draw());
            }

            String normalized = normalizeHand(hand);
            uniqueHands.add(normalized);
            attempts++;

            if (attempts % 100_000 == 0) {
                int needed = totalUniqueHands - uniqueHands.size();
                double coverage = (100.0 * uniqueHands.size()) / totalUniqueHands;
                System.out.printf("Progress: %.2f%% coverage after %,d attempts (Unique Hands: %,d / %,d | Needed: %,d)\n",
                        coverage, attempts, uniqueHands.size(), totalUniqueHands, needed);
            }

            //if (attempts > 2_000_000) break;
        }

        System.out.printf("100.00%% coverage reached after %,d attempts (Unique Hands: %,d / %,d | Needed: 0)\n",
                attempts, uniqueHands.size(), totalUniqueHands);

        return attempts;
    }

    private static int calculateTotalUniqueHands(int deckSize, int handSize) {
        return (int) combinations(deckSize, handSize);
    }

    private static long combinations(int n, int k) {
        if (k > n) return 0;
        long result = 1;
        for (int i = 1; i <= k; i++) {
            result = result * (n - i + 1) / i;
        }
        return result;
    }

    private static String normalizeHand(List<Card> hand) {
        hand.sort(Comparator.comparing(Card::getRank).thenComparing(Card::getSuit));
        StringBuilder sb = new StringBuilder();
        for (Card card : hand) {
            sb.append(card.toString()).append(";");
        }
        return sb.toString();
    }
}