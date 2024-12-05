package com.oxygenxml.mermaid;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.lib.ExtensionFunctionCall;
import net.sf.saxon.lib.ExtensionFunctionDefinition;
import net.sf.saxon.om.Sequence;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.value.BooleanValue;
import net.sf.saxon.value.SequenceType;
import net.sf.saxon.value.StringValue;

public class URLUtils extends ExtensionFunctionDefinition {

	@Override
	public SequenceType[] getArgumentTypes() {
		return new SequenceType[] { SequenceType.SINGLE_STRING };
	}

	@Override
	public StructuredQName getFunctionQName() {
		return new StructuredQName("URLUtils", "java:com.oxygenxml.mermaid.URLUtils", "check");
	}

	@Override
	public SequenceType getResultType(SequenceType[] suppliedArgumentTypes) {
		return SequenceType.SINGLE_BOOLEAN;
	}

	@Override
	public ExtensionFunctionCall makeCallExpression() {
		return new ExtensionFunctionCall() {

			@Override
			public Sequence call(XPathContext arg0, Sequence[] arguments) throws XPathException {
				try {
					String urlString = ((StringValue) arguments[0].iterate().next()).getStringValue();
					HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
					connection.setRequestMethod("HEAD");
					connection.setConnectTimeout(3000);
					connection.setReadTimeout(3000);
					int responseCode = connection.getResponseCode();
					connection.disconnect();
					return BooleanValue.get(responseCode >= 200 && responseCode < 400);
				} catch (IOException e) {
					return BooleanValue.FALSE;
				}
			}
		};
	}

}
