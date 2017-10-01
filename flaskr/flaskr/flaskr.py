import os
import sqlite3
from .mongoUtility import docs, validUrls, getDocFromLink, getAllRelatedItems, retrieveSortedCosineSimilarity
from flask import Flask, request, session, g, redirect, url_for, abort, render_template, flash

app = Flask(__name__) # create the application instance :)
app.config.from_object(__name__) # load config from this file , flaskr.py

# Load default config and override config from an environment variable
@app.route('/')
def my_form():
    return render_template("layout.html")

@app.route('/', methods=['POST'])
def my_form_post():
	url = request.form['text']

	if str(url) not in list(validUrls):
		return render_template("layout.html", link = "error: invalid url: '"+url+"'   "+str(validUrls))

	queryItem = getDocFromLink(url, docs)
	if queryItem == None or queryItem.general_type.lower() == "ignore":
		return render_template("layout.html", link = "error: invalid databaseItem")

	filtered = getAllRelatedItems(queryItem, docs)
	sortedCosine = retrieveSortedCosineSimilarity(queryItem, filtered)

	need = ["top","bottom","footwear","outer"]
	have = []
	need.remove(queryItem.general_type)
	for recommendItem in sortedCosine:
		general_type = recommendItem.general_type
		if general_type in need:
			have.append(recommendItem)
			need.remove(recommendItem.general_type)

	return render_template("layout.html", link0=queryItem.link, img_link0=queryItem.pic_link, link1=have[0].link, img_link1=have[0].pic_link, link2=have[1].link, img_link2=have[1].pic_link, link3=have[2].link, img_link3=have[2].pic_link)
