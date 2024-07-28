#include <stdio.h>
int getFirstDayOfMonth(int year, int month) {
    int day = 1;
    int k = year % 100;
    int j = year / 100;

    if (month < 3) {
        month += 12;
        year--;
    }

    int h = (day + 13 * (month + 1) / 5 + k + k / 4 + j / 4 + 5 * j) % 7;
    int d = ((h + 5) % 7) + 1;

    return d;
}

int getDaysInMonth(int month, int year) {
    switch (month) {
        case 1: return 31;
        case 2: return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) ? 29 : 28;
        case 3: return 31;
        case 4: return 30;
        case 5: return 31;
        case 6: return 30;
        case 7: return 31;
        case 8: return 31;
        case 9: return 30;
        case 10: return 31;
        case 11: return 30;
        case 12: return 31;
        default: return 0;
    }
}

void printCalendar(int year, int month) {
    char *months[] = {"January", "February", "March", "April", "May", "June",
                      "July", "August", "September", "October", "November", "December"};
    int daysInMonth = getDaysInMonth(month, year);
    int firstDay = getFirstDayOfMonth(year, month);

    printf("\n  %s %d\n", months[month - 1], year);
    printf("  Sun  Mon  Tue  Wed  Thu  Fri  Sat\n");

    for (int i = 0; i < firstDay; i++) {
        printf("     ");
    }

    for (int day = 1; day <= daysInMonth; day++) {
        printf("%5d", day);
        if ((day + firstDay) % 7 == 0) {
            printf("\n");
        }
    }
    printf("\n");
}

int main() {
    int month, year;

    printf("Enter month (1-12): ");
    scanf("%d", &month);
    printf("Enter year: ");
    scanf("%d", &year);

    if (month < 1 || month > 12 || year < 1) {
        printf("Invalid month or year!\n");
        return 1;
    }

    printCalendar(year, month);
    return 0;
}
