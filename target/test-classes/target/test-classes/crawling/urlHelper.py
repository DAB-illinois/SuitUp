# Brandon Wang. Sept. 30

from urllib.request import Request, urlopen
import lxml.html

def getLxml(url):
	urlLxml = lxml.html.parse(requestUrl(url))
	return urlLxml

def requestUrl(url):
	req = Request(url, headers={'User-Agent': 'Mozilla/5.0'})
	urlRequest = urlopen(req)
	return urlRequest