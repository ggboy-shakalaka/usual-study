package mustry.study.pattern.observer;

import java.util.ArrayList;

interface Observer {
	void dothing();
}

abstract class AbstractSubject<T> {
	private ArrayList<T> observers = new ArrayList<T>();;

	public void notifyObservers() {
		synchronized (observers) {
			for (T observer : observers)
				dothing(observer);
		}
	}

	public void add(T observer) {
		if (observer == null)
			throw new IllegalArgumentException("observer is null");
		synchronized (observers) {
			observers.add(observer);
		}
	}

	abstract void dothing(T obj);
}

public class Subject extends AbstractSubject<Observer> {

	public void action() {
		System.out.println("action");
		notifyObservers();
	}

	public static void main(String[] args) {
		Subject subject = new Subject();
		subject.add(() -> System.out.println("Observer 1 is register"));
		subject.add(() -> System.out.println("Observer 2 is register"));
		subject.action();
	}

	@Override
	void dothing(Observer obj) {
		obj.dothing();
	}
}