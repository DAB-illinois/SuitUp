# Brandon Wang. Sep. 30

import lxml.html
from lxml import etree
import urlHelper

CLASS = "class"

def validElement(element, tag, attribute, value):
	if element.tag == tag:
		attrib = element.attrib
		return(attribute in attrib and value in attrib[attribute])
	return False

def validColor(element):
	return validElement(element, "img", CLASS, "imgSwatch")

def getColor(element):
	return element.attrib["title"]

def validName(element):
	return validElement(element, "span", CLASS, "productNameInner")

def getName(element):
	return element.text_content()

def validPrice(element):
	return validElement(element, "span", "itemprop", "highPrice")

def getPrice(element):
	return element.attrib["content"]

def validPicLink(element):
	return validElement(element, "img", "id", "zoom1") and "data-src" in element.attrib

def getPicLink(element):
	return element.attrib["data-src"]

def validDescription(element):
	return validElement(element, "div", "class", "itemDescription")

def getDescription(element):
	children = element.getchildren()[0].text_content().strip()
	return children