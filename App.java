package org.TicketBooking;

 // Import the Train class

import org.TicketBooking.Service.TrainService;
import org.TicketBooking.Service.Train;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        System.out.println("Train Booking System");
        Scanner scanner = new Scanner(System.in);

        // Initialize TrainService
        TrainService trainService = new TrainService();

        // Get user input for source and destination stations
        System.out.print("Enter source station code or name: ");
        String source = scanner.nextLine();
        System.out.print("Enter destination station code or name: ");
        String destination = scanner.nextLine();

        // Fetch trains
        List<Train> trains = trainService.searchTrains(source, destination);

        // Display the list of available trains
        if (trains.isEmpty()) {
            System.out.println("No trains found between " + source + " and " + destination);
        } else {
            System.out.println("Trains between " + source + " and " + destination + ":");
            for (Train train : trains) {
                System.out.println(train);
            }
        }

        // Close the TrainService client
        //trainService.close();
    }
}
