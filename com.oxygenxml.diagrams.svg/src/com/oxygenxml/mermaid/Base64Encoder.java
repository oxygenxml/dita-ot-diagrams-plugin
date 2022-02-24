package com.oxygenxml.mermaid;

import java.util.Base64;

import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.lib.ExtensionFunctionCall;
import net.sf.saxon.lib.ExtensionFunctionDefinition;
import net.sf.saxon.om.Sequence;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.value.SequenceType;
import net.sf.saxon.value.StringValue;

public class Base64Encoder extends ExtensionFunctionDefinition {

	  @Override
	  public SequenceType[] getArgumentTypes() {
	    return new SequenceType[] { SequenceType.SINGLE_STRING};
	  }

	  @Override
	  public StructuredQName getFunctionQName() {
	    return new StructuredQName("Base64Encoder", "java:com.oxygenxml.mermaid.Base64Encoder", "encode");
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
					String mermaidContent = ((StringValue) arguments[0].iterate().next()).getStringValue();
					String encodedString = Base64.getEncoder().encodeToString(mermaidContent.getBytes("UTF-8"));
					return StringValue.makeStringValue(encodedString);
				} catch (Exception ex) {
					throw new XPathException(ex);
				}
			}
		};
	}

}
