package helper;

import java.util.ArrayList;

public interface TreeInterface<T> {
	T getRootItem();
	
	void addChild(T parent, T child) throws Exception;

    boolean removeChild(T parent, T child) throws Exception;
    
    ArrayList<T> getChildren(T parent) throws Exception;
}
