//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.15 at 07:37:24 PM CEST 
//


package com.dabi.habitv.provider.canalplus.initplayer.entities;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dabi.habitv.provider.canalplus.initplayer.entities package. 
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

    private final static QName _URLRACINE_QNAME = new QName("", "URL_RACINE");
    private final static QName _COULEUR_QNAME = new QName("", "COULEUR");
    private final static QName _ID_QNAME = new QName("", "ID");
    private final static QName _PAVE_QNAME = new QName("", "PAVE");
    private final static QName _NOM_QNAME = new QName("", "NOM");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dabi.habitv.provider.canalplus.initplayer.entities
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PUB }
     * 
     */
    public PUB createPUB() {
        return new PUB();
    }

    /**
     * Create an instance of {@link UNIVERS }
     * 
     */
    public UNIVERS createUNIVERS() {
        return new UNIVERS();
    }

    /**
     * Create an instance of {@link MESVIDEOS }
     * 
     */
    public MESVIDEOS createMESVIDEOS() {
        return new MESVIDEOS();
    }

    /**
     * Create an instance of {@link INTERSTITIEL }
     * 
     */
    public INTERSTITIEL createINTERSTITIEL() {
        return new INTERSTITIEL();
    }

    /**
     * Create an instance of {@link SELECTIONS }
     * 
     */
    public SELECTIONS createSELECTIONS() {
        return new SELECTIONS();
    }

    /**
     * Create an instance of {@link RUBRIQUAGE }
     * 
     */
    public RUBRIQUAGE createRUBRIQUAGE() {
        return new RUBRIQUAGE();
    }

    /**
     * Create an instance of {@link THEMATIQUE }
     * 
     */
    public THEMATIQUE createTHEMATIQUE() {
        return new THEMATIQUE();
    }

    /**
     * Create an instance of {@link INITPLAYER }
     * 
     */
    public INITPLAYER createINITPLAYER() {
        return new INITPLAYER();
    }

    /**
     * Create an instance of {@link RECHERCHE }
     * 
     */
    public RECHERCHE createRECHERCHE() {
        return new RECHERCHE();
    }

    /**
     * Create an instance of {@link THEMATIQUES }
     * 
     */
    public THEMATIQUES createTHEMATIQUES() {
        return new THEMATIQUES();
    }

    /**
     * Create an instance of {@link SELECTION }
     * 
     */
    public SELECTION createSELECTION() {
        return new SELECTION();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "URL_RACINE")
    public JAXBElement<String> createURLRACINE(String value) {
        return new JAXBElement<String>(_URLRACINE_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "COULEUR")
    public JAXBElement<String> createCOULEUR(String value) {
        return new JAXBElement<String>(_COULEUR_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ID")
    public JAXBElement<BigInteger> createID(BigInteger value) {
        return new JAXBElement<BigInteger>(_ID_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "PAVE")
    public JAXBElement<String> createPAVE(String value) {
        return new JAXBElement<String>(_PAVE_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "NOM")
    public JAXBElement<String> createNOM(String value) {
        return new JAXBElement<String>(_NOM_QNAME, String.class, null, value);
    }

}
