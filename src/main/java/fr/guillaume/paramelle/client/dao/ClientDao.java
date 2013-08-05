package fr.guillaume.paramelle.client.dao;

public class ClientDao {

	private static final Object SEPARATOR = "¤" ;
	private static final String MAINKEY = "plaintext";
	
	private final MyClientStorage storage ;
	
	public ClientDao(MyClientStorage storage) {
		this.storage = storage ;
	}
	
	/**
	 * @param list
	 */
	public void save(String[] list) {
		StringBuilder sb = new StringBuilder() ;
		for (String string : list) {
			if ( sb.length() > 0 )
				sb.append(SEPARATOR) ;
			sb.append(string) ;
		}
		this.storage.put(MAINKEY, sb.toString());
	}
	
	/**
	 * @return
	 */
	public String[] getList() {
		String flat = this.storage.get(MAINKEY) ;
		if ( flat == null )
			return new String[0] ;
		
		return flat.split("¤") ;
	}
}
