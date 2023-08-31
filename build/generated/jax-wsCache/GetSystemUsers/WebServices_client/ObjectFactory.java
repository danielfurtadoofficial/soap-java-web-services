
package WebServices_client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the WebServices_client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllSystemUsers_QNAME = new QName("http://WebServices/", "GetAllSystemUsers");
    private final static QName _GetAllSystemUsersResponse_QNAME = new QName("http://WebServices/", "GetAllSystemUsersResponse");
    private final static QName _Login_QNAME = new QName("http://WebServices/", "Login");
    private final static QName _LoginResponse_QNAME = new QName("http://WebServices/", "LoginResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: WebServices_client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllSystemUsers }
     * 
     */
    public GetAllSystemUsers createGetAllSystemUsers() {
        return new GetAllSystemUsers();
    }

    /**
     * Create an instance of {@link GetAllSystemUsersResponse }
     * 
     */
    public GetAllSystemUsersResponse createGetAllSystemUsersResponse() {
        return new GetAllSystemUsersResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllSystemUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "GetAllSystemUsers")
    public JAXBElement<GetAllSystemUsers> createGetAllSystemUsers(GetAllSystemUsers value) {
        return new JAXBElement<GetAllSystemUsers>(_GetAllSystemUsers_QNAME, GetAllSystemUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllSystemUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "GetAllSystemUsersResponse")
    public JAXBElement<GetAllSystemUsersResponse> createGetAllSystemUsersResponse(GetAllSystemUsersResponse value) {
        return new JAXBElement<GetAllSystemUsersResponse>(_GetAllSystemUsersResponse_QNAME, GetAllSystemUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "Login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "LoginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

}
