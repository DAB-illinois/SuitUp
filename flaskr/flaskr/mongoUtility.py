from pymongo import MongoClient
import json
from collections import namedtuple
from .key import *
from .DatabaseItems import *

DATABASE_NAME = "bmDataset"
COLLECTION_NAME = "tommyHil"

# make a new mongodb data table
client = MongoClient(url)
db = client.bmDataset
result = db.tommyHil.find()

docs = []
validUrls = []

for data in result:
	databaseItem = DatabaseItems(data["gender"], data["link"], data["description"], data["pic_link"], data["colors"], data["name"], data["category"], data["type"], data["price"], data["general_type"])
	docs.append(databaseItem)
	validUrls.append(data["link"])

def cosineSimilarity(query, rec):
	scalar = computeDotProduct(query, rec);
	norm1 = computeDotProduct(query, query) ** 0.5
	norm2 = computeDotProduct(rec, rec) ** 0.5

	return scalar / float(norm1 * norm2)

def computeDotProduct(queryValues, recValues):
	value = 0
	for i in range(len(queryValues)):
		value += queryValues[i] * recValues[i]
	return value

def sort(hashMap):
	copiedMap = hashMap.copy()
	values = hashMap.values()
	values = sorted(values)
	values.reverse()

	sortedCosine = []
	for i in range(len(values)):
		value = values[i]
		print(value)

		for databaseItem in copiedMap.keys():
			if copiedMap[databaseItem] == value:
				sortedCosine.append(databaseItem)
				del copiedMap[databaseItem]
				break
	return sortedCosine

def getDocFromLink(link1, docs):
	for databaseItem in docs:
		if databaseItem.link == link1:
			return databaseItem
	return None

def getAllRelatedItems(queryItem, databaseItems):
	if databaseItems == None or len(databaseItems) == 0 or queryItem == None:
		return None
	filteredDataBaseItems = []

	for i in range(len(databaseItems)):
		currentItem = databaseItems[i]
		if (queryItem.name.lower() != currentItem.name.lower()) and (queryItem.gender.lower() == currentItem.gender.lower()) and (queryItem.general_type.lower() != currentItem.general_type.lower()) and (currentItem.general_type.lower != "ignore"):
			filteredDataBaseItems.append(currentItem)
	return filteredDataBaseItems

def retrieveSortedCosineSimilarity(queryItem, filteredDataBaseItems):
	if filteredDataBaseItems == None or len(filteredDataBaseItems) == 0 or queryItem == None:
		return None

	similarityWithQueryitem = {}
	queryVectorValues = queryItem.generateValues()

	for item in filteredDataBaseItems:
		itemVectorValues = item.generateValues()
		cosine = cosineSimilarity(queryVectorValues, itemVectorValues)
		similarityWithQueryitem[item] = cosine

	sortedCosine = sort(similarityWithQueryitem)
	return sortedCosine
