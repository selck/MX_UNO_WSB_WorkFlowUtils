package mx.com.amx.unotv.workflow.utils.bo.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import mx.com.amx.unotv.workflow.utils.bo.IGetInfoVideoBO;
import mx.com.amx.unotv.workflow.utils.dto.ParametrosDTO;
import mx.com.amx.unotv.workflow.utils.dto.VideoOoyalaDTO;
import mx.com.amx.unotv.workflow.utils.exception.GetInfoVideoBOException;
import mx.com.amx.unotv.workflow.utils.util.Signature;
import mx.com.amx.unotv.workflow.utils.util.UtilGetInfoVideo;

@Component
@Qualifier("getInfoVideoBO")
public class GetInfoVideoBOImpl implements IGetInfoVideoBO {
	
	private static Logger logger=Logger.getLogger(GetInfoVideoBOImpl.class);
	
	public static void main(String[] args){
		String contentid="1ncm40YTE6_b9qqIuriXRnjMIOk47jac";
		try {
			GetInfoVideoBOImpl boImpl=new GetInfoVideoBOImpl();
			boImpl.getInfoVideo(contentid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public VideoOoyalaDTO getInfoVideo(String content_id) throws GetInfoVideoBOException {
		
		logger.debug("Inicia getInfoVideo");
		
		VideoOoyalaDTO videoOoyalaDTO = new VideoOoyalaDTO();
		ParametrosDTO parametrosDTO=new ParametrosDTO();
		try {
			
			parametrosDTO=UtilGetInfoVideo.getProperties("ambiente.resources.properties");
			//parametrosDTO=UtilGetInfoVideo.getProperties();
			parametrosDTO.setContent_id(content_id);
						
			long epoch = System.currentTimeMillis();
			Long expires = new Long(epoch);
			parametrosDTO.setExpires(expires.toString());			
			
			parametrosDTO.setSignature(getSignatureOoyalaByMethod(parametrosDTO, parametrosDTO.getMethod_assets()));
			JSONObject objectGeneral = getInfoByMethod(parametrosDTO, parametrosDTO.getMethod_assets());    //LLamada del metodo de ooyala
			try {
				videoOoyalaDTO.setDuration(objectGeneral.getString("duration"));
				videoOoyalaDTO.setAlternate_text(objectGeneral.getString("name"));
			} catch (Exception e) {
				logger.error("Error al ontener la informacion del objectGeneral: "+e.getLocalizedMessage());
			}
			
			parametrosDTO.setSignature(getSignatureOoyalaByMethod(parametrosDTO, parametrosDTO.getMethod_assets_source_file_info()));
			JSONObject objectSourceFileInfo = getInfoByMethod(parametrosDTO, parametrosDTO.getMethod_assets_source_file_info());    //LLamada del metodo de ooyala
			
			try {
				videoOoyalaDTO.setSource(objectSourceFileInfo.getString("source_file_url"));
				videoOoyalaDTO.setFileSize(objectSourceFileInfo.getString("file_size"));
			} catch (Exception e) {
				logger.error("Error al ontener la informacion del objectSourceFileInfo: "+e.getLocalizedMessage());
			}
			
			return videoOoyalaDTO;
		} catch (Exception e) {
			logger.error("Exception en getInfoVideo: ",e);			
			throw new GetInfoVideoBOException(e.getMessage());
		}
	}
	
	/**
	 * Metodo que hace el llamado al API de ooyala
	 * @param method_assets
	 * @param parametrosDTO
	 * @return String, con el json de la respuesta
	 * 
	 * */
	private JSONObject getInfoByMethod(ParametrosDTO parametrosDTO, String method) throws Exception{
		JSONObject respuesta;
		try {
						
			//Armamos la URL
			StringBuffer sbURL = new StringBuffer(); 			
			String requestPath = method.replace("$ASSET_ID$", parametrosDTO.getContent_id());
			sbURL.append(parametrosDTO.getPathRequest());
			sbURL.append(requestPath);			
			sbURL.append("?api_key=");
			sbURL.append(parametrosDTO.getApi_key());
			sbURL.append("&signature=");
			sbURL.append(parametrosDTO.getSignature());
			sbURL.append("&expires=");
			sbURL.append(parametrosDTO.getExpires());			
						
			URL url = new URL(sbURL.toString());		
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			if(parametrosDTO.getAmbiente().equalsIgnoreCase("produccion")){
				logger.info("Seteando X-Target");
				con.setRequestProperty("X-Target", "api.ooyala.com");
			}
			con.setRequestMethod("GET");
			
			int responseCode = con.getResponseCode();
			logger.debug("\nSending 'GET' request to URL : " + url);
			//logger.debug("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			respuesta=new JSONObject(response.toString());
			logger.debug("respuesta"+response.toString());
			return respuesta;
		} catch (Exception e) {
			logger.error("Exception getInfoByMethod: ",e);
			throw new Exception(e.getMessage());
		}
	}
	
	
	
	/**
	 * Metodo que obtiene el Signature para hacer las peticiones al api de ooyala
	 * @param method_assets
	 * @param parametrosDTO
	 * @return String
	 * 
	 * */
	private String getSignatureOoyalaByMethod(ParametrosDTO parametrosDTO, String method) throws Exception
	{
		String signatureOut="";
		//logger.debug("Inicia getSignatureOoyala..");
		try {
							
			//logger.debug("method: "+method);
			//logger.debug("content_id: "+parametrosDTO.getContent_id());
	
			String requestPath = method.replace("$ASSET_ID$", parametrosDTO.getContent_id());
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("api_key", parametrosDTO.getApi_key());			
			hashMap.put("expires", parametrosDTO.getExpires());
								
			signatureOut = Signature.generateSignature(parametrosDTO.getSecretKey(), "GET", requestPath, hashMap, "");						
			//logger.debug("signature: "+signatureOut);
			return signatureOut;
			
		} catch (Exception e) {
			logger.error("Exception getSignatureOoyalaByMethod: ",e);
			throw new Exception(e.getMessage());
		}
		
	}
}
