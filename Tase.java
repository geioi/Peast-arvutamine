public class Tase{
	protected static int tase; //Staatiline, et saaks kasutada selles klassis klassi Mängi meetodit vastamine().
	
	public Tase(int tase){
		Tase.tase=tase;
	}
/*	public void valiTase(){ //Kasutaja saab taseme valida.
		Scanner scan = new Scanner(System.in);
		
		if (scan.hasNextInt() == false){ //Kontrollib, kas sisestus on int tüüpi.
			System.out.println("Mäng lõpetatud.");

		}
		else{
			int valik = scan.nextInt();
			if (valik < 1 || valik > 3){ //Kontrollib, kas sisestus on õiges vahemikus.
					System.out.print("Palun sisestage number ühest kolmeni: ");
					valiTase();
				}
			else{
				tase = valik;
				Mängi.alustaMängu(new Arvutustehted(), tase); //Alustame mängu.
			}
		}
		scan.close();
		
	}*/

	public static int getTase() {
		return tase;
	}
	
	public static void setTase(int tase){
		Tase.tase = tase;
	}
	
	public static void esimeneTase(Arvutustehted arvutus,  String sb){
		int arv = (int) Math.round(Math.random()*1+1); //Tehte valimiseks arv 1 või 2.
		if (arv == 1){
			arvutus.moodustaLiitmine(0,10 ); //Moodustatakse liitmistehe.
			//Mängi.vastamine(arvutus,sb); //Kasutaja vastamine.
		}
		else if (arv==2) {
			arvutus.moodustaLahutamine(0, 10);
			//Mängi.vastamine(arvutus,sb);
		}
	}
	
	public static void teineTase(Arvutustehted arvutus, String sb){
		int arv = (int) Math.round(Math.random()*3+1);
		if (arv == 1){
			arvutus.moodustaLiitmine(10, 100);
			//Mängi.vastamine(arvutus,sb);
		}
		else if (arv==2) {
			arvutus.moodustaLahutamine(10, 100);
			//Mängi.vastamine(arvutus,sb);
		}
		else if (arv==3){
			arvutus.moodustaKorrutamine(0, 10);
			//Mängi.vastamine(arvutus,sb);
		}
		else if (arv==4){
			arvutus.moodustaJagamine(0, 10);
			//Mängi.vastamine(arvutus,sb);
		}
	}
	
	public static void kolmasTase(Arvutustehted arvutus, String sb){
		int arv = (int) Math.round(Math.random()*3+1);
		if (arv == 1){
			arvutus.moodustaLiitmine(100, 1000);
			//Mängi.vastamine(arvutus,sb);
		}
		else if (arv==2) {
			arvutus.moodustaLahutamine(100, 1000);
			//Mängi.vastamine(arvutus,sb);
		}
		else if (arv==3){
			arvutus.moodustaKorrutamine(10, 100);
			//Mängi.vastamine(arvutus,sb);
		}
		else if (arv==4){
			arvutus.moodustaJagamine(0, 100);
			//Mängi.vastamine(arvutus,sb);
		}
	}
}