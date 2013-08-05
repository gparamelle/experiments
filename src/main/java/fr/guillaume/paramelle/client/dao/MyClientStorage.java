package fr.guillaume.paramelle.client.dao;

import com.google.gwt.storage.client.Storage;

public class MyClientStorage {

	public void put(String key, String text) {
		
		Storage storage = Storage.getLocalStorageIfSupported() ;
		if ( storage != null )
			storage.setItem("plaintext", text);
		
	}
	
	public String get(String key) {
		Storage storage = Storage.getLocalStorageIfSupported() ;
		if ( storage != null )
			return storage.getItem(key) ;
		else 
			return null ;
	}
}
