package item;

public class MenuItem {
	
	public static enum Type {
		ITEM(0, "ITEM"),
		CATEGORY(1, "CATEGORY");
		
		private int typeInt;
		private String typeString;
		
		Type(int typeInt, String typeString) {
			this.typeInt = typeInt;
			this.typeString = typeString;
		}
		
		int getValue() {
			return this.typeInt;
		}
		
		public String getStringValue() {
			return this.typeString;
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
