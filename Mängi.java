import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Mängi extends Tase{
	public Mängi(int tase) {
		super(tase);
	}

	public static void alustaMängu(Arvutustehted arvutus, int valitudTase,  String sb){

		if (valitudTase == 1){ //Mäng algab esimeselt tasemelt.

			if (arvutus.getÕigeteArv() < 3){ //Arvutustehted esimesel tasemel, kuni õigeid on vähem kui kolm.
				esimeneTase(arvutus,sb);			
			}

			else if (arvutus.getÕigeteArv() < 10){//Arvutustehted teisel tasemel, kuni õigeid on vähem kui kümme.
				teineTase(arvutus,sb);
			}

			else if (arvutus.getÕigeteArv() >= 10){//Arvutustehted kolmandal tasemel alates kümnendast õigest vastusest.
				kolmasTase(arvutus,sb);
			}
		}

		else if (valitudTase == 2){ // Mäng algab teiselt tasemelt.
			if (arvutus.getÕigeteArv() < 7){
				teineTase(arvutus,sb);
			}

			else if (arvutus.getÕigeteArv() >= 7){
				kolmasTase(arvutus,sb);
			}

		}

		else if (valitudTase == 3){ //Mäng algab kolmandalt tasemelt.
			kolmasTase(arvutus,sb);
		}

	}
	
	public static void kirjutaFaili(Arvutustehted tehe, ArrayList<String> teheJaVastus, String sisestus) throws Exception{
		File file = new File("testFile1.txt");

		//kontrollib, kas fail oli eelnevalt olemas, kui oli ning mängu alustati uuesti, siis tühjendatakse see
		if (!file.createNewFile() && file.length() != 0 && tehe.getValedeArv() == 0){
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.setLength(0);
			raf.close();
		}

		FileWriter writer = new FileWriter(file, true);
		//appendib tekstile otsa
		writer.append("Tehe: " + teheJaVastus.get(0) +teheJaVastus.get(1)+ ", " +" aga Teie vastasite: " + sisestus +";");
		writer.close();
	}

	public static void vastamine(Arvutustehted tehe, ArrayList<String> teheJaVastus, String sb) throws Exception{ //Kasutajaga suhtlemine.
		try{
		int vastus =Integer.parseInt(teheJaVastus.get(1));
		int sisestus = Integer.parseInt(sb);
		System.out.println(vastus + " " + sisestus);
		
		if (!tehe.vastuseKontroll(Integer.parseInt(sb))){
			kirjutaFaili(tehe, teheJaVastus, sb);
		}
		tehe.annaPunkt(tehe.vastuseKontroll(Integer.parseInt(sb))); //Punkti lisamine vastavalt sisestatud vastuse tõesusele.
		System.out.print("Õigeid vastuseid: " + tehe.getÕigeteArv());
		System.out.println(", Valesid vastuseid: " + tehe.getValedeArv());}
		catch (NumberFormatException e){
			System.out.println("Vastus peab olema number!");
		}
		
		alustaMängu(new Arvutustehted(tehe.getÕigeteArv(), tehe.getValedeArv()), getTase(), sb);
	}
}