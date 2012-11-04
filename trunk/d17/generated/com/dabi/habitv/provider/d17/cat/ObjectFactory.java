//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.04 at 09:08:41 AM CET 
//


package com.dabi.habitv.provider.d17.cat;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dabi.habitv.provider.d17.cat package. 
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

    private final static QName _ProgramsVisuelIpadPortrait_QNAME = new QName("", "programs_visuel_ipad_portrait");
    private final static QName _VisuelIpadPortrait_QNAME = new QName("", "visuel_ipad_portrait");
    private final static QName _VisuelIphone_QNAME = new QName("", "visuel_iphone");
    private final static QName _ProgramsVisuelIpadPaysage_QNAME = new QName("", "programs_visuel_ipad_paysage");
    private final static QName _NbComm_QNAME = new QName("", "nb_comm");
    private final static QName _VisuelIpadPaysage_QNAME = new QName("", "visuel_ipad_paysage");
    private final static QName _Note_QNAME = new QName("", "note");
    private final static QName _Intitule_QNAME = new QName("", "intitule");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dabi.habitv.provider.d17.cat
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Program }
     * 
     */
    public Program createProgram() {
        return new Program();
    }

    /**
     * Create an instance of {@link Resume }
     * 
     */
    public Resume createResume() {
        return new Resume();
    }

    /**
     * Create an instance of {@link Programs }
     * 
     */
    public Programs createPrograms() {
        return new Programs();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "programs_visuel_ipad_portrait")
    public JAXBElement<String> createProgramsVisuelIpadPortrait(String value) {
        return new JAXBElement<String>(_ProgramsVisuelIpadPortrait_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "visuel_ipad_portrait")
    public JAXBElement<String> createVisuelIpadPortrait(String value) {
        return new JAXBElement<String>(_VisuelIpadPortrait_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "visuel_iphone")
    public JAXBElement<String> createVisuelIphone(String value) {
        return new JAXBElement<String>(_VisuelIphone_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "programs_visuel_ipad_paysage")
    public JAXBElement<String> createProgramsVisuelIpadPaysage(String value) {
        return new JAXBElement<String>(_ProgramsVisuelIpadPaysage_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "nb_comm")
    public JAXBElement<BigInteger> createNbComm(BigInteger value) {
        return new JAXBElement<BigInteger>(_NbComm_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "visuel_ipad_paysage")
    public JAXBElement<String> createVisuelIpadPaysage(String value) {
        return new JAXBElement<String>(_VisuelIpadPaysage_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "note")
    public JAXBElement<BigInteger> createNote(BigInteger value) {
        return new JAXBElement<BigInteger>(_Note_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "intitule")
    public JAXBElement<String> createIntitule(String value) {
        return new JAXBElement<String>(_Intitule_QNAME, String.class, null, value);
    }

}
