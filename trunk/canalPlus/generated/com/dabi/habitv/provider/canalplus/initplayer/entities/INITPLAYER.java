//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.22 at 02:42:30 PM CEST 
//


package com.dabi.habitv.provider.canalplus.initplayer.entities;

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
 *         &lt;element ref="{}RECHERCHE"/>
 *         &lt;element ref="{}MES_VIDEOS"/>
 *         &lt;element ref="{}THEMATIQUES"/>
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
    "recherche",
    "mesvideos",
    "thematiques"
})
@XmlRootElement(name = "INIT_PLAYER")
public class INITPLAYER {

    @XmlElement(name = "RECHERCHE", required = true)
    protected RECHERCHE recherche;
    @XmlElement(name = "MES_VIDEOS", required = true)
    protected MESVIDEOS mesvideos;
    @XmlElement(name = "THEMATIQUES", required = true)
    protected THEMATIQUES thematiques;

    /**
     * Gets the value of the recherche property.
     * 
     * @return
     *     possible object is
     *     {@link RECHERCHE }
     *     
     */
    public RECHERCHE getRECHERCHE() {
        return recherche;
    }

    /**
     * Sets the value of the recherche property.
     * 
     * @param value
     *     allowed object is
     *     {@link RECHERCHE }
     *     
     */
    public void setRECHERCHE(RECHERCHE value) {
        this.recherche = value;
    }

    /**
     * Gets the value of the mesvideos property.
     * 
     * @return
     *     possible object is
     *     {@link MESVIDEOS }
     *     
     */
    public MESVIDEOS getMESVIDEOS() {
        return mesvideos;
    }

    /**
     * Sets the value of the mesvideos property.
     * 
     * @param value
     *     allowed object is
     *     {@link MESVIDEOS }
     *     
     */
    public void setMESVIDEOS(MESVIDEOS value) {
        this.mesvideos = value;
    }

    /**
     * Gets the value of the thematiques property.
     * 
     * @return
     *     possible object is
     *     {@link THEMATIQUES }
     *     
     */
    public THEMATIQUES getTHEMATIQUES() {
        return thematiques;
    }

    /**
     * Sets the value of the thematiques property.
     * 
     * @param value
     *     allowed object is
     *     {@link THEMATIQUES }
     *     
     */
    public void setTHEMATIQUES(THEMATIQUES value) {
        this.thematiques = value;
    }

}
