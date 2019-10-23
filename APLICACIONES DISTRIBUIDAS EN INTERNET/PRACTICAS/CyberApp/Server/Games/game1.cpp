#include <iostream>

int main()
{
    std::string correctPassword = "ERROR401";
    std::string password = "";
    
    std::cout <<  "Enter the password:";
    std::cin >> password;

    while(password != correctPassword){
        std::cout << "Error, you can try again, enter the password: ";
        std::cin >> password;
    }

    std::cout << "\nYou have insert the correct password!\n\n";

    std::cin.get();

    return 0;
}