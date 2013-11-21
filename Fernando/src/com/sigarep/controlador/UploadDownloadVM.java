package com.sigarep.controlador;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;
import com.sigarep.modelo.*;

/**
 * Simple Upload & Download file demo
 *
 * @author <a href="mailto:jmcp18@gmail.com">Jose Manuel Campechano P.</a>
 * @version 1.0
 */

public class UploadDownloadVM {
	private Media media;

/*	
	//Test your preferred language, by default it uses the language of your browser
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
		 Locale locale = new Locale("es", "ES");
//		 Locale locale = new Locale("en", "US");
		 view.getDesktop().getSession().setAttribute(Attributes.PREFERRED_LOCALE, locale);
	}
*/	
	//method which intercepts the click event of the upload button, and notifies the binder that the media«s object is not empty, to enable the download button
	@NotifyChange("media")
	@Command
	public void uploadFile(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event) {
		media = event.getMedia();
		if(Util.uploadFile(media))
			Messagebox.show(Labels.getLabel("app.successfull"));
		else
			Messagebox.show(Labels.getLabel("app.error"));
	}
	
	public Media getMedia(){
		return media;
	}

	//simply download the file
	@Command
	public void downloadFile(){
		if(media != null)
			Filedownload.save(media);

	}

}
