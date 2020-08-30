package fehidro.converter;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("bigDecimalConverter")
public class BigDecimalConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
            return null;
				
		BigDecimal resul = new BigDecimal(value);
		
		return resul;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((BigDecimal) value).toString();

	}

}
