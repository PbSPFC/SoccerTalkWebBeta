package com.iteractionapps.soccertalk.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.iteractionapps.soccertalk.model.Clube;
import com.iteractionapps.soccertalk.repository.Clubes;
import com.iteractionapps.soccertalk.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Clube.class)
public class ClubeConverter implements Converter {

	//@Inject
	private Clubes clubes;
	
	public ClubeConverter() {
		clubes = CDIServiceLocator.getBean(Clubes.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Clube retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = clubes.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Clube) value).getId().toString();
		}
		
		return "";
	}

}
