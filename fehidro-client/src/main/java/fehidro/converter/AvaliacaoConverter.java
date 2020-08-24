package fehidro.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import fehidro.model.Avaliacao;

@FacesConverter(forClass = Avaliacao.class,value="avaliacaoConverter")
public class AvaliacaoConverter implements Converter, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
            return null;
		
		Avaliacao tp = new Avaliacao();
		tp.setId(Long.parseLong(value));
		
		return tp;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Avaliacao) {
			long idTipoProposta = ((Avaliacao) value).getId();
			return String.valueOf(idTipoProposta);
		}
		
		return null;
	}

}
