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

	databaseItem = getDocFromLink(url, docs)
	if databaseItem == None or databaseItem.general_type.lower() == "ignore":
		return render_template("layout.html", link = "error: invalid databaseItem")

	filtered = getAllRelatedItems(databaseItem, docs)
	sortedCosine = retrieveSortedCosineSimilarity(databaseItem, filtered)
	return render_template("layout.html", link = sortedCosine[0].link)
