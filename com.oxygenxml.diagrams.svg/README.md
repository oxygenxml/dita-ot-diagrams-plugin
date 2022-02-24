# dita-diagrams-svg
DITA Open Toolkit plugin which allows publishing embedded Mermaid and Plant UML diagrams to HTML and PDF.

http://plantuml.com/index

https://mermaid-js.github.io

The plugin was tested and developed with DITA OT 3.7

If you set the **@outputclass="embed-mermaid-diagram"** attribute on a DITA &lt;foreign> element the plugin will attempt to convert it to SVG using the free Mermaid web site converter.

If you set the **@outputclass="embed-plant-uml"** attribute on a DITA &lt;foreign> element the plugin will attempt to convert it to SVG using the PlantUML project..

The "samples" folder contains a sample DITA topic with a Plant UML equation which is properly displayed when converted to HTML and PDF-based outputs.
  
Copyright and License
---------------------
Copyright 2019 Syncro Soft SRL.

This project is licensed under [Apache License 2.0](https://github.com/oxygenxml/dita-plant-uml/blob/master/LICENSE).
