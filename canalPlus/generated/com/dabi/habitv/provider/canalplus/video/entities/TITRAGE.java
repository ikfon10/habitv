//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.17 at 10:59:08 AM CET 
//


package com.dabi.habitv.provider.canalplus.video.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &lt;element ref="{}TITRE"/>
 *         &lt;element ref="{}SOUS_TITRE"/>
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
    "titre",
    "soustitre"
})
@XmlRootElement(name = "TITRAGE")
public class TITRAGE {

    @XmlElement(name = "TITRE", required = true)
    protected String titre;
    @XmlElement(name = "SOUS_TITRE", required = true)
    protected String soustitre;

    /**
     * Gets the value of the titre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTITRE() {
        return titre;
    }

    /**
     * Sets the value of the titre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTITRE(String value) {
        this.titre = value;
    }

    /**
     * Gets the value of the soustitre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSOUSTITRE() {
        return soustitre;
    }

    /**
     * Sets the value of the soustitre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSOUSTITRE(String value) {
        this.soustitre = value;
    }

}
