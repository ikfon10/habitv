//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.14 at 08:38:14 PM CEST 
//


package com.dabi.habitv.provider.canalplus.mea.entities;

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
 *         &lt;element ref="{}GRAND"/>
 *         &lt;element ref="{}PETIT"/>
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
    "grand",
    "petit"
})
@XmlRootElement(name = "IMAGES")
public class IMAGES {

    @XmlElement(name = "GRAND", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String grand;
    @XmlElement(name = "PETIT", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String petit;

    /**
     * Gets the value of the grand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGRAND() {
        return grand;
    }

    /**
     * Sets the value of the grand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGRAND(String value) {
        this.grand = value;
    }

    /**
     * Gets the value of the petit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPETIT() {
        return petit;
    }

    /**
     * Sets the value of the petit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPETIT(String value) {
        this.petit = value;
    }

}
