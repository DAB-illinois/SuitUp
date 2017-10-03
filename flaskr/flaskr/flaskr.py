import os
import sqlite3
from .mongoUtility import docs, validUrls, getDocFromLink, getAllRelatedItems, retrieveSortedCosineSimilarity
from flask import Flask, request, session, g, redirect, url_for, abort, render_template, flash

app = Flask(__name__) # create the application instance :)
app.config.from_object(__name__) # load config from this file , flaskr.py

# Load default config and override config from an environment variable
@app.route('/')
def my_form():
    return render_template("layout.html", visibility="invisible", footer_absolute="absolute")

@app.route('/', methods=['POST'])
def my_form_post():
	url = request.form['text']

	if str(url) not in list(validUrls):
		return render_template("layout.html", visibility="invisible", footer_absolute="absolute", message="Error: Invalid URL: "+url)

	queryItem = getDocFromLink(url, docs)
	if queryItem == None or queryItem.general_type.lower() == "ignore":
		return render_template("layout.html", visibility="invisible", footer_absolute="absolute", message="Error: Not in database")

	filtered = getAllRelatedItems(queryItem, docs)
	sortedCosine = retrieveSortedCosineSimilarity(queryItem, filtered)

	need = ["top","bottom","footwear","outer"]
	have = []
	haveCosine = []
	need.remove(queryItem.general_type)
	for recommendItem in sortedCosine:
		general_type = recommendItem[0].general_type
		if general_type in need:
			have.append(recommendItem[0])
			haveCosine.append(recommendItem[1])
			need.remove(recommendItem[0].general_type)

	return render_template("layout.html", visibility="visible", name0=queryItem.name, link0=queryItem.link, img_link0=queryItem.pic_link, 
		name1=have[0].name, link1=have[0].link, img_link1=have[0].pic_link, price1=have[0].price, description1=fixDescription(have[0].description), cosine1=haveCosine[0], 
		name2=have[1].name, link2=have[1].link, img_link2=have[1].pic_link, price2=have[1].price, description2=fixDescription(have[1].description), cosine2=haveCosine[1],
		name3=have[2].name, link3=have[2].link, img_link3=have[2].pic_link, price3=have[2].price, description3=fixDescription(have[2].description), cosine3=haveCosine[2])

def fixDescription(description):
	description = description.split("•")
	return description[0]