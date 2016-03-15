package wej01.bookmanager;

import java.util.LinkedList;
import java.util.List;

import wej01.book.Car;

public class BookManager implements ICarManager {
	
	public LinkedList<Car> allCars = new LinkedList<Car>();
	
	@Override
	public void add(Car car) {
		allCars.add(car);
	}

	@Override
	public List<Car> getAll() {
		return allCars;
	}

}
