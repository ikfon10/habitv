//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.04 at 09:08:41 AM CET 
//


package com.dabi.habitv.provider.d17.ep;

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
 *         &lt;element ref="{}preroll_url"/>
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
    "prerollUrl"
})
@XmlRootElement(name = "preroll")
public class Preroll {

    @XmlElement(name = "preroll_url", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String prerollUrl;

    /**
     * Gets the value of the prerollUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrerollUrl() {
        return prerollUrl;
    }

    /**
     * Sets the value of the prerollUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrerollUrl(String value) {
        this.prerollUrl = value;
    }

}
