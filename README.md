# dita-diagrams-svg
DITA Open Toolkit plugin which allows publishing embedded Plant UML and Mermaid diagrams to HTML and PDF.

http://plantuml.com/index

https://mermaid-js.github.io

The plugin was tested and developed with DITA OT 3.7

If you set the **@outputclass="embed-mermaid-diagram"** attribute on a DITA &lt;foreign> element the plugin will attempt to convert it to SVG (for HTML-based output) or to a binary image format for PDF output using the free Mermaid web site converter.

If you set the **@outputclass="embed-plant-uml"** attribute on a DITA &lt;foreign> element the plugin will attempt to convert it to SVG using the PlantUML project..

The "samples" folder contains a sample DITA topic with a Plant UML equation which is properly displayed when converted to HTML and PDF-based outputs.
  
![Preview Plant UML as SVG](previewPlantUML.png)

## Installation
This plugin is not bundled with the publishing engine inside an Oxygen XML Editor/Author installation.
The DITA OT plugin folder **com.oxygenxml.diagrams.svg** can be manually downloaded, copied to a DITA Open Toolkit **plugins** folder and then installed using the **dita --install** command line. If you are publishing from Oxygen XML Editor, manual installation steps for plugins can be found here: https://www.oxygenxml.com/doc/ug-editor/topics/dita-ot-install-plugin.html

## Security
The PlantUML-based diagram generation is done by using local libraries without contacting any remote server.  
The Mermaid-based diagram generator is done by sending the diagram in base 64 form to the Mermaid service (https://mermaid.ink) and obtaining from that web service the corresponding image.
  
Copyright and License
---------------------
Copyright 2022 Syncro Soft SRL.

This project is licensed under [Apache License 2.0](https://github.com/oxygenxml/dita-ot-diagrams-plugin/blob/master/LICENSE).
The plugin contains a Java library provided by the PlantUML project under the Apache 2.0 License.
