package br.com.eraumavez.security.tags;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;

@FacesComponent(value="CsrfMetaTag")
public class CsrfMetaTag extends UIComponentBase{

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        CsrfToken token = (CsrfToken) req.getAttribute(CsrfToken.class.getName());
        if (token != null) {
            try {
                String s = handleToken(token);
                context.getResponseWriter().write(s);
            } catch (IOException e) {
                throw e;
            }
        }
	}

	@Override
	public String getFamily() {
		return "csrfMetaTags";
	}
	
	public String handleToken(CsrfToken token){
		return "<meta name=\"_csrf_parameter\" content=\"" + token.getParameterName()
        + "\" />" + "<meta name=\"_csrf_header\" content=\""
        + token.getHeaderName() + "\" />" + "<meta name=\"_csrf\" content=\""
        + token.getToken() + "\" />";
	}
	
	

}
