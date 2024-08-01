#include <iostream>
#include <iomanip>
#include <vector>
using namespace std;

class Scoreboard {
private:
    int runs;
    int wickets;
    float overs;
    vector<int> ballsPerOver;

public:
    Scoreboard() : runs(0), wickets(0), overs(0.0) {}

    void addRuns(int run) {
        runs += run;
    }

    void addWicket() {
        wickets++;
    }

    void addBall() {
        if (overs - static_cast<int>(overs) == 0.5) {
            overs = static_cast<int>(overs) + 1;
            ballsPerOver.push_back(6);
        } else {
            overs += 0.1;
            if (overs - static_cast<int>(overs) >= 0.6) {
                overs = static_cast<int>(overs) + 1;
                ballsPerOver.push_back(6);
            }
        }
    }

    void displayScore() const {
        cout << "Score: " << runs << "/" << wickets << endl;
        cout << "Overs: " << fixed << setprecision(1) << overs << endl;
    }

    void displayBallsPerOver() const {
        cout << "Balls per Over: ";
        for (int balls : ballsPerOver) {
            cout << balls << " ";
        }
        cout << endl;
    }
};

int main() {
    Scoreboard scoreboard;
    int choice, runs;

    while (true) {
        cout << "1. Add Runs\n2. Add Wicket\n3. Add Ball\n4. Display Score\n5. Display Balls per Over\n6. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                cout << "Enter runs: ";
                cin >> runs;
                scoreboard.addRuns(runs);
                break;
            case 2:
                scoreboard.addWicket();
                break;
            case 3:
                scoreboard.addBall();
                break;
            case 4:
                scoreboard.displayScore();
                break;
            case 5:
                scoreboard.displayBallsPerOver();
                break;
            case 6:
                return 0;
            default:
                cout << "Invalid choice. Please try again." << endl;
        }
    }

    return 0;
}
