double fun (double x)
requires ( x >= 0 )
ensures ( result > 0 )
 {
	 return 1/x;
 }

int main ()
{
  fun (3.3);
  fun (-2);
  return 0;
}
