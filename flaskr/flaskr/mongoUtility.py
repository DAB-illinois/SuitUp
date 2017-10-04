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
	database_item = DatabaseItems(data["gender"], data["link"], 
		data["description"], data["pic_link"], data["colors"], 
		data["name"], data["category"], data["type"], data["price"], 
		data["general_type"])
	docs.append(database_item)
	validUrls.append(data["link"])

def cosine_similarity(query, rec):
	scalar = compute_pot_product(query, rec);
	norm1 = compute_pot_product(query, query) ** 0.5
	norm2 = compute_pot_product(rec, rec) ** 0.5

	return scalar / float(norm1 * norm2)

def compute_pot_product(query_values, rec_values):
	value = 0
	for i in range(len(query_values)):
		value += query_values[i] * rec_values[i]
	return value

def sort(hash_map):
	copiedMap = hash_map.copy()
	values = hash_map.values()
	values = sorted(values)
	values.reverse()

	sorted_cosine = []
	for i in range(len(values)):
		value = values[i]

		for database_item in copiedMap.keys():
			if copiedMap[database_item] == value:
				sorted_cosine.append([database_item, value])
				del copiedMap[database_item]
				break
	return sorted_cosine

def get_doc_from_link(link1, docs):
	for database_item in docs:
		if database_item.link == link1:
			return database_item
	return None

def get_all_related_items(query_item, database_items):
	if database_items == None or len(database_items) == 0 or query_item == None:
		return None
	filtered_data_base_items = []

	for i in range(len(database_items)):
		currentItem = database_items[i]
		if (query_item.name.lower() != currentItem.name.lower()) and (query_item.gender.lower() == currentItem.gender.lower()) and (query_item.general_type.lower() != currentItem.general_type.lower()) and (currentItem.general_type.lower != "ignore"):
			filtered_data_base_items.append(currentItem)
	return filtered_data_base_items

def retrieve_sorted_cosine_similarity(query_item, filtered_data_base_items):
	if filtered_data_base_items == None or len(filtered_data_base_items) == 0 or query_item == None:
		return None

	similarity_with_query_item = {}
	query_vector_values = query_item.generateValues()

	for item in filtered_data_base_items:
		item_vector_values = item.generateValues()
		cosine = cosine_similarity(query_vector_values, item_vector_values)
		similarity_with_query_item[item] = cosine

	return sort(similarity_with_query_item)
