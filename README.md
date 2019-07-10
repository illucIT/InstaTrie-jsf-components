InstaTrie Components for JSF
====================================

[![Maven Central](https://img.shields.io/maven-central/v/com.illucit/instatrie-jsf-components.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.illucit%22%20AND%20a:%22instatrie-jsf-components%22)

About
-----

This package provides some JSF (Java Server Faces) components for the [InstaTrie](https://github.com/illucIT/InstaTrie) project.

**Authors**: Christian Simon <[simon@illucit.com](mailto:simon@illucit.com)>, Christian Ott <[ott@illucit.com](mailto:ott@illucit.com)>  
**Copyright**: illucIT Software GmbH  
**URL**: [www.illucit.com](https://www.illucit.com)  
**License**: [The Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)  
**Current Version**: 2.1.0

Setup
-----

The *InstraTrie JSF components* can either be downloaded directly on GitHub or included via Maven.

Then just add the Maven artifact to your dependencies:

	<dependencies>
		<dependency>
			<groupId>com.illucit</groupId>
			<artifactId>instatrie-jsf-components</artifactId>
			<version>${version.instatrie-jsf-components}</version>
		</dependency>
	<dependencies>

Usage in JSF
------------

The library provides a taglib including the `outputHighlightedText` component.
In order to use the component, first declare a namespace for the taglib in your JSF source file (where your also would include the namespace for PrimeFaces):

	<html xmlns="http://www.w3.org/1999/xhtml" lang="en"
		...
		xmlns:i="http://www.illucit.com/jsf/instatrie">

Then you can use the `outputHighlightedText` tag in your facelet file.

	<i:outputHighlightedText value="#{car.brand}" containsHtml="true" search="#{carDataBean.filterString}"/>

The following parameters are required for the `outputHighlightedText` component to work correctly:
* `value`: The total output-String that should be displayed.  
* `search`: The String containing the filters that need to be highlighted.

Disclaimer:
-----------

InstraTrie JSF components is free software and comes with NO WARRANTY!
