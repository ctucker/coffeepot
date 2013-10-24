/* A coffee maker, composed of a brewing component and a warming component */
public class CoffeeMaker {
	private final BrewingDevice brewer;
	private final Warmer warmer;

	public CoffeeMaker() {
		this.brewer = new Percolator();
		this.warmer = new SensingWarmer();
	}

	public void prepare() {
		brewer.brew();
		warmer.activate();
	}
}

interface BrewingDevice {
	void brew();
}

/* Heater that can be turned on or off */
interface Heater {
	void on();
	void off();
}

/* Sensor that can sense if coffee is present */
interface Sensor {
	boolean isCoffeePresent();
}

interface Warmer {
	void activate();
}

class Percolator implements BrewingDevice {
	String coffeeType;

	@Override
	public void brew() {
		coffeeType = "drip";
	}
}

/* A warmer that turns on the heat only when coffee is present */
class SensingWarmer implements Warmer {

	private final Heater heatingElement;
	private final Sensor potSensor;

	public SensingWarmer() {
		this.heatingElement = new HeatingElement();
		this.potSensor = new PotSensor();
	}

	public void activate() {
		if (potSensor.isCoffeePresent())
			heatingElement.on();
		else
			heatingElement.off();
	}

}

class HeatingElement implements Heater {

	private boolean isOn = false;

	@Override
	public void on() {
		this.isOn = true;
	}

	@Override
	public void off() {
		this.isOn = false;
	}

	public boolean isOn() {
		return this.isOn;
	}
}

class PotSensor implements Sensor {
	@Override
	public boolean isCoffeePresent() {
		return true;
	}
}
