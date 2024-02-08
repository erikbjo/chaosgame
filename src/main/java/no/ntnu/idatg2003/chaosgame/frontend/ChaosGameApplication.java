package no.ntnu.idatg2003.chaosgame.frontend;

import no.ntnu.idatg2003.chaosgame.frontend.view.PrimaryView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChaosGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimaryView.class, args);
	}

}
