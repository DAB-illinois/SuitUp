# Brandon Wang. Sept. 30

import urlHelper
import key
from pymongo import MongoClient
import lxml.html
import itemDataExtractor

# some constants
URL_BASE = "http://usa.tommy.com/en/"

# make a new mongodb data table
client = MongoClient(key.url)
db = client.bmDataset

# item urls
itemUrls = []
visitedUrls = []

with open("urls", "r") as f:
	content = f.readlines()

for line in content:
	itemUrls.append(line.strip())

# crawling the item urls
print("crawling item urls")
while len(itemUrls) > 0:
	if len(itemUrls) % 50 == 0:
		print(len(itemUrls))

	url = itemUrls[0]
	urlLxml = urlHelper.getLxml(url)
	if urlLxml == None:
		continue
	
	visitedUrls.append(url)
	itemUrls.remove(url)

	link = url #done
	styleId = link[-7:] #done
	name = "" #done
	colors = [] #done
	gender = "" #done
	if "women" in url.lower():
		gender = "women"
	else:
		gender = "men"
	price = "" #done
	picLink = "" #done
	clothType = "" #done
	category = url.split("/")[-2] #done
	description = ""

	for element in urlLxml.iter():
		attrib = element.attrib

		# get related items.
		# dont need to get because we went to all the categories
		#if validElement(element):
		#	link = attrib[HREF]
		#	if isItem(link) and link not in itemUrls and link not in visitedUrls:
		#		itemUrls.append(link)

		if itemDataExtractor.validName(element):
			name = itemDataExtractor.getName(element)

		if itemDataExtractor.validColor(element):
			colors.append(itemDataExtractor.getColor(element))

		if itemDataExtractor.validPrice(element):
			price = itemDataExtractor.getPrice(element)

		if itemDataExtractor.validPicLink(element):
			picLink = itemDataExtractor.getPicLink(element)

		if itemDataExtractor.validDescription(element):
			description = itemDataExtractor.getDescription(element)

	clothType = name.split(" ")[-1]

	result = db.tommyHil.insert_one(
		{
			"name": name,
			"link": link,
			"styleId": styleId,
			"gender": gender,
			"price": price,
			"pic_link": picLink,
			"colors": colors,
			"type": clothType,
			"category": category,
			"description": description
		}
	)

print(len(visitedUrls))
