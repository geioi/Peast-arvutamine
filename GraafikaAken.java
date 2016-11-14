import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GraafikaAken extends Application {
	public String valedVastused() throws FileNotFoundException{
		File valedvastused= new File("testfile1.txt");
		Scanner tekst = new Scanner(valedvastused);
		String vale=null;
		while (tekst.hasNextLine()){
			String rida = tekst.nextLine();
			vale=rida.replace(";", "\n");
		}
		tekst.close();
		return vale;
	}
	public void okVajutatud(Stage ekraan, Arvutustehted arvutus, StringBuilder sisestus) {
/*		if (sisestus.equals("")) {
			sisestus.append(Integer.parseInt(e.getText()));
			System.out.println("lisasin numbri");
		}*/
		try{
			Integer.parseInt(sisestus.toString());
			Mängi.vastamine(arvutus, arvutus.getTulemus(), sisestus.toString());
			//System.out.println("Pärast");
			Arvutustehted uusArvutus = new Arvutustehted(arvutus.getÕigeteArv(), arvutus.getValedeArv());
			Mängi.alustaMängu(uusArvutus, Tase.getTase(), sisestus.toString());
			ekraan(ekraan, uusArvutus);
		}
		catch(NumberFormatException ev){
			//System.out.println("Enne");
			ekraan(ekraan,arvutus);
			
		}
		catch(Exception event){};

	}
	public void ekraan (Stage ekraan, Arvutustehted arvutus){

		StringBuilder sisestus = new StringBuilder();
		BorderPane Paan = new BorderPane();
		TextField e = new TextField();

		e.setPrefSize(100, 60);
		e.setFont(new Font("Arial", 20));

		StackPane stack = new StackPane();
		Rectangle rec = new Rectangle(190, 100, Color.WHITE);
		Text tehe = new Text(arvutus.getTulemus().get(0));

		tehe.setFont(new Font("Arial", 30));
		stack.getChildren().addAll(rec, tehe);

		HBox all= new HBox();
		all.setPadding(new Insets(10, 10, 10, 10));
		Paan.setCenter(all);

		GridPane n = new GridPane();
		n.setVgap(10); 

		Label õiged = new Label("Õiged: ");
		Label õigedArv = new Label("0");
		Label valed = new Label("Valed: ");
		Label valedArv = new Label("0");
		valedArv.setText(Integer.toString(arvutus.getValedeArv()));
		õigedArv.setText(Integer.toString(arvutus.getÕigeteArv()));

		VBox ülemine = new VBox(5);
		HBox kaks = new HBox(30);
		HBox kaks_alumine = new HBox(30);
		HBox ülemine_ülemine_vasak = new HBox(5);
		HBox ülemine_ülemine_parem = new HBox(5);
		HBox ülemine_alumine_vasak = new HBox(20);
		HBox ülemine_alumine_parem = new HBox(20);
		kaks.getChildren().addAll(ülemine_ülemine_vasak, ülemine_ülemine_parem);
		kaks_alumine.getChildren().addAll(ülemine_alumine_vasak, ülemine_alumine_parem);
		ülemine.getChildren().addAll(kaks, kaks_alumine);
		ülemine_ülemine_vasak.getChildren().addAll(õiged, õigedArv);
		ülemine_ülemine_parem.getChildren().addAll(valed, valedArv);
		ülemine_alumine_parem.getChildren().add(e);
		ülemine_alumine_vasak.getChildren().add(stack);
		ülemine_alumine_vasak.setAlignment(Pos.CENTER_LEFT);
		ülemine_alumine_parem.setAlignment(Pos.CENTER_RIGHT);
		kaks.setAlignment(Pos.CENTER);
		kaks_alumine.setAlignment(Pos.CENTER);
		ülemine_ülemine_vasak.setAlignment(Pos.CENTER_LEFT);
		ülemine_ülemine_parem.setAlignment(Pos.TOP_RIGHT);
		Paan.setTop(ülemine);

		HBox x1=new HBox();
		x1.setSpacing(10);
		HBox x2=new HBox();
		x2.setSpacing(10);
		HBox x3=new HBox();
		x3.setSpacing(10);
		HBox x4=new HBox();
		x4.setSpacing(10);
		HBox x6 = new HBox();
		x6.setSpacing(10);
		x6.setPadding(new Insets(20,20,20,20));

		n.add(x1,0,1);
		n.add(x2,0,2);
		n.add(x3,0,3);
		n.add(x4,0,4);
		all.getChildren().add(n);
		all.setAlignment(Pos.CENTER);

		for (int i=1; i<10; i++){
			Button n1=new Button (new Integer(i).toString());
			n1.setTextFill(Color.BLUE);
			n1.setPrefSize(60,30);
			if (i<4){
				x3.getChildren().add(n1);
				int number = i;
				n1.setOnAction(event -> {
					sisestus.append(number);
					e.setText(sisestus.toString());
					e.requestFocus();
					e.selectEnd();
				});

			}
			else if (i<7){
				x2.getChildren().add(n1);
				int number = i;
				n1.setOnAction(event -> {
					sisestus.append(number);
					e.setText(sisestus.toString());
					e.requestFocus();
					e.selectEnd();
				});
			}
			else{
				x1.getChildren().add(n1);
				int number = i;
				n1.setOnAction(event -> {
					sisestus.append(number);
					e.setText(sisestus.toString());
					e.requestFocus();
					e.selectEnd();
				});
			}
		}

		Button miinus= new Button("-");
		miinus.setTextFill(Color.RED);
		miinus.setPrefSize(60,30);
		x2.getChildren().add(miinus);
		Button n0=new Button ("C");
		n0.setTextFill(Color.RED);
		n0.setPrefSize(60,30);
		Button nn=new Button ("0");
		nn.setTextFill(Color.BLUE);
		nn.setPrefSize(60,30);
		Button nm=new Button ("CE");
		nm.setTextFill(Color.RED);
		nm.setPrefSize(60,30);
		x4.getChildren().addAll(n0,nn,nm);
		miinus.setOnAction(event ->{
			if (sisestus.length() < 1){
				sisestus.append("-");
				e.setText(sisestus.toString());
				e.requestFocus();
				e.selectEnd();
			}
		});
		n0.setOnAction(event -> {
			if (sisestus.length() > 0){
				sisestus.deleteCharAt(sisestus.length()-1);
				e.setText(sisestus.toString());
				e.requestFocus();
				e.selectEnd();
			}
			else{
				e.requestFocus();
			}

		});

		nn.setOnAction(event -> {
			sisestus.append("0");
			e.setText(sisestus.toString());
			e.requestFocus();
			e.selectEnd();
		});

		nm.setOnAction(event -> {
			sisestus.setLength(0); 
			e.setText(sisestus.toString());
			e.requestFocus();
			e.selectEnd();
		});

		Button ok = new Button ("OK");
		ok.setTextFill(Color.GREEN);
		ok.setPrefSize(60,30);
		x1.getChildren().add(ok);
		
		ok.setOnAction(event-> {
			okVajutatud(ekraan, arvutus, sisestus);
		
		});
		e.setOnKeyPressed(event ->{

			if (event.getCode().equals(KeyCode.ENTER)){
				okVajutatud(ekraan, arvutus, sisestus);
				
			}
			else if (event.getCode().equals(KeyCode.BACK_SPACE)){
				if (sisestus.length() > 0){
					n0.arm();
					sisestus.deleteCharAt(sisestus.length()-1);
				}
			}
			else{
				sisestus.append(event.getText());
			}
/*			else{
				try{
					if (Integer.parseInt(event.getText()) >= 0 && Integer.parseInt(event.getText()) <= 9){
						sisestus.append(Integer.parseInt(event.getText()));
					}
				} catch(NumberFormatException exc){
					System.out.println("Sisestati täht");
					sisestus.append(event.getText());}
			}*/
		});

		Button lõpeta = new Button("Lõpeta");
		lõpeta.setPrefSize(60,30);
		x6.getChildren().add(lõpeta);
		x6.setAlignment(Pos.TOP_CENTER);
		Paan.setBottom(x6);

		lõpeta.setOnAction(event -> {
				Stage vastus = new Stage();
				vastus.initModality(Modality.WINDOW_MODAL);

				Label label = new Label();
				Button okButton = new Button("Lõpeta");
				Button uuesti = new Button("Alusta uuesti");
				try{
				label.setText(valedVastused());}
				catch(Exception ee){}
				// sündmuse lisamine nupule Jah
				okButton.setOnAction( event1->Platform.exit());

				// sündmuse lisamine nupule Ei
				uuesti.setOnAction(event12-> {
					vastus.hide();
					start(ekraan);
				});

				// nuppude grupeerimine
				FlowPane pane = new FlowPane(10, 10);
				pane.setAlignment(Pos.CENTER);
				pane.getChildren().addAll(okButton, uuesti);

				// küsimuse ja nuppude gruppi paigutamine
				VBox vBox = new VBox(10);
				vBox.setAlignment(Pos.CENTER);
				vBox.getChildren().addAll(label, pane);

				//stseeni loomine ja näitamine
				Scene stseen2 = new Scene(vBox);
				vastus.setScene(stseen2);
				vastus.show();
				ekraan.hide();
			
		});


		Scene stseen1 = new Scene(Paan,  300, 380, Color.SNOW);  // luuakse stseen  
		ekraan.setTitle("Peast Arvutamine");  // lava tiitelribale pannakse tekst
		ekraan.setScene(stseen1);  // lavale lisatakse stseen
		ekraan.setResizable(false);
		e.requestFocus();
		ekraan.show();  // lava tehakse nähtavaks

	}
	@Override
	public void start(Stage peaLava) {
		Pane miski= new Pane ();
		StringBuilder nimi =new  StringBuilder();
		VBox oopp=new VBox(10);
		HBox oop=new HBox(10);
		Label label = new Label();
		label.setText("Käesolev programm on mõeldud peast arvutamise treenimiseks.\n"
				+ "Programm esitab tehte, millele peate vastama. \n"
				+ "Vastamiseks saate kasutada nii numbrinuppe kui ka klaviatuuri. \n"
				+ "Mängul on kolm raskusastet. Mängu alustades saate valida endale sobiva\n"
				+ "raskusastme. Mängus muutuvad raskusastmed vastavalt õigete vastuste arvule. \n"
				+ "Mängu lõppedes näitab programm Teile tehteid ja vastusied, millele vastasite valesti. \n"
				+ "Mängu alustamiseks vali tase.");
		Button üks = new Button("Tase 1");
		Button kaks1 = new Button ("Tase 2");
		Button kolm = new Button ("Tase 3");
		Button lõpp = new Button("Lõpeta");
		oop.getChildren().addAll(üks, kaks1, kolm);
		oop.setAlignment(Pos.CENTER);
		oopp.getChildren().addAll(label, oop, lõpp);
		oopp.setAlignment(Pos.CENTER);
		miski.getChildren().add(oopp);

		üks.setOnAction(event -> {//valitase
			Arvutustehted arvutus = new Arvutustehted(0,0);
			Tase.setTase(1);
			peaLava.hide();
			Mängi.alustaMängu(arvutus, Tase.getTase(), nimi.toString());
			ekraan(peaLava, arvutus);

			//Tase.esimeneTase(new Arvutustehted(),nimi.toString());

		});
		kaks1.setOnAction(event -> {//valitase
			Arvutustehted arvutus = new Arvutustehted(0,0);
			Tase.setTase(2);
			peaLava.hide();
			Mängi.alustaMängu(arvutus, Tase.getTase(), nimi.toString());
			ekraan(peaLava, arvutus);
		});
		kolm.setOnAction(event -> {//valitase
			Arvutustehted arvutus = new Arvutustehted(0,0);
			Tase.setTase(3);
			peaLava.hide();
			Mängi.alustaMängu(arvutus, Tase.getTase(), nimi.toString());
			ekraan(peaLava, arvutus);
		});
		lõpp.setOnAction(event -> {Platform.exit();

		});


		Scene stseenA=new Scene(miski);
		peaLava.setTitle("Peast Arvutamine");  // lava tiitelribale pannakse tekst
		peaLava.setScene(stseenA);
		peaLava.setResizable(false);
		peaLava.show();  // lava tehakse nÃ¤htavaks

	}
	public static void main(String[] args) {
		launch(args);
	}
}