var express = require('express');
var bodyParser = require('body-parser');
var path = require('path');
var request = require('request');
//init app
var app = express();

//body parser middleware
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));

//set static path
app.use(express.static(path.join(__dirname, 'public')))

//home route
app.get('/', function(req, res){
  res.render('public');
});

app.get("/allData", function(req, res){
	console.log(req.url);
	console.log(req.query);
	var temp = req.query;
	var data = temp.newData;
	
	urlNp = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+req.query.lat+","+req.query.lon+"&radius="+req.query.distance+"&type="+req.query.catu+"&keyword="+req.query.key+"&key=AIzaSyC00xSk8OLLyPTwJPMCTxjtOCOYrX-dW6A";
	var urlNew=urlNp.replace(/ /g,'+');
	console.log(urlNp);
	var urlNew=encodeURI(urlNp);
	request.get({
	    url: urlNew,
	    json: true,
	    headers: {'User-Agent': 'request'}
	  }, (err, response, data) => {
	    if (err) 
	    {
	    	console.log('Error:', err);
	    }
	     else if (res.statusCode !== 200) {
	    	console.log('Status:', response.statusCode);
	    } else 
	    {
	      
	      	 console.log(data);
	      	res.setHeader('content-type', 'application/json');
			res.json(data);
	    }
	});    

});


app.get("/locWala", function(req, res){
	//console.log(req.url);
	//console.log(req.query);
	var temp = req.query;
	var data = temp.newLOC;
	urlLoc = "https://maps.googleapis.com/maps/api/geocode/json?address="+req.query.location+"&key=AIzaSyC00xSk8OLLyPTwJPMCTxjtOCOYrX-dW6A";
	console.log(urlLoc);
		request.get(urlLoc, (error, response, body) => {
 		 let json = JSON.parse(body);
         console.log(json);
         var urlNpLo=encodeURI(urlLoc);	 
         console.log(urlNpLo);
         var b= json.results[0].geometry.location.lat;
         var c= json.results[0].geometry.location.lng;
	urlNpLo = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+json.results[0].geometry.location.lat+","+json.results[0].geometry.location.lng+"&radius="+req.query.distance+"&type="+req.query.catu+"&keyword="+req.query.key+"&key=AIzaSyC00xSk8OLLyPTwJPMCTxjtOCOYrX-dW6A";
	console.log(urlNpLo);
	var urlNew2=encodeURI(urlNpLo);
	//console.log(urlNew2);
	request.get({
	    url: urlNew2,
	    json: true,
	    headers: {'User-Agent': 'request'}
	  }, (err, response, data) => {
	    if (err) {
	    	console.log('Error:', err);
	    } 
	    else if (res.statusCode !== 200) 
	    {
	    	console.log('Status:', response.statusCode);
	    } else 
	    {
	   
	      	 console.log(data);
	      	res.setHeader('content-type', 'application/json');
			res.json(data);
	    }
	});    
})
});

app.get("/cadataAya", function(req, res){
	//console.log(req.url);
	//console.log(req.query);
	//console.log(req.query.cadata);
	var temp = req.query;
	var next = temp.cadata;
	console.log(next);
	console.log("agai value cadata kijjjjjjj");
urlcadata = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?pagetoken="+next+"&key=AIzaSyC00xSk8OLLyPTwJPMCTxjtOCOYrX-dW6A";
	//var urlcada=encodeURI(urlcadata);
	console.log("urlcadata ki",urlcadata);
	request.get({
	    url: urlcadata,
	    json: true,
	    headers: {'User-Agent': 'request'}
	  }, (err, response, data) => {
	    if (err) 
	    {
	    	console.log('Error:', err);
	    } 
	    else if (res.statusCode !== 200) 
	    {
	    	console.log('Status:', response.statusCode);
	    } else {
	      
	      	 console.log(data);
	      	res.setHeader('content-type', 'application/json');
			res.json(data);
	    }
	});    

});
app.get("/final", function(req, res){
	
	var placeIdi=req.query.placeIdio;
	console.log(placeIdi);
	console.log("placeididididi ki",placeIdi);
	hw9Url="https://maps.googleapis.com/maps/api/place/details/json?placeid="+placeIdi+"&key=AIzaSyC00xSk8OLLyPTwJPMCTxjtOCOYrX-dW6A";
	console.log("this is the placeidi wali url",hw9Url);
	request.get({
	    url: hw9Url,
	    json: true,
	    headers: {'User-Agent': 'request'}
	  }, (err, response, data) => {
	    if (err) 
	    {
	    	console.log('Error:', err);
	    } 
	    else if (res.statusCode !== 200) 
	    {
	    	console.log('Status:', response.statusCode);
	    } else {
	      
	      	 console.log(data);
	      	res.setHeader('content-type', 'application/json');
			res.json(data);
	    }
	});    

});
app.get("/yelpBanaing", function(req, res){
	console.log(req.url);
	console.log(req.query);
	//console.log(req.query.cadata);
	var temp = req.query;
	var next = temp.nameyelp;
	console.log(next);
	console.log("agai value cadata ki");
yelpUrl = "https://api.yelp.com/v3/businesses/matches/best?name="+req.query.nameyelp+"&address1="+req.query.adddr+"&city="+req.query.cityzz+"&state="+req.query.statezz+"&country="+req.query.country;
	var urlcanada=encodeURI(yelpUrl);
	//console.log("urlcadata ki",urlcadata);
	request.get({
	    url: urlcanada,
	    json: true,
	    headers: {'User-Agent': 'request', 'Authorization': 'Bearer Njza3sFCRA9Ryj9ThYlmY38oXieiZrYlQvvTIe1DUnG89_VajllQVQntnGhSLpKHofkjdo7GYbDuQcyD0sgeaas3E5X6A31g6qMEzv1UmBg1p4EX_utsDkASwHPJWnYx'}
	  }, (err, response, data) => {
	    if (err)
	     {
	    	console.log('Error:', err);
	    } 
	    else if (res.statusCode !== 200) 
	    {
	    	console.log('Status:', response.statusCode);
	    } else {
	      // data already parsed
	      	 console.log(data);
	      	res.setHeader('content-type', 'application/json');
			res.json(data);
	    }
	});    

});


app.get("/yelpKaTable", function(req, res){
	console.log(req.url);
	console.log(req.query);
	//console.log(req.query.cadata);
	var temp = req.query;
	var next = temp.naviTaziId;
	console.log(next);
yelpFinalUrl = "https://api.yelp.com/v3/businesses/"+req.query.naviTaziId+"/reviews";
	var urlcanadaAle=encodeURI(yelpFinalUrl);
	//console.log("urlcadata ki",urlcadata);
	request.get({
	    url: urlcanadaAle,
	    json: true,
	    headers: {'User-Agent': 'request', 'Authorization': 'Bearer Njza3sFCRA9Ryj9ThYlmY38oXieiZrYlQvvTIe1DUnG89_VajllQVQntnGhSLpKHofkjdo7GYbDuQcyD0sgeaas3E5X6A31g6qMEzv1UmBg1p4EX_utsDkASwHPJWnYx'}
	  }, (err, response, data) => {
	    if (err) 
	    {
	    	console.log('Error:', err);
	    } else if (res.statusCode !== 200) 
	    {
	    	console.log('Status:', response.statusCode);
	    } else 
	    {
	      	 console.log(data);
	      	res.setHeader('content-type', 'application/json');
			res.json(data);
	    }
	});    

});
//start server
app.listen(8081, function(){
  console.log('Server started on port 3000..')
});
