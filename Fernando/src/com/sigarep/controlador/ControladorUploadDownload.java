package com.sigarep.controlador;

import java.awt.Button;

import org.springframework.stereotype.Controller;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
@SuppressWarnings({ "serial", "unused" })//Notacion para suprimir advertencias de error especificas.
@Controller
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ControladorUploadDownload extends SelectorComposer<Component> {
	private Media media;
	private Button btnSubir;
	private Button btnDescargar;

	
	
}
