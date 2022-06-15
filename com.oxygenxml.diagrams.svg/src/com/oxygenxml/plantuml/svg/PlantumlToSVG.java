package com.oxygenxml.plantuml.svg;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;

import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.lib.ExtensionFunctionCall;
import net.sf.saxon.lib.ExtensionFunctionDefinition;
import net.sf.saxon.om.NodeInfo;
import net.sf.saxon.om.Sequence;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.value.SequenceType;
import net.sf.saxon.value.StringValue;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

public class PlantumlToSVG extends ExtensionFunctionDefinition {

	  @Override
	  public SequenceType[] getArgumentTypes() {
	    return new SequenceType[] { SequenceType.SINGLE_STRING, SequenceType.OPTIONAL_STRING};
	  }

	  @Override
	  public StructuredQName getFunctionQName() {
	    return new StructuredQName("PlantUMLToSVG", "java:com.oxygenxml.plantuml.svg.PlantumlToSVG", "convert");
	  }

	  @Override
	  public SequenceType getResultType(SequenceType[] suppliedArgumentTypes) {
	    return SequenceType.SINGLE_STRING;
	  }

	@Override
	public ExtensionFunctionCall makeCallExpression() {
		return new ExtensionFunctionCall() {

			@Override
			public Sequence call(XPathContext arg0, Sequence[] arguments) throws XPathException {
				try {
					String umlContent = ((StringValue) arguments[0].iterate().next()).getStringValue();
					boolean pathWasSet = false;
					if(arguments.length > 1) {
						//The include path is also given here
						String umlIncludePath = ((StringValue) arguments[1].iterate().next()).getStringValue();
						if(umlIncludePath != null && ! umlIncludePath.isEmpty()) {
							java.lang.System.setProperty("plantuml.include.path", umlIncludePath);
							pathWasSet = true;
						}
					}
					if(!pathWasSet && arg0.getContextItem() instanceof NodeInfo){
						//Use the base URI location.
						String baseURI = ((NodeInfo)arg0.getContextItem()).getBaseURI();
						if(baseURI != null) {
							File newCurrentDir = new File(new URL(baseURI).toURI()).getParentFile();
							java.lang.System.setProperty("plantuml.include.path", newCurrentDir.getAbsolutePath());
						}
					}

					SourceStringReader reader = new SourceStringReader(umlContent);
					final ByteArrayOutputStream os = new ByteArrayOutputStream();
					// Write the first image to "os"
					reader.outputImage(os, new FileFormatOption(FileFormat.SVG, false));
					os.close();
					// The XML is stored into svg
					String svg = new String(os.toByteArray(), Charset.forName("UTF-8")).replace("xmlns=\"\"", "");
					return StringValue.makeStringValue(svg);
				} catch (Exception ex) {
					throw new XPathException(ex);
				}
			}
		};
	}

}
