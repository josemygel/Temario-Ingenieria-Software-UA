double fun (double x)
  in { assert (x > 0); }
  out (r) { assert (r>0); }
body {
  return 1/x;
 }

int main ()
{
  fun (3.3);
  fun (-2);
  return 0;
}
