package item;

public class MenuItem {
	
	public static enum Type {
		ITEM(0),
		CATEGORY(1);
		
		private int typeInt;
		
		Type(int typeInt) {
			this.typeInt = typeInt;
		}
		
		int getValue() {
			return this.typeInt;
		}
	}
	
	private String name;
	private Type type;
	
	public MenuItem(String name, Type type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public boolean equals(MenuItem item) {
		return this.name.equals(item.name) && this.type.typeInt == item.type.typeInt;
	}
}
