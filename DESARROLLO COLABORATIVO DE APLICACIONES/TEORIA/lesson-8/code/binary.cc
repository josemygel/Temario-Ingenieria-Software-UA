#include <iostream>

template <unsigned long N>
struct binary
{
  static unsigned const value = 
    binary<N/10>::value << 1 | (N%10);
};

template <>
struct binary<0>
{
  static unsigned const value = 0;
};

unsigned const one   = binary<1>::value;
unsigned const three = binary<11>::value;
unsigned const five  = binary<101>::value;
unsigned const seven = binary<111>::value;
unsigned const nine  = binary<1001>::value;

int main(int argc, char *argv[])
{
  std::cout << "Uno es: " << one << "\n";
  std::cout << "Tres es: " << three << "\n";
  std::cout << "Cinco es: " << five << "\n";
  std::cout << "Siete es: " << seven << "\n";
  std::cout << "Nueve es: " << nine << "\n";

  return 0;
}
