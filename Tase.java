public class Tase{
	protected static int tase; //Staatiline, et saaks kasutada teistes programmi klassides. 
	
	public Tase(int tase){
		Tase.tase=tase; //Taseme m��ramiseks graafilisest kasutajaliidesest. 
	}

	public static int getTase() { 
		return tase;
	}
	
	public static void setTase(int tase){
		Tase.tase = tase;
	}
	
	public static void esimeneTase(Arvutustehted arvutus){
		int arv = (int) Math.round(Math.random()*1+1); //Tehte valimiseks arv 1 v�i 2.
		if (arv == 1){
			arvutus.moodustaLiitmine(0,10 ); //Moodustatakse liitmistehe.
		}
		else {
			arvutus.moodustaLahutamine(0, 10);
		}
	}
	
	public static void teineTase(Arvutustehted arvutus){
		int arv = (int) Math.round(Math.random()*3+1);
		if (arv == 1){
			arvutus.moodustaLiitmine(10, 100);
		}
		else if (arv == 2) {
			arvutus.moodustaLahutamine(10, 100);
		}
		else if (arv == 3){
			arvutus.moodustaKorrutamine(0, 10);
		}
		else {
			arvutus.moodustaJagamine(0, 10);
		}
	}
	
	public static void kolmasTase(Arvutustehted arvutus){
		int arv = (int) Math.round(Math.random()*3+1);
		if (arv == 1){
			arvutus.moodustaLiitmine(100, 1000);
		}
		else if (arv == 2) {
			arvutus.moodustaLahutamine(100, 1000);
		}
		else if (arv == 3){
			arvutus.moodustaKorrutamine(10, 100);
		}
		else {
			arvutus.moodustaJagamine(0, 100);
		}
	}
}