package br.com.eraumavez.managedbeans;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.PersistenceContext;

import br.com.eraumavez.model.Ilustracao;

@FacesConverter(value="ilustracaoConverter",forClass=Ilustracao.class)
public class IlustracaoConverter implements Converter{
	
	@PersistenceContext

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value!=null){
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value!=null && !"".equals(value)){
			Ilustracao entity = (Ilustracao) value;
			this.addAttribute(component,entity);
			Long id = entity.getId();
			if(id!=null){
				return String.valueOf(id);
			}
		}
		return (String) value;
	}

	private void addAttribute(UIComponent component, Ilustracao ilustracao) {
		String key = ilustracao.getId().toString();
		this.getAttributesFrom(component).put(key, ilustracao);		
	}	

}
