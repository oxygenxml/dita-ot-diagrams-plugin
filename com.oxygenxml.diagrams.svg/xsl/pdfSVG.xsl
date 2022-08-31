<?xml version="1.0" encoding="UTF-8"?>
<!--
    
Oxygen Latex to SVG sample conversion plugin
Copyright (c) 1998-2019 Syncro Soft SRL, Romania.  All rights reserved.
Licensed under the terms stated in the license file LICENSE 
available in the base directory of this plugin.

-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0"
    xmlns:saxon="http://saxon.sf.net/" xmlns:converter="java:com.oxygenxml.plantuml.svg.PlantumlToSVG"
    xmlns:base64Encoder="java:com.oxygenxml.mermaid.Base64Encoder"
    xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:param name="plantuml.include.path"/>
    <!-- Plant UML -->
    <xsl:template
        match="*[contains(@class, ' topic/foreign ')][contains(@outputclass, 'embed-plant-uml')] | *[contains(@class, ' topic/plant-uml ')]"
        priority="10">
        <fo:inline>
            <xsl:call-template name="commonattributes"/>
            <fo:instream-foreign-object>
              <xsl:copy-of select="parse-xml(converter:convert(string-join(text(), ''), $plantuml.include.path))"
                    use-when="not(function-available('saxon:parse'))"/>
              <xsl:copy-of select="saxon:parse(converter:convert(string-join(text(), ''), $plantuml.include.path)"
                    use-when="function-available('saxon:parse')"/>
            </fo:instream-foreign-object>
        </fo:inline>
    </xsl:template>
    
    <!-- Mermaid -->
    <xsl:template match="*[contains(@class, ' topic/foreign ')][contains(@outputclass, 'embed-mermaid-diagram')] | *[contains(@class, ' topic/mermaid-diagram ')]" priority="10">
        <fo:inline>
            <xsl:call-template name="commonattributes"/>
          <fo:external-graphic src="{concat('https://mermaid.ink/img/', base64Encoder:encode(string-join(text(), '')))}"/>
        </fo:inline>
    </xsl:template>
</xsl:stylesheet>
