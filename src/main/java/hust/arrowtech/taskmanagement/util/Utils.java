package hust.arrowtech.taskmanagement.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Utils {

	public static void addMessage(String msg) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				msg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
