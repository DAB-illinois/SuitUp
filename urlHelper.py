# Brandon Wang. Sept. 30

from urllib.request import Request, urlopen
import lxml.html


def requestUrl(url):
	try:
		req = Request(url, headers={'User-Agent': 'Mozilla/5.0'})
		urlRequest = urlopen(req)
	except urllib.error.URL_BASEError as e:
		print("error:", url)
		return None
	urlLxml = lxml.html.parse(urlRequest)
	return urlLxml