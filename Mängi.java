import java.io.File;
import java.io.FileWriter;
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

	public static void kirjutaFaili(Arvutustehted tehe, ArrayList<String> teheJaVastus, String sisestus, File fail) throws Exception{
		FileWriter writer = new FileWriter(fail, true);
		//appendib tekstile otsa
		writer.append("Tehe: " + teheJaVastus.get(0) +teheJaVastus.get(1)+ ", " +" aga Teie vastasite: " + sisestus +";");
		writer.close();
	}

	public static void vastamine(Arvutustehted tehe, ArrayList<String> teheJaVastus, String sb, File file) throws Exception{ //Kasutajaga suhtlemine.

		if (!tehe.vastuseKontroll(Integer.parseInt(sb))){
			kirjutaFaili(tehe, teheJaVastus, sb, file);
		}
		tehe.annaPunkt(tehe.vastuseKontroll(Integer.parseInt(sb))); //Punkti lisamine vastavalt sisestatud vastuse tõesusele.

		alustaMängu(new Arvutustehted(tehe.getÕigeteArv(), tehe.getValedeArv()), getTase(), sb);
	}
}