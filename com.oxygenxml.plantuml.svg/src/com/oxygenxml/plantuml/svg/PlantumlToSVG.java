package com.oxygenxml.plantuml.svg;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.lib.ExtensionFunctionCall;
import net.sf.saxon.lib.ExtensionFunctionDefinition;
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
	    return new SequenceType[] { SequenceType.SINGLE_STRING};
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

					SourceStringReader reader = new SourceStringReader(umlContent);
					final ByteArrayOutputStream os = new ByteArrayOutputStream();
					// Write the first image to "os"
					reader.outputImage(os, new FileFormatOption(FileFormat.SVG));
					os.close();

					// The XML is stored into svg
					final String svg = new String(os.toByteArray(), Charset.forName("UTF-8"));

					return StringValue.makeStringValue(svg);
				} catch (Exception ex) {
					throw new XPathException(ex);
				}
			}
		};
	}

}