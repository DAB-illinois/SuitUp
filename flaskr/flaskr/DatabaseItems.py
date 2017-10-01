VECTOR_LENGTH = 6;
GENDER_WEIGHT = 10;
PRICE_WEIGHT = 5;
TYPE_WEIGHT = 8;
PRICE_MIN = 20;
PRICE_MAX = 1690;

CATEGORY_VALUES = {"denim": 8.0, "t-shirts": 8.0, "shirts": 2.0, "blouses": 5.0, "sweater": 8.0, "jacket": 5.0, 
			"jean": 8.0, "pants": 6.5, "polos": 7.0, "dresses": 7.0, "shorts": 8.0, "footwear": 4.0, "athleisure": 8.0, 
			"dressshirts": 0.0, "suit-separates": 0.0, "rugbys": 6.0}

TYPE_VALUES = {"bomber": [2.0, 5.0, 0.0, 0.0], "tee": [4.5, 6.5, 0.0, 0.0], "t-shirt": [4.5, 6.5, 0.0, 0.0], 
		"jean": [0.0, 8.0, 1.0, 0.0], "jogger": [7.0, 2.0, 0.0, 0.0], "cardigan": [0.0, 6.0, 3.0, 0.0], 
		"sweatshirt": [3.0, 3.0, 0.0, 0.0], "blouse": [0.0, 2.0, 6.0, 0.0], "dress": [0.0, 7.0, 5.0, 2.0], 
		"skirt": [0.0, 6.0, 6.0, 0.0], "polo": [0.0, 1.0, 7.0, 0.0], "shirtdress": [0.0, 6.0, 0.0, 0.0], 
		"boatneck": [1.0, 7.0, 0.0, 0.0], "crewneck": [0.0, 6.0, 1.0, 0.0], "shirt": [0.0, 2.0, 6.0, 0.0], 
		"pant": [0.0, 7.0, 8.0, 6.0], "top": [0.0, 7.0, 8.0, 6.0], "hoodie": [4.0, 3.0, 0.0, 0.0], 
		"sweater": [4.0, 3.0, 0.0, 0.0], "tank": [4.0, 4.0, 0.0, 0.0], "jegging": [3.0, 6.0, 0.0, 0.0], 
		"jacket": [1.0, 6.0, 0.0, 0.0], "tunic": [1.0, 7.0, 0.0, 0.0], "flannel": [0.0, 8.0, 0.0, 0.0], 
		"puffer": [0.0, 8.0, 0.0, 0.0], "parka": [0.0, 5.0, 3.0, 0.0], "coat": [0.0, 1.0, 3.0, 8.0], 
		"blazer": [0.0, 0.0, 2.0, 8.0], "biker": [0.0, 8.0, 0.0, 0.0], "trouser": [0.0, 2.0, 6.0, 4.0], 
		"legging": [3.0, 6.0, 0.0, 0.0], "chino": [0.0, 6.0, 3.0, 0.0], "jumpsuit": [0.0, 8.0, 0.0, 0.0], 
		"t-shirtdress": [0.0, 8.0, 0.0, 0.0], "short": [6.0, 2.0, 0.0, 0.0], "shorts": [0.0, 6.0, 2.0, 0.0], 
		"suit": [0.0, 0.0, 4.0, 6.0], "bootie": [0.0, 8.0, 0.0, 0.0], "hi-top": [0.0, 8.0, 0.0, 0.0], 
		"sneaker": [6.0, 8.0, 0.0, 0.0], "creeper": [0.0, 8.0, 0.0, 0.0], "flat": [0.0, 8.0, 2.0, 0.0], 
		"ballerina": [0.0, 6.0, 2.0, 0.0], "mule": [0.0, 4.0, 4.0, 0.0], "boot": [0.0, 6.0, 4.0, 0.0], 
		"hiker": [0.0, 6.0, 2.0, 0.0], "slip-on": [0.0, 8.0, 0.0, 0.0], "oxford": [0.0, 0.0, 7.0, 2.0], 
		"cardigan": [0.0, 4.0, 4.0, 0.0], "gilet": [0.0, 8.0, 0.0, 0.0], "vest": [0.0, 6.0, 2.0, 2.0], 
		"trousers": [0.0, 3.0, 6.0, 1.0], "crew": [0.0, 2.0, 6.0, 0.0], "sweatpant": [5.0, 4.0, 0.0, 0.0], 
		"sweatpants": [5.0, 4.0, 0.0, 0.0], "lounger": [4.0, 4.0, 0.0, 0.0], "turtleneck": [0.0, 6.0, 2.0, 0.0], 
		"rugby": [0.0, 8.0, 1.0, 0.0], "henley": [0.0, 8.0, 0.0, 0.0], "moccasin": [0.0, 7.0, 1.0, 0.0], 
		"shoe": [0.0, 2.0, 8.0, 8.0], "shoes": [0.0, 2.0, 8.0, 8.0], "loafer": [0.0, 2.0, 6.0, 3.0], 
		"john": [0.0, 6.0, 2.0, 0.0], "flex": [0.0, 0.0, 2.0, 8.0], "tights": [0.0, 7.0, 1.0, 0.0], 
		"fleece": [0.0, 8.0, 0.0, 0.0], "wedge": [0.0, 7.0, 1.0, 0.0], "peacoat": [0.0, 2.0, 4.0, 6.0], 
		"trench": [0.0, 6.0, 6.0, 6.0], "windbreaker": [0.0, 8.0, 0.0, 0.0]}

