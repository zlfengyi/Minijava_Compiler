public class KgCheckSyntax {

    public static void main(String args[]) {

        KangaParser parser = new KangaParser(System.in);
        try {
            parser.Goal();
				System.out.println("OK");
        }
		  catch (ParseException e) {
            System.err.println(e.getMessage());
				System.exit(1); return;
        }
    }
}
