#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_SEATS 40

typedef struct {
    int seatNumber;
    char passengerName[50];
    char passengerContact[15];
} Ticket;

void initializeSeats(Ticket seats[], int size);
void displaySeats(Ticket seats[], int size);
int bookTicket(Ticket seats[], int size);
int cancelTicket(Ticket seats[], int size);
void saveData(Ticket seats[], int size, const char* filename);
void loadData(Ticket seats[], int size, const char* filename);

int main() {
    Ticket seats[MAX_SEATS];
    int choice;
    const char* filename = "tickets.dat";

    loadData(seats, MAX_SEATS, filename);
    initializeSeats(seats, MAX_SEATS);

    while (1) {
        printf("\n----- Bus Ticket Reservation System by Naiyan Noor -----\n");
        printf("1. Book Ticket\n");
        printf("2. Cancel Ticket\n");
        printf("3. View Seats\n");
        printf("4. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                if (bookTicket(seats, MAX_SEATS)) {
                    saveData(seats, MAX_SEATS, filename);
                }
                break;
            case 2:
                if (cancelTicket(seats, MAX_SEATS)) {
                    saveData(seats, MAX_SEATS, filename);
                }
                break;
            case 3:
                displaySeats(seats, MAX_SEATS);
                break;
            case 4:
                saveData(seats, MAX_SEATS, filename);
                printf("Thank you for using the Ticket Reservation System.\n");
                exit(0);
            default:
                printf("Invalid choice. Please try again.\n");
        }
    }

    return 0;
}

void initializeSeats(Ticket seats[], int size) {
    for (int i = 0; i < size; i++) {
        seats[i].seatNumber = i + 1;
        strcpy(seats[i].passengerName, "Empty");
        strcpy(seats[i].passengerContact, "N/A");
    }
}

void displaySeats(Ticket seats[], int size) {
    printf("\n----- Seat Status -----\n");
    for (int i = 0; i < size; i++) {
        printf("Seat %d: %s\n", seats[i].seatNumber, seats[i].passengerName);
    }
}

int bookTicket(Ticket seats[], int size) {
    int seatNumber;
    printf("Enter seat number to book (1-%d): ", size);
    scanf("%d", &seatNumber);

    if (seatNumber < 1 || seatNumber > size) {
        printf("Invalid seat number.\n");
        return 0;
    }

    if (strcmp(seats[seatNumber - 1].passengerName, "Empty") != 0) {
        printf("Seat already booked.\n");
        return 0;
    }

    printf("Enter passenger name: ");
    scanf("%s", seats[seatNumber - 1].passengerName);
    printf("Enter passenger contact: ");
    scanf("%s", seats[seatNumber - 1].passengerContact);

    printf("Ticket booked successfully for seat %d.\n", seatNumber);
    return 1;
}

int cancelTicket(Ticket seats[], int size) {
    int seatNumber;
    printf("Enter seat number to cancel (1-%d): ", size);
    scanf("%d", &seatNumber);

    if (seatNumber < 1 || seatNumber > size) {
        printf("Invalid seat number.\n");
        return 0;
    }

    if (strcmp(seats[seatNumber - 1].passengerName, "Empty") == 0) {
        printf("Seat not booked yet.\n");
        return 0;
    }

    strcpy(seats[seatNumber - 1].passengerName, "Empty");
    strcpy(seats[seatNumber - 1].passengerContact, "N/A");

    printf("Ticket for seat %d cancelled successfully.\n", seatNumber);
    return 1;
}

void saveData(Ticket seats[], int size, const char* filename) {
    FILE* file = fopen(filename, "wb");
    if (file == NULL) {
        printf("Error opening file for saving data.\n");
        return;
    }

    fwrite(seats, sizeof(Ticket), size, file);
    fclose(file);
}

void loadData(Ticket seats[], int size, const char* filename) {
    FILE* file = fopen(filename, "rb");
    if (file == NULL) {
        printf("No previous data found. Initializing empty seats.\n");
        initializeSeats(seats, size);
        return;
    }

    fread(seats, sizeof(Ticket), size, file);
    fclose(file);
}
