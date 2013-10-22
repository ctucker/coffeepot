/* A coffee maker, composed of a brewing component and a warming component */
public class CoffeeMaker {
	final BrewingDevice brewer;
	final Warmer warmer;

	public CoffeeMaker() {
		this.brewer = new Percolator();
		this.warmer = new SensingWarmer();
	}

	public void prepare() {
		brewer.brew();
		warmer.trigger();
	}
}

interface BrewingDevice {
	void brew();
}

class Percolator implements BrewingDevice {
	String coffeeType;

	@Override
	public void brew() {
		coffeeType = "drip";
	}
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
	void trigger();
}

/* A warmer that turns on the heat only when coffee is present */
class SensingWarmer implements Warmer {

	final Heater heatingElement;
	final Sensor potSensor;

	public SensingWarmer() {
		this.heatingElement = new HeatingElement();
		this.potSensor = new PotSensor();
	}

	public void trigger() {
		if (potSensor.isCoffeePresent())
			heatingElement.on();
		else
			heatingElement.off();
	}

}

class HeatingElement implements Heater {

	boolean isOn = false;

	@Override
	public void on() {
		this.isOn = true;
	}

	@Override
	public void off() {
		this.isOn = false;
	}
}

class PotSensor implements Sensor {
	@Override
	public boolean isCoffeePresent() {
		return true;
	}
}
