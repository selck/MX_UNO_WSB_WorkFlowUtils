package mx.com.amx.unotv.workflow.utils.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import mx.com.amx.unotv.workflow.utils.dto.ParametrosDTO;

public class UtilGetInfoVideo {
	
	private static Logger logger=Logger.getLogger(UtilGetInfoVideo.class);
	
	public static ParametrosDTO getProperties(){
		Properties props=new Properties();
		ParametrosDTO parametrosDTO=new ParametrosDTO();
		try {
			props.load(UtilGetInfoVideo.class.getResourceAsStream("/general.properties"));
			parametrosDTO.setApi_key(props.getProperty("api_key"));
			parametrosDTO.setPathRequest(props.getProperty("pathRequest"));
			parametrosDTO.setSecretKey(props.getProperty("secretKey"));
			parametrosDTO.setSignature(props.getProperty("signature"));
			parametrosDTO.setMethod_assets(props.getProperty("method_assets"));
			parametrosDTO.setMethod_assets_source_file_info(props.getProperty("method_assets_source_file_info"));
		} catch (Exception e) {
			logger.error("Erro al obtener archivo de propiedades: ",e);
		}
		return parametrosDTO;
	}
	
	public static ParametrosDTO getProperties(String properties){
		
		ParametrosDTO parametrosDTO=new ParametrosDTO();
		try {
			Properties propsTmp = new Properties();
			propsTmp.load(UtilGetInfoVideo.class.getResourceAsStream( "/general.properties" ));
			String ambiente = propsTmp.getProperty("ambiente");
			String rutaProperties = propsTmp.getProperty(properties.replace("ambiente", ambiente));			
			Properties props = new Properties();
			props.load(new FileInputStream(new File(rutaProperties)));		
			
			parametrosDTO.setAmbiente(ambiente);
			parametrosDTO.setApi_key(props.getProperty("api_key"));
			parametrosDTO.setPathRequest(props.getProperty("pathRequest"));
			parametrosDTO.setSecretKey(props.getProperty("secretKey"));
			parametrosDTO.setSignature(props.getProperty("signature"));
			parametrosDTO.setMethod_assets(props.getProperty("method_assets"));
			parametrosDTO.setMethod_assets_source_file_info(props.getProperty("method_assets_source_file_info"));
		} catch (Exception e) {
			logger.error("Erro al obtener archivo de propiedades: ",e);
		}
		return parametrosDTO;
	}
}
