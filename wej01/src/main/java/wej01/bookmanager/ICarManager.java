package wej01.bookmanager;

import java.util.List;

import wej01.book.Car;

public interface ICarManager {
	public void add(Car car);
	List<Car> getAll();
}
