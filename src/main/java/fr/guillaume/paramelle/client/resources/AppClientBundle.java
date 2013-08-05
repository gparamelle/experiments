package fr.guillaume.paramelle.client.resources;

/*
	 * Copyright 2009 Fred Sauer
	 * 
	 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
	 * in compliance with the License. You may obtain a copy of the License at
	 * 
	 * http://www.apache.org/licenses/LICENSE-2.0
	 * 
	 * Unless required by applicable law or agreed to in writing, software distributed under the License
	 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
	 * or implied. See the License for the specific language governing permissions and limitations under
	 * the License.
	 */
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface AppClientBundle extends ClientBundle {

	interface AppCssResource extends CssResource {
	
	    public String listItem();
	
	    public String itemPositioner();

		public String inputField();

		public String body();
	}
	
	static final AppClientBundle INSTANCE = GWT.create(AppClientBundle.class);
	
	@Source("app.css")
	AppCssResource css();
}
