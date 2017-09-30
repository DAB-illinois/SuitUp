# Brandon Wang. Sep. 30

import lxml.html
from lxml import etree

def validColor(element):
	if element.tag == "span":
		attrib = element.attrib
		return("id" in attrib and attrib["id"] == "colorValue")
	return False

def getColor(link):
	rawHtml = str(lxml.html.tostring(element))
	rawHtml = str(etree.tostring(element, pretty_print=True))
	return("color: "+element.text_content())#[rawHtml.index(">")+1:rawHtml.index("<")])
			