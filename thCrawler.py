# Brandon Wang. Sept. 30

import urlHelper
import key
from pymongo import MongoClient
import lxml.html
import itemDataExtractor

# some constants
HREF = "href"
URL_BASE = "http://usa.tommy.com/en/"
WOMEN_CATEGORY_BEGIN = "women"
MEN_CATEGORY_BEGIN = "men"

# make a new mongodb data table
client = MongoClient(key.url)
db = client.bmDataset

# function to see if href is url
def validElement(element):
	if element.tag == 'a':
		attrib = element.attrib
		if HREF in attrib:
			link = attrib[HREF]
			return(URL_BASE in link and " " not in link)
	return False

# valid url: http://usa.tommy.com/en/men/men-hilfiger-denim/straight-cargo-pant-dm03070
def isItem(link):
	locationOfId = -7
	id = link[locationOfId:]
	try:
		num = int(id[2:])
	except ValueError as e:
		return False
	return True

# valid url : http://usa.tommy.com/en/JACKETS-OUTERWEAR-MEN
# valid url : http://usa.tommy.com/en/men-athleisure
def isCategory(link):
	directory = link[len(URL_BASE):]
	return not isItem(link) and (directory[0:3].lower() == MEN_CATEGORY_BEGIN or directory[-3:].lower() == MEN_CATEGORY_BEGIN)
	
# starting urls
thUrl = ["http://usa.tommy.com/en/women-hilfiger-denim", "http://usa.tommy.com/en/men-hilfiger-denim"]

# item urls
itemUrls = []
visitedUrls = []

# category urls
categoryUrls = []

#crawling the initial urls and storing urls in list
print("crawling initial urls")
for url in thUrl:
	urlLxml = urlHelper.getLxml(url)
	if urlLxml == None:
		continue
	for element in urlLxml.iter():
		if validElement(element):
			link = element.attrib[HREF]
			if isItem(link) and link not in itemUrls:
				itemUrls.append(link)
			elif isCategory(link) and link not in categoryUrls:
				categoryUrls.append(link)

# crawling the category urls
print("crawling category urls")
for url in categoryUrls:
	urlLxml = urlHelper.getLxml(url)
	if urlLxml == None:
		continue
	for element in urlLxml.iter():
		if validElement(element):
			link = element.attrib[HREF]
			if isItem(link) and link not in itemUrls:
				itemUrls.append(link)

# crawling the item urls
print("crawling item urls")
while len(itemUrls) > 0:
	url = itemUrls[0]
	urlLxml = urlHelper.getLxml(url)
	if urlLxml == None:
		continue
	print("extracting related urls from:", url)
	visitedUrls.append(url)
	itemUrls.remove(url)
	for element in urlLxml.iter():
		attrib = element.attrib

		# get related items
		if validElement(element):
			link = attrib[HREF]
			if isItem(link) and link not in itemUrls and link not in visitedUrls:
				itemUrls.append(link)

		if itemDataExtractor.validColor(element):
			print(itemDataExtractor.getColor(element))

print(len(visitedUrls))
