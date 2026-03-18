# Test PlantUML diagram in markdown

The following is a diagram

<p>
    <foreign outputclass="embed-plant-uml">@startuml
	Alice -> Bob: Authentication Request
	Bob --> Alice: Authentication Response
	Alice -> Bob: Another authentication Request
	Alice &lt;-- Bob: Another authentication Response
	    @enduml</foreign>
</p>
