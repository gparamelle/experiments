package fr.guillaume.paramelle.client;

import fr.guillaume.paramelle.client.dao.ClientDao;
import fr.guillaume.paramelle.client.widget.ListView;


public class ListPresenter implements ListView.Listener {

	private ClientDao clientDao ;
	
	public ListPresenter(ClientDao clientDao) {
		this.clientDao = clientDao ;
	}
	
	@Override
	public void onChange(String[] itemList) {
		clientDao.save(itemList);
	}

	@Override
	public String[] onPrepare() {
		return clientDao.getList() ;
	}

}
