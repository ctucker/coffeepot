import org.junit.Test;

public class CoffeeMakerTest {

	@Test
	public void makeCoffee() {
		new CoffeeMaker().prepare();
	}

	// Tests we'd like to have:
	// 1. That the warmer turns the heating element on when there is coffee present
	// 2. That the warmer turns the heating element off when there is no coffee present
	// 3. That the coffee is brewed before the warmer is triggered
	// 4. That the coffee we make is the kind of coffee we want

	// Things we'd like to be able to do by adapting the system:
	// 1. Make coffee into a thermos (no need to warm it after brewing)
	// 2. Brew a percolated coffee (different brewer)
	// 3. Sense coffee presence using a weight sensor (not a pot sensor)
	// 4. Add a Timer to start brewing at a configurable time

}
