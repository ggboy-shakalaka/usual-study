package mustry.study.easy;

public class Link {
	private String name;
	private Link next;
	
	public Link(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Link getNext() {
		return this.next;
	}
	
	public void setNext(Link link) {
		this.next = link;
	}
	
	
	public static void main(String[] args) {
		Link link = new Link("chengxc");
		link.setNext(new Link("xiaocm"));
		link.getNext().setNext(new Link("zhaizq"));
		link.setNext(link.getNext().getNext());
		
		System.out.println(link.getNext().getName());
	}
}
