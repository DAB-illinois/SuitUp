# Brandon Wang. Sept. 30

from urllib.request import Request, urlopen
import lxml.html


def requestUrl(url):
	req = Request(url, headers={'User-Agent': 'Mozilla/5.0'})
	urlRequest = urlopen(req)
	urlLxml = lxml.html.parse(urlRequest)
	return urlLxml