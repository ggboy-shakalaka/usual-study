package mustry.study.springAction;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AutoSupport implements AutowiredLocal {

	@Override
	@Autowired
	public void action() {
		System.out.println(this.getClass().getSimpleName() + " say : " + this);
	}

	public abstract void todoSomething();
}
