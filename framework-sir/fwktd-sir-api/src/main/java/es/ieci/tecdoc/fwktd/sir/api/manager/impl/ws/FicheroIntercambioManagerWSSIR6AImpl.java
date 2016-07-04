package es.ieci.tecdoc.fwktd.sir.api.manager.impl.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.axis.attachments.OctetStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import es.ieci.tecdoc.fwktd.sir.api.manager.AnexoManager;
import es.ieci.tecdoc.fwktd.sir.api.manager.ConfiguracionManager;
import es.ieci.tecdoc.fwktd.sir.api.manager.impl.FicheroIntercambioManagerImpl;
import es.ieci.tecdoc.fwktd.sir.api.service.wssir6a.RespuestaWS;
import es.ieci.tecdoc.fwktd.sir.api.service.wssir6a.WS_SIR6_AService;
import es.ieci.tecdoc.fwktd.sir.api.service.wssir6a.WS_SIR6_A_PortType;
import es.ieci.tecdoc.fwktd.sir.core.exception.SIRException;
import es.ieci.tecdoc.fwktd.sir.core.util.ToStringLoggerHelper;
import es.ieci.tecdoc.fwktd.sir.core.vo.AnexoVO;
import es.ieci.tecdoc.fwktd.sir.core.vo.AsientoRegistralVO;

/**
 * Implementaci�n del manager de env�o de ficheros de datos de intercambio en
 * formato SICRES 3.0 generado por la aplicaci�n de registro. Esta
 * implementaci�n utiliza el servicio web WS_SIR6_B del CIR.
 *
 * @author Iecisa
 * @version $Revision$
 *
 */
public class FicheroIntercambioManagerWSSIR6AImpl extends
		FicheroIntercambioManagerImpl {

	/**
	 * Logger de la clase.
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(FicheroIntercambioManagerWSSIR6AImpl.class);

	private static final String WS_SIR6_A_URL_PARAM_NAME = "WS_SIR6_A.url";
	
	/**
	 * Localizador del servicio WS_SIR6_A
	 */
	private WS_SIR6_AService serviceLocator = null;

	/**
	 * Gestor de anexos.
	 */
	private AnexoManager anexoManager = null;
	
	/**
	 * Gestor de configuraci�n.
	 */
	private ConfiguracionManager configuracionManager = null;

	/**
	 * Constructor.
	 */
	public FicheroIntercambioManagerWSSIR6AImpl() {
		super();
	}

	public AnexoManager getAnexoManager() {
		return anexoManager;
	}

	public void setAnexoManager(AnexoManager anexoManager) {
		this.anexoManager = anexoManager;
	}

	public ConfiguracionManager getConfiguracionManager() {
		return configuracionManager;
	}

	public void setConfiguracionManager(ConfiguracionManager configuracionManager) {
		this.configuracionManager = configuracionManager;
	}

	public WS_SIR6_AService getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(WS_SIR6_AService serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	/**
	 * {@inheritDoc}
	 * @see es.ieci.tecdoc.fwktd.sir.api.manager.impl.FicheroIntercambioManagerImpl#enviar(es.ieci.tecdoc.fwktd.sir.core.vo.AsientoRegistralVO)
	 */
	protected void enviar(AsientoRegistralVO asiento) {

		if (logger.isInfoEnabled()){
			logger.info("Llamada a enviarFicheroIntercambio: [{}]", ToStringLoggerHelper.toStringLogger(asiento));
		}

		Assert.notNull(getServiceLocator(), "'serviceLocator' must not be null");

		RespuestaWS respuesta = null;

		// Componer el XML del fichero de intercambio en formato SICRES 3.0
		// con los documentos fuera del XML (detached)
		String xml = getSicresXMLManager().createXMLFicheroIntercambio(asiento, false);
		logger.debug("XML fichero de intercambio: {}", xml);

		try {
			
			// Env�o del fichero de datos de intercambio con los documentos
			// excluidos del XML (detached)
			respuesta = getService(asiento.getCodigoEntidadRegistralOrigen())
					.recepcionFicheroDeAplicacion(xml,
							getDocumentos(asiento.getAnexos()));
			
		} catch (Exception e) {
			logger.error("Error al enviar el fichero de intercambio: " + ToStringLoggerHelper.toStringLogger(asiento), e);
			throw new SIRException("error.sir.ws.wssir6a", null,
					"Error en la llamada al servicio de recepci�n de ficheros de datos de intercambio (WS_SIR6_A)");
		}
			
		// Comprobar la respuesta del servicio web
		if (respuesta != null) {
			WSSIRHelper.checkRespuesta(respuesta.getCodigo(), respuesta.getDescripcion());
		}
	}

	private OctetStream[] getDocumentos(List<AnexoVO> anexos) {

		List<OctetStream> documentos = new ArrayList<OctetStream>();

		for (AnexoVO anexo : anexos) {
			if (anexo != null) {
				documentos.add(new OctetStream(getAnexoManager()
						.getContenidoAnexo(anexo.getId())));
			}
		}

		return (OctetStream[]) documentos.toArray(new OctetStream[documentos.size()]);
	}
	
	/**
	 * Obtiene el servicio a partir de la entidad registral.
	 * 
	 * @param codEntReg
	 *            C�digo de la Entidad Registral.
	 * @return Servicio.
	 * @throws ServiceException
	 *             si ocurre alg�n error al cargar el servicio.
	 * @throws MalformedURLException
	 *             si la URL del servicio web no es correcta.
	 */
	private WS_SIR6_A_PortType getService(String codEntReg)
			throws MalformedURLException, ServiceException {

		logger.info(
				"Obteniendo el WS_SIR6_A_PortType para la entidad registral [{}]",
				codEntReg);

		// URL del servicio
		String servicePortAddress = getConfiguracionManager()
				.getValorConfiguracion(
						new String[] {
								codEntReg + "." + WS_SIR6_A_URL_PARAM_NAME,
								WS_SIR6_A_URL_PARAM_NAME });
		logger.info("URL obtenida para el servicio web WS_SIR6_A: {}",
				servicePortAddress);

		return getServiceLocator().getWS_SIR6_A(new URL(servicePortAddress));
	}
}
