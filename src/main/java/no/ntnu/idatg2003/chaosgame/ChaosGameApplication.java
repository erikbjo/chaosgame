package no.ntnu.idatg2003.chaosgame;

import no.ntnu.idatg2003.chaosgame.frontend.view.PrimaryView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChaosGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChaosGameApplication.class, args);
		PrimaryView.mainApp(args);
	}
}
