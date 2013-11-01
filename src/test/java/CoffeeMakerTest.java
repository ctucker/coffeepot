import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.InOrder;

public class CoffeeMakerTest {

	@Test
	public void makeCoffee() {
		new CoffeeMaker(new Percolator(),
		                new SensingWarmer(new HeatingElement(),
		                                  new PotSensor()))
				.prepare();
	}

	// Tests we'd like to have:
	// 1. That the warmer turns the heating element on when there is coffee present
	@Test
	public void warmerTurnsElementOnWhenCoffeeIsPresent() {
		Sensor sensor = when(mock(Sensor.class).isCoffeePresent()).thenReturn(true).getMock();
		Heater heater = mock(Heater.class);
		Warmer warmer = new SensingWarmer(heater, sensor);
		warmer.activate();

		verify(heater).on();
	}

	// 2. That the warmer turns the heating element off when there is no coffee present
	@Test
	public void warmerTurnsElementOffWhenCoffeeIsNotPresent() {
		Sensor sensor = when(mock(Sensor.class).isCoffeePresent()).thenReturn(false).getMock();
		Heater heater = mock(Heater.class);
		Warmer warmer = new SensingWarmer(heater, sensor);
		warmer.activate();

		verify(heater).off();
	}

	// 3. That the coffee is brewed before the warmer is triggered
	@Test
	public void coffeeIsBrewedBeforeWarmerIsTriggered() {
		BrewingDevice brewer = mock(BrewingDevice.class);
		Warmer warmer = mock(Warmer.class);
		CoffeeMaker coffeeMaker = new CoffeeMaker(brewer, warmer);

		coffeeMaker.prepare();

		InOrder preparationOrder = inOrder(brewer, warmer);
		preparationOrder.verify(brewer).brew();
		preparationOrder.verify(warmer).activate();
	}

	// Things we'd like to be able to do by adapting the system:
	// 1. Make coffee into a thermos (no need to warm it after brewing)
	// 2. Brew a percolated coffee (different brewer)
	// 3. Sense coffee presence using a weight sensor (not a pot sensor)
	// 4. Add a Timer to start brewing at a configurable time

}
