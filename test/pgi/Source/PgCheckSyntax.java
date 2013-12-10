// Just checks to see if the input is parseable.  Used by
// the grading scripts to give better error messages.
public class PgCheckSyntax
{
   public static void main(String [] args)
	{
		try {
			new PigletParser(System.in).Goal();
			System.out.println("OK.");
		}
		catch (ParseException e) {
			System.err.println(e.toString());
			System.exit(1); return;
		}
   }
}

