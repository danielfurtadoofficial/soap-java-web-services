
package WebServices_client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "GetSystemUsers", targetNamespace = "http://WebServices/", wsdlLocation = "http://localhost:8080/ProjOficina/GetSystemUsers?wsdl")
public class GetSystemUsers
    extends Service
{

    private final static URL GETSYSTEMUSERS_WSDL_LOCATION;
    private final static WebServiceException GETSYSTEMUSERS_EXCEPTION;
    private final static QName GETSYSTEMUSERS_QNAME = new QName("http://WebServices/", "GetSystemUsers");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/ProjOficina/GetSystemUsers?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        GETSYSTEMUSERS_WSDL_LOCATION = url;
        GETSYSTEMUSERS_EXCEPTION = e;
    }

    public GetSystemUsers() {
        super(__getWsdlLocation(), GETSYSTEMUSERS_QNAME);
    }

    public GetSystemUsers(WebServiceFeature... features) {
        super(__getWsdlLocation(), GETSYSTEMUSERS_QNAME, features);
    }

    public GetSystemUsers(URL wsdlLocation) {
        super(wsdlLocation, GETSYSTEMUSERS_QNAME);
    }

    public GetSystemUsers(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, GETSYSTEMUSERS_QNAME, features);
    }

    public GetSystemUsers(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GetSystemUsers(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SystemUsersServices
     */
    @WebEndpoint(name = "SystemUsersServicesPort")
    public SystemUsersServices getSystemUsersServicesPort() {
        return super.getPort(new QName("http://WebServices/", "SystemUsersServicesPort"), SystemUsersServices.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SystemUsersServices
     */
    @WebEndpoint(name = "SystemUsersServicesPort")
    public SystemUsersServices getSystemUsersServicesPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://WebServices/", "SystemUsersServicesPort"), SystemUsersServices.class, features);
    }

    private static URL __getWsdlLocation() {
        if (GETSYSTEMUSERS_EXCEPTION!= null) {
            throw GETSYSTEMUSERS_EXCEPTION;
        }
        return GETSYSTEMUSERS_WSDL_LOCATION;
    }

}
