# dita-plant-uml
DITA Open Toolkit plugin which allows publishing embedded Plant UML diagrams to HTML and PDF.

http://plantuml.com/index

The plugin was tested and developed with DITA OT 3.2.1.

To make the plugin work you need to download the "plantuml.jar" library and copy it to the "com.oxygenxml.plantuml.svg\lib" folder.
http://plantuml.com/download

If you set the @outputclass="embed-plant-uml" attribute on a DITA <foreign> element the plugin will attempt to convert it to SVG.
The "samples" folder contains a sample DITA topic with a Plant UML equation which is properly displayed when converted to HTML and PDF-based outputs.
  
![Preview Plant UML as SVG](previewPlantUML.png)
  
Copyright and License
---------------------
Copyright 2019 Syncro Soft SRL.

This project is licensed under [Apache License 2.0](https://github.com/oxygenxml/dita-latex/blob/master/LICENSE).
The plugin contains a Java library provided by the PlantUML under the Apache 2.0 License.