class DatabaseItems():
	def __init__(self, gender, link, description, pic_link, colors, name, category, clothingType, price, general_type):
		self.gender = gender
		self.link = link
		self.description = description
		self.pic_link = pic_link
		self.colors = colors
		self.name = name
		self.category = category
		self.clothingType = clothingType
		self.price = price
		self.general_type = general_type #"ignore" if not giving recommendations for
		self.athletic = -1
		self.leisure = -1
		self.business = -1
		self.fancy = -1
		self.pattern = -1
		self.vector = []

	def generateValues(self):
		self.vector.append(self.generateGenderValue())
		self.vector.append(self.generatePriceValue())
		styleVector = self.generateStyleValue()
		for i in range(len(styleVector)):
			self.vector.append(styleVector[i])
		return self.vector

	def generateGenderValue(self):
		if self.gender.lower == "women":
			return GENDER_WEIGHT
		else:
			return -1 * GENDER_WEIGHT

	def generatePriceValue(self):
		priceDouble = float(self.price)
		priceDouble -= PRICE_MIN
		priceDouble -= (PRICE_MAX - PRICE_MIN) / 2.0
		priceDouble /= (PRICE_MAX - PRICE_MIN) / 2.0
		priceDouble *= PRICE_WEIGHT
		return priceDouble

	def generateStyleValue(self):
		fourStyles = []
		if "ignore" == self.general_type.lower():
			return [0,0,0,0]

		#get category value
		categoryLower = self.category.lower()
		casualCategoryValue = -1
		formalCategoryValue = -1;

		for c in CATEGORY_VALUES:
			if c in categoryLower:
				casualCategoryValue = CATEGORY_VALUES[c]
				formalCategoryValue = TYPE_WEIGHT - casualCategoryValue
				break

		if casualCategoryValue == -1:
			typeValues = TYPE_VALUES[self.clothingType.lower()]
			if typeValues == None:
				return [0,0,0,0]
			for i in range(len(typeValues)):
				fourStyles.append(typeValues[i])
		else:
			typeValues = TYPE_VALUES[self.clothingType.lower()]
			if typeValues == None:
				return [0,0,0,0]
			for i in range(2):
				fourStyles.append(typeValues[i] * casualCategoryValue / TYPE_WEIGHT)
			for i in range(2,4):
				fourStyles.append(typeValues[i] * formalCategoryValue / TYPE_WEIGHT)
		return fourStyles



