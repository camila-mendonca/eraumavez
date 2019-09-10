package br.com.eraumavez.managedbeans;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.eraumavez.model.Livro;

@FacesConverter(value="livroConverter",forClass=Livro.class)
public class LivroConverter implements Converter{
	
	@PersistenceContext
	private transient EntityManager em;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value!=null){
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	protected Map<String,Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value!=null&&!"".equals(value)){
			Livro entity = (Livro) value;
			this.addAttribute(component,entity);
			Long id = entity.getId();
			if(id!=null){
				return String.valueOf(id);
			}
		}
		return (String) value;
	}

	private void addAttribute(UIComponent component, Livro livro) {
		String key = livro.getId().toString();
		this.getAttributesFrom(component).put(key, livro);
	}

}
