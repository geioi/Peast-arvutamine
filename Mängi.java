import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Mängi extends Tase{ //Klass mängi on klassi tase alamklass.
	public Mängi(int tase) {
		super(tase); //Mängu loomisel kantakse kaasas taset.
	}

	public static void alustaMängu(Arvutustehted arvutus, int valitudTase){

		if (valitudTase == 1){ //Mäng algab esimeselt tasemelt.

			if (arvutus.getÕigeteArv() < 3){ //Arvutustehted esimesel tasemel, kuni õigeid on vähem kui kolm.
				esimeneTase(arvutus);			
			}

			else if (arvutus.getÕigeteArv() < 10){//Arvutustehted teisel tasemel, kuni õigeid on vähem kui kümme.
				teineTase(arvutus);
			}

			else {//Arvutustehted kolmandal tasemel alates kümnendast õigest vastusest.
				kolmasTase(arvutus);
			}
		}

		else if (valitudTase == 2){ // Mäng algab teiselt tasemelt.
			if (arvutus.getÕigeteArv() < 7){
				teineTase(arvutus);
			}

			else {
				kolmasTase(arvutus);
			}

		}

		else { //Mäng algab kolmandalt tasemelt.
			kolmasTase(arvutus);
		}

	}

	public static void kirjutaFaili(Arvutustehted tehe, ArrayList<String> teheJaVastus, String sisestus, File fail) throws Exception{
		FileWriter writer = new FileWriter(fail, true); //Avab faili, kuhu kirjutatakse, true lubab faili lisada meetodiga append. 
		writer.append("Tehe: " + teheJaVastus.get(0) +teheJaVastus.get(1)+ ", " +" aga Teie vastasite: " + sisestus +";");
		writer.close();
	}

	public static void vastamine(Arvutustehted tehe, ArrayList<String> teheJaVastus, String sisestus, File file) throws Exception{ 
//Vastuse kontroll.
		if (!tehe.vastuseKontroll(Integer.parseInt(sisestus))){ //Kui sisestus oli vale, siis kirjutab faili.
			kirjutaFaili(tehe, teheJaVastus, sisestus, file);
		}
		tehe.annaPunkt(tehe.vastuseKontroll(Integer.parseInt(sisestus))); //Punkti lisamine vastavalt sisestatud vastuse tõesusele.
	}
}