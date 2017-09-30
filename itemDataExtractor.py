# Brandon Wang. Sep. 30

import lxml.html
from lxml import etree

def validColor(element):
	if element.tag == "span":
		attrib = element.attrib
		return("id" in attrib and attrib["id"] == "colorValue")
	return False

def getColor(element):
	rawHtml = str(lxml.html.tostring(element))
	rawHtml = etree.tostring(element, pretty_print=True)
	return("color: "+rawHtml)#[rawHtml.index(">")+1:rawHtml.index("<")])
			