//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.23 at 08:46:09 AM CEST 
//


package com.dabi.habitv.provider.canalplus.video.entities;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}ID"/>
 *         &lt;element ref="{}TYPE"/>
 *         &lt;element ref="{}INFOS"/>
 *         &lt;element ref="{}CHAINE"/>
 *         &lt;element ref="{}RUBRIQUAGE"/>
 *         &lt;element ref="{}MEDIA"/>
 *         &lt;element ref="{}PUB"/>
 *         &lt;element ref="{}URL"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "type",
    "infos",
    "chaine",
    "rubriquage",
    "media",
    "pub",
    "url"
})
@XmlRootElement(name = "VIDEO")
public class VIDEO {

    @XmlElement(name = "ID", required = true)
    protected BigInteger id;
    @XmlElement(name = "TYPE", required = true)
    protected String type;
    @XmlElement(name = "INFOS", required = true)
    protected INFOS infos;
    @XmlElement(name = "CHAINE", required = true)
    protected BigInteger chaine;
    @XmlElement(name = "RUBRIQUAGE", required = true)
    protected RUBRIQUAGE rubriquage;
    @XmlElement(name = "MEDIA", required = true)
    protected MEDIA media;
    @XmlElement(name = "PUB", required = true)
    protected PUB pub;
    @XmlElement(name = "URL", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String url;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setID(BigInteger value) {
        this.id = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTYPE() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTYPE(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the infos property.
     * 
     * @return
     *     possible object is
     *     {@link INFOS }
     *     
     */
    public INFOS getINFOS() {
        return infos;
    }

    /**
     * Sets the value of the infos property.
     * 
     * @param value
     *     allowed object is
     *     {@link INFOS }
     *     
     */
    public void setINFOS(INFOS value) {
        this.infos = value;
    }

    /**
     * Gets the value of the chaine property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCHAINE() {
        return chaine;
    }

    /**
     * Sets the value of the chaine property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCHAINE(BigInteger value) {
        this.chaine = value;
    }

    /**
     * Gets the value of the rubriquage property.
     * 
     * @return
     *     possible object is
     *     {@link RUBRIQUAGE }
     *     
     */
    public RUBRIQUAGE getRUBRIQUAGE() {
        return rubriquage;
    }

    /**
     * Sets the value of the rubriquage property.
     * 
     * @param value
     *     allowed object is
     *     {@link RUBRIQUAGE }
     *     
     */
    public void setRUBRIQUAGE(RUBRIQUAGE value) {
        this.rubriquage = value;
    }

    /**
     * Gets the value of the media property.
     * 
     * @return
     *     possible object is
     *     {@link MEDIA }
     *     
     */
    public MEDIA getMEDIA() {
        return media;
    }

    /**
     * Sets the value of the media property.
     * 
     * @param value
     *     allowed object is
     *     {@link MEDIA }
     *     
     */
    public void setMEDIA(MEDIA value) {
        this.media = value;
    }

    /**
     * Gets the value of the pub property.
     * 
     * @return
     *     possible object is
     *     {@link PUB }
     *     
     */
    public PUB getPUB() {
        return pub;
    }

    /**
     * Sets the value of the pub property.
     * 
     * @param value
     *     allowed object is
     *     {@link PUB }
     *     
     */
    public void setPUB(PUB value) {
        this.pub = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURL() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURL(String value) {
        this.url = value;
    }

}
