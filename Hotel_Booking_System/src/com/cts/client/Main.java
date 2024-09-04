package com.cts.client;

import com.cts.model.Booking;
import com.cts.model.Customer;
import com.cts.model.Room;
import com.cts.service.BookingServices;
import com.cts.service.CustomerServices;
import com.cts.service.RoomServices;
import com.cts.util.CustomException;
import com.cts.util.NotAvailableException;
import com.cts.util.NotFoundException;

import java.sql.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\n---Welcome to Hotel Booking System---");

            while (true) {
                System.out.println("\nHotel Booking System Menu:");
                System.out.println("--------------------------");
                System.out.println("1. Room Management");
                System.out.println("2. Customer Management");
                System.out.println("3. Booking Management");
                System.out.println("0. Exit");
                System.out.print("\nEnter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> manageRooms(sc);
                    case 2 -> manageCustomers(sc);
                    case 3 -> manageBookings(sc);
                    case 0 -> {
                        System.out.println("\nExiting the system. Goodbye!");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("\nInvalid choice! Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("\n" + e.getMessage());
        }
    } // End of main method

    private static void manageRooms(Scanner sc) {
        RoomServices roomServices = new RoomServices();
        CustomException customException = new CustomException();

        try {
            while (true) {
                System.out.println("\nRoom Management Menu:");
                System.out.println("---------------------");
                System.out.println("1. Add a New Room");
                System.out.println("2. View Room Details");
                System.out.println("3. View All Rooms");
                System.out.println("4. Update Room Details");
                System.out.println("5. Delete a Room");
                System.out.println("0. Back to Main Menu");
                System.out.print("\nEnter your choice: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.print("\nEnter room number: ");
                        String roomNumber = sc.next();
                        System.out.print("Enter room type: ");
                        String type = sc.nextLine();
                        type += sc.nextLine();
                        System.out.print("Enter room price(Rs.): ");
                        double price = sc.nextDouble();
                        System.out.print("Enter room status(available/booked): ");
                        String status = sc.next();

                        roomServices.addRoom(new Room(roomNumber, type, price, status));
                    }
                    case 2 -> {
                        System.out.print("\nEnter room Id: ");
                        int roomId = sc.nextInt();
                        boolean roomFound = customException.roomExists(roomId);
                        if(roomFound) {
                            roomServices.viewRoomDetails(roomId);
                        } else {
                            throw new NotFoundException("\nRoom not found!");
                        }
                    }
                    case 3 -> roomServices.viewAllRooms();
                    case 4 -> {
                        System.out.print("\nEnter room Id to update: ");
                        int roomId = sc.nextInt();
                        boolean roomFound = customException.roomExists(roomId);
                        if(roomFound) {
                            roomServices.viewRoomDetails(roomId);

                            System.out.print("\nEnter new room number: ");
                            String roomNumber = sc.next();
                            System.out.print("Enter new room type: ");
                            String type = sc.nextLine();
                            type += sc.nextLine();
                            System.out.print("Enter new room price(Rs.): ");
                            double price = sc.nextDouble();
                            System.out.print("Enter new room status(available/booked): ");
                            String status = sc.next();

                            roomServices.updateRoom(new Room(roomNumber, type, price, status), roomId);
                        } else {
                            throw new NotFoundException("\nRoom not found!");
                        }
                    }
                    case 5 -> {
                        System.out.print("\nEnter room Id to delete: ");
                        int roomId = sc.nextInt();
                        boolean roomFound = customException.roomExists(roomId);
                        if(roomFound) {
                            roomServices.deleteRoom(roomId);
                        } else {
                            throw new NotFoundException("\nRoom not found!");
                        }
                    }
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("\nInvalid choice! Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }  // End of manageRooms method

    private static void manageCustomers(Scanner sc) {
        CustomerServices customerServices = new CustomerServices();
        CustomException customException = new CustomException();

        try {
            while (true) {
                System.out.println("\nCustomer Management Menu:");
                System.out.println("---------------------");
                System.out.println("1. Add a New Customer");
                System.out.println("2. View Customer Details");
                System.out.println("3. View All Customers");
                System.out.println("4. Update Customer Details");
                System.out.println("5. Remove a Customer");
                System.out.println("0. Back to Main Menu");
                System.out.print("\nEnter your choice: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.print("\nEnter name: ");
                        String name = sc.nextLine();
                        name += sc.nextLine();
                        System.out.print("Enter email: ");
                        String email = sc.next();
                        System.out.print("Enter phone number: ");
                        String phoneNumber = sc.next();

                        customerServices.addCustomer(new Customer(name, email, phoneNumber));
                    }
                    case 2 -> {
                        System.out.print("\nEnter customer Id: ");
                        int customerId = sc.nextInt();
                        boolean customerFound = customException.customerExists(customerId);
                        if(customerFound) {
                            customerServices.viewCustomerDetails(customerId);
                        } else {
                            throw new NotFoundException("\nCustomer not found!");
                        }
                    }
                    case 3 -> customerServices.viewAllCustomers();
                    case 4 -> {
                        System.out.print("\nEnter customer Id to update: ");
                        int customerId = sc.nextInt();
                        boolean customerFound = customException.customerExists(customerId);
                        if(customerFound) {
                            customerServices.viewCustomerDetails(customerId);

                            System.out.print("\nEnter new name: ");
                            String name = sc.nextLine();
                            name += sc.nextLine();
                            System.out.print("Enter new email: ");
                            String email = sc.next();
                            System.out.print("Enter new phone number: ");
                            String phoneNumber = sc.next();

                            customerServices.updateCustomer(new Customer(name, email, phoneNumber), customerId);
                        } else {
                            throw new NotFoundException("\nCustomer not found!");
                        }
                    }
                    case 5 -> {
                        System.out.print("\nEnter customer Id to remove: ");
                        int customerId = sc.nextInt();
                        boolean customerFound = customException.customerExists(customerId);
                        if(customerFound) {
                            customerServices.deleteCustomer(customerId);
                        } else {
                            throw new NotFoundException("\nCustomer not found!");
                        }
                    }
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("\nInvalid choice! Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }  // End of manageCustomers method

    private static void manageBookings(Scanner sc) {
        BookingServices bookingServices = new BookingServices();
        CustomException customException = new CustomException();

        try {
            while (true) {
                System.out.println("\nBooking Management Menu:");
                System.out.println("------------------------");
                System.out.println("1. Add a New Booking");
                System.out.println("2. View Booking Details");
                System.out.println("3. View All Bookings");
                System.out.println("4. Cancel a Booking");
                System.out.println("0. Back to Main Menu");
                System.out.print("\nEnter your choice: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.print("\nEnter room Id: ");
                        int roomId = sc.nextInt();
                        boolean roomFound = customException.roomExists(roomId);
                        if(!roomFound) {
                            throw new NotFoundException("\nRoom not found!");
                        }
                        boolean roomAvailable = customException.roomAvailable(roomId);
                        if(!roomAvailable) {
                            throw new NotAvailableException("\nRoom not available!");
                        }
                        System.out.print("Enter customer Id: ");
                        int customerId = sc.nextInt();
                        boolean customerFound = customException.customerExists(customerId);
                        if(!customerFound) {
                            throw new NotFoundException("\nCustomer not found!");
                        }
                        System.out.print("Enter check-in date(YYYY-MM-DD): ");
                        Date checkInDate = Date.valueOf(sc.next());
                        System.out.print("Enter check-out date(YYYY-MM-DD): ");
                        Date checkOutDate = Date.valueOf(sc.next());

                        bookingServices.addBooking(new Booking(roomId, customerId, checkInDate, checkOutDate));
                    }
                    case 2 -> {
                        System.out.print("\nEnter booking Id: ");
                        int bookingId = sc.nextInt();
                        boolean bookingFound = customException.bookingExists(bookingId);
                        if(bookingFound) {
                            bookingServices.viewBookingDetails(bookingId);
                        } else {
                            throw new NotFoundException("\nBooking not found!");
                        }
                    }
                    case 3 -> bookingServices.viewAllBookings();
                    case 4 -> {
                        System.out.print("\nEnter booking Id to cancel: ");
                        int bookingId = sc.nextInt();
                        boolean bookingFound = customException.bookingExists(bookingId);
                        if(bookingFound) {
                            bookingServices.deleteBooking(bookingId);
                        } else {
                            throw new NotFoundException("\nBooking not found!");
                        }
                    }
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("\nInvalid choice! Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    } // End of manageBookings method
}
