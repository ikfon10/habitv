//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.22 at 02:42:30 PM CEST 
//


package com.dabi.habitv.provider.canalplus.mea.entities;

import java.math.BigInteger;
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
 *         &lt;element ref="{}DESCRIPTION"/>
 *         &lt;element ref="{}NB_VUES"/>
 *         &lt;element ref="{}NOTE"/>
 *         &lt;element ref="{}TITRAGE"/>
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
    "description",
    "nbvues",
    "note",
    "titrage"
})
@XmlRootElement(name = "INFOS")
public class INFOS {

    @XmlElement(name = "DESCRIPTION", required = true)
    protected String description;
    @XmlElement(name = "NB_VUES", required = true)
    protected BigInteger nbvues;
    @XmlElement(name = "NOTE", required = true)
    protected NOTE note;
    @XmlElement(name = "TITRAGE", required = true)
    protected TITRAGE titrage;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDESCRIPTION() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDESCRIPTION(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the nbvues property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNBVUES() {
        return nbvues;
    }

    /**
     * Sets the value of the nbvues property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNBVUES(BigInteger value) {
        this.nbvues = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link NOTE }
     *     
     */
    public NOTE getNOTE() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link NOTE }
     *     
     */
    public void setNOTE(NOTE value) {
        this.note = value;
    }

    /**
     * Gets the value of the titrage property.
     * 
     * @return
     *     possible object is
     *     {@link TITRAGE }
     *     
     */
    public TITRAGE getTITRAGE() {
        return titrage;
    }

    /**
     * Sets the value of the titrage property.
     * 
     * @param value
     *     allowed object is
     *     {@link TITRAGE }
     *     
     */
    public void setTITRAGE(TITRAGE value) {
        this.titrage = value;
    }

}
