import os
import sqlite3
from .mongoUtility import docs, validUrls, get_doc_from_link, get_all_related_items, retrieve_sorted_cosine_similarity
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

	query_item = get_doc_from_link(url, docs)
	if query_item == None or query_item.general_type.lower() == "ignore":
		return render_template("layout.html", visibility="invisible", footer_absolute="absolute", message="Error: Not in database")

	filtered = get_all_related_items(query_item, docs)
	sortedCosine = retrieve_sorted_cosine_similarity(query_item, filtered)

	haveType = query_item.general_type
	need = []
	if haveType == "full_body_": # TODO: Change this after render_template fixed
		need = ["footwear"]
	else:
		need = ["top","bottom","footwear","outer"]
		need.remove(haveType)
	
	have = []
	haveCosine = []
	
	for recommendItem in sortedCosine:
		general_type = recommendItem[0].general_type
		if general_type in need:
			have.append(recommendItem[0])
			haveCosine.append(recommendItem[1])
			need.remove(recommendItem[0].general_type)

 # TODO: change render_template to accept only one item.
	return render_template("layout.html", visibility="visible", 
		name0=query_item.name, link0=query_item.link, img_link0=query_item.pic_link, vector0=round_to_fifth_dec_list(query_item.vector),
		name1=have[0].name, link1=have[0].link, img_link1=have[0].pic_link, price1=have[0].price, description1=fix_description(have[0].description), cosine1=round_to_fifth_dec(haveCosine[0]), vector1=round_to_fifth_dec_list(have[0].vector),
		name2=have[1].name, link2=have[1].link, img_link2=have[1].pic_link, price2=have[1].price, description2=fix_description(have[1].description), cosine2=round_to_fifth_dec(haveCosine[1]), vector2=round_to_fifth_dec_list(have[1].vector),
		name3=have[2].name, link3=have[2].link, img_link3=have[2].pic_link, price3=have[2].price, description3=fix_description(have[2].description), cosine3=round_to_fifth_dec(haveCosine[2]), vector3=round_to_fifth_dec_list(have[2].vector))

def fix_description(description):
	description = description.split("•")
	return description[0]

def round_to_fifth_dec(i):
	return int(i*100000)/100000

def round_to_fifth_dec_list(l):
	for i in range(len(l)):
		l[i] = round_to_fifth_dec(l[i])
	return l