# Brandon Wang. Sep. 30

import lxml.html
from lxml import etree
import urlHelper

def validColor(element):
	if element.tag == "img":
		attrib = element.attrib
		return("class" in attrib and attrib["class"] == "imgSwatch")
	return False

def getColor(element):
	return element.attrib["title"]
	

			