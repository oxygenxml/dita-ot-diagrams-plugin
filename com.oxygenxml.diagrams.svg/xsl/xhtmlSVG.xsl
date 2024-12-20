<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Oxygen Latex to SVG sample conversion plugin
    Copyright (c) 1998-2024 Syncro Soft SRL, Romania.  All rights reserved.
    Licensed under the terms stated in the license file LICENSE
    available in the base directory of this plugin.
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:saxon="http://saxon.sf.net/"
  xmlns:converter="java:com.oxygenxml.plantuml.svg.PlantumlToSVG"
  xmlns:base64Encoder="java:com.oxygenxml.mermaid.Base64Encoder"
  xmlns:urlUtils="java:com.oxygenxml.mermaid.URLUtils"
  exclude-result-prefixes="#all"
  version="3.0">

  <xsl:param name="transtype"/>
  <xsl:param name="plantuml.include.path"/>

  <!-- Plant UML -->
  <xsl:template match="*[contains(@class, ' topic/foreign ')][contains(@outputclass, 'embed-plant-uml')] | *[contains(@class, ' topic/plant-uml ')]" priority="10">
    <span>
      <xsl:call-template name="commonattributes"/>
      <xsl:copy-of select="parse-xml(converter:convert(string-join(text(), ''), $plantuml.include.path))" use-when="not(function-available('saxon:parse'))"/>
      <xsl:copy-of select="saxon:parse(converter:convert(string-join(text(), ''), $plantuml.include.path)" use-when="function-available('saxon:parse')"/>
    </span>
  </xsl:template>

  <!-- Mermaid -->
  <xsl:template match="*[contains(@class, ' topic/foreign ')][contains(@outputclass, 'embed-mermaid-diagram')] | *[contains(@class, ' topic/mermaid-diagram ')]" priority="10">
    <span>
      <xsl:call-template name="commonattributes"/>
      <xsl:choose>
        <xsl:when test="urlUtils:checkHTTPResourceAccessible('https://mermaid.ink')">
          <xsl:choose>
            <xsl:when test="starts-with($transtype, 'pdf-css-html5')">
              <img src="{concat('https://mermaid.ink/img/', base64Encoder:encode(string-join(text(), '')))}"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:copy-of select="document(concat('https://mermaid.ink/svg/', base64Encoder:encode(string-join(text(), ''))))"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:when>
        <xsl:otherwise>
          <xsl:message>[OXYXX001W][WARN] Cannot generate Mermaid diagram, please check you have access to https://mermaid.ink.</xsl:message>
        </xsl:otherwise>
      </xsl:choose>
    </span>
  </xsl:template>
</xsl:stylesheet>
