/*
* Copyright 2016 Ministerio de Sanidad, Servicios Sociales e Igualdad 
* Licencia con arreglo a la EUPL, Versi�n 1.1 o �en cuanto sean aprobadas por laComisi�n Europea� versiones posteriores de la EUPL (la �Licencia�); 
* Solo podr� usarse esta obra si se respeta la Licencia. 
* Puede obtenerse una copia de la Licencia en: 
* http://joinup.ec.europa.eu/software/page/eupl/licence-eupl 
* Salvo cuando lo exija la legislaci�n aplicable o se acuerde por escrito, el programa distribuido con arreglo a la Licencia se distribuye �TAL CUAL�, SIN GARANT�AS NI CONDICIONES DE NING�N TIPO, ni expresas ni impl�citas. 
* V�ase la Licencia en el idioma concreto que rige los permisos y limitaciones que establece la Licencia. 
*/

package es.map.directorio.manager.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.6 in JDK 6 Generated
 * source version: 2.1
 * 
 */
@WebServiceClient(name = "SD02OF_DescargaOficinasService",
    targetNamespace = "http://impl.manager.directorio.map.es",
    wsdlLocation = "http://dir3ws.redsara.es/directorio/services/SD02OF_DescargaOficinas?wsdl")
public class SD02OFDescargaOficinasService extends Service {

    private final static URL SD02OFDESCARGAOFICINASSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger
	.getLogger(es.map.directorio.manager.impl.SD02OFDescargaOficinasService.class.getName());

    static {
	URL url = null;
	try {
	    URL baseUrl;
	    baseUrl =
		es.map.directorio.manager.impl.SD02OFDescargaOficinasService.class.getResource(".");
	    url =
		new URL(
		    baseUrl,
		    "http://dir3ws.redsara.es/directorio/services/SD02OF_DescargaOficinas?wsdl");
	}
	catch (MalformedURLException e) {
	    logger
		.warning("Failed to create URL for the wsdl Location: 'http://dir3ws.redsara.es/directorio/services/SD02OF_DescargaOficinas?wsdl', retrying as a local file");
	    logger.warning(e.getMessage());
	}
	SD02OFDESCARGAOFICINASSERVICE_WSDL_LOCATION = url;
    }

    public SD02OFDescargaOficinasService(URL wsdlLocation, QName serviceName) {
	super(wsdlLocation, serviceName);
    }

    public SD02OFDescargaOficinasService() {
	super(SD02OFDESCARGAOFICINASSERVICE_WSDL_LOCATION, new QName(
	    "http://impl.manager.directorio.map.es", "SD02OF_DescargaOficinasService"));
    }

    /**
     * 
     * @return returns SD02OFDescargaOficinas
     */
    @WebEndpoint(name = "SD02OF_DescargaOficinas")
    public SD02OFDescargaOficinas getSD02OFDescargaOficinas() {
	return super.getPort(
	    new QName(
		"http://impl.manager.directorio.map.es", "SD02OF_DescargaOficinas"),
	    SD02OFDescargaOficinas.class);
    }

    /**
     * 
     * @param features
     *            A list of {@link javax.xml.ws.WebServiceFeature} to configure
     *            on the proxy. Supported features not in the
     *            <code>features</code> parameter will have their default
     *            values.
     * @return returns SD02OFDescargaOficinas
     */
    @WebEndpoint(name = "SD02OF_DescargaOficinas")
    public SD02OFDescargaOficinas getSD02OFDescargaOficinas(
	WebServiceFeature... features) {
	return super.getPort(
	    new QName(
		"http://impl.manager.directorio.map.es", "SD02OF_DescargaOficinas"),
	    SD02OFDescargaOficinas.class, features);
    }

}
